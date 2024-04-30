package model;

import model.card.Card;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;
    private Faction faction;
    private int life;
    private int point;
    //TODO: implement rows
    public Player(){
        hand = new ArrayList<>();
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        life = 2;
        point = 0;
    }


}
