
package model.card.speacialCards;

import model.Faction;
import model.card.DecksCard;

public abstract class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, Faction faction, boolean discardAfterPlaying) {
        super(name, explanation, faction, false);
        this.discardAfterPlaying = discardAfterPlaying;
    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}
