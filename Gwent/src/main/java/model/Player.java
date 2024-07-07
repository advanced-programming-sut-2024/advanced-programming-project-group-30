package model;

import model.card.DecksCard;
import model.card.Leader;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final User user;
    private Leader leader;
    private final ArrayList<DecksCard> deck;
    private final ArrayList<DecksCard> hand = new ArrayList<>();
    private final ArrayList<DecksCard> discardPile = new ArrayList<>();
    private final Row closeCombat = new Row("closeCombat");
    private final Row rangedCombat = new Row("rangedCombat");
    private final Row siege = new Row("siege");
    private int point = 0;
    private final int[] roundsPoint = new int[3];
    private int life = 2;

    public Player(User user, ArrayList<DecksCard> deck) {
        this.user = user;
        this.deck = deck;
        Random random = new Random();
        for (int i = 0; i < 10; i++)
            hand.add(deck.remove(random.nextInt(deck.size())));
    }

    public User getUser() {
        return user;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public ArrayList<DecksCard> getDeck() {
        return deck;
    }

    public ArrayList<DecksCard> getHand() {
        return hand;
    }

    public ArrayList<DecksCard> getDiscardPile() {
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

    public void playCard(DecksCard decksCard) {

    }

    public void playCard(DecksCard decksCard, Row row) {

    }

    public void playCard(DecksCard decksCard, DecksCard target) {

    }
}
