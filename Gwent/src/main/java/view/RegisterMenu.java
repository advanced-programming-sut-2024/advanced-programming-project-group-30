package view;

import controller.RegisterMenuController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.App;
import model.Result;
import network.Client;
import network.ClientMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RegisterMenu implements Menu {
    private final Client client = ClientView.getClient();

    @FXML
    private Pane firstPage;
    @FXML
    private Pane secondPage;
    @FXML
    private TextField username;
    @FXML
    private Label usernameError;
    @FXML
    private PasswordField password;
    @FXML
    private TextField shownPassword;
    @FXML
    private ToggleButton showPasswordButton;
    @FXML
    private Label passwordError;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private TextField shownPasswordConfirm;
    @FXML
    private ToggleButton showPasswordConfirmButton;
    @FXML
    private Label passwordConfirmError;
    @FXML
    private TextField nickname;
    @FXML
    private Label nicknameError;
    @FXML
    private TextField email;
    @FXML
    private Label emailError;
    @FXML
    private Label continueError;
    @FXML
    private ChoiceBox questions;
    @FXML
    private Label completeError;
    @FXML
    private TextField answer;


    @FXML
    public void initialize() {
        questions.setItems(FXCollections.observableArrayList(SecurityQuestion.values()));
        username.textProperty().addListener((Void) -> setUsernameError());
        password.textProperty().addListener((Void) -> handlePasswordFieldEvent());
        shownPassword.textProperty().addListener((Void) -> {
            if (password.isDisable()) password.setText(shownPassword.getText());
        });
        passwordConfirm.textProperty().addListener((Void) -> handlePasswordConfirmEvent());
        shownPasswordConfirm.textProperty().addListener((Void) -> {
            if (passwordConfirm.isDisable()) passwordConfirm.setText(shownPasswordConfirm.getText());
        });
        nickname.textProperty().addListener((Void) -> setNicknameError());
        email.textProperty().addListener((Void) -> setEmailError());
    }

    @FXML
    private void backToFirstPage() {
        changePage(secondPage, firstPage);
        completeError.setText("");
    }

    @FXML
    private void togglePassword() {
        showHidePassword(showPasswordButton.isSelected(), password, shownPassword);
    }

    @FXML
    private void togglePasswordConfirm(ActionEvent event) {
        showHidePassword(showPasswordConfirmButton.isSelected(), passwordConfirm, shownPasswordConfirm);
    }

    @FXML
    private void setRandomPassword() {
        ClientMessage clientMessage = new ClientMessage("RegisterController", "createRandomPassword", null);
        client.sendMessageToServer(clientMessage);
        String randomPassword = (String) client.getLastServerData(String.class);
        password.setText(randomPassword);
        shownPassword.setText(randomPassword);
        passwordConfirm.setText(randomPassword);
        shownPasswordConfirm.setText(randomPassword);
    }

    @FXML
    private void goToLoginMenu() {
        resetFields();
        App.getSceneManager().goToLoginMenu();
    }

    @FXML
    private void continueSignUp() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController", "checkInformation",
                new ArrayList<>(List.of(new String[]{username.getText(), password.getText(), passwordConfirm.getText(),
                        nickname.getText(), email.getText()})));
        client.sendMessageToServer(clientMessage);
        Result result = (Result) client.getLastServerData(Result.class);
        if (result.isNotSuccessful()) continueError.setText(result.toString());
        else {
            changePage(firstPage, secondPage);
            continueError.setText("");
        }
    }

    @FXML
    private void signup() {
        ClientMessage clientMessage = new ClientMessage("RegisterController", "checkSecurityQuestion",
                new ArrayList<>(List.of(new String[]{questions.getValue().toString(), answer.getText()})));
        client.sendMessageToServer(clientMessage);
        Result result = (Result) client.getLastServerData(Result.class);
        if (result.isNotSuccessful()) completeError.setText(result.toString());
        else {
            clientMessage = new ClientMessage("RegisterController", "register",
                    new ArrayList<>(List.of(new String[]{username.getText(), password.getText(), nickname.getText(),
                            email.getText(), questions.getValue().toString(), answer.getText()})));
            client.sendMessageToServer(clientMessage);
            goToLoginMenu();
        }
    }

    private void setUsernameError() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController",
                "checkUsername", new ArrayList<>(Collections.singleton(username.getText())));
        client.sendMessageToServer(clientMessage);
        usernameError.setText(client.getLastServerData(Result.class).toString());
    }

    private void handlePasswordFieldEvent() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController",
                "checkPassword", new ArrayList<>(Collections.singleton(password.getText())));
        client.sendMessageToServer(clientMessage);
        passwordError.setText(client.getLastServerData(Result.class).toString());
        if (shownPassword.isDisable()) shownPassword.setText(password.getText());
    }

    private void handlePasswordConfirmEvent() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController",
                "checkPasswordConfirm", new ArrayList<>(Collections.singleton(passwordConfirm.getText())));
        client.sendMessageToServer(clientMessage);
        passwordConfirmError.setText(client.getLastServerData(Result.class).toString());
        if (shownPasswordConfirm.isDisable()) shownPasswordConfirm.setText(passwordConfirm.getText());
    }

    private void setNicknameError() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController",
                "checkNickname", new ArrayList<>(Collections.singleton(nickname.getText())));
        client.sendMessageToServer(clientMessage);
        nicknameError.setText(client.getLastServerData(Result.class).toString());
    }

    private void setEmailError() {
        ClientMessage clientMessage = new ClientMessage("UserInformationController",
                "checkEmail", new ArrayList<>(Collections.singleton(email.getText())));
        client.sendMessageToServer(clientMessage);
        emailError.setText(client.getLastServerData(Result.class).toString());
    }

    private void changePage(Pane previousPage, Pane destinationPage) {
        previousPage.setDisable(true);
        previousPage.setVisible(false);
        destinationPage.setDisable(false);
        destinationPage.setVisible(true);
    }

    private void resetFields() {
        backToFirstPage();
        username.setText("");
        showHidePassword(false, password, shownPassword);
        showPasswordButton.setSelected(false);
        password.setText("");
        showHidePassword(false, passwordConfirm, shownPasswordConfirm);
        showPasswordConfirmButton.setSelected(false);
        passwordConfirm.setText("");
        nickname.setText("");
        email.setText("");
        continueError.setText("");
        questions.setValue("choose a question");
        answer.setText("");
        completeError.setText("");
        username.requestFocus();
    }

    private void showHidePassword(boolean isHide, PasswordField password, TextField shownPassword) {
        password.setDisable(isHide);
        password.setVisible(!isHide);
        shownPassword.setDisable(!isHide);
        shownPassword.setVisible(isHide);
    }
}