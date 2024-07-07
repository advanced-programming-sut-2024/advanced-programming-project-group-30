package controller;

import enums.Ability;
import enums.CssAddress;
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
        else return game.getOpponentPlayer();
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
            AnimationMaker.getInstance().cardPlaceAnimation(card, row.getRowView().getRow(), player.getPlayerView().getHandView(), game, gameMenu);
            row.addCardToRow((RegularCard) card);
        }
        if (!game.isRoundPassed()) {
            checkPassTurn(card, game);
        }
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
        } else row.setSpecialCard(card);
        checkPassTurn(card, game);
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
        checkPassTurn(weatherCard, game);
        resetRowStyles(game);
    }

    private void handleRepeatedWeatherCard(WeatherCard weatherCard, Game game, CardView card) {
        TranslateTransition translate = AnimationMaker.getInstance().getTranslate(weatherCard, weatherCard.getCardView().localToScene(weatherCard.getCardView().getBoundsInLocal()), gameMenu.getWeatherCardPosition());
        translate.setOnFinished(event1 -> {
            game.getCurrentPlayer().getPlayerView().getHandView().getChildren().remove(card);
            gameMenu.getWeatherCardPosition().getChildren().add(card);
            card.setTranslateX(0);
            card.setTranslateY(0);
            AnimationMaker.getInstance().discardAnimation((DecksCard) card.getCard(), gameMenu.getWeatherCardPosition(), game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);

        });
        translate.play();
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

    public void handleSpecialCardEvents(SpecialCard specialCard, Game game, Player player1, Player player2) {
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

    private void checkPassTurn(DecksCard card, Game game) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), actionEvent -> {
            try {
                passTurn(game);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void resetRowStyles(Game game) {
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenu.resetStyles(row.getRowView());
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            gameMenu.resetStyles(row.getRowView());
        }
    }

    //TODO: complete row point checking
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

    private void setRowCardsPoint(Row row) {
        int point;
        boolean hasCommanderHorn = false;
        for (RegularCard regularCard : row.getCards()) {
            if (regularCard.isHero()) continue;
            if (((DeckCardData) regularCard.getCardData()).getAbility() != null && ((DeckCardData) regularCard.getCardData()).getAbility().equals(Ability.HORN_COMMANDER))
                hasCommanderHorn = true;
        }
        for (RegularCard card : row.getCards()) {
            if (card.isHero()) continue;
            point = card.getPoint();
            if (row.isDamaged()) point = 1;
            point += row.getExtraPoint();
            Ability ability = ((DeckCardData) card.getCardData()).getAbility();
            SpecialCard specialCard = row.getSpecialCard();
            Ability specialCardAbility = ((DeckCardData) card.getCardData()).getAbility();
            if (ability != null) {
                if (ability.equals(Ability.MORAL_BOOST)) point--;
                if (hasCommanderHorn && !ability.equals(Ability.HORN_COMMANDER)) point *= 2;
            }
            if (hasCommanderHorn) point *= 2;
            if (specialCard != null) {
                if (specialCardAbility.equals(Ability.SPECIAL_COMMANDER_HORN)) point *= 2;
            }
            card.setPointInGame(point);
        }

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

    //TODO: implement it with reflection
    private void passTurn(Game game) throws NoSuchMethodException {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        PlayerView currentPlayerView = currentPlayer.getPlayerView();
        PlayerView opponentPlayerView = opponentPlayer.getPlayerView();
        gameMenu.getRowsPane().getChildren().removeAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        switchCoordinate(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        gameMenu.getRowsPane().getChildren().addAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        currentPlayerView.getBoardView().getChildren().clear();
        opponentPlayerView.getBoardView().getChildren().clear();
        switchNodes(currentPlayerView, opponentPlayerView, PlayerView.class.getDeclaredMethod("getDiscardPileView"), PlayerView.class.getDeclaredMethod("getDeckView"), PlayerView.class.getDeclaredMethod("getPlayerInformationView"));
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDeckView(), opponentPlayerView.getDeckView());
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDiscardPileView(), opponentPlayerView.getDiscardPileView());
        gameMenu.setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
        switchRows(currentPlayer, opponentPlayer);
        game.changeTurn();
        gameMenu.handlePassTurn(game);
    }

    private void switchRows(Player currentPlayer, Player opponentPlayer) {
        switchCoordinate(currentPlayer.getCloseCombat().getRowView(), opponentPlayer.getCloseCombat().getRowView());
        switchCoordinate(currentPlayer.getRangedCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView());
        switchCoordinate(currentPlayer.getSiege().getRowView(), opponentPlayer.getSiege().getRowView());
        currentPlayer.getPlayerView().getBoardView().getChildren().addAll(currentPlayer.getSiege().getRowView(), currentPlayer.getRangedCombat().getRowView(), currentPlayer.getCloseCombat().getRowView());
        opponentPlayer.getPlayerView().getBoardView().getChildren().addAll(opponentPlayer.getCloseCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView(), opponentPlayer.getSiege().getRowView());
    }

    private void switchNodes(PlayerView currentPlayerView, PlayerView opponentPlayerView, Method... method) {
        Node node1, node2;
        try {
            for (Method m : method) {
                node1 = (Node) m.invoke(currentPlayerView);
                node2 = (Node) m.invoke(opponentPlayerView);
                gameMenu.getPane().getChildren().removeAll(node1, node2);
                switchCoordinate(node1, node2);
            }
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchCoordinate(Node component1, Node component2) {
        double tempX = component1.getLayoutX();
        double tempY = component1.getLayoutY();
        component1.setLayoutY(component2.getLayoutY());
        component1.setLayoutX(component2.getLayoutX());
        component2.setLayoutY(tempX);
        component2.setLayoutY(tempY);
    }

    private void clearWeather(WeatherCard card, Game game, HBox sourceHBox, HBox destinationHBox) {
        Bounds nodeBounds = card.getCardView().localToScene(card.getCardView().getBoundsInLocal());
        TranslateTransition translate = AnimationMaker.getInstance().getTranslate(card, nodeBounds, destinationHBox);
        translate.setOnFinished(event -> {
            sourceHBox.getChildren().remove(card.getCardView());
            destinationHBox.getChildren().add(card.getCardView());
            card.getCardView().setTranslateX(0);
            card.getCardView().setTranslateY(0);
            for (Node cardView : gameMenu.getWeatherCardPosition().getChildren()) {
                AnimationMaker.getInstance().discardAnimation((DecksCard) ((CardView) cardView).getCard(), destinationHBox, game.getCurrentPlayer().getPlayerView().getDiscardPileView(), game, gameMenu);
            }
            card.run(game);
        });
        translate.play();
    }

}