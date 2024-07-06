package model.card;

import enums.FactionType;
import enums.RegularCardPositionType;
import enums.cardsData.CardData;
import model.ability.RegularCardsAbility;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private final int point;
    private int pointInGame;
    private final RegularCardsAbility ability;
    private final RegularCardPositionType positionType;

    public RegularCard(String name, FactionType faction, CardData cardData, boolean isHero, int point, RegularCardsAbility ability, RegularCardPositionType position) {
        super(name, faction,cardData, false);
        this.isHero = isHero;
        this.point = point;
        this.pointInGame = point;
        this.ability = ability;
        this.positionType = position;
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