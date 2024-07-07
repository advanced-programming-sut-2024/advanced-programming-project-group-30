package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.App;

public class Main extends Application {
    public static void main(String[] args) {
        App.loadUsers();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        App.setPrimaryStage(stage);
        App.testSetup();
        stage.setScene(App.getCurrentMenuScene().getScene());
        stage.setTitle("Gwent");
        stage.show();
    }
}