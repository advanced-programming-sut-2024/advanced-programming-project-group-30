package view;

import controller.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.App;

public class MainMenu implements Menu {
    private final MainMenuController controller = new MainMenuController();
    @FXML
    private Pane mainPane;
    @FXML
    private VBox root;
    @FXML
    private Label infoLabel;

    public void initialize() {
        root.widthProperty().addListener((Void) -> scalePane(mainPane));
        root.heightProperty().addListener((Void) -> scalePane(mainPane));
    }

    public void setUserInfo(String username, String nickname) {
        this.infoLabel.setText("Welcome " + nickname + " :> -- (" + username + ")");
        infoLabel.requestFocus();
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

    private void scalePane(Pane pane) {
        Double scale = App.getSceneManager().getScale(root.getWidth(), root.getHeight(), 1000, 625, 1);
        if (scale == null) return;
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        pane.setLayoutX((root.getWidth() - 1150) / 2);
        pane.setLayoutY((root.getHeight() - 660) / 2 + 5 * (double) 1);
    }
}