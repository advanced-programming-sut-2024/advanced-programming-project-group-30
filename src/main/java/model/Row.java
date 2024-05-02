package model;
import model.card.Card;

import java.util.ArrayList;

public class Row {
    private final String name;
    private final ArrayList<Card> cards = new ArrayList<>();
    private int rowPoint = 0;
    private Card specialCard = null;
    private boolean hasBonus = false;
    private int extraPoint = 0;
    private boolean isDamaged = false;

    public Row(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getRowPoint() {
        return rowPoint;
    }

    public void setRowPoint(int rowPoint) {
        this.rowPoint = rowPoint;
    }

    public Card getSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(Card specialCard) {
        this.specialCard = specialCard;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public void setBonus(boolean hasBonus) {
        this.hasBonus = hasBonus;
    }

    public int getExtraPoint() {
        return extraPoint;
    }

    public void setExtraPoint(int extraPoint) {
        this.extraPoint = extraPoint;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }
}