package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.App;
import network.Client;
import network.ServerResponse;

import java.util.ArrayList;

public class ClientView extends Application {
    public final static ArrayList<ServerResponse> responses = new ArrayList<>();
    private static Client client = new Client("127.0.0.1", 7000);

    public static Client getClient() {
        return client;
    }

    public static void main(String[] args) {
        client = new Client("127.0.0.1", 7000);
        client.start();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        App.setPrimaryStage(stage);
        stage.setScene(App.getCurrentMenuScene().getScene());
        stage.setTitle("Gwent");
        stage.show();
    }
}