package model;

import model.card.Card;

import java.util.ArrayList;

public class Row {
    private String name;
    private ArrayList<Card> cards;
    private int rowPoint = 0;
    private Card specialCard;

    public  void setSpecialCard(Card card){}
    public  Card getSpecialCard(){return specialCard;}
    public  void addCard(Card card){}
    public  void removeCard(Card card){}
    public  ArrayList<Card> getCards(){return cards;}
    public  void addRowPoint(int rowPoint){}
    public void setRowPoint(int rowPoint){}
}
