package model.card.specialCard;

import enums.FactionType;
import model.card.DecksCard;

public abstract class SpecialCard extends DecksCard {
    private final String explanation;
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, FactionType faction, boolean discardAfterPlaying) {
        super(name, faction, false);
        this.explanation = explanation;
        this.discardAfterPlaying = discardAfterPlaying;
    }

    public String getExplanation() {
        return explanation;
    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}