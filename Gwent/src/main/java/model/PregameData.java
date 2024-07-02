package model;

import enums.FactionType;
import enums.cardsData.CardData;
import enums.cardsData.DeckCardData;
import enums.cardsData.SpecialCardsData;
import model.card.DecksCard;

import java.util.*;

public class PregameData {
    private final User user;
    private final TreeMap<DeckCardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    private final HashMap<DeckCardData, ArrayList<DecksCard>> cardsInDeck = new HashMap<>();
    private int cardsInDeckNumber = 0;
    private int numberOfUnitCards = 0;
    private int numberOfSpecialCards = 0;
    private int totalUnitCardsStrength = 0;
    private int numberOfHeroCards = 0;

    public PregameData(User user) {
        this.user = user;
        setFaction(user.getSelectedFaction());
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardCollection() {
        return cardCollection;
    }

    public HashMap<DeckCardData, ArrayList<DecksCard>> getCardsInDeck() {
        return cardsInDeck;
    }

    public int getCardsInDeckNumber() {
        return cardsInDeckNumber;
    }

    public void setFaction(FactionType faction) {
        user.setSelectedFaction(faction);
        cardCollection.clear();
        this.cardCollection.putAll(user.getCardCollection().getCardsMapByFactionsType(faction));
        cardsInDeck.clear();
        System.gc();
    }

    public void addToPreDeck(DeckCardData chosenCard) {
        cardsInDeckNumber++;
        for (DeckCardData cardData : cardCollection.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardCollection.get(cardData).remove(0);
                if (cardsInDeck.get(cardData) != null) cardsInDeck.get(cardData).add(card);
                else cardsInDeck.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    public void removeFromPreDeck(DeckCardData chosenCard) {
        cardsInDeckNumber--;
        for (DeckCardData cardData : cardsInDeck.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardsInDeck.get(cardData).remove(0);
                if (cardCollection.get(cardData) != null) cardCollection.get(cardData).add(card);
                else cardCollection.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    private void changeNumberData(CardData chosenCard, int sign) {
        cardsInDeckNumber = cardsInDeckNumber + sign;
        if (chosenCard instanceof SpecialCardsData) numberOfSpecialCards = numberOfSpecialCards + sign;
        else {
            numberOfUnitCards = numberOfHeroCards + sign;
        }
    }
}