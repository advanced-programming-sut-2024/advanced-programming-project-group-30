package model.card;

import enums.FactionType;
import enums.SecurityQuestion;
import model.GameHistory;
import model.User;

import java.util.ArrayList;

public class UserData {
    private String username;
    private String nickname;
    private String email;
    private String password;
    private SecurityQuestion securityQuestion;
    private String questionAnswer;
    private int wins;
    private int losses;
    private int draws;
    private int highestScore;
    private int rank;
    private FactionType factionType;
    private ArrayList<GameHistory> gameHistories;
    public UserData(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.securityQuestion = user.getSecurityQuestion();
        this.questionAnswer = user.getSecurityAnswer();
        this.factionType = user.getSelectedFaction();
        this.gameHistories = user.getGameHistories();
        this.wins = user.getWins();
        this.losses = user.getLosses();
        this.draws = user.getDraws();
        this.highestScore = user.getHighestScore();
    }

    public String getNickname() {
        return nickname;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public int getRank() {
        return rank;
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

    public int getHighestScore() {
        return highestScore;
    }

    public FactionType getFactionType() {
        return factionType;
    }

    public ArrayList<GameHistory> getGameHistories() {
        return gameHistories;
    }
}
