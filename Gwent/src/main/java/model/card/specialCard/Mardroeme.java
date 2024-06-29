package model.card.specialCard;

import model.Faction;
import model.Game;
import model.Row;

public class Mardroeme extends SpecialCard {
    public Mardroeme(String name, String explanation, Faction faction) {
        super(name, explanation, faction, false);
    }

    public void run(Row row, Game game) {
    }
}
