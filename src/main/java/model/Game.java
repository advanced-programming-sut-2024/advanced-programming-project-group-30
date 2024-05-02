package model;

import model.card.SpecialCard;

import java.util.ArrayList;

public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private final ArrayList<SpecialCard> specialPosition = new ArrayList<>();
    private int turn = 1;
    private int roundNumber = 1;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public ArrayList<SpecialCard> getSpecialPosition() {
        return specialPosition;
    }

    public int getTurn() {
        return turn;
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
        turn++;
        return true;
    }

    public void endRound() {
        roundNumber++;
    }

    public GameHistory endGame() {
        return null;
    }
}