package model.card;

import enums.FactionType;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;

    public DecksCard(String name, FactionType faction, boolean isSpecialCard) {
        super(name, faction);
        this.isSpecialCard = isSpecialCard;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}
