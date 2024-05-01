package model;

import java.util.Date;

public class GameHistory {
    private final User user1;
    private final User user2;
    private final User winner;
    private final int[][] roundsScore = new int[3][2];
    private final Date date;
}
