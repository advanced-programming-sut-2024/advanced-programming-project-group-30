package controller;

import model.Result;

public class MainMenuController extends Controller{
    public Result logout(){
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
