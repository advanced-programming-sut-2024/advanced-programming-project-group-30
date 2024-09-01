package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import model.App;
import view.Menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.Socket;

public class Client extends Thread {
    private final String serverIP;
    private final int serverPort;
    private final Gson gsonAgent;

    private String id;

    private Socket mainSocket = null;
    private DataInputStream mainReceiveBuffer = null;
    private DataOutputStream mainSendBuffer = null;

    private Socket socket;
    private DataInputStream receiveBuffer;
    private DataOutputStream sendBuffer;

    private String lastServerMessage; // TODO: remove

    public Client(String serverIP, int serverPort) {
        GsonBuilder builder = new GsonBuilder();
        this.gsonAgent = builder.create();
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public String getClientId() {
        return id;
    }

    @Override
    public void run() {
        setup();
        requestForId();
        while (true) {
            ServerResponse response;
            try {
                String message = mainReceiveBuffer.readUTF();
                response = gsonAgent.fromJson(message, ServerResponse.class);
                if (response.getResponseType().equals("view")) {
                    Menu menu = App.getCurrentMenuScene().getMenu();
                    Method method = menu.getClass().getDeclaredMethod(response.getResponse(), String.class);
                    Platform.runLater(() -> {
                        try {
                            // TODO
                            method.invoke(menu, response.getContents().get(0).toString());
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (IOException | NoSuchMethodException e) {
                // TODO : connection error
                throw new RuntimeException(e);
            }
        }
    }

    private void setup() {
        try {
            mainSocket = new Socket(serverIP, serverPort);
            mainSendBuffer = new DataOutputStream(mainSocket.getOutputStream());
            mainReceiveBuffer = new DataInputStream(mainSocket.getInputStream());
        } catch (Exception e) {
            System.err.println("Unable to initialize main socket!");
            // TODO: disable current view and show error message in popup
            System.exit(1); // TODO: refresh button. boolean? or ...
        }
    }

    private void requestForId() {
        try {
            mainSendBuffer.writeUTF(gsonAgent.toJson(new ClientRequest(null, "Connection", "id request", null)));
            this.id = gsonAgent.fromJson(mainReceiveBuffer.readUTF(), String.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToServer2(ClientRequest clientRequest) {
        if (!establishConnection()) return;
        String message = gsonAgent.toJson(clientRequest);
        try {
            sendBuffer.writeUTF(message);
            this.lastServerMessage = receiveBuffer.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        endConnection();
    }

    public void sendMessageToServer(ClientRequest clientRequest) {
        if (!establishConnection()) return;
        String message = gsonAgent.toJson(clientRequest);
        try {
            sendBuffer.writeUTF(message);
            receiveBuffer.readUTF(); // TODO : remove or ...
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        endConnection();
    }

    public Object getLastServerData(Type type) {
        return gsonAgent.fromJson(lastServerMessage, type);
    }

    public void login(String username, String nickname, String email, boolean stayLoggedIn) {
        App.setLoggedInUser(username, nickname, email, stayLoggedIn);
    }

    private boolean establishConnection() {
        try {
            socket = new Socket(serverIP, serverPort);
            sendBuffer = new DataOutputStream(socket.getOutputStream());
            receiveBuffer = new DataInputStream(socket.getInputStream());
            return true;
        } catch (Exception e) {
            System.err.println("Unable to initialize socket!");
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
}