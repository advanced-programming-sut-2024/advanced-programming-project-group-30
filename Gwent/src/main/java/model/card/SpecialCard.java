package model.card;

import model.Ability;
import model.Faction;

public class SpecialCard extends Card {
    public SpecialCard(String name, String explanation, Faction faction, Ability ability) {
        super(name, explanation, faction, false, true, ability);
    }
}
