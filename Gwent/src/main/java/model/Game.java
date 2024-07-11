package model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import model.card.DecksCard;
import model.card.WeatherCard;


public class Game {
    private Player currentPlayer;
    private Player opponentPlayer;
    private int roundNumber = 1;
    private Row selectedRow;
    private DecksCard selectedCard;
    private ArrayList<WeatherCard> weatherCards = new ArrayList<>();
    private boolean roundIsPassed = false;

    public Game(PregameData gameData) {
        currentPlayer = gameData.getCurrentPlayer();
        opponentPlayer = gameData.getOpponentPlayer();
    }

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

    public void endGame() {
        String winnerName;
        String loserName;
        if (currentPlayer.getLife() == 0) {
            winnerName = currentPlayer.getUser().getUsername();
            loserName = opponentPlayer.getUser().getUsername();
        }
        else {
            winnerName = opponentPlayer.getUser().getUsername();
            loserName = currentPlayer.getUser().getUsername();
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM H");
        String formattedDateTime = localDateTime.format(formatter);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM H");
        Date date = null;
        try {
             date = sdf.parse(formattedDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GameHistory gameHistory = new GameHistory(currentPlayer, opponentPlayer, loserName, winnerName, date);
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

    //TODO:added this
    public void resetGame() {
        currentPlayer.resetRound();
        opponentPlayer.resetRound();
        selectedRow = null;
        selectedCard = null;
        weatherCards.clear();
        this.roundIsPassed = false;
    }

    public void roundFinished() {

        currentPlayer.setRoundPoint(roundNumber - 1, currentPlayer.getPoint());
        opponentPlayer.setRoundPoint(roundNumber - 1, opponentPlayer.getPoint());
        endRound();
    }
}