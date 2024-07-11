package model;

import enums.cardsData.LeaderCardData;
import model.card.DecksCard;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final User user;
    private final LeaderCardData leader;
    private final ArrayList<DecksCard> deck;
    private final ArrayList<DecksCard> hand = new ArrayList<>();
    private final ArrayList<DecksCard> discardPile = new ArrayList<>();
    private final Row closeCombat = new Row("closeCombat");
    private final Row rangedCombat = new Row("rangedCombat");
    private final Row siege = new Row("siege");
    private int point = 0;
    private final int[] roundsPoint = new int[3];
    private int life = 2;

    public Player(User user, LeaderCardData leader, ArrayList<DecksCard> deck) {
        this.user = user;
        this.deck = deck;
        this.leader = leader;
        Random random = new Random();
        for (int i = 0; i < 10; i++)
            addCardToHand(deck.remove(random.nextInt(deck.size())));
    }

    public User getUser() {
        return user;
    }

    public LeaderCardData getLeader() {
        return leader;
    }

    public ArrayList<DecksCard> getDeck() {
        return deck;
    }

    public void addCardToDeck(DecksCard card) {
        deck.add(card);
    }

    public void removeCardFromDeck(DecksCard card) {
        deck.remove(card);
    }

    public ArrayList<DecksCard> getHand() {
        return hand;
    }

    public void addCardToHand(DecksCard card) {
        hand.add(card);
    }

    public ArrayList<DecksCard> getDiscardPile() {
        return discardPile;
    }

    public void discardCard(DecksCard card) {
        discardPile.add(card);
    }

    public Row getCloseCombat() {
        return closeCombat;
    }

    public Row getRangedCombat() {
        return rangedCombat;
    }

    public Row getSiege() {
        return siege;
    }

    public int getPoint() {
        point = 0;
        for (Row row : getRows())
            point += row.getRowPoint();
        return point;
    }

    public void updatePoint(int point) {
        this.point += point;
    }

    private void resetPoints() {
        point = 0;
    }

    public int[] getRoundsPoint() {
        return roundsPoint;
    }

    public void setRoundPoint(int round, int point) {
        roundsPoint[round] = point;
    }

    public ArrayList<Row> getRows() {
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(closeCombat);
        rows.add(rangedCombat);
        rows.add(siege);
        return rows;
    }

    public int getLife() {
        return life;
    }

    public void reduceLife() {
        life--;
    }

    public void playCard(DecksCard decksCard) {
        hand.remove(decksCard);
    }

    public void playCard(DecksCard decksCard, Row row) {
        // for agile
    }

    public void playCard(DecksCard decksCard, DecksCard target) {
        // for decoy
    }

    public void resetRound() {
        resetLives();
        resetPoints();
        for (Row row : getRows()) {
//            evacuateRow(row);
            row.resetRow();
        }
    }

//    private void evacuateRow(Row row) {
//        for (DecksCard decksCard : row.getCards())
//            discardPile.add(decksCard);
//    }

    private void resetLives() {
        life = 2;
    }
}