package model.card;

import enums.FactionType;
import enums.RegularCardPositionType;
import enums.cardsData.RegularCardData;
import model.Game;
import model.ability.RegularCardsAbility;
import model.ability.WeatherCardAbility;
import view.CardView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private int pointInGame;
    private final Method ability;
    private final RegularCardPositionType positionType;

    public RegularCard(String name, FactionType faction, RegularCardData cardData, boolean isHero, Method ability, RegularCardPositionType position) {
        super(name, faction, cardData, ability, false);
        this.isHero = isHero;
        this.ability = ability;
        this.pointInGame = cardData.getPoint();
        this.positionType = position;
        this.cardView = new CardView(this);
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
        this.cardView.updatePoint();
    }

    public RegularCardPositionType getPositionType() {
        return positionType;
    }

    public void run(Game game) {
        try {
            ability.invoke(RegularCardsAbility.getInstance(),game);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("ability " + ability.getName() + " not found");
        }
    }

    public int getPoint() {
        return ((RegularCardData) getCardData()).getPoint();
    }
}