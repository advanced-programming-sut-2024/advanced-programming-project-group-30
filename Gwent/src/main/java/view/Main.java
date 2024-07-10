package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.App;
import network.Client;

public class Main extends Application {
    private static Client client;

    public static void main(String[] args) {
        client = new Client();
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