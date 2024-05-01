package controller;

import model.Result;

public class RegisterMenuController extends Controller{
    public Result register(String username, String password, String nickname, String email) {
        return new Result("", true, false);
    }
    public Result pickQuestion(int questionNumber, String answer, String answerConfirmation) {
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
