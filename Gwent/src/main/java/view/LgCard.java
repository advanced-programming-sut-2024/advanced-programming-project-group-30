package view;

import enums.SizeData;
import enums.cardsData.CardData;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class LgCard extends Rectangle {
    private final CardData cardData;
    private final double preGameHeight = SizeData.preGameCard.getHeight();
    private final double preGameWidth = SizeData.preGameCard.getWidth();
    private final double preGameRadius = SizeData.preGameCard.getRadius();
    private final double gameHeight = SizeData.gameLgCard.getHeight();
    private final double gameWidth = SizeData.gameLgCard.getWidth();
    private final double gameRadius = SizeData.gameLgCard.getRadius();

    public LgCard(CardData cardData, boolean setForPreGame) {
        this.cardData = cardData;
        this.setFill(new ImagePattern(cardData.getLgImage()));
        if (setForPreGame) setForPreGame();
        else setForGame();
    }

    public void setForPreGame() {
        this.setHeight(preGameHeight);
        this.setWidth(preGameWidth);
        this.setArcHeight(preGameRadius);
        this.setArcWidth(preGameRadius);
    }

    public void setForGame() {
        this.setHeight(gameHeight);
        this.setWidth(gameWidth);
        this.setArcHeight(gameRadius);
        this.setArcWidth(gameRadius);
    }

    public CardData getCardData() {
        return cardData;
    }
}