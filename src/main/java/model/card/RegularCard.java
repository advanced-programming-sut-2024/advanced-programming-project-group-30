
package model.card;

import enums.RegularCardPositionType;
import model.ability.RegularCardsAbility;
import model.Faction;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;
    private int pointInGame;
    private final RegularCardsAbility ability;
    private final RegularCardPositionType positionType;

    public RegularCard(String name, String explanation, Faction faction, boolean itIsLeader, boolean itIsSpecialCard, boolean isHero, int point, RegularCardsAbility ability, String position, RegularCardPositionType position1) {
        super(name, explanation, faction, false);
        this.isHero = isHero;
        this.point = point;
        this.pointInGame = point;
        this.ability = ability;
        this.positionType = position1;
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

    public int getPointInGame() {
        return pointInGame;
    }

    public void setPointInGame(int pointInGame) {
        this.pointInGame = pointInGame;
    }

    public RegularCardPositionType getPositionType() {
        return positionType;
    }
}
