package controller;

import enums.Menu;
import model.App;
import model.Game;

public class MainMenuController {
    public void logout() {
        App.setLoggedInUser(null);
        App.setCurrentMenu(Menu.LOGIN_MENU);
    }
    public void enterGameMenu() {
        Game game = new Game();
        App.setCurrentGame(game);
        App.setCurrentMenu(Menu.GAME_MENU);
    }
    public void enterProfileMenu() {
        App.setCurrentMenu(Menu.PROFILE_MENU);
    }

}
