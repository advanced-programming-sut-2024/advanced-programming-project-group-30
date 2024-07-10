package controller.server;

import enums.CheckInformationRegex;
import model.App;
import model.Result;
import model.User;
import network.Server;

import java.util.Random;

public class UserInformationControllerServer {
    public Result checkInformation(String username, String password, String passwordConfirm, String
            nickname, String email) {
        if (checkUsername(username).isNotSuccessful()) return new Result(false, "please check username field");
        if (checkPassword(password).isNotSuccessful()) return new Result(false, "please check password field");
        if (!checkPasswordConfirm(password, passwordConfirm))
            return new Result(false, "password and confirmation do not match");
        if (checkNickname(nickname).isNotSuccessful()) return new Result(false, "please fill nickname field");
        if (checkEmail(email).isNotSuccessful()) return new Result(false, "please check email field");
        return new Result(true, "");
    }

    public Result checkUsername(String username) {
        if (username.isEmpty()) return new Result(false, "** username cannot be empty.");
        else if (!isUsernameFormatValid(username))
            return new Result(false, "** username must only contain letters, numbers and underline.");
        else if (!isUsernameUnique(username)) {
            String uniqueUsername = createUniqueUserName(username);
            return new Result(false, "** this username is already taken. suggest: \"" + uniqueUsername + "\".");
        } else return new Result(true, "");
    }

    public Result checkPassword(String password) {
        if (password.isEmpty()) return new Result(false, "** password cannot be empty.");
        if (!isPasswordFormatValid(password))
            return new Result(false, "** password must only contain letters, numbers and special characters.");
        if (!isPasswordStrong(password)) return new Result(false, "** password is weak.");
        return new Result(true, "");
    }

    public Result checkPasswordConfirm(String passwordConfirm) {
        if (passwordConfirm.isEmpty()) return new Result(false, "** password confirm cannot be empty.");
        return new Result(true, "");
    }

    public Result checkNickname(String nickname) {
        if (nickname.isEmpty()) return new Result(false, "** nickname cannot be empty.");
        return new Result(true, "");
    }

    public Result checkEmail(String email) {
        if (email.isEmpty()) return new Result(false, "** email cannot be empty.");
        if (!isEmailFormatValid(email)) return new Result(false, "** please enter a valid email.");
        return new Result(true, "");
    }

    // TODO: server.
    public Result checkPasswordForChange(String newPassword, String oldPassword) {
//        User user = App.getLoggedInUsersUsername();
//        if (newPassword.isEmpty()) return new Result(false, "** password cannot be empty.");
//        if (oldPassword.isEmpty()) return new Result(false, "** please enter your current password.");
//        if (!user.getPassword().equals(oldPassword)) return new Result(false, "** your current password is incorrect.");
//        if (!isPasswordFormatValid(newPassword))
//            return new Result(false, "** password must only contain english letters, numbers and special characters.");
//        if (!isPasswordStrong(newPassword)) return new Result(false, "** password is weak.");
//        if (user.getPassword().equals(newPassword))
//            return new Result(false, "** new password is the same as your current password.");
        return new Result(true, "");
    }

    private boolean checkPasswordConfirm(String password, String passwordConfirm) {
        if (passwordConfirm.isEmpty()) return false;
        return password.equals(passwordConfirm);
    }

    private boolean isUsernameUnique(String username) {
        return Server.getUserByUsername(username) == null;
    }

    private boolean isUsernameFormatValid(String username) {
        return CheckInformationRegex.VALID_USERNAME.getMatcher(username).matches();
    }

    private boolean isPasswordFormatValid(String password) {
        return CheckInformationRegex.VALID_PASSWORD.getMatcher(password).matches();
    }

    private boolean isPasswordStrong(String password) {
        return CheckInformationRegex.STRONG_PASSWORD.getMatcher(password).matches();
    }

    private boolean isEmailFormatValid(String email) {
        return CheckInformationRegex.VALID_EMAIL.getMatcher(email).matches();
    }

    private String createUniqueUserName(String duplicateUsername) {
        Random random = new Random();
        StringBuilder uniqueUsernameBuilder = new StringBuilder(duplicateUsername);
        for (int i = 0; i < duplicateUsername.length(); i++) {
            int randomInt = random.nextInt(3);
            switch (randomInt) {
                case 1 -> uniqueUsernameBuilder.insert(i, random.nextInt(10));
                case 2 -> uniqueUsernameBuilder.insert(i, '_');
            }
            if (Server.getUserByUsername(uniqueUsernameBuilder.toString()) == null) break;
        }
        if (Server.getUserByUsername(uniqueUsernameBuilder.toString()) != null)
            return createUniqueUserName(uniqueUsernameBuilder.toString());
        return uniqueUsernameBuilder.toString();
    }
}