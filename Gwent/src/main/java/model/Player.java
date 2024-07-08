package model;

import enums.CoordinateData;
import enums.CssAddress;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.card.DecksCard;
import model.card.Leader;
import model.card.SpecialCard;
import view.PlayerInformationView;
import view.PlayerView;

import java.util.ArrayList;

public class Player {
    private final User user;
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
    private PlayerView playerView;

    public Player(User user) {
        this.user = user;
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
    //TODO: changed this
    public void addCardToHand(DecksCard card){
        card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        hand.add(card);
    }
    //TODO: changed this
    public void addCardToDeck(DecksCard card){
        deck.add(card);
    }
    public void removeCardFromDeck(DecksCard card){
        deck.remove(card);
    }

    public int getPoint() {
        point = 0;
        for (Row row : getRows()){
            point += row.getRowPoint();
        }
        return point;
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
        hand.remove(decksCard);
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
    public void createPlayerView(Pane pane, VBox boardView, HBox discardPileView, HBox deckView, HBox handView, HBox leaderView, CoordinateData coordinateData, CssAddress cssAddress){
        playerView = new PlayerView(this, pane,boardView, discardPileView, deckView, handView, leaderView,coordinateData, cssAddress);
    }
    public PlayerInformationView getPlayerInformationView() {
        return playerView.getPlayerInformationView();
    }
    public void setPlayerView(PlayerView playerView){
        this.playerView = playerView;
    }

    public PlayerView getPlayerView(){
        return playerView;
    }
    public void updatePoint(int point) {
        this.point += point;
        playerView.getPlayerInformationView().updateTotalScore();
    }
    public void discardCard(DecksCard decksCard){
        discardPile.add(decksCard);
    }

    public void addToDiscardPile(DecksCard card) {
        discardPile.add(card);
    }
}
