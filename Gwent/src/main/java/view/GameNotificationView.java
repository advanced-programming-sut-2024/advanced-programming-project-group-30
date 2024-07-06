package view;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameNotificationView extends Pane {
    private Pane pane;
    private Label label;
    private ImageView imageView;
    private static GameNotificationView gameNotification;
    public static GameNotificationView getInstance(){
        if (gameNotification == null)
            gameNotification = new GameNotificationView();
        return gameNotification;
    }
    public GameNotificationView(){
        gameNotification = this;
        pane = new Pane();
        label = new Label();
        imageView = new ImageView();
        pane.getChildren().addAll(label, imageView);
        this.getChildren().add(pane);
    }


}
