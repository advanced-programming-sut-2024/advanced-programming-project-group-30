package model;

import enums.FactionType;
import enums.SecurityQuestion;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private final SecurityQuestion securityQuestion;
    private final String securityAnswer;
    private boolean isLoggedIn = false;
    private int rank;
    private int wins;
    private int losses;
    private int draws;
    private int highestScore;
    private final ArrayList<GameHistory> gameHistories = new ArrayList<>();
    private final CardCollection cardCollection = new CardCollection();
    private FactionType selectedFaction = FactionType.NORTHERN_REALMS;

    public User(String username, String password, String email, String nickName, SecurityQuestion securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
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

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLosses() {
        return losses;
    }

    public void addToLosses() {
        losses++;
    }

    public int getWins() {
        return wins;
    }

    public void addToWins() {
        wins++;
    }

    public int getDraws() {
        return draws;
    }

    public void addToDraws() {
        draws++;
    }

    public ArrayList<GameHistory> getGameHistories() {
        return gameHistories;
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }

    public FactionType getSelectedFaction() {
        return selectedFaction;
    }

    public void setSelectedFaction(FactionType selectedFaction) {
        this.selectedFaction = selectedFaction;
    }

    public String getNickname() {
        return nickName;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }
}