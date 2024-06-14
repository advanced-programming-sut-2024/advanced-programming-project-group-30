package controller;

import enums.RegisterMenuCheck;
import model.App;
import model.Result;
import view.ProfileMenu;

public class ProfileMenuController {
    private static final UserInformationController userInformationController = new UserInformationController();
    private ProfileMenu profileMenu;
    public ProfileMenuController(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
    }

    public Result changeUsername(String newUsername) {
        Result result = userInformationController.checkUsername(newUsername);
        if (result.isSuccessful()) {
            App.getLoggedInUser().setUsername(newUsername);
            profileMenu.changeUsername(newUsername);
        }
        return result;
    }

    public Result changeNickname(String newNickname) {
        Result result = userInformationController.checkNickname(newNickname);
        if (result.isSuccessful()) {
            App.getLoggedInUser().setNickName(newNickname);
            profileMenu.changeNickname(newNickname);
        }
        return result;
    }

    public Result changePassword(String newPassword) {
        Result result = userInformationController.checkPassword(newPassword);
        if (result.isSuccessful())
            App.getLoggedInUser().setPassword(newPassword);
        return result;
    }

    public Result changeEmail(String newEmail) {
        Result result = userInformationController.checkEmail(newEmail);
        if (result.isSuccessful()){
            App.getLoggedInUser().setEmail(newEmail);
            profileMenu.changeEmail(newEmail);
        }
        return result;
    }

    public String showGameHistory(String numberString) {
        return null;
    }
    public String showInformation() {
        return null;
    }
}
