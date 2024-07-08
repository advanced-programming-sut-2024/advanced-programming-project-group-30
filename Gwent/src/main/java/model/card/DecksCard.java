package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import view.CardView;

import java.lang.reflect.Method;


public abstract class DecksCard extends Card {
    private final boolean isSpecialCard;
    protected final Method ability;
    protected CardView cardView;

    public DecksCard(String name, FactionType faction, CardData cardData, Method ability, boolean isSpecialCard) {
        super(name, faction, cardData);
        this.isSpecialCard = isSpecialCard;
        this.ability = ability;
    }

    public boolean isSpecialCard() {
        return isSpecialCard;
    }

    public CardView getCardView() {
        return cardView;
    }
}
