package model;

import controller.SceneManager;
import enums.MenuScene;
import javafx.stage.Stage;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.REGISTER_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static Game currentGame; //hooom??
    private static LoggedInUser loggedInUser;
    // TODO : remove all users from app

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



    public static int getUserRank() {
        return 1;
    }

}

record LoggedInUser(User user, boolean stayLoggedIn) {
}