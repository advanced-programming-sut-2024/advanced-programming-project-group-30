package model;

import java.util.Arrays;
import java.util.Date;

public class GameHistory {
    private final User user1;
    private final User user2;
    private final User winner;
    private final int[][] roundsScore = new int[2][3];
    private final Date date;

    public GameHistory(Player player1, Player player2, User winner, Date date) {
        this.user1 = player1.getUser();
        this.user2 = player2.getUser();
        for (int i = 0; i < 3; i++) {
            roundsScore[0] = player1.getRoundsPoint();
            roundsScore[1] = player2.getRoundsPoint();
        }
        this.winner = winner;
        this.date = date;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
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
        return "GameHistory{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                ", winner=" + winner +
                ", roundsScore=" + Arrays.toString(roundsScore) +
                ", date=" + date +
                '}';
    }
}
