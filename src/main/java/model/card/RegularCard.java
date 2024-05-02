package model.card;
import model.ability.Ability;
import model.Faction;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;
    private final Ability ability;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, Ability ability, String position) {
        super(name, explanation, faction, itIsLeader, false, position);
        this.isHero = isHero;
        this.point = point;
        this.ability = ability;
    }
    public Ability getAbility(){
        return ability;
    }

    public boolean isHero() {
        return isHero;
    }

    public int getPoint() {
        return point;
    }
}