package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;

public class ClearWeather extends SpecialCard {
    public ClearWeather(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, true);
    }

    public void run(Game game) {
    }
}