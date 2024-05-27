package controller;

import enums.Menu;
import model.App;
import model.Result;
import model.User;

public class LoginMenuController {
    public Result login(String username, String password, String stayLoggedInTag) {

        User user = App.getUserByUsername(username);
        App.setLoggedInUser(user);
        return null;
    }
    private static boolean doesUsernameExist(String username){
        return (App.getUserByUsername(username) == null);
    }
    private static boolean isPasswordCorrect(String username, String password){
        User user = App.getLoggedInUser();
        return (password.equals(user.getPassword()));
    }



    public Result checkUsernameForForgetPassword(String username) {
        return null;
    }

    public Result checkAnswerOfSecurityQuestion(String username, int questionNumber, String answer) {
        return null;
    }


    public Result enterRegisterMenu() {
        App.setCurrentMenu(Menu.REGISTER_MENU);
        return null;
    }

    public Result exitMenu() {
        return null;
    }
}
