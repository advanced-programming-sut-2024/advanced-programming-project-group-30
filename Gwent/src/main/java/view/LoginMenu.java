package view;

import controller.LoginMenuController;
import enums.MenuScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.App;

import java.util.Scanner;

public class LoginMenu implements Menu{
    LoginMenuController controller = new LoginMenuController();

    public void run(){
    }

    @FXML
    private void goToRegisterMenu() {
        controller.enterRegisterMenu();
    }
}
