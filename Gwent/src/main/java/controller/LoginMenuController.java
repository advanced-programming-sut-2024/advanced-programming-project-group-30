package controller;

import model.Result;

public class LoginMenuController extends Controller{
    public Result login(String username, String password) {
        return new Result("", true, false);
    }

    public Result goToRegisterMenu(){
        return new Result("", true, false);
    }

    public Result checkUsernameForForgetPassword(String username){
        return new Result("", true, false);
    }

    public Result checkAnswerForSecurityQuestion(String username, int questionNumber, String answer) {
        return new Result("", true, false);
    }

    public Result setPassword(String username, String password) {
        return new Result("", true, false);
    }
    @Override
    public Result exitMenu() {
        return null;
    }
    @Override
    public Result enterMenu(String menuName) {
        return null;
    }
    @Override
    public Result showCurrentMenu() {
        return new Result("login menu", true, false);
    }
}
