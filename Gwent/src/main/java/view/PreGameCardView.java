package view;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.card.DecksCard;

import java.util.ArrayList;
import java.util.Objects;

public class PreGameCardView extends Group {
    private final LgCard card;
    private int number;
    private final Label numberLabel = new Label();
    private final ImageView numberIcon;
    private final ArrayList<DecksCard> cards;

    public PreGameCardView(LgCard card, int number, ArrayList<DecksCard> cards) {
        this.card = card;
        this.number = number;
        numberLabel.setText("×" + number);
        numberLabel.setStyle("-fx-font-family: Cambria; -fx-text-alignment: CENTER; -fx-text-fill: #4e3b22; -fx-font-size: 10; -fx-font-weight: bold;");
        numberLabel.setLayoutX(92);
        numberLabel.setLayoutY(174);
        this.getChildren().add(card);
        this.getChildren().add(numberLabel);
        numberIcon =new ImageView(new Image(Objects.requireNonNull(
                this.getClass().getResourceAsStream("/Images/Game/PregameIcons/preview_count.png"))));
        numberIcon.setFitWidth(10);
        numberIcon.setPreserveRatio(true);
        numberIcon.setY(174);
        numberIcon.setX(81);
        this.getChildren().add(numberIcon);
        this.setCursor(Cursor.HAND);
        this.cards = new ArrayList<>(cards);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        numberLabel.setText("×" + number);
    }

    public LgCard getCard() {
        return card;
    }

    public ArrayList<DecksCard> getCards() {
        return cards;
    }
}
