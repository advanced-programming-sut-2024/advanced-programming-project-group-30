package model.card.specialcard;

import model.Faction;
import model.Game;
import model.card.Card;

public class Decoy extends SpecialCard {

    public Decoy(String name, String explanation, Faction faction) {
        super(name, explanation, faction, false);
    }

    public void run(Card card, Game game) {
    }
}