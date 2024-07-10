package model;

import controller.SceneManager;
import enums.MenuScene;
import enums.SecurityQuestion;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.REGISTER_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static Game currentGame; //hooom??
    private static LoggedInUser loggedInUser; //not view!
    // TODO : remove all users from app
    private static final ArrayList<User> allUsers = new ArrayList<>();

    public static void testSetup() {
        User testUser1 = new User("jojo", "j",
                "jojo@gmail.com", "jojo", SecurityQuestion.QUESTION_1, "j");
        User testUser2 = new User("pishi", "p",
                "pishi@gmail.com", "pishi", SecurityQuestion.QUESTION_1, "p");
        allUsers.add(testUser1);
        allUsers.add(testUser2);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        App.primaryStage = primaryStage;
    }

    public static MenuScene getCurrentMenuScene() {
        return currentMenuScene;
    }

    public static void setCurrentMenuScene(MenuScene currentMenuScene) {
        App.currentMenuScene = currentMenuScene;
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    public static User getLoggedInUser() {
        return loggedInUser.user();
    }

    public static void setLoggedInUser(User user, boolean stayLoggedIn) {
        if (user == null) loggedInUser = null;
        else loggedInUser = new LoggedInUser(user, stayLoggedIn);
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
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

    public static int getUserRank() {
        return 1;
    }

    public static void setAllUsers(ArrayList<User> users) {
        allUsers.clear();
        allUsers.addAll(users);
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
}

record LoggedInUser(User user, boolean stayLoggedIn) {
}