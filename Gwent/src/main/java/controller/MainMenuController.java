package controller;

import model.Result;

public class MainMenuController extends Controller{
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
        return new Result("main menu", true, false);
    }
}
