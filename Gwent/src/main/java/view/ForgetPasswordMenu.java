package view;

import controller.ForgetPasswordMenuController;
import enums.SecurityQuestion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.App;
import model.Result;

public class ForgetPasswordMenu implements Menu{
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
        App.getSceneManager().goToLoginMenu();
        resetFields();
    }

    @FXML
    private void continueForgetPassword() {
        Result result = controller.checkUsername(username.getText());
        if (result.isNotSuccessful()) {
            continueError.setText(result.toString());
            return;
        }
        changePage(firstPage, secondPage);
        continueError.setText("");
    }

    @FXML
    private void backToFirstPage() {
        changePage(secondPage, firstPage);
        getPasswordError.setText("");
    }

    @FXML
    private void getPassword() {
        Result result = controller.getPassword(username.getText(), questions.getValue().toString(), answer.getText());
        if (result.isNotSuccessful()) getPasswordError.setText(result.toString());
        else showPassword(result.toString());
    }

    private void showPassword(String password) {
        this.password.setText(password);
        changePage(secondPage, showPasswordPane);
        showPasswordPane.requestFocus();
    }

    private void changePage(Pane previousPage, Pane destinationPage) {
        previousPage.setDisable(true);
        previousPage.setVisible(false);
        destinationPage.setDisable(false);
        destinationPage.setVisible(true);
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