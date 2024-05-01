package model.card;

import model.Ability;
import model.Faction;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, Ability ability, String position) {
        super(name, explanation, faction, itIsLeader, ability, false, position);
        this.isHero = isHero;
        this.point = point;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getPoint() {
        return point;
    }
}
