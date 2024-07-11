package model;

import enums.FactionType;
import enums.cardsData.*;
import model.card.DecksCard;

import java.util.*;

public class PregameData {
    private final CardCollection userCardCollection;
    private FactionType faction;
    private final TreeMap<DeckCardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    private final TreeMap<DeckCardData, ArrayList<DecksCard>> cardsInDeck = new TreeMap<>(CardComparator.getCardComparator());
    private LeaderCardData leader;
    private int cardsInDeckNumber = 0;
    private int unitCardsNumber = 0;
    private int specialCardsNumber = 0;
    private int totalCardsStrength = 0;
    private int heroCardsNumber = 0;

    public PregameData(CardCollection userCardCollection, FactionType faction) {
        this.userCardCollection = userCardCollection;
        this.faction = faction;
        this.cardCollection.putAll(userCardCollection.getCardsMapByFactionsType(faction));
    }

    public FactionType getFaction() {
        return faction;
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardCollection() {
        return cardCollection;
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardsInDeck() {
        return cardsInDeck;
    }

    public LeaderCardData getLeader() {
        return leader;
    }

    public void setLeader(LeaderCardData leader) {
        this.leader = leader;
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
        this.faction = faction;
        cardCollection.clear();
        this.cardCollection.putAll(userCardCollection.getCardsMapByFactionsType(faction));
        cardsInDeck.clear();
        cardsInDeckNumber = 0;
        unitCardsNumber = 0;
        specialCardsNumber = 0;
        totalCardsStrength = 0;
        heroCardsNumber = 0;
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

    public void changeNumberData(DeckCardData cardData, int sign) {
        cardsInDeckNumber = cardsInDeckNumber + sign;
        if (cardData instanceof SpecialCardsData || cardData instanceof WeatherCardsData)
            specialCardsNumber = specialCardsNumber + sign;
        else {
            RegularCardData regularCardData = (RegularCardData) cardData;
            unitCardsNumber = unitCardsNumber + sign;
            totalCardsStrength = totalCardsStrength + sign * regularCardData.getPoint();
            if (regularCardData.isHero()) heroCardsNumber = heroCardsNumber + sign;
        }
    }

    public ArrayList<DecksCard> getDeck() {
        ArrayList<DecksCard> deck = new ArrayList<>();
        for (DeckCardData cardData : cardsInDeck.keySet())
            deck.addAll(cardsInDeck.get(cardData));
        return deck;
    }
}