package model;


import java.util.ArrayList;

import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;


public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private int roundNumber = 1;
    private Row selectedRow;
    private DecksCard selectedCard;
    private ArrayList<WeatherCard> weatherCards = new ArrayList<>();
    private boolean roundIsPassed = false;
    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void addWeatherCard(WeatherCard weatherCard) {
        weatherCards.add(weatherCard);
    }
    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void changeTurn() {
        Player temp = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = temp;
    }

    public void endRound() {
        roundNumber++;
    }
    public GameHistory endGame() {
        return null;
    }
    public void selectRow(Row row) {
        selectedRow = row;
    }
    public Row getSelectedRow() {
        return selectedRow;
    }
    public ArrayList<WeatherCard> getWeatherCards() {
        return weatherCards;
    }
    public boolean isRoundPassed() {
        return roundIsPassed;
    }
    public void setRoundIsPassed(boolean roundIsPassed) {
        this.roundIsPassed = roundIsPassed;
    }

    public DecksCard getSelectedCard() {
        return selectedCard;
    }
    public void selectCard(DecksCard card) {
        selectedCard = card;
    }
}
