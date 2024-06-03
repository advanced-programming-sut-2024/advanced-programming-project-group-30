package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(Objects.requireNonNull(Main.class.getResource("/FXML/LoginMenu.fxml"))));
        stage.setScene(scene);
        stage.setTitle("Login Menu");
        stage.show();
    }
}
