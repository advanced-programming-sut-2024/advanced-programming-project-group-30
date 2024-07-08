package model;

import enums.CoordinateData;
import enums.cardsData.CardData;
import enums.cardsData.RegularCardData;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import view.RowView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Row extends Position{
    private final String name;
    private final ArrayList<RegularCard> cards = new ArrayList<>();
    private int rowPoint = 0;
    private int bonus = 1; //coefficient of tight bonds
    private int extraPoint = 0; //moral boost
    private boolean isDamaged = false; //weather card effects
    private RowView rowView;
    private SpecialCardPosition specialCardPosition;
    private HashMap<CardData, ArrayList<RegularCard>> cardDataMap = new HashMap<>();

    public Row(String name) {
        this.name = name;
        specialCardPosition = new SpecialCardPosition();
        rowView = new RowView(this, Objects.requireNonNull(CoordinateData.getCoordinateData(name)));
    }

    public String getName() {
        return name;
    }

    public ArrayList<RegularCard> getCards() {
        return cards;
    }
    public void addCardToRow(RegularCard card){
        cards.add(card);
    }
    public int getRowPoint() {
        rowPoint = 0;
        for (RegularCard card : cards){
            rowPoint += card.getPointInGame();
        }
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
    public void addExtraPoint(){
        this.extraPoint++;
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
    public void addToCardDataMap(RegularCard card){
        CardData cardData = card.getCardData();
        if (cardDataMap.containsKey(cardData)){
            cardDataMap.get(cardData).add(card);
        }else {
            ArrayList<RegularCard> cards = new ArrayList<>();
            cards.add(card);
            cardDataMap.put(cardData, cards);
        }
    }


}
