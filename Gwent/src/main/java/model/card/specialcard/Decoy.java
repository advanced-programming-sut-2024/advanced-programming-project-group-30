package model.card.speacialCards;

import model.Faction;
import model.Game;
import model.card.Card;
import model.card.specialcard.SpecialCard;

public class Decoy extends SpecialCard {
    private Card card;

    public Decoy(String name, String explanation, Faction faction) {
        super(name, explanation, faction, false);
    }
    public void setSelectedCard(Card card){
        this.card = card;
    }
    public Card getSelectedCard(){
        return this.card;
    }
    public void run(Card card, Game game){

    }
}
