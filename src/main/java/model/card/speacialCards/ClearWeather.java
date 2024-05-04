package model.card.speacialCards;

import model.Faction;
import model.Game;

public class ClearWeather extends SpecialCard {
    public ClearWeather(String name, String explanation, Faction faction) {
        super(name, explanation, faction, true);
    }

    public void run(Game game) {
    }
}