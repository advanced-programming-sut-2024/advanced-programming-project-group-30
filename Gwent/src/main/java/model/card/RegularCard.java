package model.card;

import model.ability.RegularCardsAbility;
import model.Faction;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;
    private final RegularCardsAbility ability;
    private final String position;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, RegularCardsAbility ability, String position, String position1) {
        super(name, explanation, faction, itIsLeader, false);
        this.isHero = isHero;
        this.point = point;
        this.ability = ability;
        this.position = position1;
    }

    public RegularCardsAbility getAbility() {
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