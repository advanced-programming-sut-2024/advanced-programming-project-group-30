package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import model.Game;
import view.CardView;

import java.lang.reflect.Method;

public class SpecialCard extends DecksCard {
    private final boolean discardAfterPlaying;

    public SpecialCard(String name, FactionType faction, CardData cardData, boolean discardAfterPlaying, Method ability) {
        super(name, faction, cardData, ability, true);
        this.discardAfterPlaying = discardAfterPlaying;
        this.cardView = new CardView(this);
    }
    @Override
    public void run(Game game) {

    }

    public boolean isDiscardAfterPlaying() {
        return discardAfterPlaying;
    }
}