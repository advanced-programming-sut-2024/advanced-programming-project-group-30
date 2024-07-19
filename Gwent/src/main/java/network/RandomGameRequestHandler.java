package network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enums.cardsData.LeaderCardData;
import model.Game;
import model.Player;
import model.ShippablePregameData;

import java.io.*;

public class RandomGameRequestHandler extends Thread {
    private Gson gsonAgent;
    private Connection connection1 = null;
    private Connection connection2 = null;

    @Override
    public void run() {
        this.gsonAgent = new GsonBuilder().create();
        while (true) {
            synchronized (Server.getRandomGameRequest()) {
                while (Server.getRandomGameRequest().size() < 2) {
                    System.out.println(Server.getRandomGameRequest().size());
                    if (!Server.getRandomGameRequest().isEmpty()) {
                        System.out.println("salam3");
                        Connection connection = Server.getRandomGameRequest().get(0);
                        try {
                            System.out.println("salam1");
                            connection.dataOutputStream().writeUTF("wait");
                            connection.dataInputStream().readUTF();
                            this.join(1000);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (Server.getRandomGameRequest().size() > 2)
                connection1 = Server.getRandomGameRequest().remove(0);
                connection2 = Server.getRandomGameRequest().remove(1);
            }
            if ((connection1 == null) != (connection2 == null)) {
                if (connection2 != null) Server.getRandomGameRequest().add(connection2);
                else Server.getRandomGameRequest().add(connection1);
            }
            if (connection2 != null && connection1 != null) createGame();
        }
    }

    private void createGame() {
        try {
            DataInputStream receiveBuffer1 = connection1.dataInputStream();
            DataOutputStream sendBuffer1 = connection1.dataOutputStream();
            DataInputStream receiveBuffer2 = connection2.dataInputStream();
            DataOutputStream sendBuffer2 = connection2.dataOutputStream();
            sendBuffer1.writeUTF("please send your pregame data");
            ShippablePregameData pregameData1 = gsonAgent.fromJson(receiveBuffer1.readUTF(), ShippablePregameData.class);
            sendBuffer2.writeUTF("please send your pregame data");
            ShippablePregameData pregameData2 = gsonAgent.fromJson(receiveBuffer2.readUTF(), ShippablePregameData.class);
            Player player1 = new Player(Server.getUserByUsername(pregameData1.getUsername()),
                    LeaderCardData.valueOf(pregameData1.getLeader()), pregameData1.getDeck());
            Player player2 = new Player(Server.getUserByUsername(pregameData2.getUsername()),
                    LeaderCardData.valueOf(pregameData2.getLeader()), pregameData2.getDeck());
            Game game = new Game(player1, player2);
            String id = String.valueOf(game.hashCode());
            Server.getAllGames().put(id, game);
            sendBuffer1.writeUTF(id);
            sendBuffer2.writeUTF(id);
            receiveBuffer1.close();
            sendBuffer1.close();
            connection1.socket().close();
            receiveBuffer2.close();
            sendBuffer2.close();
            connection2.socket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
