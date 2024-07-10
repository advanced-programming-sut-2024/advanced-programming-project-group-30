package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.App;
import model.Result;
import network.Client;
import network.ClientMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoginMenu implements Menu {
    private final Client client = ClientView.getClient();
    @FXML
    private Label usernameError;
    @FXML
    private TextField username;
    @FXML
    private Label passwordError;
    @FXML
    private PasswordField password;
    @FXML
    private TextField shownPassword;
    @FXML
    private ToggleButton showPasswordButton;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Label loginError;

    @FXML
    public void initialize() {
        username.textProperty().addListener((Void) -> setUsernameError());
        password.textProperty().addListener((Void) -> {
            setPasswordError();
            if (shownPassword.isDisable()) shownPassword.setText(password.getText());
        });
        shownPassword.textProperty().addListener((Void) -> {
            if (password.isDisable()) password.setText(shownPassword.getText());
        });
    }

    @FXML
    private void togglePassword() {
        showHidePassword(showPasswordButton.isSelected());
    }

    @FXML
    private void goToForgetPasswordMenu() {
        App.getSceneManager().goToForgetPasswordMenu();
        resetFields();
    }

    @FXML
    private void login() {
        ClientMessage clientMessage = new ClientMessage("LoginController", "checkInformationForLogin",
                new ArrayList<>(List.of(new String[]{username.getText(), password.getText()})));
        client.sendMessageToServer(clientMessage);
        Result result = (Result) client.getLastServerData(Result.class);
        if (result.isNotSuccessful()) {
            loginError.setText(result.toString());
            return;
        }
        clientMessage = new ClientMessage("LoginController", "login",
                new ArrayList<>(Collections.singleton(username.getText())));
        client.sendMessageToServer(clientMessage);
        String[] userData = (String[]) client.getLastServerData(String[].class);
        client.login(userData[0], userData[1], userData[2], rememberMe.isSelected());
        App.getSceneManager().goToMainMenu(userData[0], userData[1]);
        resetFields();
    }

    @FXML
    private void goToRegisterMenu() {
        App.getSceneManager().goToRegisterMenu();
        resetFields();
    }

    private void setPasswordError() {
        ClientMessage clientMessage = new ClientMessage("LoginController", "getEmptyError",
                new ArrayList<>(List.of(new String[]{password.getText(), "password"})));
        client.sendMessageToServer(clientMessage);
        passwordError.setText(client.getLastServerData(Result.class).toString());
    }

    private void setUsernameError() {
        ClientMessage clientMessage = new ClientMessage("LoginController", "getEmptyError",
                new ArrayList<>(List.of(new String[]{username.getText(), "username"})));
        client.sendMessageToServer(clientMessage);
        usernameError.setText(client.getLastServerData(Result.class).toString());
    }

    private void resetFields() {
        username.setText("");
        showHidePassword(false);
        showPasswordButton.setSelected(false);
        password.setText("");
        showHidePassword(false);
        rememberMe.setSelected(false);
        loginError.setText("");
        username.requestFocus();
    }

    private void showHidePassword(boolean isHide) {
        password.setDisable(isHide);
        password.setVisible(!isHide);
        shownPassword.setDisable(!isHide);
        shownPassword.setVisible(isHide);
    }
}