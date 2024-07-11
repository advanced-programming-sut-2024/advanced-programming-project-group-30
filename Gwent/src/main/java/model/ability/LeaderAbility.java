package model.ability;

import enums.Ability;
import enums.MenuScene;
import enums.cardsData.WeatherCardsData;
import model.Game;
import model.Player;
import model.card.DecksCard;
import model.card.WeatherCard;
import view.GameMenu;

import java.lang.reflect.Method;
import java.util.ArrayList;

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
        Player currentPlayer = currentGame.getCurrentPlayer();
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        ArrayList<DecksCard> cards = new ArrayList<>();
        cards.addAll(currentPlayer.getDeck());
        cards.addAll(currentPlayer.getHand());
        for (DecksCard decksCard : cards){
            if (decksCard instanceof WeatherCard &&
                    ((WeatherCardsData)decksCard.getCardData()).getAbility().equals(Ability.IMPENETRABLE_FOG)){
                gameMenu.addWeatherCard(currentPlayer.getUser().getUsername(), (WeatherCard) decksCard, currentGame);
                decksCard.run(currentGame);
                currentGame.addWeatherCard((WeatherCard) decksCard);
                if (currentPlayer.getDeck().contains(decksCard)) {
                   if(!currentPlayer.getDeck().remove(decksCard)) System.err.println("Error in removing card from deck in siegemaster");
                }
                else if (!currentPlayer.getHand().remove(decksCard))
                    System.err.println("Error in removing card from hand in siegemaster");
                break;
            }
        }
    }

    public void theSteelForgedAbility(Game currentGame) {
        WeatherCardsData.CLEAR_WEATHER.createCard().run(currentGame);
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        gameMenu.evacuateWeatherCards(currentGame);
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