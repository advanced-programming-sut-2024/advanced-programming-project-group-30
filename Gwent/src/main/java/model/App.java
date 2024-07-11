package model;

import controller.DataSaverController;
import model.card.UserData;
import view.SceneManager;
import enums.MenuScene;
import enums.SecurityQuestion;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App {
    private static Stage primaryStage;
    private static MenuScene currentMenuScene = MenuScene.LOGIN_SCENE;
    private static final SceneManager sceneManager = new SceneManager();
    private static Game currentGame;
    private static LoggedInUser loggedInUser;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final DataSaverController dataSaverController = new DataSaverController();

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

    public static void setAllUsers(ArrayList<UserData> userDatas) {
        for (UserData userData: userDatas) {
            boolean isUserUnique = true;
            if (allUsers != null){
                for (User user : allUsers) {
                    if (user.getUsername().equals(userData.getUsername())) {
                        isUserUnique = false;
                        break;
                    }
                }
            }
            if (isUserUnique)
                allUsers.add(new User(userData));
        }

    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void saveUsers() {
        ArrayList<UserData> userDatas = new ArrayList<>();
        for (User user : allUsers) {
            userDatas.add(new UserData(user));
        }
        dataSaverController.saveUsers(userDatas);
    }

    public static void loadUsers() {
        dataSaverController.loadUsers();
    }
}

record LoggedInUser(User user, boolean stayLoggedIn) {
}