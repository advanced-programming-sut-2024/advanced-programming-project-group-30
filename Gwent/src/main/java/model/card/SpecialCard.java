package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import view.CardView;

import java.lang.reflect.Method;

public class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;
    private final Method ability;

    public SpecialCard(String name, FactionType faction, CardData cardData, boolean discardAfterPlaying, Method ability) {
        super(name, faction, cardData, true);
        this.discardAfterPlaying = discardAfterPlaying;
        this.ability = ability;
        super.cardView = new CardView(this);
    }
    public void run(){

    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}
