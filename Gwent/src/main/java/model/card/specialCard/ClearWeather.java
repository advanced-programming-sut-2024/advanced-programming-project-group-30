package model.card.specialCard;

import enums.FactionType;
import model.Game;

public class ClearWeather extends SpecialCard {
    public ClearWeather(String name, String explanation) {
        super(name, explanation, FactionType.NEUTRAL, true);
    }

    public void run(Game game) {
    }
}