package model;

import enums.FactionType;
import enums.cardsData.CardData;
import model.card.DecksCard;

import java.util.*;

public class PregameData {
    private final User user;
    private final TreeMap<CardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    private final HashMap<CardData, ArrayList<DecksCard>> cardsInDeck = new HashMap<>();

    public PregameData(User user) {
        this.user = user;
        setFaction(user.getSelectedFaction());
    }

    public void setFaction(FactionType faction) {
        user.setSelectedFaction(faction);
        this.cardCollection.putAll(user.getCardCollection().getCardsMapByFactionsType(faction));
        cardsInDeck.clear();
        System.gc();
    }

    public void addToPreDeck(CardData chosenCard) {
        for (CardData cardData : cardCollection.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardCollection.get(cardData).remove(0);
                if(cardsInDeck.get(cardData) != null) cardsInDeck.get(cardData).add(card);
                else cardsInDeck.put(cardData,new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    public void removeFromPreDeck(CardData chosenCard) {
        for (CardData cardData : cardsInDeck.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardsInDeck.get(cardData).remove(0);
                cardCollection.get(cardData).add(card);
                return;
            }
    }

    public TreeMap<CardData, ArrayList<DecksCard>> getCardCollection() {
        return cardCollection;
    }

    public HashMap<CardData, ArrayList<DecksCard>> getCardsInDeck() {
        return cardsInDeck;
    }
}