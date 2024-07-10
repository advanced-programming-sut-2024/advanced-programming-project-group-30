package controller;

import enums.MenuScene;
import model.App;
import model.Game;
import model.PregameData;
import model.User;
import view.MainMenu;
import view.PregameMenu;
import view.ProfileMenu;

public class SceneManager {
    public Double getScale(double stageWidth, double stageHeight, double paneWidth, double paneHeight, double scaleCoef) {
        if (stageWidth < 0.1 || stageHeight < 0.1 || paneWidth < 0.1 || paneHeight < 0.1) return null;
        double scaleX = stageWidth / paneWidth;
        double scaleY = stageHeight / paneHeight;
        return Math.min(scaleY, scaleX) * scaleCoef;
    }

    public void goToLoginMenu() {
        stageSizeData sizeData = getStageSizeData();
        App.setCurrentMenuScene(MenuScene.LOGIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.LOGIN_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToRegisterMenu() {
        stageSizeData sizeData = getStageSizeData();
        App.setCurrentMenuScene(MenuScene.REGISTER_SCENE);
        App.getPrimaryStage().setScene(MenuScene.REGISTER_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToForgetPasswordMenu() {
        stageSizeData sizeData = getStageSizeData();
        App.setCurrentMenuScene(MenuScene.FORGET_PASSWORD_SCENE);
        App.getPrimaryStage().setScene(MenuScene.FORGET_PASSWORD_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToMainMenu(String username, String nickname) {
        stageSizeData sizeData = getStageSizeData();
        MainMenu mainMenu = (MainMenu) MenuScene.MAIN_SCENE.getMenu();
        mainMenu.setUserInfo(username, nickname);
        App.setCurrentMenuScene(MenuScene.MAIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.MAIN_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToProfileMenu() {
        stageSizeData sizeData = getStageSizeData();
        ProfileMenu profileMenu = (ProfileMenu) MenuScene.PROFILE_SCENE.getMenu();
//        User user = App.getLoggedInUsersUsername();
//        profileMenu.setFields(user.getUsername(), user.getNickName(), user.getEmail(),
//                user.getRank(), user.getHighestScore(), user.getGameHistories().size(),
//                user.getWins(), user.getLosses(), user.getDraws());
        App.setCurrentMenuScene(MenuScene.PROFILE_SCENE);
        App.getPrimaryStage().setScene(MenuScene.PROFILE_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToChooseOpponentMenu() {
        stageSizeData sizeData = getStageSizeData();
        App.setCurrentMenuScene(MenuScene.CHOOSE_OPPONENT_SCENE);
        App.getPrimaryStage().setScene(MenuScene.CHOOSE_OPPONENT_SCENE.getScene());
        setupStage(sizeData);
    }

    public void goToPregameMenu(PregameData pregameData) {
        PregameMenu pregameMenu = (PregameMenu) MenuScene.PREGAME_SCENE.getMenu();
        pregameMenu.setup(pregameData);
        App.setCurrentMenuScene(MenuScene.PREGAME_SCENE);
        App.getPrimaryStage().setScene(MenuScene.PREGAME_SCENE.getScene());
        App.getPrimaryStage().sizeToScene();
        App.getPrimaryStage().centerOnScreen();
    }

    public void goToGame(Game game) {
//        App.setCurrentGame(game);
//        GameMenu menu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
//        menu.setup();
//        App.setCurrentMenuScene(MenuScene.GAME_SCENE);
//        App.getPrimaryStage().setScene(MenuScene.GAME_SCENE.getScene());
    }

    private stageSizeData getStageSizeData() {
        boolean isMaximized = App.getPrimaryStage().isMaximized();
        double height = App.getPrimaryStage().getHeight();
        double width = App.getPrimaryStage().getWidth();
        return new stageSizeData(isMaximized, height, width);
    }

    private void setupStage(stageSizeData sizeData) {
        App.getPrimaryStage().setHeight(sizeData.height());
        App.getPrimaryStage().setWidth(sizeData.width());
        if (sizeData.isMaximized()) App.getPrimaryStage().setMaximized(true);
    }
}

record stageSizeData(boolean isMaximized, double height, double width) {
}