package view;

import enums.cardsData.CardData;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LgCard extends Rectangle {
    private final int preGameHeight = 212;
    private final int preGameWidth = 110;
    private final int gameHeight = 0;
    private final int gameWidth = 0;
    private final CardData cardData;

    public LgCard(CardData cardData, boolean setForPreGame) {
        this.cardData = cardData;
        this.setFill(new ImagePattern(cardData.getLgImage()));
        if (setForPreGame) setForPreGame();
        else setForGame();
    }

    public void setForPreGame() {
        this.setHeight(preGameHeight);
        this.setWidth(preGameWidth);
        this.setArcHeight(15);
        this.setArcWidth(15);
    }

    public void setForGame() {
        this.setHeight(gameHeight);
        this.setWidth(gameWidth);
    }

    public CardData getCardData() {
        return cardData;
    }
}