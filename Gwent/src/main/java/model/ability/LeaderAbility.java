package model.ability;

import enums.MenuScene;
import enums.cardsData.WeatherCardsData;
import model.Game;
import model.card.WeatherCard;
import view.CardView;
import view.GameMenu;

import java.lang.reflect.Method;

public class LeaderAbility {
    private static LeaderAbility instance;

    private LeaderAbility() {
    }

    public static LeaderAbility getInstance() {
        if (instance == null) instance = new LeaderAbility();
        return instance;
    }

    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = LeaderAbility.class.getDeclaredMethod(name, Game.class);
            method.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    public void theSiegemasterAbility(Game currentGame) {
        WeatherCard weatherCard = WeatherCardsData.IMPENETRABLE_FOG.createCard();
        currentGame.addWeatherCard(weatherCard);
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        gameMenu.getWeatherCardPosition().getChildren().add(weatherCard.getCardView());
        weatherCard.run(currentGame);
    }

    public void theSteelForgedAbility(Game currentGame) {

    }

    public void kingOfTemeriaAbility(Game currentGame) {

    }

    public void theWhiteFlameAbility(Game currentGame) {

    }

    public void lordCommanderOfTheNorthAbility(Game currentGame) {

    }

    public void sonOfMedellAbility(Game currentGame) {

    }

    public void hisImperialMajestyAbility(Game currentGame) {

    }

    public void emporerOfNilfgaardAbility(Game currentGame) {

    }

    public void theRelentlessAbility(Game currentGame) {

    }

    public void invaderOfTheNorthAbility(Game currentGame) {

    }

    public void bringerOfDeathAbility(Game currentGame) {

    }

    public void kingOfTheWildHuntAbility(Game currentGame) {

    }

    public void destroyerOfWorldsAbility(Game currentGame) {

    }

    public void commanderOfTheRedRidersAbility(Game currentGame) {

    }

    public void theTreacherousAbility(Game currentGame) {

    }

    public void queenOfDolBlathannaAbility(Game currentGame) {

    }

    public void theBeautifulAbility(Game currentGame) {

    }

    public void daisyOfTheValleyAbility(Game currentGame) {

    }

    public void pureBloodElfAbility(Game currentGame) {

    }

    public void hopeOfTheAenSeidheAbility(Game currentGame) {

    }

    public void crachAnCraiteAbility(Game currentGame) {

    }

    public void kingBranAbility(Game currentGame) {

    }
}