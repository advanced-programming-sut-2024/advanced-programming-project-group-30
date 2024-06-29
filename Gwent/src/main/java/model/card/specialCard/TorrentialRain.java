package model.card.specialCard;

import enums.FactionType;
import model.Game;

public class TorrentialRain extends SpecialCard {
    public TorrentialRain(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(Game game){
    }
}