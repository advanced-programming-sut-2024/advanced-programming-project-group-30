package network;

import enums.SecurityQuestion;
import model.Game;
import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final HashMap<String, Game> allGames = new HashMap<>();

    private static ServerSocket serverSocket;
    private static final int PORT = 7000;
    private static final int WORKERS = 5;

    private static ServerListener serverListener;
    private static ServerSender serverSender;
    private static final ArrayList<ServerWorker> serverWorkers = new ArrayList<>();
    private static final HashMap<String, Socket> clients = new HashMap<>();
    private static ArrayList<Socket> connections;
    private static final HashMap<String, ServerResponse> responses = new HashMap<>();

    // TODO : remove (just for test)
    public static void testSetup() {
        User testUser1 = new User("jojo", "j",
                "jojo@gmail.com", "jojo", SecurityQuestion.QUESTION_1, "j");
        User testUser2 = new User("pishi", "p",
                "pishi@gmail.com", "pishi", SecurityQuestion.QUESTION_1, "p");
        allUsers.add(testUser1);
        allUsers.add(testUser2);
    }

    public static void addUser(User user) {
        synchronized (allUsers) {
            allUsers.add(user);
        }
    }

    public static User getUserByUsername(String username) {
        synchronized (allUsers) {
            for (User user : allUsers)
                if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    protected static String addClient(Socket client) {
        synchronized (clients) {
            String id = Integer.toString(clients.keySet().size() + 1000);
            clients.put(id, client);
            return id;
        }
    }

    protected static Socket getClient(String id) {
        synchronized (clients) {
            return clients.get(id);
        }
    }

    // TODO : synchronized
    protected static ArrayList<Socket> getConnections() {
        return connections;
    }

    // TODO : synchronized
    protected synchronized static HashMap<String, ServerResponse> getResponses() {
        return responses;
    }

    public static void main(String[] args) {
        try {
            Server.setupServer();
            for (ServerWorker serverWorker : serverWorkers)
                serverWorker.start();
            serverSender.start();
            serverListener.start();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            System.exit(1);
        }
    }

    private static void setupServer() {
        try {
            testSetup();
            serverSocket = new ServerSocket(PORT);
            connections = new ArrayList<>();
            serverListener = new ServerListener(serverSocket);
            serverSender = new ServerSender(serverSocket);
            for (int i = 0; i < WORKERS; i++)
                serverWorkers.add(new ServerWorker());
        } catch (IOException e) {
            System.err.println("The setup server encountered a problem");
            e.getCause().printStackTrace(System.err);
            System.exit(-1);
        }
    }
}
