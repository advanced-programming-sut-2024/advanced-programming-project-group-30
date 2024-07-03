package model;

import enums.CoordinateData;
import enums.CssAddress;
import model.card.DecksCard;
import model.card.Leader;
import view.PlayerInformationView;

import java.util.ArrayList;

public class Player {
    private final User user;
    private final Game game;
    private Leader leader;
    private final ArrayList<DecksCard> deck = new ArrayList<>();
    private final ArrayList<DecksCard> hand = new ArrayList<>();
    private final ArrayList<DecksCard> discardPile = new ArrayList<>();
    private final Row closeCombat = new Row("closeCombat");
    private final Row rangedCombat = new Row("rangedCombat");
    private final Row siege = new Row("siege");
    private int point = 0;
    private final int[] roundsPoint = new int[3];
    private int life = 2;
    private DecksCard selectedCard;
    private PlayerInformationView playerInformationView;

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
    public void removeCardFromHand(DecksCard card){
        hand.remove(card);
    }
    public void addCardToHand(DecksCard card){
        hand.add(card);
    }
    public void addCardToDeck(DecksCard card){
        deck.add(card);
    }
    public void removeCardFromDeck(DecksCard card){
        deck.remove(card);
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
    public ArrayList<Row> getRows(){
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(closeCombat);
        rows.add(rangedCombat);
        rows.add(siege);
        return rows;
    }
    public void setSelectedCard(DecksCard selectedCard) {
        this.selectedCard = selectedCard;
    }
    public DecksCard getSelectedCard() {
        return selectedCard;
    }
    public void createPlayerInformationView(CoordinateData coordinateData, CssAddress cssAddress){
        playerInformationView = new PlayerInformationView(this, coordinateData, cssAddress);
    }
    public PlayerInformationView getPlayerInformationView() {
        return playerInformationView;
    }
}
