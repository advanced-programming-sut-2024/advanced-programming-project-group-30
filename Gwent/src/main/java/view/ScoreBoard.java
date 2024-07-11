package view;

import controller.ScoreBoardController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.App;
import model.User;

import java.util.ArrayList;

public class ScoreBoard extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window
        primaryStage.setTitle("Gwent Leaderboard");

        // Create a VBox layout
        VBox vbox = new VBox(20); // 20px spacing between elements
        vbox.setPadding(new Insets(20)); // 20px padding around the VBox

        // Set background image for VBox
        BackgroundImage myBI= new BackgroundImage(
                new Image(getClass().getResource("/Images/Backgrounds/MenusBackground.jpeg").toExternalForm(),1000,625,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        vbox.setBackground(new Background(myBI));

        // Create a ListView to display the leaderboard
        ListView<String> listView = new ListView<>();

        // Set styles for the ListView
        listView.setStyle("-fx-background-color: rgba(0, 0, 0, 0.65); -fx-control-inner-background: rgba(0, 0, 0, 0.65);");
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            setTextFill(Color.GOLD); // Gold text
                            setFont(Font.font("Times New Roman", FontWeight.BOLD, 18)); // Medieval-style font
                            setStyle("-fx-background-color: rgba(21, 17, 15, 0.9);"
                                    + "-fx-background-radius: 20;"
                                    + "-fx-border-width: 1;"
                                    + "-fx-border-color: rgb(101, 88, 71);"
                                    + "-fx-border-radius: 20;"
                                    + "-fx-padding: 10px;");
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        Button button = new Button("back");
        button.getStylesheets().add(getClass().getResource("/CSS/NodeStyle.css").toExternalForm());
        button.getStyleClass().add("changePasswordButton");
        button.setLayoutX(10);
        button.setLayoutY(10);
        button.setPrefWidth(30);
        button.setPrefHeight(40);
        button.setOnAction(e -> {
            ScoreBoardController.goToMainMenu();
        });
        vbox.getChildren().add(button);
        // Get the sorted list of users
        ArrayList<User> users = ScoreBoardController.calculateRankings();

        // Populate the ListView with the user data
        for (User user : users) {
            listView.getItems().add(user.getUsername() + " - Wins: " + user.getWins());
        }

        // Add a label and the ListView to the VBox
        Label label = new Label("Gwent Leaderboard:");
        label.setTextFill(Color.GOLD); // Gold text
        label.setBackground(Background.fill(Color.web("#333333d9")));
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24)); // Medieval-style font
        label.setStyle("-fx-padding: 10px; -fx-border-radius: 99px;");

        vbox.getChildren().addAll(label, listView);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(vbox, 800, 500); // Increased size for a modern look

        // Set the scene on the primary stage
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        App.loadUsers();

//        App.testSetup();
//        App.getUserByUsername("pishi").addToWins();
//        App.getUserByUsername("pishi").addToWins();
//        App.getUserByUsername("jojo").addToWins();
        launch(args);
    }

}
