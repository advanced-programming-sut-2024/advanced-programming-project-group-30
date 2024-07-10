package model;

import controller.SceneManager;
import enums.MenuScene;
import javafx.stage.Stage;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.REGISTER_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static Game currentGame;
    private static LoggedInUser loggedInUser = null;

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

    public static String getLoggedInUsersUsername() {
        return loggedInUser.username();
    }

    public static void setLoggedInUser(String username, String nickName, String email, boolean stayLoggedIn) {
        if (username == null) loggedInUser = null;
        else loggedInUser = new LoggedInUser(username, nickName, email, stayLoggedIn);
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }


    public static int getUserRank() {
        return 1;
    }

}

record LoggedInUser(String username, String nickname, String email, boolean stayLoggedIn) {
}