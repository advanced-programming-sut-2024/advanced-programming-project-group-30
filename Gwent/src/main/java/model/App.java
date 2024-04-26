package model;

import enums.Menu;

import java.util.ArrayList;

public abstract class App {
    private static Menu currentMenu;
    private static Game currentGame;
    private static User loggedInUser;
    private static ArrayList<User> allUsers;
    private static ArrayList<String> securityQuestions;








    public static Menu getCurrentMenu() {
        return currentMenu;
    }

}
