package model.ability;

import model.card.WeatherCard;

import java.lang.reflect.Method;

public abstract class WeatherCardAbility {
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = WeatherCardAbility.class.getDeclaredMethod(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    private void clearWeather() {
    }

    private void impenetrableFog() {
    }

    private void bitingFrost() {
    }

    private void torrentialRain() {
    }

    private void skelligeStorm() {
    }
}
