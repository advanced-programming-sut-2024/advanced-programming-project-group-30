package controller;

import enums.RegisterMenuCheck;
import model.App;
import model.Result;
import model.User;

public class UserInformationController {
    public Result checkUsername(String username) {
        User loggedInUser = App.getLoggedInUser();
        if (username.isEmpty()) return new Result(false, "** username cannot be empty.");
        else if (loggedInUser != null && loggedInUser.getUsername().equals(username))
                return new Result(false, "** new username is the same as your current username.");
        else if (!isUsernameFormatValid(username))
            return new Result(false, "** username can only contain english letters, numbers and underline.");
        else if (!isUsernameUnique(username)) return new Result(false, "** this username is already taken.");
        else return new Result(true, "");
    }

    public Result checkPassword(String password) {
        User loggedInUser = App.getLoggedInUser();
        if (password.isEmpty()) return new Result(false, "** password cannot be empty.");
        else if (!isPasswordFormatValid(password))
            return new Result(false, "** password must only contain english letters, numbers and special characters.");
        else if (loggedInUser != null && loggedInUser.getPassword().equals(password))
            return new Result(false, "** new password is the same as your current password.");
        else if (!isPasswordStrong(password)) return new Result(false, "** password is weak.");
        else return new Result(true, "");
    }

    public Result checkEmail(String email) {
        User loggedInUser = App.getLoggedInUser();
        if (email.isEmpty()) return new Result(false, "** email cannot be empty.");
        else if (loggedInUser != null && loggedInUser.getEmail().equals(email))
            return new Result(false, "** new email is the same as your current email.");
        else if (!isEmailFormatValid(email)) return new Result(false, "** please enter a valid email.");
        else return new Result(true, "");
    }

    public Result checkNickname(String nickname){
        User user = App.getLoggedInUser();
        if (nickname.isEmpty()) return new Result(false, "** nickname cannot be empty.");
        else if (user != null && user.getNickName().equals(nickname))
            return new Result(false, "** new nickname is the same as your current nickname.");
        else return new Result(true, "");
    }

    private boolean isUsernameUnique(String username) {
        return App.getUserByUsername(username) == null;
    }

    private boolean isUsernameFormatValid(String username) {
        return RegisterMenuCheck.VALID_USERNAME.getMatcher(username).matches();
    }

    private boolean isPasswordFormatValid(String password) {
        return RegisterMenuCheck.VALID_PASSWORD.getMatcher(password).matches();
    }

    private boolean isPasswordStrong(String password) {
        return RegisterMenuCheck.STRONG_PASSWORD.getMatcher(password).matches();
    }

    private boolean isEmailFormatValid(String email) {
        return RegisterMenuCheck.VALID_EMAIL.getMatcher(email).matches();
    }
}
