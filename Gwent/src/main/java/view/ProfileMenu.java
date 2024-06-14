package view;

import controller.UserInformationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.App;

import java.util.Scanner;

public class ProfileMenu extends Menu{
    private final UserInformationController userInformationController = new UserInformationController();
    @FXML
    public Button changePasswordButton;
    @FXML
    public Button changeUsernameButton;
    @FXML
    public Button changeNicknameButton;
    @FXML
    public Button changeEmailButton;
    @FXML
    public PasswordField oldPassword;
    @FXML
    public PasswordField newPassword;
    @FXML
    public BorderPane changePasswordPane;
    @FXML
    public Label newPasswordError;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField nicknameTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public Label usernameErrorField;
    @FXML
    public Label emailErrorField;
    @FXML
    public Label nicknameErrorField;
    @FXML
    public Button setNewUsernameButton;
    @FXML
    public Button setNewNicknameButton;
    @FXML
    public Button setNewEmailButton;
    @FXML
    public Button cancelChangingUsernameButton;
    @FXML
    public Button cancelChangingNicknameButton;
    @FXML
    public Button cancelChangingEmailButton;
    public BorderPane pane;
    @FXML
    public ImageView pencileImage;

    @Override
    public void run(){

    }
    @FXML
    public void initialize(){
//        usernameTextField.setPromptText(App.getLoggedInUser().getUsername());
//        nicknameTextField.setPromptText(App.getLoggedInUser().getNickName());
//        emailTextField.setPromptText(App.getLoggedInUser().getEmail());
        usernameTextField.textProperty().addListener((observableValue, s, t1) -> {
            usernameErrorField.setText(userInformationController.checkUsername(usernameTextField.getText()).toString());
        });
        nicknameTextField.textProperty().addListener((observableValue, s, t1) -> {
            nicknameErrorField.setText(userInformationController.checkNickname(nicknameTextField.getText()).toString());
        });
        emailTextField.textProperty().addListener((observableValue, s, t1) -> {
            emailErrorField.setText(userInformationController.checkEmail(emailTextField.getText()).toString());
        });
        newPassword.textProperty().addListener((observableValue, s, t1) -> {
            newPasswordError.setText(userInformationController.checkPassword(newPassword.getText()).toString());
        });


//        username.setPromptText(App.getLoggedInUser().getUsername());
//        nickname.setPromptText(App.getLoggedInUser().getNickName());
//        email.setPromptText(App.getLoggedInUser().getEmail());
//        userInfoTabUsername.setText(App.getLoggedInUser().getUsername());
//        userInfoTabNickname.setText(App.getLoggedInUser().getNickName());
//        rank.setText(String.valueOf(App.getLoggedInUser().getRank()));
//        wins.setText(String.valueOf("Wins: " + App.getLoggedInUser().getWins()));
//        losses.setText(String.valueOf("Losses" + App.getLoggedInUser().getLosses()));
//        draws.setText(String.valueOf("Draws" + App.getLoggedInUser().getDraws()));
        //numberOfPlays.setText(String.valueOf(App.getLoggedInUser().getGameHistories().size()));
    }
    @FXML
    public void changeUsername() {
        usernameTextField.setEditable(true);
        usernameTextField.setPromptText("Enter new username");
        changeUsernameButton.setVisible(false);
        setNewUsernameButton.setVisible(true);
        cancelChangingUsernameButton.setVisible(true);

    }
    @FXML
    public void changePassword(){
        changePasswordButton.setVisible(false);
        changePasswordPane.setVisible(true);
    }
    @FXML
    public void changeNickname(){
        nicknameTextField.setEditable(true);
        nicknameTextField.setPromptText("Enter new nickname");
        changeNicknameButton.setVisible(false);
        setNewNicknameButton.setVisible(true);
        cancelChangingNicknameButton.setVisible(true);
    }
    @FXML
    public void changeEmail(){
        emailTextField.setEditable(true);
        emailTextField.setPromptText("Enter new email");
        changeEmailButton.setVisible(false);
        setNewEmailButton.setVisible(true);
        cancelChangingEmailButton.setVisible(true);
    }
    @FXML
    public void setNewUsername() {
    }
    @FXML
    public void setNewNickname(){

    }
    @FXML
    public void setNewEmail(){

    }
    @FXML
    public void cancelChangingUsername() {
        setNewUsernameButton.setVisible(false);
        cancelChangingUsernameButton.setVisible(false);
        changeUsernameButton.setVisible(true);
//        usernameTextField.setPromptText(App.getLoggedInUser().getUsername());
    }
    @FXML
    public void cancelChangingEmail(){
        setNewEmailButton.setVisible(false);
        cancelChangingEmailButton.setVisible(false);
        changeEmailButton.setVisible(true);
        //       emailTextField.setPromptText(App.getLoggedInUser().getEmail());

    }
    @FXML
    public void cancelChangingNickname(){
        setNewNicknameButton.setVisible(false);
        cancelChangingNicknameButton.setVisible(false);
        changeNicknameButton.setVisible(true);
        //nicknameTextField.setPromptText(App.getLoggedInUser().getNickName());
    }
    public void changeUsername(String newUsername){
    }
    public void changeNickname(String newNickname){
    }
    public void changeEmail(String newEmail){
    }

}
