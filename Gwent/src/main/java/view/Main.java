package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.App;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.setPrimaryStage(stage);
        stage.setScene(App.getCurrentMenuScene().getScene());
        stage.setTitle("Gwent");
        stage.show();
    }
}
