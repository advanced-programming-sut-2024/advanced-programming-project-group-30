package model;

import enums.CoordinateData;
import enums.CssAddress;
import enums.FactionType;
import enums.MenuScene;
import enums.cardsData.*;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.card.DecksCard;
import view.GameMenu;
import view.PlayerView;

import java.util.*;

public class PregameData {
    private PregameUserData currentData;
    private PregameUserData anotherData;
    private final int randomStart = new Random().nextInt(2);

    public PregameData(User currentUser, User anotherUser) {
        currentData = new PregameUserData(currentUser);
        anotherData = new PregameUserData(anotherUser);
    }

    public void changeTurn() {
        PregameUserData temp = currentData;
        currentData = anotherData;
        anotherData = temp;
    }

    public User getUser() {
        return currentData.user;
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardCollection() {
        return currentData.cardCollection;
    }

    public TreeMap<DeckCardData, ArrayList<DecksCard>> getCardsInDeck() {
        return currentData.cardCollection;
    }

    public void setLeader(LeaderCardData leader) {
        currentData.leader = leader;
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

    public Player getCurrentPlayer() {
        if (randomStart == 1)
            return currentData.createPlayer(CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE, false);
        else
            return anotherData.createPlayer(CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE, false);
    }

    public Player getOpponentPlayer() {
        if (randomStart == 0)
            return currentData.createPlayer(CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE, true);
        else
            return anotherData.createPlayer(CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE, true);
    }

    private void changeNumberData(DeckCardData cardData, int sign) {
        currentData.changeNumberData(cardData, sign);
    }
}

class PregameUserData {
    final User user;
    final TreeMap<DeckCardData, ArrayList<DecksCard>> cardCollection = new TreeMap<>(CardComparator.getCardComparator());
    final TreeMap<DeckCardData, ArrayList<DecksCard>> cardsInDeck = new TreeMap<>(CardComparator.getCardComparator());
    LeaderCardData leader;
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
        cardsInDeckNumber = 0;
        unitCardsNumber = 0;
        specialCardsNumber = 0;
        totalCardsStrength = 0;
        heroCardsNumber = 0;
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
        if (cardData instanceof SpecialCardsData || cardData instanceof WeatherCardsData) specialCardsNumber = specialCardsNumber + sign;
        else {
            RegularCardData regularCardData = (RegularCardData) cardData;
            unitCardsNumber = unitCardsNumber + sign;
            totalCardsStrength = totalCardsStrength + sign * regularCardData.getPoint();
            if (regularCardData.isHero()) heroCardsNumber = heroCardsNumber + sign;
        }
    }

    Player createPlayer(CoordinateData coordinate, CssAddress cssAddress, boolean isOpponent) {
        return new Player(user, leader, getDeck(), (GameMenu) MenuScene.GAME_SCENE.getMenu(), coordinate, cssAddress, isOpponent);
    }

    private ArrayList<DecksCard> getDeck() {
        ArrayList<DecksCard> deck = new ArrayList<>();
        for (DeckCardData cardData : cardsInDeck.keySet())
            deck.addAll(cardsInDeck.get(cardData));
        return deck;
    }
}