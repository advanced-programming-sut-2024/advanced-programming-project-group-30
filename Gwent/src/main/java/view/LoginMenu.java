package view;

import controller.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.App;
import model.Result;

public class LoginMenu implements Menu{
    private final LoginMenuController controller = new LoginMenuController();
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
        username.textProperty().addListener((Void) ->
                usernameError.setText(controller.getEmptyError(username.getText(), "username").toString()));
        password.textProperty().addListener((Void) -> {
            passwordError.setText(controller.getEmptyError(password.getText(), "password").toString());
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
        Result result = controller.checkInformationForLogin(username.getText(), password.getText());
        if (result.isNotSuccessful()) {
            loginError.setText(result.toString());
            return;
        }
        controller.login(username.getText(), rememberMe.isSelected());
        resetFields();
    }

    @FXML
    private void goToRegisterMenu() {
        App.getSceneManager().goToRegisterMenu();
        resetFields();
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