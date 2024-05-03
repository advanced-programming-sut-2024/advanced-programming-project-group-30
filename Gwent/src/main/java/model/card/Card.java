package model.card;

import model.Faction;

abstract class Card {
    private final String name;
    private final String explanation;
    private final Faction faction;

    public Card(String name, String explanation, Faction faction) {
        this.name = name;
        this.explanation = explanation;
        this.faction = faction;
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
}