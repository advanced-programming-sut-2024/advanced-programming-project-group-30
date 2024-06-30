package model;

import enums.FactionType;
import enums.cardsData.NeutralRegularCardsData;
import enums.cardsData.SpecialCardsData;
import model.card.DecksCard;
import model.card.RegularCard;

import java.util.ArrayList;
import java.util.HashMap;

public class CardCollection {
    private final ArrayList<DecksCard> NeutralCards;
    private final HashMap<FactionType, ArrayList<RegularCard>> FactionsCard;


    public CardCollection() {
        NeutralCards = new ArrayList<>(NeutralRegularCardsData.getAllRegularCard());
        NeutralCards.addAll(SpecialCardsData.getAllSpecialCard());
        FactionsCard = new HashMap<>();
        for (FactionType type : FactionType.values())
            FactionsCard.put(type, type.getFactionRegularCards());
    }

    public ArrayList<DecksCard> getCardsByFactionsName(FactionType factionType) {
        ArrayList<DecksCard> cards = new ArrayList<>(NeutralCards);
        cards.addAll(FactionsCard.get(factionType));
        return cards;
    }
}