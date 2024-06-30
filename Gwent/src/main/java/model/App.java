package model;

import controller.SceneManager;
import enums.MenuScene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.GAME_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static Game currentGame;
    private static User loggedInUser;
    private static final ArrayList<User> allUsers = new ArrayList<>();

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
        return loggedInUser;
    }
    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
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
}