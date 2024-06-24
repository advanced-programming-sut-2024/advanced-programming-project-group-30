package controller;

import enums.MenuScene;
import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;

public class ForgetPasswordMenuController {
    public void goToLoginMenu() {
        App.setCurrentMenuScene(MenuScene.LOGIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.LOGIN_SCENE.getScene());
    }

    public Result checkUsername(String username) {
        if (username.isEmpty()) return new Result(false, "please enter your username");
        if (App.getUserByUsername(username) == null) return new Result(false, "this username does not exist");
        return new Result(true, "");
    }

    public Result getPassword(String username, String question, String answer) {
        User user = App.getUserByUsername(username);
        if (user == null) return new Result(false, "this username does not exist");
        if (answer.isEmpty()) return new Result(false, "please enter your answer");
        SecurityQuestion securityQuestion = SecurityQuestion.getSecurityQuestion(question);
        if (securityQuestion == null) return new Result(false, "please choose a question");
        if (!answer.equals(user.getSecurityQuestions().get(securityQuestion)))
            return new Result(false, "!!!");
        return new Result(true, user.getPassword());
    }
}
