package model.card.specialCard;

import enums.FactionType;
import model.Game;

public class SkelliegeStorm extends SpecialCard {
    public SkelliegeStorm(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(Game game){
    }
}