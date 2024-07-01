package model.card.specialcard;

import enums.cardsData.CardData;
import model.Faction;
import model.Game;
import model.Row;

public class CommanderHorn extends SpecialCard {
    public CommanderHorn(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(Game game, Row row) {
    }
}
