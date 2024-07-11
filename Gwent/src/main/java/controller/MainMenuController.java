package controller;

import model.App;
import view.ScoreBoard;

public class MainMenuController {
    public void logout() {
        App.setLoggedInUser(null, false);
        App.getSceneManager().goToLoginMenu();
    }

    public void createGame() {
        // TODO
    }
    public void goToProfileMenu() {
        App.getSceneManager().goToProfileMenu();
    }

}


