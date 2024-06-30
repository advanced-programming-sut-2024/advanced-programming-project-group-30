package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;

public class SkelliegeStorm extends SpecialCard {
    public SkelliegeStorm(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(Game game) {
    }
}