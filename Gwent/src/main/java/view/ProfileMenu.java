package view;

import controller.ProfileMenuController;
import controller.UserInformationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.App;
import model.Result;

import java.util.Objects;
import java.util.Scanner;

public class ProfileMenu extends Menu{
    private final UserInformationController userInformationController = new UserInformationController();
    private final ProfileMenuController profileMenuController = new ProfileMenuController(this);
    @FXML
    private Button changePasswordButton, editUsernameButton, editNicknameButton, editEmailButton;
    @FXML
    private PasswordField oldPassword, newPassword;
    @FXML
    private Pane changePasswordPane;
    @FXML
    private TextField usernameTextField, nicknameTextField, emailTextField, gameHistoryCount;
    @FXML
    private Label usernameErrorField, emailErrorField, nicknameErrorField, gameHistoryNumberErrorField;
    @FXML
    private Button setNewUsernameButton, setNewNicknameButton, setNewEmailButton;
    @FXML
    private Button cancelChangingUsernameButton, cancelChangingNicknameButton, cancelChangingEmailButton;
    @FXML
    private Label rank, highestScore, gameCount, wins, losses, draws;
    @FXML
    private VBox editInformationBox;
    @FXML
    private Label newPasswordError;
    @FXML
    private Label passwordConfirmationErrorField;
    @FXML
    private ScrollPane gameHistoryScrollPane;
    @FXML
    private VBox scrollPaneVbox;
    @FXML
    private Text gameHistoryText;

