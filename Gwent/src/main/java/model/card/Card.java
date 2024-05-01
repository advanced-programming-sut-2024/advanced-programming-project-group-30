package model.card;

import model.Ability;
import model.Faction;

public abstract class Card {
    private final String name;
    private final String explanation;
    private final Faction faction;
    private final boolean isLeader;
    private final Ability ability;

    public Card(String name, String explanation, Faction faction, boolean itIsLeader, Ability ability) {
        this.name = name;
        this.explanation = explanation;
        this.faction = faction;
        this.isLeader = itIsLeader;
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

    public Ability getAbility() {
        return ability;
    }
}