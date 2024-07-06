package controller;

import enums.MenuScene;
import model.App;
import view.MainMenu;

public class SceneManager {
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

    public void goToMainMenu() {
        stageSizeData sizeData = getStageSizeData();
        MainMenu mainMenu = (MainMenu) MenuScene.MAIN_SCENE.getMenu();
        mainMenu.setFields(App.getLoggedInUser().getUsername(), App.getLoggedInUser().getNickName());
        App.setCurrentMenuScene(MenuScene.MAIN_SCENE);
        App.getPrimaryStage().setScene(MenuScene.MAIN_SCENE.getScene());
        setupStage(sizeData);
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