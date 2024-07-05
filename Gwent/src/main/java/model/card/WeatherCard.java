package model.card;

import enums.FactionType;
import enums.cardsData.CardData;
import view.CardView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WeatherCard extends DecksCard{
    private final Method ability;
    public WeatherCard(String name, FactionType factionType, CardData cardData, boolean isSpecialCard, Method ability){
        super(name, factionType , cardData, isSpecialCard);
        this.ability = ability;
        super.cardView = new CardView(this);
    }
    public void run() throws InvocationTargetException, IllegalAccessException {
        ability.invoke(this);
    }
}
