package model.card;

import enums.FactionType;
import enums.RegularCardPositionType;
import enums.cardsData.CardData;
import enums.cardsData.RegularCardData;
import view.CardView;

import java.lang.reflect.Method;

public class RegularCard extends DecksCard {
    private final boolean isHero;
    private int pointInGame;
    private final Method ability;
    private final RegularCardPositionType positionType;

    public RegularCard(String name, FactionType faction, CardData cardData, boolean isHero, int point, Method ability, RegularCardPositionType position) {
        super(name, faction,cardData, false);
        this.isHero = isHero;
        this.pointInGame = point;
        this.ability = ability;
        this.positionType = position;
        cardView = new CardView(this);
    }
    public Method getAbility() {
        return ability;
    }

    public boolean isHero() {
        return isHero;
    }

    public void resetPoint(){
        pointInGame = ((RegularCardData)getCardData()).getPoint();
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
    public void run(){

    }

    public int getPoint() {
        return ((RegularCardData)getCardData()).getPoint();
    }
}

