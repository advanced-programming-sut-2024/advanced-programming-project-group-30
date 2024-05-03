package model.card;

import model.Faction;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;

    public DecksCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean isSpecialCard) {
        super(name, explanation, faction, itIsLeader);
        this.isSpecialCard = isSpecialCard;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}