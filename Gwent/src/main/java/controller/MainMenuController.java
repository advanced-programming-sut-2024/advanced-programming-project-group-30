package controller;

import enums.Menu;
import model.App;
import model.Result;

public class MainMenuController extends Controller{
    @Override
    public Result exitMenu() {
        App.setCurrentMenu(Menu.LOGIN_MENU);
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
