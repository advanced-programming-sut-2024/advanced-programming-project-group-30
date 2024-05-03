package model;

import java.util.Date;

public class GameHistory {
    private final User loser;
    private final User winner;
    private final int[][] roundsScore = new int[2][3];
    private final Date date;

    public GameHistory(Player player1, Player player2, User loser, User winner, Date date) {
        this.loser = loser;
        for (int i = 0; i < 3; i++) {
            roundsScore[0] = player1.getRoundsPoint();
            roundsScore[1] = player2.getRoundsPoint();
        }
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

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return null;
    }
}
