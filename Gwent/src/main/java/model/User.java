package model;


import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private HashMap<Integer, String> securityQuestions;
    private ArrayList<GameHistory> gameHistories;
    private int rank;
    private int wins;
    private int losses;
    private int draws;


    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void addToLosses() {
        losses++;
    }

    public void addToWins() {
        wins++;
    }

    public void addToDraws() {
        draws++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public void addToGameHistory(GameHistory gameHistory) {
        gameHistories.add(gameHistory);
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


}
