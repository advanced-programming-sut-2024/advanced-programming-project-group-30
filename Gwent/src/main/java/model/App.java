package model;

import view.SceneManager;
import enums.MenuScene;
import javafx.stage.Stage;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.REGISTER_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static LoggedInUser loggedInUser = null;
    private static String currentGameId;

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

    public static boolean getStayLoggedIn() {
        return loggedInUser.stayLoggedIn();
    }

    public static String getLoggedInUsersUsername() {
        return loggedInUser.username();
    }

    public static String getLoggedInUsersNickname() {
        return loggedInUser.nickname();
    }

    public static String getLoggedInUsersEmail() {
        return loggedInUser.email();
    }

    public static void setLoggedInUser(String username, String nickName, String email, boolean stayLoggedIn) {
        if (username == null) loggedInUser = null;
        else loggedInUser = new LoggedInUser(username, nickName, email, stayLoggedIn);
    }

    public static String getCurrentGame() {
        return currentGameId;
    }

    public static void setCurrentGameId(String currentGameId) {
        App.currentGameId = currentGameId;
    }


    public static int getUserRank() {
        return 1;
    }
}

record LoggedInUser(String username, String nickname, String email, boolean stayLoggedIn) {
}