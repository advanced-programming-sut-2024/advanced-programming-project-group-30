package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import model.Game;
import model.ability.WeatherCardAbility;
import view.CardView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WeatherCard extends DecksCard{
    private final Method ability;
    private final CardView cardView;
    public WeatherCard(String name, FactionType factionType, CardData cardData, boolean isSpecialCard, Method ability){
        super(name, factionType , cardData, isSpecialCard);
        this.ability = ability;
        this.cardView = new CardView(this);
        super.cardView = this.cardView;

    }
    public void run(Game game) {
        try {
            ability.invoke(WeatherCardAbility.getInstance(),game);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
