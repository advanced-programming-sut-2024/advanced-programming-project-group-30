package view;

import enums.cardsData.CardData;
import javafx.scene.shape.Rectangle;

public class LgCard extends Rectangle {
    private final int preGameHeight = 118;
    private final int preGameWidth = 224;
    private final int gameHeight = 0;
    private final int gameWidth = 0;
    private final CardData cardData;

    public LgCard(CardData cardData) {
        this.cardData = cardData;

    }

    public void setForPreGame(){
        this.setHeight(preGameHeight);
        this.setWidth(preGameWidth);
    }


}
