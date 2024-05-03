package model.card;

import model.ability.Ability;
import model.Faction;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;
    private final Ability ability;
    private final String position;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, Ability ability, String position, String position1) {
        super(name, explanation, faction, itIsLeader, false);
        this.isHero = isHero;
        this.point = point;
        this.ability = ability;
        this.position = position1;
    }

    public Ability getAbility() {
        return ability;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getPoint() {
        return point;
    }

    public String getPosition() {
        return position;
    }
}