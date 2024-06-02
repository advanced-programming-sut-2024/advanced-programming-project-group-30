package controller;

import enums.Menu;
import model.App;
import model.Game;
import model.Result;

public class MainMenuController {
    public Result logout() {
        App.setLoggedInUser(null);
        App.setCurrentMenu(Menu.LOGIN_MENU);
        return null;
    }

    public Result enterGameMenu(String menuName) {
        Game game = new Game();
        App.setCurrentGame(game);
        return null;
    }
    public Result enterProfileMenu(String menuName) {
        App.setCurrentMenu(Menu.PROFILE_MENU);
        return null;
    }
}
