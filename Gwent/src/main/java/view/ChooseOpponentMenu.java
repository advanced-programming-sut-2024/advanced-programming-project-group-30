package view;

import controller.ChooseOpponentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.App;
import model.Result;

public class ChooseOpponentMenu implements Menu {
    private final ChooseOpponentController controller = new ChooseOpponentController();
    @FXML
    private TextField opponentUsername;
    @FXML
    private PasswordField opponentPassword;
    @FXML
    private Label errorLabel;

    @FXML
    private void backToMainMenu() {
//        App.getSceneManager().goToMainMenu();
    }

    @FXML
    private void chooseOpponent() {
        Result result = controller.checkOpponentInformation(opponentUsername.getText(), opponentPassword.getText());
        errorLabel.setText(result.toString());
        if (result.isNotSuccessful()) return;
        App.getSceneManager().goToPregameMenu(controller.createPregameData(opponentUsername.getText()));
    }
}
