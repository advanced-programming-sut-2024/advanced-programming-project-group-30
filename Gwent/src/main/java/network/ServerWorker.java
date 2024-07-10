package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.server.UserInformationControllerServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServerWorker extends Thread {
    private final ServerSocket serverSocket;
    private DataOutputStream sendBuffer;
    private DataInputStream recieveBuffer;
    private final Gson gsonAgent;
    private final UserInformationControllerServer userInformationControllerServer = new UserInformationControllerServer();

    public ServerWorker(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.gsonAgent = new GsonBuilder().create();
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            synchronized (Server.getConnections()) {
                while (Server.getConnections().isEmpty()) {
                    try {
                        Server.getConnections().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                socket = Server.getConnections().remove(0);
            }
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private void handleConnection(Socket socket) {
        try {
            recieveBuffer = new DataInputStream(socket.getInputStream());
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            String messageString = recieveBuffer.readUTF();
            ClientMessage clientMessage = gsonAgent.fromJson(messageString, ClientMessage.class);
            Object serverMessage = null;
            switch (clientMessage.getClassAddress()) {
                case "UserInformationController" -> {
                    serverMessage = handleUserInformationControllerRequest(clientMessage);
                }
            }
            sendBuffer.writeUTF(gsonAgent.toJson(serverMessage));
            sendBuffer.close();
            recieveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object handleUserInformationControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "checkInformation" -> {
                return userInformationControllerServer.checkInformation((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2),
                        (String) clientMessage.getFields().get(3), (String) clientMessage.getFields().get(4));
            }
            case "checkUsername" -> {
                return userInformationControllerServer.checkUsername((String) clientMessage.getFields().get(0));
            }
            case "checkPassword" -> {
                return userInformationControllerServer.checkPassword((String) clientMessage.getFields().get(0));
            }
            case "checkPasswordConfirm" -> {
                return userInformationControllerServer.checkPasswordConfirm((String) clientMessage.getFields().get(0));
            }
            case "checkNickname" -> {
                return userInformationControllerServer.checkNickname((String) clientMessage.getFields().get(0));
            }
            case "checkEmail" -> {
                return userInformationControllerServer.checkEmail((String) clientMessage.getFields().get(0));
            }
            case "checkPasswordForChange" -> {
                return userInformationControllerServer.checkPasswordForChange((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            default -> {
                System.err.println("invalid method!! in userInformation ->  name:" + clientMessage.getMethodName());
                return "invalid method";
            }
        }
    }
}
