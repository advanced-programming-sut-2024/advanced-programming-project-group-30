package controller;

import enums.MenuScene;
import model.App;
import model.User;
import view.MainMenu;
import view.ProfileMenu;

public class SceneManager {
    public void goToLoginMenu() {
        App.setCurrentMenuScene(MenuScene.LOGIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.LOGIN_SCENE.getScene());
    }

    public void goToRegisterMenu() {
        App.setCurrentMenuScene(MenuScene.REGISTER_SCENE);
        App.getPrimaryStage().setScene(MenuScene.REGISTER_SCENE.getScene());
    }

    public void goToForgetPasswordMenu() {
        App.setCurrentMenuScene(MenuScene.FORGET_PASSWORD_SCENE);
        App.getPrimaryStage().setScene(MenuScene.FORGET_PASSWORD_SCENE.getScene());
    }

    public void goToMainMenu() {
        MainMenu mainMenu = (MainMenu) MenuScene.MAIN_SCENE.getMenu();
        mainMenu.setFields(App.getLoggedInUser().getUsername(), App.getLoggedInUser().getNickName());
        App.setCurrentMenuScene(MenuScene.MAIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.MAIN_SCENE.getScene());
    }

    public void goToProfileMenu() {
        ProfileMenu profileMenu = (ProfileMenu) MenuScene.PROFILE_SCENE.getMenu();
        User user = App.getLoggedInUser();
        profileMenu.setFields(user.getUsername(), user.getNickName(), user.getEmail(),
                user.getRank(), user.getHighestScore(), user.getGameHistories().size(),
                user.getWins(), user.getLosses(), user.getDraws());
        App.setCurrentMenuScene(MenuScene.PROFILE_SCENE);
        App.getPrimaryStage().setScene(MenuScene.PROFILE_SCENE.getScene());
    }
}