package controller;

import enums.SecurityQuestion;
import model.App;
import model.Result;
import model.User;
import network.Server;

public class ForgetPasswordMenuController {
    public Result checkUsername(String username) {
        if (username.isEmpty()) return new Result(false, "please enter your username");
        if (Server.getUserByUsername(username) == null) return new Result(false, "this username does not exist");
        return new Result(true, "");
    }

    public Result getPassword(String username, String question, String answer) {
        User user = Server.getUserByUsername(username);
        if (user == null) return new Result(false, "this username does not exist");
        if (answer.isEmpty()) return new Result(false, "please enter your answer");
        SecurityQuestion securityQuestion = SecurityQuestion.getSecurityQuestion(question);
        if (securityQuestion == null) return new Result(false, "please choose a question");
        if (!answer.equals(user.getSecurityAnswer()) || !securityQuestion.equals(user.getSecurityQuestion()))
            return new Result(false, "incorrect credentials!");
        return new Result(true, user.getPassword());
    }
}