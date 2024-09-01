package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import enums.FactionType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class ServerWorker extends Thread {
    private final Gson gsonAgent;
    private final UserInformationController userInformationController = new UserInformationController();
    private final RegisterMenuController registerMenuController = new RegisterMenuController();
    private final LoginMenuController loginMenuController = new LoginMenuController();
    private final ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
    private final MainMenuController mainMenuController = new MainMenuController();
    private final ProfileMenuController profileMenuController = new ProfileMenuController();
    private final PregameController pregameController = new PregameController();

    public ServerWorker() {
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
            if (socket != null) handleConnection(socket);
        }
    }

    private void handleConnection(Socket socket) {
        try {
            DataInputStream receiveBuffer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream sendBuffer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String messageString = receiveBuffer.readUTF();
            ClientRequest clientRequest = gsonAgent.fromJson(messageString, ClientRequest.class);
            String serverMessage = switch (clientRequest.getRequestType()) {
                case "Connection" -> gsonAgent.toJson(handleConnectionRequest(clientRequest, socket));
                case "GameRequestHandler" ->
                        gsonAgent.toJson(handleGameRequest(clientRequest, socket, receiveBuffer, sendBuffer));
                case "UserInformationController" ->
                        gsonAgent.toJson(handleUserInformationControllerRequest(clientRequest));
                case "UserInformationController2" ->
                        gsonAgent.toJson(handleUserInformationControllerRequest2(clientRequest));
                case "RegisterController" -> gsonAgent.toJson(handleRegisterControllerRequest(clientRequest));
                case "LoginController" -> gsonAgent.toJson(handleLoginControllerRequest(clientRequest));
                case "ForgetPasswordController" ->
                        gsonAgent.toJson(handleForgetPasswordControllerRequest(clientRequest));
                case "MainMenuController" -> gsonAgent.toJson(handleMainMenuControllerRequest(clientRequest));
                case "ProfileMenuController" -> gsonAgent.toJson(handleProfileMenuControllerRequest(clientRequest));
                case "PregameController" -> gsonAgent.toJson(handlePregameControllerRequest(clientRequest));
                default -> null;
            };
            if (serverMessage != null) {
                sendBuffer.writeUTF(serverMessage);
                sendBuffer.flush();
            }
            else System.err.println("Control Error!!! (not find controller " + clientRequest.getRequestType() + ")");
            if (clientRequest.getRequestType().equals("Connection")) return; // don't close // TODO: clean
            sendBuffer.close();
            receiveBuffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object handleConnectionRequest(ClientRequest clientRequest, Socket socket) {
        switch (clientRequest.getRequest()) {
            case "id request" -> {
                return Server.addClient(socket);
            }
            default -> {
                System.err.println("invalid method!! in connection request ->  name:" + clientRequest.getRequest());
                return "invalid method";
            }
        }
    }

    private Object handleUserInformationControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "checkInformation" -> {
                return userInformationController.checkInformation((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1), (String) clientRequest.getContents().get(2),
                        (String) clientRequest.getContents().get(3), (String) clientRequest.getContents().get(4));
            }
            case "checkUsername" -> {
                return userInformationController.checkUsername((String) clientRequest.getContents().get(0));
            }
            case "checkPassword" -> {
                return userInformationController.checkPassword((String) clientRequest.getContents().get(0));
            }
            case "checkPasswordConfirm" -> {
                return userInformationController.checkPasswordConfirm((String) clientRequest.getContents().get(0));
            }
            case "checkNickname" -> {
                return userInformationController.checkNickname((String) clientRequest.getContents().get(0));
            }
            case "checkEmail" -> {
                return userInformationController.checkEmail((String) clientRequest.getContents().get(0));
            }
            case "checkPasswordForChange" -> {
                return userInformationController.checkPasswordForChange((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1), (String) clientRequest.getContents().get(2));
            }
            case "getUserFaction" -> {
                return userInformationController.getUserFaction((String) clientRequest.getContents().get(0));
            }
            case "setUserFaction" -> {
                return userInformationController.setUserFaction((String) clientRequest.getContents().get(0),
                        FactionType.valueOf((String) clientRequest.getContents().get(1)));
            }
            default -> {
                System.err.println("invalid method!! in userInformation ->  name:" + clientRequest.getRequest());
                return "invalid method";
            }
        }
    }

    private Object handleUserInformationControllerRequest2(ClientRequest clientRequest) {
        ServerResponse response;
        switch (clientRequest.getRequest()) {
            case "checkUsername" -> {
                response = new ServerResponse(clientRequest.getClientId(), "view", "setUsernameError",
                        new ArrayList<>(Collections.singleton(userInformationController.checkUsername((String) clientRequest.getContents().get(0)).toString())));
                Server.getResponses().put(clientRequest.getClientId(), response);
            }
        }
        return "get your request";
    }

    private Object handleRegisterControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "register" -> {
                return registerMenuController.register((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1), (String) clientRequest.getContents().get(2),
                        (String) clientRequest.getContents().get(3), (String) clientRequest.getContents().get(4),
                        (String) clientRequest.getContents().get(5));
            }
            case "checkSecurityQuestion" -> {
                return registerMenuController.checkSecurityQuestion((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1));
            }
            case "createRandomPassword" -> {
                return registerMenuController.createRandomPassword();
            }
            default -> {
                System.err.println("invalid method!! in register controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleLoginControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "login" -> {
                return loginMenuController.login((String) clientRequest.getContents().get(0));
            }
            case "checkInformationForLogin" -> {
                return loginMenuController.checkInformationForLogin((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1));
            }
            case "getEmptyError" -> {
                return loginMenuController.getEmptyError((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1));
            }
            default -> {
                System.err.println("invalid method!! in login controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleForgetPasswordControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "checkUsername" -> {
                return forgetPasswordController.checkUsername((String) clientRequest.getContents().get(0));
            }
            case "getPassword" -> {
                return forgetPasswordController.getPassword((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1), (String) clientRequest.getContents().get(2));
            }
            default -> {
                System.err.println("invalid method!! in forget password controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleMainMenuControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "logout" -> {
                return mainMenuController.logout((String) clientRequest.getContents().get(0));
            }
            case "getProfileData" -> {
                return mainMenuController.getProfileData((String) clientRequest.getContents().get(0));
            }
            default -> {
                System.err.println("invalid method!! in main menu controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleProfileMenuControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "changeUsername" -> {
                return profileMenuController.changeUsername((String) clientRequest.getContents().get(0), (String) clientRequest.getContents().get(1));
            }
            case "changeNickname" -> {
                return profileMenuController.changeNickname((String) clientRequest.getContents().get(0), (String) clientRequest.getContents().get(1));
            }
            case "changeEmail" -> {
                return profileMenuController.changeEmail((String) clientRequest.getContents().get(0), (String) clientRequest.getContents().get(1));
            }
            case "getDefaultGameHistory" -> {
                return profileMenuController.getDefaultGameHistory((String) clientRequest.getContents().get(0));
            }
            case "changePassword" -> {
                return profileMenuController.changePassword((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1), (String) clientRequest.getContents().get(2));
            }
            case "checkGameHistory" -> {
                return profileMenuController.checkGameHistory((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1));
            }
            case "getGameHistoryByUserRequest" -> {
                return profileMenuController.getGameHistoryByUserRequest((String) clientRequest.getContents().get(0),
                        (String) clientRequest.getContents().get(1));
            }
            default -> {
                System.err.println("invalid method!! in profile menu controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handlePregameControllerRequest(ClientRequest clientRequest) {
        switch (clientRequest.getRequest()) {
            case "getUserCardCollection" -> {
                return pregameController.getUserCardCollection((String) clientRequest.getContents().get(0));
            }
            default -> {
                System.err.println("invalid method!! in pregame controller ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return null;
    }

    private String handleGameRequest(ClientRequest clientRequest, Socket socket, DataInputStream
            dataInputStream, DataOutputStream dataOutputStream) {
        switch (clientRequest.getRequest()) {
            case "requestToRandomUser" -> {
                synchronized (Server.getRandomGameRequest()) {
                    Server.getRandomGameRequest().add(new Connection(socket, dataInputStream, dataOutputStream));
                    Server.getRandomGameRequest().notify();
                    System.out.println("nofity!");
                }
            }
            case "requestToFriend" ->
                    Server.getGameWithFriendRequest().put((String) clientRequest.getContents().get(0), socket);
            default -> {
                System.err.println("invalid method!! in handleGameRequest ->  name:" + clientRequest.getRequest());
                System.exit(-1);
            }
        }
        return "your request received";
    }

    private void addResponse(String clientId, ServerResponse response) {
        Server.getResponses().put(clientId, response);
    }
}