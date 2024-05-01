package controller;

import model.Result;

public class ProfileMenuController extends Controller{
    public Result changeUsername(String username) {
        return new Result("", true, false);
    }
    public Result changePassword(String currentPassword, String newPassword) {
        return new Result("", true, false);
    }
    public Result changeEmail(String email) {
        return new Result("", true, false);
    }
    public Result changeNickname(String nickname) {
        return new Result("", true, false);
    }
    public Result showUserInfo(){
        return new Result("", true, false);
    }


    @Override
    public Result menuEnter() {
        return null;
    }

    @Override
    public String menuExit() {
        return "";
    }
}
