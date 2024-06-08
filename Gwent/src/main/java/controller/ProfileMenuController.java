package controller;

import enums.RegisterMenuCheck;
import model.App;
import model.Result;

public class ProfileMenuController {

    private static boolean isUsernameTheSame(String username){
        return App.getLoggedInUser().getUsername().equals(username);
    }
    private static boolean isUsernameValid(String username){
        return RegisterMenuCheck.VALID_USERNAME.getMatcher(username).matches();
    }
    public void changeUsername(String newUsername) {

    }

    public void changeNickname(String newNickname) {

    }

    public void changePassword(String currentPassword, String newPassword) {

    }

    public void changeEmail(String newEmail) {

    }

    public String showGameHistory(String numberString) {
        return null;
    }
    public String showInformation(){
        return null;
    }

}
