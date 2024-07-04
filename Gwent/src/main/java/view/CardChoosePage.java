package view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CardChoosePage extends HBox {
    private final ImageView rightCard = new ImageView();;
    private final ImageView centerCard = new ImageView();
    private final ImageView leftCard = new ImageView();
    private final ArrayList<chosenModelView> chosenModelViews;
    private int index;

    public CardChoosePage(ArrayList<chosenModelView> chosenModelViews, int index) {
        this.chosenModelViews = chosenModelViews;
        this.index = index;
        rightCard.setFitHeight(270);
        rightCard.setFitWidth(150);
        leftCard.setFitWidth(150);
        leftCard.setFitHeight(270);
        centerCard.setFitHeight(400);
        centerCard.setFitWidth(220);
        if (index > 0) rightCard.setImage(this.chosenModelViews.get(index - 1));
        if (index >= 0) centerCard.setImage(this.chosenModelViews.get(index));
        if (index < chosenModelViews.size() - 1) leftCard.setImage(this.chosenModelViews.get(index + 1));
        this.getChildren().add(rightCard);
        this.getChildren().add(centerCard);
        this.getChildren().add(leftCard);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        leftCard.setOnMouseClicked((Void) -> toLeft());
        rightCard.setOnMouseClicked((Void) -> toRight());
    }

    public void toRight() {
        if (index <= 0) return;
        index--;
        if (index > 0) rightCard.setImage(this.chosenModelViews.get(index - 1));
        if (index >= 0) centerCard.setImage(this.chosenModelViews.get(index));
        if (index < chosenModelViews.size() - 1) leftCard.setImage(this.chosenModelViews.get(index + 1));
    }

    public void toLeft() {
        if (chosenModelViews.size() - 1 <= index) return;
        index++;
        if (index > 0) rightCard.setImage(this.chosenModelViews.get(index - 1));
        if (index >= 0) centerCard.setImage(this.chosenModelViews.get(index));
        if (index < chosenModelViews.size() - 1) leftCard.setImage(this.chosenModelViews.get(index + 1));
    }
}
