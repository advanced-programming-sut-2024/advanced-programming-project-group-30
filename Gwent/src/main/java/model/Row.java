package model;

import enums.CoordinateData;
import enums.cardsData.RegularCardData;
import model.card.DecksCard;
import model.card.SpecialCard;
import view.RowView;

import java.util.ArrayList;
import java.util.Objects;

public class Row extends Position{
    private final String name;
    private final ArrayList<DecksCard> cards = new ArrayList<>();
    private int rowPoint = 0;
    private int bonus = 1;
    private int extraPoint = 0;
    private boolean isDamaged = false;
    private RowView rowView;
    private SpecialCardPosition specialCardPosition;

    public Row(String name) {
        this.name = name;
        rowView = new RowView(this, Objects.requireNonNull(CoordinateData.getCoordinateData(name)));
    }

    public String getName() {
        return name;
    }

    public ArrayList<DecksCard> getCards() {
        return cards;
    }

    public int getRowPoint() {
        return rowPoint;
    }

    public void setRowPoint(int rowPoint) {
        this.rowPoint = rowPoint;
    }

    public SpecialCard getSpecialCard() {
        return this.specialCardPosition.getSpecialCard();
    }

    public void setSpecialCard(SpecialCard specialCard) {
        this.specialCardPosition.setCard(specialCard);
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
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
    public RowView getRowView(){
        return rowView;
    }
    public void updateRowScore(){
        rowView.updateRowScore();
    }



}
