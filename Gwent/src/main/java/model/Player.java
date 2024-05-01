package model;

import model.card.Card;
import model.card.Leader;

import java.util.ArrayList;

public class Player {
    private final User user;
    private final Game game;
    private Faction faction;
    private Leader leader;
    private final ArrayList<Card> deck = new ArrayList<>();
    private final ArrayList<Card> hand = new ArrayList<>();
    private final ArrayList<Card> discardPile = new ArrayList<>();
    private final Row closeCombat = new Row("closeCombat");
    private final Row rangedCombat = new Row("rangedCombat");
    private final Row siege = new Row("siege");
    private int point = 0;
    private final int[] roundsPoint = new int[3];
    private int life = 2;


    public Player(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
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

    public void playCard(Card card) {

    }

    public void playCard(Card card, Row row) {

    }

    public void playCard(Card card, Card target) {

    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int[] getRoundsPoint() {
        return roundsPoint;
    }

    public int getLife() {
        return life;
    }

    public void reduceLife() {
        life--;
    }
}