package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.SpecialCard;

public class ImpenetrableFog extends SpecialCard {
    public ImpenetrableFog(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, false);
    }

    public void run(Game game){

    }
}
