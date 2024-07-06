package view;

import controller.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenu implements Menu {
    private final MainMenuController controller = new MainMenuController();
    @FXML
    private Label infoLabel;

    public void setUserInfo(String username, String nickname) {
        this.infoLabel.setText("Welcome " + nickname + " :> -- (" + username + ")");
    }

    @FXML
    private void goToProfileMenu() {
        // TODO
    }

    @FXML
    private void createNewGame() {
        // TODO
    }

    @FXML
    private void logout() {
        controller.logout();
    }
}