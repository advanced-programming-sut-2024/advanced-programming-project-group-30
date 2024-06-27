package controller;

import model.App;

public class MainMenuController {
    public void logout() {
        App.setLoggedInUser(null);
        App.getSceneManager().goToLoginMenu();
    }

    public void createGame() {
        // TODO
    }
    public void goToProfileMenu() {
        App.getSceneManager().goToProfileMenu();
    }
}


