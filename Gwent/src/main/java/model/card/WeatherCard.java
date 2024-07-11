package model.card;

import enums.FactionType;
import model.Game;
import model.ability.WeatherCardAbility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WeatherCard extends DecksCard {
    public WeatherCard(String name, FactionType factionType, String cardDataName, Method method) {
        super(name, factionType, cardDataName, method);
    }

    public void run(Game game) {
        try {
            ability.invoke(WeatherCardAbility.getInstance(), game);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}