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
    //TODO:changed this from RegularCard to DecksCard
    private final ArrayList<DecksCard> cards = new ArrayList<>();
    private int rowPoint = 0;
    private boolean bonus = false;
    private int extraPoint = 0;
    private boolean isDamaged = false;
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

    public ArrayList<DecksCard> getCards() {
        return cards;
    }
    public void addCardToRow(RegularCard card){
        cards.add(card);
    }
    //TODO: changed this
    public int getRowPoint() {
        rowPoint = 0;
        for (DecksCard decksCard : cards){
            if (!(decksCard instanceof RegularCard card)) continue;
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

    public boolean getBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
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
    //TODO:added these
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
    public HashMap<CardData, ArrayList<RegularCard>> getCardDataMap(){
        return this.cardDataMap;
    }

    public void resetRow() {
        cards.clear();
        rowPoint = 0;
        bonus = false;
        extraPoint = 0;
        isDamaged = false;
        specialCardPosition.setCard(null);
        cardDataMap.clear();
        rowView.resetRow();
    }
    //TODO: added these


}
