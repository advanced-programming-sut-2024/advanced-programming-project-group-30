package view;

import controller.MainMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenu implements Menu{
    private final MainMenuController controller = new MainMenuController();
    @FXML
    private Label username;
    @FXML
    private Label nickname;

    public void setFields(String username, String nickname){
        this.username.setText(username);
        this.nickname.setText(nickname);
    }

    @FXML
    private void goToProfileMenu(){
        // TODO
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