package model.card;

import model.Faction;

abstract class Card {
    private final String name;
    private final Faction faction;

    public Card(String name, Faction faction) {
        this.name = name;
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }
}
