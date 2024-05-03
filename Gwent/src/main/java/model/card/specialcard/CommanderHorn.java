package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.Row;
import model.card.specialcard.SpecialCard;

public class CommanderHorn extends SpecialCard {
    public CommanderHorn(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, false);
    }

    public void run(Game game, Row row) {

    }
}
