package model.card.specialCard;

import enums.FactionType;
import model.Game;
import model.Row;

public class Scorch extends SpecialCard {
    public Scorch(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, true);
    }

    public void run(Row row, Game game) {
    }
}
