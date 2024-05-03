package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.specialcard.SpecialCard;

public class ClearWeather extends SpecialCard {
    public ClearWeather(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, true);
    }
    public void run(Game game){
        //TODO
    }
}
