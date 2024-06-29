package model.card;

import model.Faction;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;

    public DecksCard(String name, Faction faction, boolean isSpecialCard) {
        super(name, faction);
        this.isSpecialCard = isSpecialCard;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}
