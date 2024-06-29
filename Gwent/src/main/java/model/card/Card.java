package model.card;

import enums.FactionType;

abstract class Card {
    private final String name;
    private final FactionType faction;

    public Card(String name, FactionType faction) {
        this.name = name;
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public FactionType getFaction() {
        return faction;
    }
}
