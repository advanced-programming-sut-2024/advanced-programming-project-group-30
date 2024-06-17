package controller;

import enums.MenuScene;
import enums.SecurityQuestion;
import javafx.scene.control.PasswordField;
import model.App;
import model.Result;
import model.User;

public class LoginMenuController {
    public void login(String username, String rememberMe) {
        User user = App.getUserByUsername(username);
        App.setLoggedInUser(user);
        enterMainMenu();
    }

    public Result checkInformationForLogin(String username, String password) {
        if (username.isEmpty()) return new Result(false, "please fill username field.");
        if (password.isEmpty()) return new Result(false, "please fill password field.");
        User loggedInUser = App.getUserByUsername(username);
        if (loggedInUser == null) return new Result(false, "this username does not exist.");
        if (!password.equals(loggedInUser.getPassword())) return new Result(false, "wrong password.");
        return new Result(true, "");
    }

    public Result getEmptyError(String string, String name) {
        if (string.isEmpty()) return new Result(false, "** " + name + " cannot be empty.");
        return new Result(true, "");
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
        App.setCurrentMenuScene(MenuScene.REGISTER_SCENE);
        App.getPrimaryStage().setScene(MenuScene.REGISTER_SCENE.getScene());
    }

    private void enterMainMenu() {
        App.setCurrentMenuScene(MenuScene.MAIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.MAIN_SCENE.getScene());
    }
}
