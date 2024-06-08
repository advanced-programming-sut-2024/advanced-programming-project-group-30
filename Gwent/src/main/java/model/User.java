package model;

import enums.SecurityQuestion;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private final HashMap<SecurityQuestion, String> securityQuestions = new HashMap<>();
    private int rank;
    private int wins;
    private int losses;
    private int draws;
    private final ArrayList<GameHistory> gameHistories = new ArrayList<>();
    private final HashMap<String, Faction> factions = new HashMap<>();
    private Faction selectedFaction;

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

    public HashMap<SecurityQuestion, String> getSecurityQuestions() {
        return securityQuestions;
    }
    public void addToSecurityQuestions(SecurityQuestion securityQuestion, String answer) {
        securityQuestions.put(securityQuestion, answer);
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

    public Faction getFactionByName(String factionName) {
        return factions.get(factionName);
    }

    public Faction getSelectedFaction() {
        return selectedFaction;
    }

    public void setSelectedFaction(Faction selectedFaction) {
        this.selectedFaction = selectedFaction;
    }

    public String getNickname() {
        return nickName;
    }
}
