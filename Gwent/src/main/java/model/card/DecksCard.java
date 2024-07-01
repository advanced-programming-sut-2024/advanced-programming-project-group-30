package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import javafx.scene.control.Label;
import model.Faction;


public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;

    public DecksCard(String name, FactionType faction, CardData cardData, boolean isSpecialCard) {
        super(name, faction, cardData);
        this.isSpecialCard = isSpecialCard;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }
}
