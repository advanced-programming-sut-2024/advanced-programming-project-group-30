package model.card.specialCard;

import enums.FactionType;
import model.Game;
import model.card.DecksCard;

public class Decoy extends SpecialCard {

    public Decoy(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(DecksCard card, Game game) {
    }
}
