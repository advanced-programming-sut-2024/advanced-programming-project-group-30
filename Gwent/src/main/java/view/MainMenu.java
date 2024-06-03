package view;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Scanner;

public class MainMenu extends Menu{
    private static MainMenuController mainMenuController;
    @FXML
    public Label username;
    @FXML
    public Label nickname;


    @FXML
    public void goToProfileMenu(){
        mainMenuController.enterProfileMenu();
    }

    @FXML
    public void goToPreGameMenu(){
        mainMenuController.enterGameMenu();
    }

    @FXML
    public void logout(){
        mainMenuController.logout();
    }
}
