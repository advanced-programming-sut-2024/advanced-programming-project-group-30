package model.card.specialCard;

import enums.FactionType;
import model.Game;
import model.Row;

public class Mardroeme extends SpecialCard {
    public Mardroeme(String name, String explanation) {
        super(name, explanation, FactionType.SKELLIGE, false);
    }

    public void run(Row row, Game game) {
    }
}