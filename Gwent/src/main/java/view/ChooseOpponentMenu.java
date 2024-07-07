package view;

import controller.ChooseOpponentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.App;
import model.Result;

public class ChooseOpponentMenu implements Menu {
    private final ChooseOpponentController controller = new ChooseOpponentController();
    @FXML
    private Pane root;
    @FXML
    private VBox mainPane;
    @FXML
    private PasswordField opponentPassword;
    @FXML
    private TextField opponentUsername;
    @FXML
    private Label errorLabel;

    public void chooseOpponent() {
        Result result = controller.checkOpponentInformation(opponentUsername.getText(), opponentPassword.getText());
        errorLabel.setText(result.toString());
        if (result.isNotSuccessful()) return;
        App.getSceneManager().goToPregameMenu(controller.createPregameData(opponentUsername.getText()));
    }
}
