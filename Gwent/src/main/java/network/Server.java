package network;

import com.google.gson.Gson;
import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final int PORT = 8000;
    private static final int WORKERS = 5;
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> connections;
    private static Listener listener;
    private static final ArrayList<ServerWorker> serverWorkers = new ArrayList<>();
    private static Gson gsonAgent;

    public static ArrayList<Socket> getConnections() {
        return connections;
    }

    private static void setupServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            connections = new ArrayList<>();
            listener = new Listener(serverSocket);
            for (int i = 0; i < WORKERS; i++)
                serverWorkers.add(new ServerWorker(serverSocket));
        } catch (IOException e) {
            System.err.println("The setup server encountered a problem");
            e.getCause().printStackTrace(System.err);
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        try {
            Server.setupServer();
            for (ServerWorker serverWorker : serverWorkers)
                serverWorker.start();
            listener.start();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            System.exit(-1);
        }
    }
}
