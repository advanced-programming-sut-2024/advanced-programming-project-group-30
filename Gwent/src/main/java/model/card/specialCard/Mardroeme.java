package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;
import model.Row;

public class Mardroeme extends SpecialCard {
    public Mardroeme(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData,false);
    }

    public void run(Row row, Game game) {
    }
}