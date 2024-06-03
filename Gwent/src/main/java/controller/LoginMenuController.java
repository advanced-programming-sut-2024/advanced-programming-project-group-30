package controller;

import enums.Menu;
import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;

public class LoginMenuController {
    public void login(String username, String password, String stayLoggedInTag) {
        User user = App.getUserByUsername(username);
        App.setLoggedInUser(user);
    }




    private static Result doesUsernameExist(String username) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "Username does not exist");
        }
        return new Result(true, "Username exists");
    }

    private static Result isPasswordCorrect(String username, String password) {
        User user = App.getLoggedInUser();
        if (!user.getPassword().equals(password)) {
            return new Result(false, "Password is incorrect");
        }
        return new Result(true, "Password is correct");
    }

    public boolean checkAnswerOfSecurityQuestion(String username, int questionNumber, String answer) {
        SecurityQuestion securityQuestion = SecurityQuestion.values()[questionNumber];
        User user = App.getUserByUsername(username);
        assert user != null;
        return user.getSecurityQuestions().get(securityQuestion).equals(answer);
    }

    public void changePassword(String password) {
        User user = App.getLoggedInUser();
        user.setPassword(password);
    }

    public void enterRegisterMenu() {
        App.setCurrentMenu(Menu.REGISTER_MENU);
    }

    public void exitMenu() {
        System.exit(0);
    }
}
