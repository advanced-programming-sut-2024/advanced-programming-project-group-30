package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream recieveBuffer;
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
            sendBuffer = new DataOutputStream(
                    socket.getOutputStream()
            );
            recieveBuffer = new DataInputStream(
                    socket.getInputStream()
            );
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
            e.printStackTrace();
            return false;
        }
    }

    private boolean endConnection() {
        if(socket == null) return true;
        try {
            socket.close();
            recieveBuffer.close();
            sendBuffer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void sendMessageToServer(ClientMessage clientMessage) {
        establishConnection();
        String message = gsonAgent.toJson(clientMessage);
        System.out.println(message);
        try {
            sendBuffer.writeUTF(message);
            this.lastServerMessage = recieveBuffer.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLastDataSentByServer() {
        System.out.println(lastServerMessage);
        return lastServerMessage;
    }
}
