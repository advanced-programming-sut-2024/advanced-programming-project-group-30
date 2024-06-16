package view;

import controller.RegisterMenuController;
import controller.UserInformationController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Result;


public class RegisterMenu implements Menu {
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
    private Label passwordError;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private TextField shownPasswordConfirm;
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

    public void run() {
    }

    @FXML
    public void initialize() {
        questions.setItems(FXCollections.observableArrayList(SecurityQuestion.values()));
        username.textProperty().addListener((Void) -> {
            usernameError.setText(userInformationController.checkUsername(username.getText()).toString());
            usernameError.setStyle("");
        });
        password.textProperty().addListener((Void) -> {
            passwordError.setText(userInformationController.checkPassword(password.getText()).toString());
            passwordError.setStyle("");
            if (shownPassword.isDisable()) shownPassword.setText(password.getText());
        });
        shownPassword.textProperty().addListener((Void) -> {
            if (password.isDisable()) password.setText(shownPassword.getText());
        });
        passwordConfirm.textProperty().addListener((Void) -> {
            passwordConfirmError.setText(userInformationController.checkPasswordConfirm(passwordConfirm.getText()).toString());
            passwordConfirmError.setStyle("");
            if (shownPasswordConfirm.isDisable()) shownPasswordConfirm.setText(passwordConfirm.getText());
        });
        shownPasswordConfirm.textProperty().addListener((Void) -> {
            if (passwordConfirm.isDisable()) passwordConfirm.setText(shownPasswordConfirm.getText());
        });
        nickname.textProperty().addListener((Void) -> {
            nicknameError.setText(userInformationController.checkNickname(nickname.getText()).toString());
            nicknameError.setStyle("");
        });
        email.textProperty().addListener((Void) -> {
            emailError.setText(userInformationController.checkEmail(email.getText()).toString());
            emailError.setStyle("");
        });
    }

    @FXML
    private void togglePassword(ActionEvent event) {
        boolean passwordIsShown = !((ToggleButton) event.getSource()).isSelected();
        password.setDisable(!passwordIsShown);
        password.setVisible(passwordIsShown);
        shownPassword.setDisable(passwordIsShown);
        shownPassword.setVisible(!passwordIsShown);
    }

    @FXML
    private void togglePasswordConfirm(ActionEvent event) {
        boolean passwordConfirmIsShown = !((ToggleButton) event.getSource()).isSelected();
        passwordConfirm.setDisable(!passwordConfirmIsShown);
        passwordConfirm.setVisible(passwordConfirmIsShown);
        shownPasswordConfirm.setDisable(passwordConfirmIsShown);
        shownPasswordConfirm.setVisible(!passwordConfirmIsShown);
    }

    @FXML
    private void goToLoginMenu() {
        resetFields();
        registerController.enterLoginMenu();
    }

    @FXML
    private void continueSignUp() {
        Result result = userInformationController.checkInformation(username.getText(), password.getText(), passwordConfirm.getText(), nickname.getText(), email.getText());
        if (!result.isSuccessful()) {
            continueError.setText(result.toString());
            return;
        }
        firstPage.setDisable(true);
        firstPage.setVisible(false);
        secondPage.setDisable(false);
        secondPage.setVisible(true);
        continueError.setText("");
    }

    @FXML
    private void backToFirstPage() {
        secondPage.setDisable(true);
        secondPage.setVisible(false);
        firstPage.setDisable(false);
        firstPage.setVisible(true);
        resetSecondPageFields();
    }

    @FXML
    private void signup() {
        Result result = registerController.checkSecurityQuestion(questions.getValue().toString(), answer.getText());
        if (!result.isSuccessful()) {
            completeError.setText(result.toString());
            return;
        }
        registerController.register(username.getText(), password.getText(), nickname.getText(), email.getText(), questions.getValue().toString(), answer.getText());
        goToLoginMenu();
    }

    private void resetFields() {
        username.setText("");
        password.setText("");
        passwordConfirm.setText("");
        nickname.setText("");
        email.setText("");
        continueError.setText("");
        resetSecondPageFields();
    }

    private void resetSecondPageFields() {
        questions.setValue("choose a question");
        answer.setText("");
    }
}