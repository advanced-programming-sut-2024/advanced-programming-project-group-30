package model;

import enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu;
    private static Game currentGame;
    private static User loggedInUser;
    private static final ArrayList<User> allUsers = new ArrayList<>();

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
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

    public void addUser(User user) {
        allUsers.add(user);
    }

    public void removeUser(User user) {
        allUsers.remove(user);
    }

    public User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username)) return user;
        return null;

    }

    public static int getUserRank() {
        return 1;
    }
}
