package model;

import enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static User loggedInUser;
    private static Game currentGame;
    private static ArrayList<String> securityQuestions = new ArrayList<>();
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }
    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    public static void addUser(User user) {
        allUsers.add(user);
    }
    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }
    public static Menu getCurrentMenu() {
        return currentMenu;
    }
    public static Game getCurrentGame() {
        return currentGame;
    }
    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }
    public static String getSecurityQuestion(int questionNumber) {
        return securityQuestions.get(questionNumber);
    }
    public static String giveSecurityQuestionList(){
        return null;
    }
    public int getUserRank(User user) {
        return 0;
    }
}
