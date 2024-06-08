package view;

import controller.UserInformationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.App;

import java.util.Scanner;

public class ProfileMenu extends Menu{
    private final UserInformationController userInformationController = new UserInformationController();
    @FXML
    public TextField username;
    @FXML
    public TextField nickname;
    @FXML
    public TextField email;
    @FXML
    public Label usernameErrorField;

    @Override
    public void run(){

    }
    @FXML
    public void initialize(){
//        username.setPromptText(App.getLoggedInUser().getUsername());
//        nickname.setPromptText(App.getLoggedInUser().getNickName());
//        email.setPromptText(App.getLoggedInUser().getEmail());
        username.textProperty().addListener(Void ->{
            usernameErrorField.setText(userInformationController.checkUsername(username.getText()).toString());
        });
    }


    @FXML
    public void editProfile() {
    }
    @FXML
    public void saveChanges() {
    }
}
