package model.card.specialcard;

import enums.cardsData.CardData;
import model.Faction;
import model.Game;
import model.card.DecksCard;

public class Decoy extends SpecialCard {

    public Decoy(String name, String explanation, CardData cardData) {
        super(name, explanation, null, cardData, false);
    }

    public void run(DecksCard card, Game game) {
    }
}