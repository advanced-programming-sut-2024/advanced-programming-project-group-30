package model.card.speacialCards;

import model.Faction;
import model.card.SpecialCard;

public class CleanWeather extends SpecialCard {
    public CleanWeather(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, true);
    }
}
