package model.card.specialCard;

import enums.FactionType;
import model.Game;

public class BitingFrost extends SpecialCard {
    public BitingFrost(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(Game game) {
    }
}
