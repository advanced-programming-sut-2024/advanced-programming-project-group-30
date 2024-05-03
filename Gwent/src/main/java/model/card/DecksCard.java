package model.card;

import model.Faction;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;

    public DecksCard(String name, String explanation, Faction faction, boolean isSpecialCard) {
        super(name, explanation, faction, false);
        this.isSpecialCard = isSpecialCard;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}