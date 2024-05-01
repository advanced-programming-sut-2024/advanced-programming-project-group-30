package model.card;

import model.Ability;
import model.Faction;

public abstract class Card {
    private final String name;
    private final String explanation;
    private final Faction faction;
    private final boolean isLeader;
    private final boolean isSpecialCard;
    private final Ability ability;

    public Card(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, Ability ability) {
        this.name = name;
        this.explanation = explanation;
        this.faction = faction;
        this.isLeader = itIsLeader;
        this.isSpecialCard = itIsSpecialCard;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public Faction getFaction() {
        return faction;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }

    public Ability getAbility() {
        return ability;
    }
}