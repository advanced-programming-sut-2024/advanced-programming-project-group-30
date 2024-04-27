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
}
