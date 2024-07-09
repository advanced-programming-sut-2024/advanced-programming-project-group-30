package model;

import enums.FactionType;
import enums.cardsData.*;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.WeatherCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class CardCollection {
    private final ArrayList<DecksCard> NeutralCards;
    private final HashMap<FactionType, ArrayList<RegularCard>> FactionsCard;

    public CardCollection() {
        NeutralCards = new ArrayList<>(SpecialCardsData.getAllSpecialCard());
        NeutralCards.addAll(NeutralRegularCardsData.getAllRegularCard());
        FactionsCard = new HashMap<>();
        for (FactionType type : FactionType.values())
            FactionsCard.put(type, type.getFactionRegularCards());
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardsMapByFactionsType(FactionType factionType) {
        ArrayList<DecksCard> cards = new ArrayList<>(NeutralCards);
        cards.addAll(FactionsCard.get(factionType));
        TreeMap<DeckCardData, ArrayList<DecksCard>> cardsMap = new TreeMap<>(CardComparator.getCardComparator());
        ArrayList<DecksCard> oneTypeCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            while (i < cards.size() - 1 && cards.get(i).getCardData() == cards.get(i + 1).getCardData())
                oneTypeCards.add(cards.get(i++));
            oneTypeCards.add(cards.get(i));
            cardsMap.put((DeckCardData) cards.get(i).getCardData(), new ArrayList<>(oneTypeCards));
            oneTypeCards.clear();
        }
        return cardsMap;
    }
}