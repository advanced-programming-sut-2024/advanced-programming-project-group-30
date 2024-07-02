package view;

import enums.CssAddress;
import enums.SizeData;
import enums.cardsData.CardData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PregameCardView extends Group {
    private final CardData cardData;
    private int number;
    private final Label numberLabel;
    private final Rectangle cardImage;

    public PregameCardView(CardData cardData) {
        this.cardData = cardData;
        this.number = cardData.getNumber();
        this.numberLabel = createNumberLabel();
        this.cardImage = createCardImage();
        addNodes();
        this.getStyleClass().add(CssAddress.PREGAME_CARD_VIEW.getStyleClass());
    }

    public CardData getCardData() {
        return cardData;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        this.numberLabel.setText("×" + number);
    }

    private Rectangle createCardImage() {
        Rectangle cardImage = new Rectangle(SizeData.PRE_GAME_CARD.getWidth(), SizeData.PRE_GAME_CARD.getHeight());
        cardImage.setArcHeight(SizeData.PRE_GAME_CARD.getRadius());
        cardImage.setArcWidth(SizeData.PRE_GAME_CARD.getRadius());
        cardImage.setFill(new ImagePattern(cardData.getLgImage()));
        return cardImage;
    }

    private Label createNumberLabel() {
        Label numberLabel = new Label("×" + number);
        numberLabel.getStyleClass().add(CssAddress.NUMBER_OF_CARD_TYPE_LABEL.getStyleClass());
        return numberLabel;
    }

    private ImageView createIcon() {
        ImageView icon = new ImageView();
        icon.getStyleClass().add(CssAddress.NUMBER_OF_CARD_TYPE_ICON.getStyleClass());
        icon.setFitWidth(SizeData.NUMBER_OF_CARD_TYPE.getWidth());
        icon.setFitHeight(SizeData.NUMBER_OF_CARD_TYPE.getHeight());
        return icon;
    }

    private void addNodes() {
        this.getChildren().add(cardImage);
        this.getChildren().add(createIcon());
        this.getChildren().add(numberLabel);
    }
}