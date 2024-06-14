package view;

import controller.RegisterMenuController;
import controller.UserInformationController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Result;

import java.util.Scanner;

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
    private Label passwordError;
    @FXML
    private PasswordField passwordConfirm;
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
    private ChoiceBox questions;

    public void run(Scanner scanner) {
    }

    @FXML
    public void initialize() {
        for (SecurityQuestion q : SecurityQuestion.values())
            questions.getItems().add(q.getQuestionText());

        username.textProperty().addListener((Void) -> {
            usernameError.setText(userInformationController.checkUsername(username.getText()).toString());
            usernameError.setStyle("");
        });
        password.textProperty().addListener((Void) -> {
            passwordError.setText(userInformationController.checkPassword(password.getText()).toString());
            passwordError.setStyle("");
        });
        passwordConfirm.textProperty().addListener((Void) -> {
            passwordConfirmError.setText(userInformationController.checkPasswordConfirm(passwordConfirm.getText()).toString());
            passwordConfirmError.setStyle("");
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
    private void continueSignUp() {
        Result result = userInformationController.checkInformation(username.getText(), password.getText(), passwordConfirm.getText(), nickname.getText(), email.getText());
        if (!result.isSuccessful()) {
            return;
        }
        firstPage.setDisable(true);
        firstPage.setVisible(false);
        secondPage.setDisable(false);
        secondPage.setVisible(true);
    }


}
