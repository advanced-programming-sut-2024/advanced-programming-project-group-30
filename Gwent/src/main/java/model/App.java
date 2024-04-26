package model;

import enums.Menu;

import java.util.ArrayList;

public abstract class App {
    private static Menu currentMenu;
    private static Game currentGame;
    private static User loggedInUser;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private static final ArrayList<String> securityQuestions = new ArrayList<>();
    static {

    }







    public void addToAllUsers(User user){
        allUsers.add(user);
    }
    public User getUserByUsername(String username){
        for (User user : allUsers)
            if (user.getUsername().equals(username)) return user;
        return null;

    }
    public void setCurrentMenu(Menu menu){
        currentMenu = menu;
    }
    public static Menu getCurrentMenu() {
        return currentMenu;
    }
    public static int getUserRank(){
        return 1;
    }

}
