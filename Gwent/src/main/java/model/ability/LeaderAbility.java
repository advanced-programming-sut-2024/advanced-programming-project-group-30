package model.ability;

import enums.Ability;
import enums.CssAddress;
import enums.MenuScene;
import enums.RegularCardPositionType;
import enums.cardsData.SpecialCardsData;
import enums.cardsData.WeatherCardsData;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;
import view.GameMenu;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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
        playWeatherCard(currentGame, Ability.IMPENETRABLE_FOG);
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
        playWeatherCard(currentGame, Ability.TORRENTIAL_RAIN);
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
        Player currentPlayer = currentGame.getCurrentPlayer();
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Random random = new Random();
        DecksCard card1 = chooseCard(currentPlayer, random);
        DecksCard card2 = chooseCard(opponentPlayer, random);
        Row row1 = null, row2 = null;
        placeCard(card1, currentPlayer, opponentPlayer, currentGame);
        placeCard(card2, opponentPlayer, currentPlayer, currentGame);
        if (!currentPlayer.getDiscardPile().remove(card1)) System.err.println("Error removing card from discard pile in invader of the north");
        if (!opponentPlayer.getDiscardPile().remove(card2)) System.err.println("Error removing card from discard pile in invader of the north");
    }
    private void placeCard(DecksCard card, Player player1, Player player2, Game game){
        if (card instanceof RegularCard regularCard){
            setCardInRow(card, Objects.requireNonNull(findRow(regularCard, player1, player2)), game);
        } else if (card instanceof WeatherCard weatherCard) {
            ((GameMenu)MenuScene.GAME_SCENE.getMenu()).addWeatherCard(weatherCard);
            weatherCard.run(game);
            game.addWeatherCard(weatherCard);
        } else {
            setCardInSpecialPosition((SpecialCard) card, player1);
        }
    }
    private DecksCard chooseCard(Player player, Random random){
        int index = random.nextInt(player.getDiscardPile().size());
        DecksCard card = player.getDiscardPile().get(index);
        if (card  instanceof RegularCard || card instanceof WeatherCard) return card;
        boolean specialCardPositionIsEmpty = false;
        for (Row row : player.getRows()){
            if (row.getSpecialCard() == null) {
                specialCardPositionIsEmpty = true;
                break;
            }
        }
        if (!specialCardPositionIsEmpty){
            while (card instanceof SpecialCard){
                card = player.getDiscardPile().get(random.nextInt(player.getDiscardPile().size()));
            }
        }
        return card;
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
        playWeatherCard(currentGame, Ability.BITING_FROST);
    }

    public void hopeOfTheAenSeidheAbility(Game currentGame) {

    }

    public void crachAnCraiteAbility(Game currentGame) {

    }

    public void kingBranAbility(Game currentGame) {

    }

    private void killMostPowerfulCard(Row row, Player opponentPlayer) {
        int point = 0;
        RegularCard powerfulCard = null;
        for (DecksCard card : row.getCards())
            if (card instanceof RegularCard regularCard) point += regularCard.getPointInGame();
        if (point < 10) return;
        int maxPoint = 0;
        for (DecksCard card : row.getCards()) {
            if (card instanceof RegularCard regularCard && !regularCard.isHero()) {
                if (powerfulCard == null) {
                    powerfulCard = regularCard;
                    maxPoint = regularCard.getPointInGame();
                } else if (regularCard.getPointInGame() > maxPoint) {
                    powerfulCard = regularCard;
                    maxPoint = regularCard.getPointInGame();
                }
            }
        }
        if (powerfulCard != null) {
            if (!row.getCards().remove(powerfulCard))
                System.err.println("Error in removing card from row in lord commander of the north");
            if (!opponentPlayer.getDiscardPile().add(powerfulCard))
                System.err.println("Error in adding card to discard pile in lord commander of the north");
            if (!row.getRowView().getRow().getChildren().remove(powerfulCard.getCardView()))
                System.err.println("Error in removing card from row view in lord commander of the north");
            opponentPlayer.getPlayerView().getDiscardPileView().getChildren().clear();
            if (!opponentPlayer.getDiscardPile().isEmpty())
                opponentPlayer.getPlayerView().getDiscardPileView().getChildren().add(powerfulCard.getCardView());
        }
    }

    private void addCommanderHornToRow(Row row) {
        SpecialCard commanderHorn = SpecialCardsData.COMMANDER_HORN.createCard();
        if (row.getSpecialCard() == null) {
            row.setSpecialCard(commanderHorn);
            commanderHorn.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
            row.getRowView().getSpecialCardPosition().getChildren().clear();
            row.getRowView().getSpecialCardPosition().getChildren().add(commanderHorn.getCardView());
            row.setBonus(true);
        }
    }

    private Row findRow(RegularCard regularCard, Player currentPlayer, Player opponentPlayer) {
        return switch (regularCard.getPositionType()) {
            case CLOSE_COMBAT, AGILE -> currentPlayer.getCloseCombat();
            case RANGED_COMBAT -> currentPlayer.getRangedCombat();
            case SIEGE -> currentPlayer.getSiege();
            case OPPONENT_CLOSE_COMBAT -> opponentPlayer.getCloseCombat();
            case OPPONENT_RANGED_COMBAT -> opponentPlayer.getRangedCombat();
            case OPPONENT_SIEGE -> opponentPlayer.getSiege();
            default -> null;
        };
    }

    private void setCardInRow(DecksCard card, Row row, Game game) {
        ((RegularCard) card).setPointInGame(((RegularCard) card).getPoint());
        if (!row.getCards().add(card)) System.err.println("Error in add decoy to row in decoy");
        row.getRowView().updateRowScore();
        card.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
        if (!row.getRowView().getRow().getChildren().contains(card.getCardView()))
            row.getRowView().getRow().getChildren().add(card.getCardView());
        else System.err.println("Duplicate children in setCardInRow in leader");
        card.getCardView().setTranslateX(0);
        card.getCardView().setTranslateY(0);
        card.run(game);
    }
    private void setCardInSpecialPosition(SpecialCard specialCard, Player player){
        Row position = null;
        for (Row row : player.getRows()){
            if (row.getSpecialCard() == null){
                position = row;
                break;
            }
        }
        assert position != null;
        position.setSpecialCard(specialCard);
        position.getRowView().getSpecialCardPosition().getChildren().clear();
        if (!position.getRowView().getSpecialCardPosition().getChildren().contains(specialCard.getCardView()))
            position.getRowView().getSpecialCardPosition().getChildren().add(specialCard.getCardView());

    }
    private void playWeatherCard(Game currentGame, Ability ability){
        Player currentPlayer = currentGame.getCurrentPlayer();
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        ArrayList<DecksCard> cards = new ArrayList<>();
        cards.addAll(currentPlayer.getDeck());
        cards.addAll(currentPlayer.getHand());
        for (DecksCard decksCard : cards) {
            if (decksCard instanceof WeatherCard && ((WeatherCardsData) decksCard.getCardData()).getAbility().equals(ability)) {
                gameMenu.addWeatherCard((WeatherCard) decksCard);
                decksCard.run(currentGame);
                currentGame.addWeatherCard((WeatherCard) decksCard);
                if (currentPlayer.getDeck().contains(decksCard)) {
                    if (!currentPlayer.getDeck().remove(decksCard))
                        System.err.println("Error in removing card from deck in siegemaster");
                } else if (!currentPlayer.getHand().remove(decksCard))
                    System.err.println("Error in removing card from hand in siegemaster");
                break;
            }
        }
    }
}