package view;

import controller.ForgetPasswordMenuController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Result;

public class ForgetPasswordMenu {
    private final ForgetPasswordMenuController controller = new ForgetPasswordMenuController();

    @FXML
    private Pane firstPage;
    @FXML
    private TextField username;
    @FXML
    private Label continueError;
    @FXML
    private Pane secondPage;
    @FXML
    private ChoiceBox questions;
    @FXML
    private TextField answer;
    @FXML
    private Label getPasswordError;
    @FXML
    private Pane showPasswordPane;
    @FXML
    private TextField password;

    @FXML
    public void initialize() {
        questions.setItems(FXCollections.observableArrayList(SecurityQuestion.values()));
    }

    @FXML
    private void goToLoginMenu() {
        controller.goToLoginMenu();
        resetFields();
    }

    @FXML
    private void continueForgetPassword() {
        Result result = controller.checkUsername(username.getText());
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
        getPasswordError.setText("");
    }

    @FXML
    private void getPassword() {
        Result result = controller.getPassword(username.getText(), questions.getValue().toString(), answer.getText());
        if (!result.isSuccessful()) {
            getPasswordError.setText(result.toString());
            return;
        }
        showPassword(result.toString());
    }

    private void showPassword(String password) {
        this.password.setText(password);
        secondPage.setDisable(true);
        secondPage.setVisible(false);
        showPasswordPane.setVisible(true);
        showPasswordPane.setDisable(false);
        showPasswordPane.requestFocus();
    }

    private void resetFields() {
        password.setText("");
        showPasswordPane.setDisable(true);
        showPasswordPane.setVisible(false);
        backToFirstPage();
        username.setText("");
        continueError.setText("");
        questions.setValue("choose a question");
        answer.setText("");
        getPasswordError.setText("");
        username.requestFocus();
    }
}