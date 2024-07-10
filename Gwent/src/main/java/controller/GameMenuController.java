package controller;

import enums.Ability;
import enums.CssAddress;
import enums.GameNotification;
import enums.RegularCardPositionType;
import enums.cardsData.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.App;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;
import view.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameMenuController {
    private final GameMenu menu;

    public GameMenuController(GameMenu menu) {
        this.menu = menu;
    }

    public void setup() {
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        menu.addInformationViews(player.getPlayerInformationView(), opponentPlayer.getPlayerInformationView());
        setupRows(player, opponentPlayer);
        handelHandsCardEvent(player.getHand(), game, player, opponentPlayer);
        handelHandsCardEvent(opponentPlayer.getHand(), game, opponentPlayer, player);
        menu.setHand(player.getHand());
        menu.setUpNotificationBox();
    }

    public void handelHandsCardEvent(ArrayList<DecksCard> handsCards, Game game, Player player, Player opponentPlayer) {
        for (DecksCard card : handsCards) {
            if (card instanceof SpecialCard) handleSpecialCardEvents((SpecialCard) card, game, player);
            if (card instanceof RegularCard) handleRegularCardEvents((RegularCard) card, game, player, opponentPlayer);
            if (card instanceof WeatherCard) handleWeatherCardEvents((WeatherCard) card, game);
        }
    }

    private void setupRows(Player player, Player opponentPlayer) {
        Row siege = player.getSiege();
        Row ranged = player.getRangedCombat();
        Row close = player.getCloseCombat();
        Row opSiege = opponentPlayer.getSiege();
        Row opRanged = opponentPlayer.getRangedCombat();
        Row opClose = opponentPlayer.getCloseCombat();
        menu.setUpBoard(siege.getRowView(), ranged.getRowView(), close.getRowView(), opSiege.getRowView(), opRanged.getRowView(), opClose.getRowView());
    }

    public Player checkForHigherScore(Game game) {
        if (game.getCurrentPlayer().getPoint() > game.getOpponentPlayer().getPoint()) return game.getCurrentPlayer();
        else if (game.getCurrentPlayer().getPoint() < game.getOpponentPlayer().getPoint())
            return game.getOpponentPlayer();
        else return null;
    }

    public void handleRegularCardEvents(RegularCard card, Game game, Player player1, Player player2) {
        CardView cardView = card.getCardView();
        cardView.setOnMouseClicked(event -> {
            RegularCardPositionType position = card.getPositionType();
            resetRowStyles(game);
            game.selectCard(card);
            try {
                Player player = player1;
                Method method = RowView.class.getDeclaredMethod("getRow");
                ArrayList<Method> getRow = new ArrayList<>();
                switch (position) {
                    case CLOSE_COMBAT:
                        getRow.add(Player.class.getDeclaredMethod("getCloseCombat"));
                        break;
                    case RANGED_COMBAT:
                        getRow.add(Player.class.getDeclaredMethod("getRangedCombat"));
                        break;
                    case SIEGE:
                        getRow.add(Player.class.getDeclaredMethod("getSiege"));
                        break;
                    case AGILE:
                        getRow.add(Player.class.getDeclaredMethod("getRangedCombat"));
                        getRow.add(Player.class.getDeclaredMethod("getCloseCombat"));
                        break;
                    case OPPONENT_CLOSE_COMBAT:
                        getRow.add(Player.class.getDeclaredMethod("getCloseCombat"));
                        player = player2;
                        break;
                    case OPPONENT_RANGED_COMBAT:
                        getRow.add(Player.class.getDeclaredMethod("getRangedCombat"));
                        player = player2;
                        break;
                    default:
                        getRow.add(Player.class.getDeclaredMethod("getSiege"));
                        player = player2;
                        break;
                }
                setRowEvent(card, game, method, getRow, player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void handleRegularCardMovement(DecksCard card, Game game, Row row) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Player player = game.getCurrentPlayer();
        HBox hbox = row.getRowView().getRow();
        resetRowStyles(game);
        menu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        menu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        if (!hbox.getChildren().contains(card.getCardView())) {
            row.addCardToRow((RegularCard) card);
            AnimationMaker.getInstance().cardPlaceAnimation(card, player.getPlayerView().getHandView(), row.getRowView().getRow(), game, menu, true);
        }
        player.playCard(card);
    }

    private void handleSpecialCardMovement(SpecialCard card, Game game, Row row) {
        resetRowStyles(game);
        menu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        menu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        AnimationMaker.getInstance().cardPlaceAnimation(card, game.getCurrentPlayer().getPlayerView().getHandView(), row.getRowView().getSpecialCardPosition(), game, menu, true);
        if (card.isDiscardAfterPlaying()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                AnimationMaker.getInstance().discardAnimation(card, row.getRowView().getSpecialCardPosition(), game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, menu);
            }));
            timeline.setCycleCount(1);
            timeline.play();
            game.getCurrentPlayer().discardCard(card);
        } else row.setSpecialCard(card);
        game.getCurrentPlayer().playCard(card);
        resetRowStyles(game);
    }

    private void handleWeatherCardMovement(WeatherCard weatherCard, Game game) {
        Player player = game.getCurrentPlayer();
        menu.removeNodeStyle(weatherCard.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        menu.setNodeStyle(weatherCard.getCardView(), CssAddress.CARD_IN_ROW);
        menu.setNodeStyle(menu.getWeatherCardPosition(), CssAddress.CARD_ROW);
        if (((WeatherCardsData) weatherCard.getCardData()).getAbility().equals(Ability.CLEAR_WEATHER)) {
            clearWeather(weatherCard, game, player.getPlayerView().getHandView(), menu.getWeatherCardPosition());
        } else {
            AnimationMaker.getInstance().cardPlaceAnimation(weatherCard, player.getPlayerView().getHandView(), menu.getWeatherCardPosition(), game, menu, true);
        }
        game.addWeatherCard(weatherCard);
        player.playCard(weatherCard);
        resetRowStyles(game);
    }

    public void handleWeatherCardEvents(WeatherCard weatherCard, Game game) {
        CardView cardView = weatherCard.getCardView();
        cardView.setOnMouseClicked(event -> {
            resetRowStyles(game);
            game.selectCard(weatherCard);
            menu.setNodeStyle(menu.getWeatherCardPosition(), CssAddress.CARD_ROW);
            menu.getWeatherCardPosition().setOnMouseClicked(event1 -> {
                handleWeatherCardMovement(weatherCard, game);
            });
        });
    }

    public void handleSpecialCardEvents(SpecialCard specialCard, Game game, Player player1) {
        CardView cardView = specialCard.getCardView();
        game.selectCard(specialCard);
        try {
            Method method = RowView.class.getDeclaredMethod("getSpecialCardPosition");
            cardView.setOnMouseClicked(event -> {
                resetRowStyles(game);
                if (((SpecialCardsData) specialCard.getCardData()).getAbility().equals(Ability.DECOY))
                    specialCard.run(game);
                else {
                    for (Row row : player1.getRows()) {
                        if (row.getRowView().getSpecialCardPosition().getChildren().isEmpty()) {
                            menu.setNodeStyle(row.getRowView().getSpecialCardPosition(), CssAddress.CARD_ROW);
                            row.getRowView().addStyle(CssAddress.CARD_ROW);
                            try {
                                handleRowEvents(specialCard, game, method, row);
                            } catch (InvocationTargetException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleRowEvents(DecksCard card, Game game, Method method, Row... rows) throws InvocationTargetException, IllegalAccessException {
        for (Row row : rows) {
            RowView rowView = row.getRowView();
            Object object = method.invoke(rowView);
            ((Node) object).setOnMousePressed(event -> {
                game.selectRow(row);
                if (card instanceof SpecialCard) handleSpecialCardMovement((SpecialCard) card, game, row);
                else {
                    try {
                        handleRegularCardMovement(card, game, row);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void removeHandler(DecksCard card) {
        card.getCardView().setOnMouseClicked(null);
    }

    public void resetRowStyles(Game game) {
        for (Row row : game.getCurrentPlayer().getRows()) {
            menu.resetStyles(row.getRowView());
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            menu.resetStyles(row.getRowView());
        }
    }

    public void updateGame(Game game) {
        updateScores(game);
        updateHandCardNumber(game);
        ArrayList<Row> allRows = new ArrayList<>();
        allRows.addAll(game.getCurrentPlayer().getRows());
        allRows.addAll(game.getOpponentPlayer().getRows());
        menu.updateScores(allRows);
        if (!game.isRoundPassed()) passTurn(game);
    }

    private void updateScores(Game game) {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        for (Row row : game.getCurrentPlayer().getRows()) {
            setCardsPoint(row);
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            setCardsPoint(row);
        }
        player.getPlayerInformationView().updateTotalScore();
        opponentPlayer.getPlayerInformationView().updateTotalScore();
    }

    private void updateHandCardNumber(Game game) {
        Player player = game.getCurrentPlayer();
        PlayerInformationView playerInformationView = player.getPlayerInformationView();
        menu.updateHandCardNumber(playerInformationView.getHandCardNumber(), player.getHand().size());
    }

    public void checkRound(Game game) {
        if (game.isRoundPassed()) {
            endRound(game);
        } else {
            passTurn(game);
            game.setRoundIsPassed(true);
        }
        //TODO: leader abilities should be considered
    }

    //TODO: complete endRound method
    private void endRound(Game game) {
        Player winner = checkForHigherScore(game);
        Player loser = null;
        Player beginner = setBeginnerOfTheRound(game);
        String message;
        if (winner == null) {
            message = GameNotification.DRAW_ROUND.getNotification();
            game.getOpponentPlayer().reduceLife();
            game.getCurrentPlayer().reduceLife();
        } else if (winner.equals(App.getCurrentGame().getCurrentPlayer())) {
            message = winner.getUser().getUsername() + " won";
            loser = game.getOpponentPlayer();
            game.getOpponentPlayer().reduceLife();
        } else {
            game.getCurrentPlayer().reduceLife();
            loser = game.getCurrentPlayer();
            message = winner.getUser().getUsername() + " won";
        }
        if (gameIsOver(game)) endGame(game);
        else {
            checkForTransformers(game);
            setUpNextRound(game, loser);
            boolean isBeginnerYou = (beginner == game.getCurrentPlayer());
            resetRound(game, isBeginnerYou);
            try {
                if (!(beginner == game.getCurrentPlayer())) {
                    switchBoard(game);
                    game.changeTurn();
                    menu.handlePassTurn(game);
                }
            } catch (NoSuchMethodException e) {
                System.err.println("error in switch board in end round method in game controller");
                throw new RuntimeException(e);
            }
            game.roundFinished();
            menu.endRound(message);
        }
    }

    private void checkForTransformers(Game game) {
        Player currentPlayer = game.getCurrentPlayer();
        for (Row row : currentPlayer.getRows()) {
            for (DecksCard decksCard : row.getCards()) {
                Method ability;
                if (!(decksCard instanceof RegularCard)) continue;
                if (((RegularCardData) decksCard.getCardData()).getAbility() != null) {
                    ability = ((RegularCard) decksCard).getAbility();
                    if (ability.equals(Ability.TRANSFORMER)) {
                        game.selectCard(decksCard);
                        game.selectRow(row);
                        decksCard.run(game);
                    }
                }
            }
        }
    }

    private void resetRound(Game game, boolean isBeginnerYou) {
//        for (Row row : game.getCurrentPlayer().getRows()) {
//            for (DecksCard decksCard : row.getCards()) {
//                AnimationMaker.getInstance().discardAnimation(decksCard, row.getRowView().getRow(),
//                        game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);
//            }
//        }
//        for (Row row : game.getOpponentPlayer().getRows()) {
//            for (DecksCard decksCard : row.getCards()) {
//                AnimationMaker.getInstance().discardAnimation(decksCard, row.getRowView().getRow(),
//                        game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);
//            }
//        }
        game.resetGame();
        //TODO: add the animation of cards being added to discard
    }

    private Player setBeginnerOfTheRound(Game game) {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) return game.getCurrentPlayer();
        else return game.getOpponentPlayer();
    }

    private boolean gameIsOver(Game game) {
        return game.getCurrentPlayer().getLife() == 0 || game.getOpponentPlayer().getLife() == 0;
    }

    private void endGame(Game game) {

        //TODO: complete this method
    }

    private void setUpNextRound(Game game, Player loser) {
        if (loser == null) {
            setLoserOfTheRound(game.getOpponentPlayer());
            setLoserOfTheRound(game.getCurrentPlayer());
        } else {
            System.out.println(loser == game.getCurrentPlayer());
            System.out.println(loser == game.getOpponentPlayer());
            if (loser == game.getCurrentPlayer()) setLoserOfTheRound(game.getCurrentPlayer());
            else setLoserOfTheRound(game.getOpponentPlayer());
        }
    }

    private void setLoserOfTheRound(Player loser) {
        if (loser.getLife() == 1) setUpLoserCrystal(loser, 1);
        else if (loser.getLife() == 0) setUpLoserCrystal(loser, 2);
    }

    private void setUpLoserCrystal(Player player, int roundNumber) {
        if (roundNumber == 1) {
            System.out.println("set up loser crystal");
            player.getPlayerView().getPlayerInformationView().setFirstRoundOfLoss();
        } else if (roundNumber == 2) {
            System.out.println("set up loser crystal");
            player.getPlayerView().getPlayerInformationView().setSecondRoundOfLoss();
        }
    }

    private void passTurn(Game game) {
        menu.disablePane();
        if (!game.isRoundPassed()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                try {
                    switchBoard(game);
                    game.changeTurn();
                    menu.handlePassTurn(game);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void setCardsPoint(Row row) {
        int point;
        boolean hasCommanderHorn = hasRegularCommanderHorn(row);
        HashMap<CardData, ArrayList<RegularCard>> cardMap = row.getCardDataMap();
        for (DecksCard decksCard : row.getCards()) {
            if (!(decksCard instanceof RegularCard card)) continue;
            if (card.isHero()) continue;
            RegularCardData regularCardData = (RegularCardData) card.getCardData();
            point = ((RegularCardData) card.getCardData()).getPoint();
            if (row.isDamaged()) point = 1;
            if (cardMap.containsKey(regularCardData)) {
                ArrayList<RegularCard> cards = cardMap.get(regularCardData);
                int coef = cards.size();
                if (coef > 1) {
                    point *= coef;
                }
            }
            point += row.getExtraPoint();
            Ability ability = ((DeckCardData) card.getCardData()).getAbility();
            SpecialCard specialCard = row.getSpecialCard();
            if (ability != null) {
                if (ability.equals(Ability.MORAL_BOOST) && row.getExtraPoint() > 0) point--;
                if (hasCommanderHorn && !ability.equals(Ability.HORN_COMMANDER)) point *= 2;
            } else if (hasCommanderHorn) point *= 2;
            if (specialCard != null) {
                if (((DeckCardData) specialCard.getCardData()).getAbility().equals(Ability.SPECIAL_COMMANDER_HORN))
                    point *= 2;
            }
            card.setPointInGame(point);
        }
    }

    private void switchBoard(Game game) throws NoSuchMethodException {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        PlayerView currentPlayerView = currentPlayer.getPlayerView();
        PlayerView opponentPlayerView = opponentPlayer.getPlayerView();
        resetRowStyles(game);

        swapBoardViews(currentPlayerView, opponentPlayerView);
        clearBoardViews(currentPlayerView, opponentPlayerView);

        swapPlayerViews(currentPlayerView, opponentPlayerView, PlayerView.class.getDeclaredMethod("getDiscardPileView"), PlayerView.class.getDeclaredMethod("getDeckView"), PlayerView.class.getDeclaredMethod("getPlayerInformationView"));

        setupViewsAfterSwitch(currentPlayerView, opponentPlayerView);

        swapRows(currentPlayer, opponentPlayer);
    }

    private boolean hasRegularCommanderHorn(Row row) {
        for (DecksCard decksCard : row.getCards()) {
            if (!(decksCard instanceof RegularCard regularCard)) continue;
            if (regularCard.isHero()) continue;
            if (((DeckCardData) regularCard.getCardData()).getAbility() != null && ((DeckCardData) regularCard.getCardData()).getAbility().equals(Ability.HORN_COMMANDER)) {
                return true;
            }
        }
        return false;
    }

    private void setRowEvent(DecksCard card, Game game, Method method, ArrayList<Method> methods, Player player) throws InvocationTargetException, IllegalAccessException {
        if (methods.size() == 2) {
            Row row1 = (Row) methods.get(0).invoke(player);
            Row row2 = (Row) methods.get(1).invoke(player);
            row1.getRowView().addStyle(CssAddress.CARD_ROW);
            row2.getRowView().addStyle(CssAddress.CARD_ROW);
            menu.setNodeStyle(row1.getRowView().getRow(), CssAddress.CARD_ROW);
            menu.setNodeStyle(row2.getRowView().getRow(), CssAddress.CARD_ROW);
            handleRowEvents(card, game, method, row1, row2);
        } else {
            Row row = (Row) methods.get(0).invoke(player);
            row.getRowView().addStyle(CssAddress.CARD_ROW);
            menu.setNodeStyle(row.getRowView().getRow(), CssAddress.CARD_ROW);
            handleRowEvents(card, game, method, row);
        }
    }

    private void swapBoardViews(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
        menu.getRowsPane().getChildren().removeAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        switchCoordinates(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        menu.getRowsPane().getChildren().addAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
    }

    private void clearBoardViews(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
        currentPlayerView.getBoardView().getChildren().clear();
        opponentPlayerView.getBoardView().getChildren().clear();
    }

    private void swapPlayerViews(PlayerView currentPlayerView, PlayerView opponentPlayerView, Method... methods) {
        try {
            for (Method method : methods) {
                Node node1 = (Node) method.invoke(currentPlayerView);
                Node node2 = (Node) method.invoke(opponentPlayerView);
                menu.getPane().getChildren().removeAll(node1, node2);
                switchCoordinates(node1, node2);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to switch player views", e);
        }
    }

    private void setupViewsAfterSwitch(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
        menu.setUpAfterSwitch(menu.getPane(), currentPlayerView.getDeckView(), opponentPlayerView.getDeckView());
        menu.setUpAfterSwitch(menu.getPane(), currentPlayerView.getDiscardPileView(), opponentPlayerView.getDiscardPileView());
        menu.setUpAfterSwitch(menu.getPane(), currentPlayerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
    }

    private void swapRows(Player currentPlayer, Player opponentPlayer) {
        switchCoordinates(currentPlayer.getCloseCombat().getRowView(), opponentPlayer.getCloseCombat().getRowView());
        switchCoordinates(currentPlayer.getRangedCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView());
        switchCoordinates(currentPlayer.getSiege().getRowView(), opponentPlayer.getSiege().getRowView());
        currentPlayer.getPlayerView().getBoardView().getChildren().addAll(currentPlayer.getSiege().getRowView(), currentPlayer.getRangedCombat().getRowView(), currentPlayer.getCloseCombat().getRowView());
        opponentPlayer.getPlayerView().getBoardView().getChildren().addAll(opponentPlayer.getCloseCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView(), opponentPlayer.getSiege().getRowView());
    }

    private void switchCoordinates(Node component1, Node component2) {
        double tempX = component1.getLayoutX();
        double tempY = component1.getLayoutY();
        component1.setLayoutX(component2.getLayoutX());
        component1.setLayoutY(component2.getLayoutY());
        component2.setLayoutX(tempX);
        component2.setLayoutY(tempY);
    }

    private void clearWeather(WeatherCard card, Game game, HBox sourceHBox, HBox destinationHBox) {
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        game.getCurrentPlayer().discardCard(card);
        TranslateTransition translate = AnimationMaker.getInstance().getTranslate(card, nodeBounds, destinationHBox, 0.4);
        translate.setOnFinished(event -> {
            sourceHBox.getChildren().remove(card.getCardView());
            destinationHBox.getChildren().add(card.getCardView());
            card.getCardView().setTranslateX(0);
            card.getCardView().setTranslateY(0);
            ArrayList<Node> nodes = new ArrayList<>(menu.getWeatherCardPosition().getChildren());
            for (Node cardView : nodes) {
                game.getCurrentPlayer().discardCard((DecksCard) ((CardView) cardView).getCard());
                AnimationMaker.getInstance().discardAnimation((DecksCard) ((CardView) cardView).getCard(), destinationHBox, game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, menu);
            }
            game.getWeatherCards().clear();
            card.run(game);
        });
        translate.play();
    }
}