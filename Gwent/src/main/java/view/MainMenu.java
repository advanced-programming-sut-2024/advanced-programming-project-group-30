package view;

import controller.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu implements Menu{
    private final MainMenuController controller = new MainMenuController();
    @FXML
    private VBox Vbox;
    @FXML
    private Label username;
    @FXML
    private Label nickname;

    public void setFields(String username, String nickname){
        Vbox.requestFocus();
        this.username.setText(username);
        this.nickname.setText(nickname);
    }

    @FXML
    private void goToProfileMenu(){
        controller.goToProfileMenu();
    }

    @FXML
    private void createNewGame(){
        // TODO
    }

    @FXML
    private void logout(){
        controller.logout();
    }
}