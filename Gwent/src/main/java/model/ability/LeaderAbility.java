package model.ability;

import enums.Ability;
import enums.CssAddress;
import enums.MenuScene;
import enums.cardsData.SpecialCardsData;
import enums.cardsData.WeatherCardsData;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;
import view.AnimationMaker;
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
        Player currentPlayer = currentGame.getCurrentPlayer();
        Row row = currentPlayer.getSiege();
        addCommanderHornToRow(row);
    }

    public void theWhiteFlameAbility(Game currentGame) {

    }

    public void lordCommanderOfTheNorthAbility(Game currentGame) {
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Row row = opponentPlayer.getSiege();
        killMostPowerfulCard(row, opponentPlayer);
    }

    public void sonOfMedellAbility(Game currentGame) {
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Row row = opponentPlayer.getRangedCombat();
        killMostPowerfulCard(row, opponentPlayer);
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
        Player currentPlayer = currentGame.getCurrentPlayer();
        Row row = currentPlayer.getCloseCombat();
        addCommanderHornToRow(row);
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
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Row row = opponentPlayer.getCloseCombat();
        killMostPowerfulCard(row, opponentPlayer);
    }

    public void theBeautifulAbility(Game currentGame) {
        Player currentPlayer = currentGame.getCurrentPlayer();
        Row row = currentPlayer.getRangedCombat();
        addCommanderHornToRow(row);
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
    private void killMostPowerfulCard(Row row, Player opponentPlayer){
        int point = 0;
        RegularCard powerfulCard = null;
        for (DecksCard card : row.getCards())
            if (card instanceof RegularCard regularCard) point += regularCard.getPointInGame();
        if (point < 10) return;
        int maxPoint = 0;
        for (DecksCard card : row.getCards()){
            if (card instanceof RegularCard regularCard && !regularCard.isHero()){
                if (powerfulCard == null) {
                    powerfulCard = regularCard;
                    maxPoint = regularCard.getPointInGame();
                } else if (regularCard.getPointInGame() > maxPoint){
                    powerfulCard = regularCard;
                    maxPoint = regularCard.getPointInGame();
                }
            }
        }
        if (powerfulCard != null) {
            if(!row.getCards().remove(powerfulCard)) System.err.println("Error in removing card from row in lord commander of the north");
            if (!opponentPlayer.getDiscardPile().add(powerfulCard)) System.err.println("Error in adding card to discard pile in lord commander of the north");
            if (!row.getRowView().getRow().getChildren().remove(powerfulCard.getCardView())) System.err.println("Error in removing card from row view in lord commander of the north");
            opponentPlayer.getPlayerView().getDiscardPileView().getChildren().clear();
            if (!opponentPlayer.getDiscardPile().isEmpty())
                opponentPlayer.getPlayerView().getDiscardPileView().getChildren().add(powerfulCard.getCardView());
        }
    }
    private void addCommanderHornToRow(Row row){
        SpecialCard commanderHorn = SpecialCardsData.COMMANDER_HORN.createCard();
        if (row.getSpecialCard() == null) {
            row.setSpecialCard(commanderHorn);
            commanderHorn.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
            row.getRowView().getSpecialCardPosition().getChildren().clear();
            row.getRowView().getSpecialCardPosition().getChildren().add(commanderHorn.getCardView());
            row.setBonus(true);
        }
    }
}