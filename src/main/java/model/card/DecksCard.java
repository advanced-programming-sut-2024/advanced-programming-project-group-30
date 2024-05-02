package model.card;

import model.Ability;
import model.Faction;

public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;
    private final String position;
    private final Ability ability;

    public DecksCard(String name, String explanation, Faction faction, boolean itIsLeader, Ability ability, boolean isSpecialCard, String position) {
        super(name, explanation, faction, itIsLeader);
        this.isSpecialCard = isSpecialCard;
        this.position = position;
        this.ability = ability;
    }
    public Ability getAbility(){
        return this.ability;
    }


    public boolean isSpecialCard() {
        return isSpecialCard;
    }

    public String getPosition() {
        return position;
    }
}