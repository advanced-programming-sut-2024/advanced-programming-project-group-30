package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;

public class TorrentialRain extends SpecialCard {
    public TorrentialRain(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(Game game) {
    }
}