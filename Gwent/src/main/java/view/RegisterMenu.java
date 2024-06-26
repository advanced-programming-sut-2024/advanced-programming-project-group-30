package view;

import controller.RegisterMenuController;
import controller.UserInformationController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.App;
import model.Result;


public class RegisterMenu implements Menu{
    private final RegisterMenuController registerController = new RegisterMenuController();
    private final UserInformationController userInformationController = new UserInformationController();

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
        username.textProperty().addListener((Void) ->
                usernameError.setText(userInformationController.checkUsername(username.getText()).toString()));
        password.textProperty().addListener((Void) -> {
            passwordError.setText(userInformationController.checkPassword(password.getText()).toString());
            if (shownPassword.isDisable()) shownPassword.setText(password.getText());
        });
        shownPassword.textProperty().addListener((Void) -> {
            if (password.isDisable()) password.setText(shownPassword.getText());
        });
        passwordConfirm.textProperty().addListener((Void) -> {
            passwordConfirmError.setText(userInformationController.checkPasswordConfirm(passwordConfirm.getText()).toString());
            if (shownPasswordConfirm.isDisable()) shownPasswordConfirm.setText(passwordConfirm.getText());
        });
        shownPasswordConfirm.textProperty().addListener((Void) -> {
            if (passwordConfirm.isDisable()) passwordConfirm.setText(shownPasswordConfirm.getText());
        });
        nickname.textProperty().addListener((Void) ->
                nicknameError.setText(userInformationController.checkNickname(nickname.getText()).toString()));
        email.textProperty().addListener((Void) -> emailError.setText(userInformationController.checkEmail(email.getText()).toString()));
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
        String randomPassword = registerController.createRandomPassword();
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
        Result result = userInformationController.checkInformation(username.getText(), password.getText(),
                passwordConfirm.getText(), nickname.getText(), email.getText());
        if (result.isNotSuccessful()) continueError.setText(result.toString());
        else {
            changePage(firstPage, secondPage);
            continueError.setText("");
        }
    }

    @FXML
    private void signup() {
        Result result = registerController.checkSecurityQuestion(questions.getValue().toString(), answer.getText());
        if (result.isNotSuccessful()) completeError.setText(result.toString());
        else {
            registerController.register(username.getText(), password.getText(), nickname.getText(), email.getText(),
                    questions.getValue().toString(), answer.getText());
            goToLoginMenu();
        }
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