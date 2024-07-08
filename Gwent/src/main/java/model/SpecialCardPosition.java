package model;

import model.card.Card;
import model.card.SpecialCard;

public class SpecialCardPosition extends Position{
    private SpecialCard card;
    public SpecialCardPosition(){
    }
    public void setCard(SpecialCard specialCard){
        card = specialCard;
    }
    public void removeCard(){
        card = null;
    }

    public SpecialCard getSpecialCard() {
        return card;
    }

}
