package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.App;
import model.PregameData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;
    private final String serverIP;
    private final int serverPort;
    private final Gson gsonAgent;
    private String lastServerMessage;

    public Client(String serverIP, int serverPort) {
        GsonBuilder builder = new GsonBuilder();
        this.gsonAgent = builder.create();
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    private boolean establishConnection() {
        try {
            socket = new Socket(serverIP, serverPort);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private void endConnection() {
        if (socket == null) return;
        try {
            socket.close();
            receiveBuffer.close();
            sendBuffer.close();
        } catch (IOException e) {
            System.err.println("end connection failed!");
        }
    }

    public void sendMessageToServer(ClientMessage clientMessage) {
        if (!establishConnection()) return;
        String message = gsonAgent.toJson(clientMessage);
        try {
            sendBuffer.writeUTF(message);
            this.lastServerMessage = receiveBuffer.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        endConnection();
    }

    public boolean requestForRandomGame(PregameData pregameData) {
        Socket socket;
        DataOutputStream sendBuffer;
        DataInputStream receiveBuffer;
        try {
            socket = new Socket(serverIP, serverPort);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Unable to initialize socket! (game Request)");
            e.printStackTrace();
            return false;
        }
        String message = gsonAgent.toJson(new ClientMessage("GameRequestHandler", "requestToRandomUser", new ArrayList<>()));
        try {
            sendBuffer.writeUTF(message);
            System.out.println("to json successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                System.out.println("salam be server");
                if (receiveBuffer.readUTF().equals("wait")) {
                    sendBuffer.writeUTF("ok");
                }
                else {
                    System.out.println("kpkpk");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sendBuffer.writeUTF(gsonAgent.toJson(pregameData.getShippablePregameData()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Integer id = receiveBuffer.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sendBuffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            receiveBuffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("salam");
        return true;
    }

    public Object getLastServerData(Type type) {
        return gsonAgent.fromJson(lastServerMessage, type);
    }

    public void login(String username, String nickname, String email, boolean stayLoggedIn) {
        App.setLoggedInUser(username, nickname, email, stayLoggedIn);
    }
}
