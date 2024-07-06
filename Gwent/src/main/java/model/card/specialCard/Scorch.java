package model.card.specialCard;

import enums.cardsData.CardData;
import model.Game;
import model.Row;

public class Scorch extends SpecialCard {
    public Scorch(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData,true);
    }

    public void run(Row row, Game game) {
    }
}
