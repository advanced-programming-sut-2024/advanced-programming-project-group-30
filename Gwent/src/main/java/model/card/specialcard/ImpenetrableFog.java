package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.specialcard.SpecialCard;

public class ImpenetrableFog extends SpecialCard {
    public ImpenetrableFog(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, false);
    }

    public void run(Game game){

    }
}
