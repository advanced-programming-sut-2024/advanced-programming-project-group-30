package model;

import java.util.Date;

public class GameHistory {
    private final String loserName;
    private final String winnerName;
    private final String opponentName;
    private final int[][] roundsScore = new int[2][3];
    private final Date date;

    public GameHistory(Player player1, Player opponent, String loserName, String winnerName, Date date) {
        this.loserName = loserName;
        int[] player1RoundsPoint = player1.getRoundsPoint();
        int[] opponentRoundsPoint = opponent.getRoundsPoint();
        for (int i = 0; i < 3; i++){
            roundsScore[0][i] = player1RoundsPoint[i];
            roundsScore[1][i] = opponentRoundsPoint[i];
        }
        this.winnerName = winnerName;
        this.opponentName = opponent.getUser().getUsername();
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getLoserName() {
        return loserName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public int[][] getRoundsScore() {
        return roundsScore;
    }
}
