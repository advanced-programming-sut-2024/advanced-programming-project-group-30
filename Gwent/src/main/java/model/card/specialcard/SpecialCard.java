package model.card.specialcard;

import enums.FactionType;
import enums.cardsData.CardData;
import model.Faction;
import model.card.DecksCard;

public abstract class SpecialCard extends DecksCard {
    private final String explanation;
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, String explanation, FactionType faction, CardData cardData, boolean discardAfterPlaying) {
        super(name, faction, cardData, false);
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
