package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;

public class SkelligeStorm extends SpecialCard {
    public SkelligeStorm(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(Game game) {
    }
}