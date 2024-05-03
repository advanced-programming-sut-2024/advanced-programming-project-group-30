package model.card;

import model.ability.Ability;
import model.Faction;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;
    private final String position;

    public DecksCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean isSpecialCard, String position) {
        super(name, explanation, faction, itIsLeader);
        this.isSpecialCard = isSpecialCard;
        this.position = position;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }

    public String getPosition() {
        return position;
    }
}