package enums;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import view.Menu;

import java.io.IOException;

public enum MenuScene {
    LOGIN_SCENE("/FXML/LoginMenu.fxml"),
    REGISTER_SCENE("/FXML/RegisterMenu.fxml"),
    MAIN_SCENE("/FXML/MainMenu.fxml"),
    FORGET_PASSWORD_SCENE("/FXML/ForgetPasswordMenu.fxml"),
    PROFILE_SCENE("/FXML/ProfileMenu.fxml"),
    PREGAME_SCENE("/FXML/PregameMenu.fxml"),
    ;

    private final Scene scene;
    private final FXMLLoader loader;

    MenuScene(String urlString) {
        this.loader = new FXMLLoader(getClass().getResource(urlString));
        try {
            this.scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scene getScene() {
        return scene;
    }

    public Menu getMenu() {
        return loader.getController();
    }
}