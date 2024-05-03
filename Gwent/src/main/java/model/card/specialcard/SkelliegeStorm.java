package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.specialcard.SpecialCard;


public class SkelliegeStorm extends SpecialCard {
    public SkelliegeStorm(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, false);
    }

    public void run(Game game){

    }
}
