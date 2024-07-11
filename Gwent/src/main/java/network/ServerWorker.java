package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.PregameController;
import controller.server.*;
import enums.FactionType;

import java.io.*;
import java.net.Socket;

public class ServerWorker extends Thread {
    private final Gson gsonAgent;
    private final UserInformationControllerServer userInformationControllerServer = new UserInformationControllerServer();
    private final RegisterMenuControllerServer registerMenuControllerServer = new RegisterMenuControllerServer();
    private final LoginMenuControllerServer loginMenuControllerServer = new LoginMenuControllerServer();
    private final ForgetPasswordControllerServer forgetPasswordControllerServer = new ForgetPasswordControllerServer();
    private final MainMenuControllerServer mainMenuControllerServer = new MainMenuControllerServer();
    private final ProfileMenuControllerServer profileMenuControllerServer = new ProfileMenuControllerServer();
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
            if (serverMessage != null)
                sendBuffer.writeUTF(serverMessage);
            else {
                System.err.println("Control Error!!! (not find controller " + clientMessage.getControllerName() + ")");
                System.exit(1);
            }
            sendBuffer.close();
            receiveBuffer.close();
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
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2));
            }
            case "getUserFaction" -> {
                return userInformationControllerServer.getUserFaction((String) clientMessage.getFields().get(0));
            }
            case "setUserFaction" -> {
                return userInformationControllerServer.setUserFaction((String) clientMessage.getFields().get(0),
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

    private Object handleLoginControllerRequest(ClientMessage clientMessage) {
        switch (clientMessage.getMethodName()) {
            case "login" -> {
                return loginMenuControllerServer.login((String) clientMessage.getFields().get(0));
            }
            case "checkInformationForLogin" -> {
                return loginMenuControllerServer.checkInformationForLogin((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "getEmptyError" -> {
                return loginMenuControllerServer.getEmptyError((String) clientMessage.getFields().get(0),
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
                return forgetPasswordControllerServer.checkUsername((String) clientMessage.getFields().get(0));
            }
            case "getPassword" -> {
                return forgetPasswordControllerServer.getPassword((String) clientMessage.getFields().get(0),
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
                return mainMenuControllerServer.logout((String) clientMessage.getFields().get(0));
            }
            case "getProfileData" -> {
                return mainMenuControllerServer.getProfileData((String) clientMessage.getFields().get(0));
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
                return profileMenuControllerServer.changeUsername((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "changeNickname" -> {
                return profileMenuControllerServer.changeNickname((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "changeEmail" -> {
                return profileMenuControllerServer.changeEmail((String) clientMessage.getFields().get(0), (String) clientMessage.getFields().get(1));
            }
            case "getDefaultGameHistory" -> {
                return profileMenuControllerServer.getDefaultGameHistory((String) clientMessage.getFields().get(0));
            }
            case "changePassword" -> {
                return profileMenuControllerServer.changePassword((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1), (String) clientMessage.getFields().get(2));
            }
            case "checkGameHistory" -> {
                return profileMenuControllerServer.checkGameHistory((String) clientMessage.getFields().get(0),
                        (String) clientMessage.getFields().get(1));
            }
            case "getGameHistoryByUserRequest" -> {
                return profileMenuControllerServer.getGameHistoryByUserRequest((String) clientMessage.getFields().get(0),
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
}