    @Override
    public void run(){

    }
    @FXML
    public void initialize(){
        gameHistoryCount.setFocusTraversable(false);
        usernameTextField.textProperty().addListener((observableValue, s, t1) -> {
            if (!editUsernameButton.isVisible()) {
                usernameErrorField.setVisible(true);
                usernameErrorField.setText(userInformationController.checkUsername(usernameTextField.getText()).toString());
            } else usernameErrorField.setText("");
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

        Text text = new Text(profileMenuController.showDefaultGameHistory().toString());
        scrollPaneVbox.getChildren().add(text);
        gameHistoryScrollPane.setContent(scrollPaneVbox);
//        gameHistoryCount.textProperty().addListener(((observableValue, s, t1) -> {
//            gameHistoryNumberErrorField.setText(profileMenuController.checkGameHistory(gameHistoryCount.getText()).toString());
//        }));
//        Text text = new Text("Game 1: 1000\nGame 2: 900\nGame 3: 800\nGame 4: 700\nGame 5: 600\nGame 6: 500\nGame 7: 400\nGame 8: 300\nGame 9: 200\nGame 10: 100");
//        gameHistory.getChildren().add(text);
//        username.textProperty().addListener((observable) -> {
//            username.setText(App.getLoggedInUser().getUsername());
//        });
        //TODO: add listener for prompt texts
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
    private void editUsername() {
        usernameTextField.setEditable(true);
        setNewUsernameButton.setVisible(true);
        cancelChangingUsernameButton.setVisible(true);
        editUsernameButton.setVisible(false);
        usernameTextField.setPromptText("Enter new username");
    }


    @FXML
    private void editPassword(){
        changePasswordButton.setVisible(false);
        editInformationBox.setVisible(false);
        changePasswordPane.setVisible(true);

    }
    @FXML
    private void editNickname(){
        nicknameTextField.setEditable(true);
        nicknameTextField.setPromptText("Enter new nickname");
        editNicknameButton.setVisible(false);
        setNewNicknameButton.setVisible(true);
        cancelChangingNicknameButton.setVisible(true);
    }
    @FXML
    private void editEmail(){
        emailTextField.setEditable(true);
        emailTextField.setPromptText("Enter new email");
        editEmailButton.setVisible(false);
        setNewEmailButton.setVisible(true);
        cancelChangingEmailButton.setVisible(true);
    }
    @FXML
    private void checkAndSetNewUsername() {
        Result result = userInformationController.checkUsername(usernameTextField.getText());
        if (!result.isSuccessful()){
            usernameErrorField.setText(result.toString());
        } else {
            changeUsername(usernameTextField.getText());
            usernameTextField.setEditable(false);
            setNewUsernameButton.setVisible(false);
            cancelChangingUsernameButton.setVisible(false);
            editUsernameButton.setVisible(true);
        }
    }
    @FXML
    private void checkAndSetNewNickname(){
        Result result = userInformationController.checkNickname(nicknameTextField.getText());
        if (!result.isSuccessful()){
            nicknameErrorField.setText(result.toString());
        } else {
            changeNickname(nicknameTextField.getText());
            nicknameTextField.setEditable(false);
            setNewNicknameButton.setVisible(false);
            cancelChangingNicknameButton.setVisible(false);
            editNicknameButton.setVisible(true);
        }

    }
    @FXML
    private void checkAndSetNewEmail(){
        Result result = userInformationController.checkEmail(emailTextField.getText());
        if (!result.isSuccessful()){
            emailErrorField.setText(result.toString());
        } else {
            changeEmail(emailTextField.getText());
            emailTextField.setEditable(false);
            setNewEmailButton.setVisible(false);
            cancelChangingEmailButton.setVisible(false);
            editEmailButton.setVisible(true);
        }

    }
    @FXML
    private void cancelChangingUsername() {
        setNewUsernameButton.setVisible(false);
        usernameErrorField.setVisible(false);
        cancelChangingUsernameButton.setVisible(false);
        usernameTextField.setEditable(false);
        editUsernameButton.setVisible(true);
        usernameTextField.clear();
    }
    @FXML
    private void cancelChangingEmail(){
        setNewEmailButton.setVisible(false);
        emailErrorField.setVisible(false);
        emailTextField.setEditable(false);
        cancelChangingEmailButton.setVisible(false);
        editEmailButton.setVisible(true);
        emailTextField.clear();

    }
    @FXML
    private void cancelChangingNickname(){
        setNewNicknameButton.setVisible(false);
        nicknameTextField.setEditable(false);
        nicknameErrorField.setVisible(false);
        cancelChangingNicknameButton.setVisible(false);
        editNicknameButton.setVisible(true);
        nicknameTextField.clear();
    }
    public void changeUsername(String newUsername){
            App.getLoggedInUser().setUsername(newUsername);
    }
    public void changeNickname(String newNickname){
        App.getLoggedInUser().setNickName(newNickname);
    }
    public void changeEmail(String newEmail){
        App.getLoggedInUser().setEmail(newEmail);
    }
    public void changePassword(String newPassword){
        App.getLoggedInUser().setPassword(newPassword);
    }

    @FXML
    private void setNewPassword() {
        Result result = userInformationController.checkPassword(newPassword.getText());
        if (!result.isSuccessful()){
            passwordConfirmationErrorField.setText(result.toString());
        } else {
            changePassword(newPassword.getText());
            changePasswordPane.setVisible(false);
            editInformationBox.setVisible(true);
            changePasswordButton.setVisible(true);
        }
    }
    @FXML
    private void backToEditInformation(){
        editInformationBox.setVisible(true);
        changePasswordButton.setVisible(true);
        changePasswordPane.setVisible(false);
    }
    @FXML
    private void checkAndSetGameHistoryCount() {
        Result result = profileMenuController.checkGameHistory(gameHistoryCount.getText());
        if (!result.isSuccessful()){
            gameHistoryNumberErrorField.setText(result.toString());
        } else {
            Text text = new Text(profileMenuController.showGameHistoryByUserRequest(gameHistoryCount.getText()).toString());
            scrollPaneVbox.getChildren().add(text);
            gameHistoryScrollPane.setContent(scrollPaneVbox);
            gameHistoryNumberErrorField.setText("");
        }
    }
}
