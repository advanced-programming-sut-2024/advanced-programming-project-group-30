package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import enums.FactionType;

import java.io.*;
import java.net.Socket;

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
            if (socket != null) {
                handleConnection(socket);
            }
        }
    }

    private void handleConnection(Socket socket) {
        try {
            DataInputStream receiveBuffer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream sendBuffer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String messageString = receiveBuffer.readUTF();
            ClientMessage clientMessage = gsonAgent.fromJson(messageString, ClientMessage.class);
            String serverMessage = switch (clientMessage.getControllerName()) {
                case "GameRequestHandler" -> gsonAgent.toJson(handleGameRequest(clientMessage, socket, receiveBuffer, sendBuffer));
                case "UserInformationController" ->
                        gsonAgent.toJson(handleUserInformationControllerRequest(clientMessage));
                case "RegisterController" -> gsonAgent.toJson(handleRegisterControllerRequest(clientMessage));
                case "LoginController" -> gsonAgent.toJson(handleLoginControllerRequest(clientMessage));
                case "ForgetPasswordController" ->
                        gsonAgent.toJson(handleForgetPasswordControllerRequest(clientMessage));
                case "MainMenuController" -> gsonAgent.toJson(handleMainMenuControllerRequest(clientMessage));
                case "ProfileMenuController" -> gsonAgent.toJson(handleProfileMenuControllerRequest(clientMessage));
                case "PregameController" -> gsonAgent.toJson(handlePregameControllerRequest(clientMessage));
                default -> null;
            };
            if (clientMessage.getControllerName().equals("GameRequestHandler")) return;
            if (serverMessage != null)
                sendBuffer.writeUTF(serverMessage);
            else {
                System.err.println("Control Error!!! (not find controller " + clientMessage.getControllerName() + ")");
                System.exit(1);
            }
            sendBuffer.close();
            receiveBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object handleUserInformationControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "checkInformation" -> {
                return userInformationController.checkInformation((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2),
                        (String) clientMessage.getFields().get(3), (String) clientMessage.getFields().get(4));
            }
            case "checkUsername" -> {
                return userInformationController.checkUsername((String) clientMessage.getFields().get(0));
            }
            case "checkPassword" -> {
                return userInformationController.checkPassword((String) clientMessage.getFields().get(0));
            }
            case "checkPasswordConfirm" -> {
                return userInformationController.checkPasswordConfirm((String) clientMessage.getFields().get(0));
            }
            case "checkNickname" -> {
                return userInformationController.checkNickname((String) clientMessage.getFields().get(0));
            }
            case "checkEmail" -> {
                return userInformationController.checkEmail((String) clientMessage.getFields().get(0));
            }
            case "checkPasswordForChange" -> {
                return userInformationController.checkPasswordForChange((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2));
            }
            case "getUserFaction" -> {
                return userInformationController.getUserFaction((String) clientMessage.getFields().get(0));
            }
            case "setUserFaction" -> {
                return userInformationController.setUserFaction((String) clientMessage.getFields().get(0),
                        FactionType.valueOf((String) clientMessage.getFields().get(1)));
            }
            default -> {
                System.err.println("invalid method!! in userInformation ->  name:" + clientMessage.getMethodName());
                return "invalid method";
            }
        }
    }

    private Object handleRegisterControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "register" -> {
                return registerMenuController.register((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2),
                        (String) clientMessage.getFields().get(3), (String) clientMessage.getFields().get(4),
                        (String) clientMessage.getFields().get(5));
            }
            case "checkSecurityQuestion" -> {
                return registerMenuController.checkSecurityQuestion((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "createRandomPassword" -> {
                return registerMenuController.createRandomPassword();
            }
            default -> {
                System.err.println("invalid method!! in register controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleLoginControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "login" -> {
                return loginMenuController.login((String) clientMessage.getFields().get(0));
            }
            case "checkInformationForLogin" -> {
                return loginMenuController.checkInformationForLogin((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "getEmptyError" -> {
                return loginMenuController.getEmptyError((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            default -> {
                System.err.println("invalid method!! in login controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleForgetPasswordControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "checkUsername" -> {
                return forgetPasswordController.checkUsername((String) clientMessage.getFields().get(0));
            }
            case "getPassword" -> {
                return forgetPasswordController.getPassword((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2));
            }
            default -> {
                System.err.println("invalid method!! in forget password controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleMainMenuControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "logout" -> {
                return mainMenuController.logout((String) clientMessage.getFields().get(0));
            }
            case "getProfileData" -> {
                return mainMenuController.getProfileData((String) clientMessage.getFields().get(0));
            }
            default -> {
                System.err.println("invalid method!! in main menu controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handleProfileMenuControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "changeUsername" -> {
                return profileMenuController.changeUsername((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "changeNickname" -> {
                return profileMenuController.changeNickname((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "changeEmail" -> {
                return profileMenuController.changeEmail((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "getDefaultGameHistory" -> {
                return profileMenuController.getDefaultGameHistory((String) clientMessage.getFields().get(0));
            }
            case "changePassword" -> {
                return profileMenuController.changePassword((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2));
            }
            case "checkGameHistory" -> {
                return profileMenuController.checkGameHistory((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "getGameHistoryByUserRequest" -> {
                return profileMenuController.getGameHistoryByUserRequest((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            default -> {
                System.err.println("invalid method!! in profile menu controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private Object handlePregameControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "getUserCardCollection" -> {
                return pregameController.getUserCardCollection((String) clientMessage.getFields().get(0));
            }
            default -> {
                System.err.println("invalid method!! in pregame controller ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return null;
    }

    private String handleGameRequest(ClientMessage clientMessage, Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        switch (clientMessage.getMethodName()) {
            case "requestToRandomUser" -> Server.getRandomGameRequest().add(new Connection(socket, dataInputStream, dataOutputStream));
            case "requestToFriend" -> Server.getGameWithFriendRequest().put((String) clientMessage.getFields().get(0), socket);
            default -> {
                System.err.println("invalid method!! in handleGameRequest ->  name:" + clientMessage.getMethodName());
                System.exit(-1);
            }
        }
        return "your request received";
    }
}