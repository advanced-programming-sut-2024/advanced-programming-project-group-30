package model.card.specialCard;

import enums.FactionType;
import model.Game;
import model.Row;

public class CommanderHorn extends SpecialCard {
    public CommanderHorn(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(Game game, Row row) {
    }
}