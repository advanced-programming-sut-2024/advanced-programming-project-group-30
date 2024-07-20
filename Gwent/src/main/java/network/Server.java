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
    private static final int PORT = 7000;
    private static final int WORKERS = 5;
    private static final int RANDOM_GAME_REQUEST_HANDLERS = 5;
    private static final int GAME_WITH_FRIEND_REQUEST_HANDLERS = 5;
    private static ServerSocket serverSocket;
    private static ServerListener serverListener;
    private static ArrayList<Socket> connections;
    private static ArrayList<Connection> randomGameRequest;
    private static HashMap<String, Socket> gameWithFriendRequest;
    private static final ArrayList<ServerWorker> serverWorkers = new ArrayList<>();
    private static final ArrayList<RandomGameRequestHandler> randomGameRequestHandlers = new ArrayList<>();
    private static final ArrayList<GameWithFriendRequestHandler> gameWithFriendRequestHandlers = new ArrayList<>();

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

    public static User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username)) return user;
        return null;

    }

    public static HashMap<String, Game> getAllGames() {
        return allGames;
    }

    protected static ArrayList<Socket> getConnections() {
        return connections;
    }

    protected static ArrayList<Connection> getRandomGameRequest() {
        return randomGameRequest;
    }

    protected static HashMap<String, Socket> getGameWithFriendRequest() {
        return gameWithFriendRequest;
    }

    private static void setupServer() {
        try {
            testSetup();
            serverSocket = new ServerSocket(PORT);
            connections = new ArrayList<>();
            randomGameRequest = new ArrayList<>();
            gameWithFriendRequest = new HashMap<>();
            serverListener = new ServerListener(serverSocket);
            for (int i = 0; i < WORKERS; i++)
                serverWorkers.add(new ServerWorker());
            for (int i = 0; i < GAME_WITH_FRIEND_REQUEST_HANDLERS; i++)
                gameWithFriendRequestHandlers.add(new GameWithFriendRequestHandler());
            for (int i = 0; i < RANDOM_GAME_REQUEST_HANDLERS; i++)
                randomGameRequestHandlers.add(new RandomGameRequestHandler());
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
            for (GameWithFriendRequestHandler gameWithFriendRequestHandler : gameWithFriendRequestHandlers)
                gameWithFriendRequestHandler.start();
            for (RandomGameRequestHandler randomGameRequestHandler : randomGameRequestHandlers)
                randomGameRequestHandler.start();
            serverListener.start();
        } catch (Exception e) {
            System.out.println("Server encountered a problem!");
            System.exit(-1);
        }
    }
}
