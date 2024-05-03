package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.SpecialCard;

public class ClearWeather extends SpecialCard {
    public ClearWeather(String name, String explanation, Faction faction, String position, boolean discardAfterPlaying) {
        super(name, explanation, faction, position, true);
    }
    public void run(Game game){
        //TODO
    }
}
