package network;

import com.google.gson.Gson;
import enums.SecurityQuestion;
import model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final int PORT = 7000;
    private static final int WORKERS = 5;
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> connections;
    private static ServerListener serverListener;
    private static final ArrayList<ServerWorker> serverWorkers = new ArrayList<>();
    private static Gson gsonAgent;

    public static void testSetup() {
        User testUser1 = new User("jojo", "j",
                "jojo@gmail.com", "jojo", SecurityQuestion.QUESTION_1, "j");
        User testUser2 = new User("pishi", "p",
                "pishi@gmail.com", "pishi", SecurityQuestion.QUESTION_1, "p");
        allUsers.add(testUser1);
        allUsers.add(testUser2);
    }

    public static void addUser(User user) {
        allUsers.add(user);
    }

    public static void removeUser(User user) {
        allUsers.remove(user);
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username)) return user;
        return null;

    }

    public static ArrayList<Socket> getConnections() {
        return connections;
    }

    public static int getPort() {
        return PORT;
    }

    private static void setupServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            connections = new ArrayList<>();
            serverListener = new ServerListener(serverSocket);
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
            serverListener.start();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            System.exit(-1);
        }
    }
}
