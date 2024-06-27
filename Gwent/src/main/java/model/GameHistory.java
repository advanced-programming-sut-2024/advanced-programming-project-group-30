package model;

import java.util.Date;

public class GameHistory {
    private final User loser;
    private final User winner;
    private final User opponent;
    private final int[][] roundsScore = new int[2][3];
    private final Date date;

    public GameHistory(Player player1, Player opponent, User loser, User winner, Date date) {
        this.loser = loser;
        int[] player1RoundsPoint = player1.getRoundsPoint();
        int[] opponentRoundsPoint = opponent.getRoundsPoint();
        for (int i = 0; i < 3; i++){
            roundsScore[0][i] = player1RoundsPoint[i];
            roundsScore[1][i] = opponentRoundsPoint[i];
        }
        this.opponent = opponent.getUser();
        this.winner = winner;
        this.date = date;
    }

    public User getLoser() {
        return loser;
    }

    public User getWinner() {
        return winner;
    }

    public int[][] getRoundsScore() {
        return roundsScore;
    }
    public User getOpponent() {
        return opponent;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return null;
    }
}
