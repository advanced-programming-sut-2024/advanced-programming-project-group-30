package model.card;

import model.Ability;
import model.Faction;

public class DecksCard extends Card {
    private final boolean isSpecialCard;
    private final String position;

    public DecksCard(String name, String explanation, Faction faction, boolean itIsLeader, Ability ability, boolean isSpecialCard, String position) {
        super(name, explanation, faction, itIsLeader, ability);
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