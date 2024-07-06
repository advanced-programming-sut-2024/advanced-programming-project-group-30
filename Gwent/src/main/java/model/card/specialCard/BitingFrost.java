package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;

public class BitingFrost extends SpecialCard {
    public BitingFrost(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(Game game) {
    }
}
