package model.card.specialCard;

import model.Faction;
import model.Game;
import model.Row;

public class CommanderHorn extends SpecialCard {
    public CommanderHorn(String name, String explanation, Faction faction) {
        super(name, explanation, faction, false);
    }

    public void run(Game game, Row row) {
    }
}
