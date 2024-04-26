package model;

import model.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private HashMap<Integer, String> securityQuestions;
    private ArrayList<GameHistory> gameHistories;


    public User(String username, String password, String email, String nickName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
    }
}
