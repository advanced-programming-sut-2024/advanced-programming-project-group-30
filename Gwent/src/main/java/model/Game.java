package model;


import java.util.ArrayList;

import model.card.Card;
import model.card.specialcard.SpecialCard;



public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private final ArrayList<SpecialCard> specialPosition = new ArrayList<>();
    private int roundNumber = 1;
    private Card selectedCard;
    private Row selectedRow;
    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public ArrayList<SpecialCard> getSpecialPosition() {
        return specialPosition;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addInSpecialPosition(SpecialCard specialCard) {
        specialPosition.add(specialCard);
    }

    public void cleanSpecialPosition(SpecialCard specialCard) {
        specialPosition.clear();
    }

    public boolean changeTurn() {
        return true;
    }

    public void endRound() {
        roundNumber++;
    }

    public GameHistory endGame() {
        return null;
    }
    public void selectCard(Card card) {
        selectedCard = card;
    }
    public Card getSelectedCard() {
        return selectedCard;
    }
    public void selectRow(Row row) {
        selectedRow = row;
    }
    public Row getSelectedRow() {
        return selectedRow;
    }
}
