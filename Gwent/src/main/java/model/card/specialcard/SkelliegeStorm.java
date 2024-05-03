package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.SpecialCard;

public class SkelliegeStorm extends SpecialCard {
    public SkelliegeStorm(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, false);
    }

    public void run(Game game){

    }
}
