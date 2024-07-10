package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.server.RegisterMenuControllerServer;
import controller.server.UserInformationControllerServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWorker extends Thread {
    private final ServerSocket serverSocket;
    private DataOutputStream sendBuffer;
    private DataInputStream recieveBuffer;
    private final Gson gsonAgent;
    private final UserInformationControllerServer userInformationControllerServer = new UserInformationControllerServer();
    private final RegisterMenuControllerServer registerMenuControllerServer = new RegisterMenuControllerServer();


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
            recieveBuffer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            sendBuffer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String messageString = recieveBuffer.readUTF();
            ClientMessage clientMessage = gsonAgent.fromJson(messageString, ClientMessage.class);
            String serverMessage = switch (clientMessage.getControllerName()) {
                case "UserInformationController" ->
                        gsonAgent.toJson(handleUserInformationControllerRequest(clientMessage));
                case "RegisterController" -> gsonAgent.toJson(handleRegisterControllerRequest(clientMessage));
                default -> null;
            };
            if (serverMessage != null)
                sendBuffer.writeUTF(serverMessage);
            else {
                System.err.println("Control Error!!! (not find controller " + clientMessage.getControllerName() + ")");
                System.exit(1);
            }
            sendBuffer.close();
            recieveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object handleRegisterControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "register" -> {
                return registerMenuControllerServer.register((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2),
                        (String) clientMessage.getFields().get(3), (String) clientMessage.getFields().get(4),
                        (String) clientMessage.getFields().get(5));
            }
            case "checkSecurityQuestion" -> {
                return registerMenuControllerServer.checkSecurityQuestion((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "createRandomPassword" -> {
                return registerMenuControllerServer.createRandomPassword();
            }
            default -> {
                System.err.println("invalid method!! in register controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
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

