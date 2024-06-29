package model.card.specialCard;

import enums.FactionType;
import model.Game;

public class ImpenetrableFog extends SpecialCard {
    public ImpenetrableFog(String name, String explanation, boolean discardAfterPlaying) {
        super(name, explanation, FactionType.NEUTRAL, false);
    }

    public void run(Game game){
    }
}
