package controller;

import enums.Ability;
import enums.CssAddress;
import enums.GameNotification;
import enums.RegularCardPositionType;
import enums.cardsData.DeckCardData;
import enums.cardsData.WeatherCardsData;
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

public class GameMenuController {
    private final GameMenu gameMenu;

    public GameMenuController(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
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
            player1.setSelectedCard(card);
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
        gameMenu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        if (!hbox.getChildren().contains(card.getCardView())) {
            row.addCardToRow((RegularCard) card);
            AnimationMaker.getInstance().cardPlaceAnimation(card, row.getRowView().getRow(), player.getPlayerView().getHandView(), game, gameMenu);
        }
        passTurn(game);
        player.playCard(card);
        updateHandCardNumber(game);
        resetRowStyles(game);
    }

    private void handleSpecialCardMovement(SpecialCard card, Game game, Row row) {
        resetRowStyles(game);
        gameMenu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        game.getCurrentPlayer().playCard(card);
        AnimationMaker.getInstance().cardPlaceAnimation(card, row.getRowView().getSpecialCardPosition(), game.getCurrentPlayer().getPlayerView().getHandView(), game, gameMenu);
        if (card.isDiscardAfterPlaying()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                AnimationMaker.getInstance().discardAnimation(card, row.getRowView().getSpecialCardPosition(), game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);
            }));
            timeline.setCycleCount(1);
            timeline.play();
            game.getCurrentPlayer().addToDiscardPile(card);
        } else row.setSpecialCard(card);
        passTurn(game);
        game.getCurrentPlayer().playCard(card);
        updateHandCardNumber(game);

    }

    private void handleWeatherCardMovement(WeatherCard weatherCard, Game game) {
        Player player = game.getCurrentPlayer();
        gameMenu.removeNodeStyle(weatherCard.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(weatherCard.getCardView(), CssAddress.CARD_IN_ROW);
        gameMenu.setNodeStyle(gameMenu.getWeatherCardPosition(), CssAddress.CARD_ROW);
        if (((WeatherCardsData) weatherCard.getCardData()).getAbility().equals(Ability.CLEAR_WEATHER)) {
            clearWeather(weatherCard, game, player.getPlayerView().getHandView(), gameMenu.getWeatherCardPosition());
        } else {
            AnimationMaker.getInstance().cardPlaceAnimation(weatherCard, gameMenu.getWeatherCardPosition(), player.getPlayerView().getHandView(), game, gameMenu);
        }
        game.addWeatherCard(weatherCard);
        player.playCard(weatherCard);
        updateHandCardNumber(game);
        passTurn(game);
        resetRowStyles(game);
    }

    public void handleWeatherCardEvents(WeatherCard weatherCard, Game game) {
        CardView cardView = weatherCard.getCardView();
        cardView.setOnMouseClicked(event -> {
            resetRowStyles(game);
            game.getCurrentPlayer().setSelectedCard(weatherCard);
            gameMenu.setNodeStyle(gameMenu.getWeatherCardPosition(), CssAddress.CARD_ROW);
            gameMenu.getWeatherCardPosition().setOnMouseClicked(event1 -> {
                handleWeatherCardMovement(weatherCard, game);
            });
        });
    }

    public void handleSpecialCardEvents(SpecialCard specialCard, Game game, Player player1) {
        CardView cardView = specialCard.getCardView();
        player1.setSelectedCard(specialCard);
        try {
            Method method = RowView.class.getDeclaredMethod("getSpecialCardPosition");
            cardView.setOnMouseClicked(event -> {
                resetRowStyles(game);
                for (Row row : player1.getRows()) {
                    if (!specialCard.getName().equals("Decoy") && row.getRowView().getSpecialCardPosition().getChildren().isEmpty()) {
                        gameMenu.setNodeStyle(row.getRowView().getSpecialCardPosition(), CssAddress.CARD_ROW);
                        row.getRowView().addStyle(CssAddress.CARD_ROW);
                        try {
                            handleRowEvents(specialCard, game, method, row);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
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
                if (card instanceof SpecialCard) {
                    handleSpecialCardMovement((SpecialCard) card, game, row);
                } else {
                    try {
                        handleRegularCardMovement(card, game, row);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public void resetRowStyles(Game game) {
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenu.resetStyles(row.getRowView());
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            gameMenu.resetStyles(row.getRowView());
        }
    }

    public void updateScores(Game game) {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        for (Row row : game.getCurrentPlayer().getRows()) {
            setRowCardsPoint(row);
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            setRowCardsPoint(row);
        }
        player.getPlayerInformationView().updateTotalScore();
        opponentPlayer.getPlayerInformationView().updateTotalScore();
    }

    public void setUpBoard(Game game) {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        Row siege = player.getSiege();
        Row ranged = player.getRangedCombat();
        Row close = player.getCloseCombat();
        Row opSiege = opponentPlayer.getSiege();
        Row opRanged = opponentPlayer.getRangedCombat();
        Row opClose = opponentPlayer.getCloseCombat();
        gameMenu.setUpScores(game);
        gameMenu.setUpBoard(siege.getRowView(), ranged.getRowView(), close.getRowView(), opSiege.getRowView(), opRanged.getRowView(), opClose.getRowView());
    }

    public void setUpUsername(Game game) {
        Player player = game.getCurrentPlayer();
        PlayerInformationView playerInformationView = player.getPlayerInformationView();
        gameMenu.setUpUserInformation(playerInformationView.getUsernameLabel(), player.getUser().getUsername());
    }

    public void updateHandCardNumber(Game game) {
        Player player = game.getCurrentPlayer();
        PlayerInformationView playerInformationView = player.getPlayerInformationView();
        gameMenu.updateHandCardNumber(playerInformationView.getHandCardNumber(), player.getHand().size());
    }

    public void switchBoard(Game game) throws NoSuchMethodException {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        PlayerView currentPlayerView = currentPlayer.getPlayerView();
        PlayerView opponentPlayerView = opponentPlayer.getPlayerView();

        swapBoardViews(currentPlayerView, opponentPlayerView);
        clearBoardViews(currentPlayerView, opponentPlayerView);

        swapPlayerViews(currentPlayerView, opponentPlayerView, PlayerView.class.getDeclaredMethod("getDiscardPileView"), PlayerView.class.getDeclaredMethod("getDeckView"), PlayerView.class.getDeclaredMethod("getPlayerInformationView"));

        setupViewsAfterSwitch(currentPlayerView, opponentPlayerView);

        swapRows(currentPlayer, opponentPlayer);
    }

    public void checkRound(Game game) {
        if (game.isRoundPassed()) {
            endRound(game);
        } else passTurn(game);
        //TODO: leader abilities should be considered
    }

    //TODO: complete endRound method
    public GameNotification endRound(Game game) {
        Player winner = checkForHigherScore(game);
        if (winner == null) return GameNotification.DRAW_ROUND;
        else if (winner.equals(App.getCurrentGame().getCurrentPlayer())) return GameNotification.WIN_ROUND;
        else return GameNotification.LOSE_ROUND;

    }

    private void passTurn(Game game) {
        if (!game.isRoundPassed()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), actionEvent -> {
                try {
                    switchBoard(game);
                    game.changeTurn();
                    gameMenu.handlePassTurn(game);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }));

            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void setRowCardsPoint(Row row) {
        int point;
        boolean hasCommanderHorn = hasRegularCommanderHorn(row);
        for (RegularCard card : row.getCards()) {
            if (card.isHero()) continue;
            point = card.getPoint();
            if (row.isDamaged()) point = 1;
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

    private boolean hasRegularCommanderHorn(Row row) {
        for (RegularCard regularCard : row.getCards()) {
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
            gameMenu.setNodeStyle(row1.getRowView().getRow(), CssAddress.CARD_ROW);
            gameMenu.setNodeStyle(row2.getRowView().getRow(), CssAddress.CARD_ROW);
            handleRowEvents(card, game, method, row1, row2);
        } else {
            Row row = (Row) methods.get(0).invoke(player);
            row.getRowView().addStyle(CssAddress.CARD_ROW);
            gameMenu.setNodeStyle(row.getRowView().getRow(), CssAddress.CARD_ROW);
            handleRowEvents(card, game, method, row);
        }
    }

    private void swapBoardViews(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
        gameMenu.getRowsPane().getChildren().removeAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        switchCoordinates(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        gameMenu.getRowsPane().getChildren().addAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
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
                gameMenu.getPane().getChildren().removeAll(node1, node2);
                switchCoordinates(node1, node2);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to switch player views", e);
        }
    }

    private void setupViewsAfterSwitch(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDeckView(), opponentPlayerView.getDeckView());
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDiscardPileView(), opponentPlayerView.getDiscardPileView());
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
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
        game.getCurrentPlayer().addToDiscardPile(card);
        TranslateTransition translate = AnimationMaker.getInstance().getTranslate(card, nodeBounds, destinationHBox);
        translate.setOnFinished(event -> {
            sourceHBox.getChildren().remove(card.getCardView());
            destinationHBox.getChildren().add(card.getCardView());
            card.getCardView().setTranslateX(0);
            card.getCardView().setTranslateY(0);
            ArrayList<Node> nodes = new ArrayList<>(gameMenu.getWeatherCardPosition().getChildren());
            for (Node cardView : nodes) {
                game.getCurrentPlayer().addToDiscardPile((DecksCard) ((CardView) cardView).getCard());
                AnimationMaker.getInstance().discardAnimation((DecksCard) ((CardView) cardView).getCard(), destinationHBox, game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);

            }
            game.getWeatherCards().clear();
            card.run(game);
        });
        translate.play();
    }
}