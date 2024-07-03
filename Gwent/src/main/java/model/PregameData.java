package model;

import enums.FactionType;
import enums.cardsData.DeckCardData;
import enums.cardsData.RegularCardData;
import enums.cardsData.SpecialCardsData;
import model.card.DecksCard;

import java.util.*;

public class PregameData {
    private final User user;
    private final TreeMap<DeckCardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    private final HashMap<DeckCardData, ArrayList<DecksCard>> cardsInDeck = new HashMap<>();
    private int cardsInDeckNumber = 0;
    private int unitCardsNumber = 0;
    private int specialCardsNumber = 0;
    private int totalCardsStrength = 0;
    private int heroCardsNumber = 0;

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

    public int getUnitCardsNumber() {
        return unitCardsNumber;
    }

    public int getSpecialCardsNumber() {
        return specialCardsNumber;
    }

    public int getTotalCardsStrength() {
        return totalCardsStrength;
    }

    public int getHeroCardsNumber() {
        return heroCardsNumber;
    }

    public boolean isUnitCardsNumberValid() {
        return 22 < unitCardsNumber;
    }

    public boolean isSpecialCardsNumberValid() {
        return specialCardsNumber <= 10;
    }

    public void setFaction(FactionType faction) {
        user.setSelectedFaction(faction);
        cardCollection.clear();
        this.cardCollection.putAll(user.getCardCollection().getCardsMapByFactionsType(faction));
        cardsInDeck.clear();
        System.gc();
    }

    public void addToPreDeck(DeckCardData chosenCard) {
        changeNumberData(chosenCard, 1);
        for (DeckCardData cardData : cardCollection.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardCollection.get(cardData).remove(0);
                if (cardsInDeck.get(cardData) != null) cardsInDeck.get(cardData).add(card);
                else cardsInDeck.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    public void removeFromPreDeck(DeckCardData chosenCard) {
        changeNumberData(chosenCard, -1);
        for (DeckCardData cardData : cardsInDeck.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardsInDeck.get(cardData).remove(0);
                if (cardCollection.get(cardData) != null) cardCollection.get(cardData).add(card);
                else cardCollection.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    private void changeNumberData(DeckCardData cardData, int sign) {
        cardsInDeckNumber = cardsInDeckNumber + sign;
        if (cardData instanceof SpecialCardsData) specialCardsNumber = specialCardsNumber + sign;
        else {
            RegularCardData regularCardData = (RegularCardData) cardData;
            unitCardsNumber = unitCardsNumber + sign;
            totalCardsStrength = totalCardsStrength + sign * regularCardData.getPoint();
            if (regularCardData.isHero()) heroCardsNumber = heroCardsNumber + sign;
        }
    }
}