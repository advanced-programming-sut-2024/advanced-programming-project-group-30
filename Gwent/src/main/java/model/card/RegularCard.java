package model.card;

import model.Ability;
import model.Faction;

public class RegularCard extends Card {
    private final boolean isHero;
    private final boolean hasAction;
    private final int point;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, boolean hasAction, int point, Ability ability) {
        super(name, explanation, faction, itIsLeader, itIsSpecialCard, ability);
        this.isHero = isHero;
        this.hasAction = hasAction;
        this.point = point;
    }

    public boolean isHero() {
        return isHero;
    }

    public boolean isHasAction() {
        return hasAction;
    }

    public int getPoint() {
        return point;
    }
}
