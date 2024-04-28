package model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private HashMap<Integer, String> userSecurityQuestions = new HashMap<>();
    private ArrayList<GameHistory> gameHistories = new ArrayList<>();
    private int losses = 0;
    private int wins = 0;
    private int draws = 0;
    private int rank = 0;

    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getNickName() {
        return nickName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void addSecurityQuestionAnswer(int questionNumber, String answer) {
        userSecurityQuestions.put(questionNumber, answer);
    }
    public String getSecurityQuestionAnswer(int questionNumber) {
        return userSecurityQuestions.get(questionNumber);
    }
    public void addGameHistory(GameHistory gameHistory) {
        gameHistories.add(gameHistory);
    }
    public ArrayList<GameHistory> getGameHistories() {
        return gameHistories;
    }
    public int getLosses() {
        return losses;
    }
    public int getWins() {
        return wins;
    }
    public int getDraws() {
        return draws;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void increaseWins() {
        wins++;
    }
    public void increaseLosses() {
        losses++;
    }
    public void increaseDraws() {
        draws++;
    }

}
