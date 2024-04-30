package model.card;

import model.Ability;
import model.Faction;

public class Leader extends Card {
    public Leader(String name, String explanation,Faction faction, Ability ability) {
        super(name, explanation,faction, true, false, ability);
    }
}