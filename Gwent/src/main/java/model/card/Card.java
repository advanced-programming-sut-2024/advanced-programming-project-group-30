package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import javafx.scene.Group;
import model.Faction;

public abstract class Card {
    private final String name;
    private final FactionType faction;
    private final CardData cardData;

    public Card(String name, FactionType faction, CardData cardData) {
        this.name = name;
        this.faction = faction;
        this.cardData = cardData;
    }

    public String getName() {
        return name;
    }

    public FactionType getFaction() {
        return faction;
    }

    public CardData getCardData() {
        return cardData;
    }

    public boolean sames(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardData == card.cardData;
    }
}
