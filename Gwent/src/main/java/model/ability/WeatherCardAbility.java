package model.ability;

import enums.CssAddress;
import enums.cardsData.WeatherCardsData;
import javafx.scene.layout.HBox;
import model.Game;
import model.Player;
import model.Row;
import model.card.WeatherCard;

import javax.swing.text.html.CSS;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public class WeatherCardAbility {
    private static WeatherCardAbility instance;
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
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        clearEffect(currentPlayer);
        clearEffect(opponentPlayer);
        game.getWeatherCards().clear();
    }

    private void clearEffect(Player player) {
        for (Row row : player.getRows()){
            row.getRowView().getRow().getStyleClass().
                    remove(Objects.requireNonNull(CssAddress.getCssAddress(WeatherCardsData.IMPENETRABLE_FOG.toString().toLowerCase())).getStyleClass());
            row.getRowView().getRow().getStyleClass().
                    remove(Objects.requireNonNull(CssAddress.getCssAddress(WeatherCardsData.BITING_FROST.toString().toLowerCase())).getStyleClass());
            row.getRowView().getRow().getStyleClass().
                    remove(Objects.requireNonNull(CssAddress.getCssAddress(WeatherCardsData.TORRENTIAL_RAIN.toString().toLowerCase())).getStyleClass());
        }
    }

    public void impenetrableFog(Game game) {
        ArrayList<CssAddress> cssAddresses = new ArrayList<>();
        cssAddresses.add(CssAddress.getCssAddress(WeatherCardsData.IMPENETRABLE_FOG.toString().toLowerCase()));
        try {
            setEffects(cssAddresses, findRows(Player.class.getMethod("getCloseCombat"), game));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bitingFrost(Game game) {
        ArrayList<CssAddress> cssAddresses = new ArrayList<>();
        cssAddresses.add(CssAddress.getCssAddress(WeatherCardsData.BITING_FROST.toString().toLowerCase()));
        try {
            setEffects(cssAddresses, findRows(Player.class.getMethod("getCloseCombat"), game));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void torrentialRain(Game game) {
        ArrayList<CssAddress> cssAddresses = new ArrayList<>();
        cssAddresses.add(CssAddress.getCssAddress(WeatherCardsData.TORRENTIAL_RAIN.toString().toLowerCase()));
        try {
            setEffects(cssAddresses, findRows(Player.class.getMethod("getSiege"), game));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void skelligeStorm(Game game) {
        ArrayList<CssAddress> cssAddresses = new ArrayList<>();
        cssAddresses.add(CssAddress.getCssAddress(WeatherCardsData.IMPENETRABLE_FOG.toString().toLowerCase()));
        cssAddresses.add(CssAddress.getCssAddress(WeatherCardsData.TORRENTIAL_RAIN.toString().toLowerCase()));
        ArrayList<Row> rows = new ArrayList<>();
        try {
            rows.addAll(findRows(Player.class.getMethod("getRangedCombat"), game));
            rows.addAll(findRows(Player.class.getMethod("getSiege"), game));
            setEffects(cssAddresses, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private ArrayList<Row> findRows(Method method, Game game){
        Player currentPlayer = game.getCurrentPlayer();
        Player opponetPlayer = game.getOpponentPlayer();
        ArrayList<Row> rows = new ArrayList<>();
        try {
            rows.add((Row) method.invoke(currentPlayer));
            rows.add((Row) method.invoke(opponetPlayer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }
    private void setPoints(){}
    private void setEffects(ArrayList<CssAddress> styles, ArrayList<Row> rows){
        int i = 0;
        for (Row row : rows){
            if (i <= 1)
             row.getRowView().getRow().getStyleClass().add(styles.get(0).getStyleClass());
            else row.getRowView().getRow().getStyleClass().add(styles.get(1).getStyleClass());
            i++;

        }
    }

}
