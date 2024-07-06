package model.ability;

import enums.CssAddress;
import enums.cardsData.WeatherCardsData;
import model.Game;
import model.Player;
import model.Row;
import model.card.WeatherCard;

import javax.swing.text.html.CSS;
import java.lang.reflect.Method;

public class WeatherCardAbility {
    private static final WeatherCardAbility instance = new WeatherCardAbility();
    public static WeatherCardAbility getInstance(){
        if (instance == null) {
            return new WeatherCardAbility();
        } return instance;
    }
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = WeatherCardAbility.class.getMethod(name, Game.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    public void clearWeather(Game game) {
        for (WeatherCard weatherCard : game.getWeatherCards()) {

        }
    }

    public void impenetrableFog(Game game) {
    }

    public void bitingFrost(Game game) {
        String effectStyle = CssAddress.getCssAddress(WeatherCardsData.BITING_FROST.toString().toLowerCase());
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        Row closeCombat = currentPlayer.getCloseCombat();
        Row opCloseCombat = opponentPlayer.getCloseCombat();
        closeCombat.getRowView().getRow().getStyleClass().add(effectStyle);
        opCloseCombat.getRowView().getRow().getStyleClass().add(effectStyle);


    }

    public void torrentialRain(Game game) {
    }

    public void skelligeStorm(Game game) {
    }
}
