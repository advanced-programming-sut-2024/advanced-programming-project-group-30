package model;

import enums.FactionType;
import enums.cardsData.DeckCardData;
import enums.cardsData.RegularCardData;
import enums.cardsData.SpecialCardsData;
import model.card.DecksCard;

import java.util.*;

public class PregameData {
    private PregameUserData currentData;
    private PregameUserData anotherData;

    public PregameData(User currentUser, User anotherUser) {
        currentData = new PregameUserData(currentUser);
        anotherData = new PregameUserData(anotherUser);
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardCollection() {
        return currentData.cardCollection;
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardsInDeck() {
        return currentData.cardCollection;
    }

    public int getCardsInDeckNumber() {
        return currentData.cardsInDeckNumber;
    }

    public int getUnitCardsNumber() {
        return currentData.unitCardsNumber;
    }

    public int getSpecialCardsNumber() {
        return currentData.specialCardsNumber;
    }

    public int getTotalCardsStrength() {
        return currentData.totalCardsStrength;
    }

    public int getHeroCardsNumber() {
        return currentData.heroCardsNumber;
    }

    public boolean isUnitCardsNumberValid() {
        return 22 < currentData.unitCardsNumber;
    }

    public boolean isSpecialCardsNumberValid() {
        return currentData.specialCardsNumber <= 10;
    }

    public FactionType getFaction() {
        return currentData.getFaction();
    }

    public void setFaction(FactionType faction) {
        currentData.setFaction(faction);
    }

    public void addToPreDeck(DeckCardData chosenCard) {
        currentData.addToPreDeck(chosenCard);
    }

    public void removeFromPreDeck(DeckCardData chosenCard) {
        currentData.removeFromPreDeck(chosenCard);
    }

    private void changeNumberData(DeckCardData cardData, int sign) {
        currentData.changeNumberData(cardData, sign);
    }
}

class PregameUserData {
    final User user;
    final TreeMap<DeckCardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    final TreeMap<DeckCardData, ArrayList<DecksCard>> cardsInDeck = new TreeMap<>(CardComparator.getCardComparator());
    int cardsInDeckNumber = 0;
    int unitCardsNumber = 0;
    int specialCardsNumber = 0;
    int totalCardsStrength = 0;
    int heroCardsNumber = 0;

    PregameUserData(User user) {
        this.user = user;
        setFaction(user.getSelectedFaction());
    }

    FactionType getFaction() {
        return user.getSelectedFaction();
    }

    void setFaction(FactionType faction) {
        user.setSelectedFaction(faction);
        cardCollection.clear();
        this.cardCollection.putAll(user.getCardCollection().getCardsMapByFactionsType(faction));
        cardsInDeck.clear();
        System.gc();
    }

    void addToPreDeck(DeckCardData chosenCard) {
        changeNumberData(chosenCard, 1);
        for (DeckCardData cardData : cardCollection.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardCollection.get(cardData).remove(0);
                if (cardsInDeck.get(cardData) != null) cardsInDeck.get(cardData).add(card);
                else cardsInDeck.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    void removeFromPreDeck(DeckCardData chosenCard) {
        changeNumberData(chosenCard, -1);
        for (DeckCardData cardData : cardsInDeck.keySet())
            if (cardData == chosenCard) {
                DecksCard card = cardsInDeck.get(cardData).remove(0);
                if (cardCollection.get(cardData) != null) cardCollection.get(cardData).add(card);
                else cardCollection.put(cardData, new ArrayList<>(Collections.singletonList(card)));
                return;
            }
    }

    void changeNumberData(DeckCardData cardData, int sign) {
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