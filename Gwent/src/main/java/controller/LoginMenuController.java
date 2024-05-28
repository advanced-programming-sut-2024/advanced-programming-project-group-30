package controller;

import enums.Menu;
import enums.SecurityQuestion;
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

    public boolean checkAnswerOfSecurityQuestion(String username, int questionNumber, String answer) {
        SecurityQuestion securityQuestion = SecurityQuestion.values()[questionNumber];
        User user = App.getUserByUsername(username);
        assert user != null;
        return user.getSecurityQuestions().get(securityQuestion).equals(answer);
    }

    public Result changePassword(String password) {
        User user = App.getLoggedInUser();
        user.setPassword(password);
        return new Result(true, "Password changed successfully");
    }
    public void enterRegisterMenu() {
        App.setCurrentMenu(Menu.REGISTER_MENU);
    }

    public Result exitMenu() {
        return null;
    }
}
