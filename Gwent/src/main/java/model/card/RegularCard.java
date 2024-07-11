package model.card;

import enums.FactionType;
import enums.RegularCardPositionType;
import enums.cardsData.RegularCardData;
import model.Game;
import model.ability.RegularCardsAbility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private int pointInGame;
    private final RegularCardPositionType positionType;

    public RegularCard(String name, FactionType faction, boolean isHero, RegularCardPositionType position, String cardDataName, Method ability) {
        super(name, faction, cardDataName, ability);
        this.isHero = isHero;
        this.pointInGame = 0;
        this.positionType = position;
    }

    public Method getAbility() {
        return ability;
    }

    public boolean isHero() {
        return isHero;
    }

    public void resetPoint() {
        pointInGame = ((RegularCardData) getCardData()).getPoint();
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

    public void run(Game game) {
        try {
            ability.invoke(RegularCardsAbility.getInstance(), game);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("ability " + ability.getName() + " not found");
        }
    }

    public int getPoint() {
        return ((RegularCardData) getCardData()).getPoint();
    }
}