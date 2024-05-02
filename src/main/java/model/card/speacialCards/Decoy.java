package model.card.speacialCards;

import model.Faction;
import model.card.Card;
import model.card.SpecialCard;

public class Decoy extends SpecialCard {
    private Card card;

    public Decoy(String name, String explanation, Faction faction, String position) {
        super(name, explanation, faction, position, false);
    }
    public void setSelectedCard(Card card){
        this.card = card;
    }
    public Card getSelectedCard(){
        return this.card;
    }
    public void run(Card card){

    }
}
