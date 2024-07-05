package controller;

import enums.CssAddress;
import enums.RegularCardPositionType;
import enums.cardsData.RegularCardData;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Game;
import model.Player;
import model.Result;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;
import view.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameMenuController {
    private final GameMenu gameMenu;

    public GameMenuController(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    private static String showPlayerInfo(Player player) {
        return null;
    }

    private static int showPlayerTotalScore(Player player) {
        return 1;
    }

    public void handleRegularCardMovement(DecksCard card, Game game, Row row, Method method) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        HBox hbox = (HBox) method.invoke(row.getRowView());
        resetRowStyles(game);
        gameMenu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        if (!hbox.getChildren().contains(card.getCardView())) {
            if (card instanceof RegularCard) {
                row.addCard(card, false);
                if (player.getRows().contains(row)) {
                    player.updatePoint(((RegularCardData) card.getCardData()).getPoint());
                } else opponentPlayer.updatePoint(((RegularCardData) card.getCardData()).getPoint());
            } else if (!card.getName().equals("Decoy")) {
                row.addCard(card, true);
            }
            updateScores(game);
        }
        passTurn(game);
        player.playCard(card);
        updateHandCardNumber(game);
        resetRowStyles(game);
    }
    public void handleWeatherCardMovement(WeatherCard weatherCard, Game game){
        Player player = game.getCurrentPlayer();
        gameMenu.removeNodeStyle(weatherCard.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(weatherCard.getCardView(), CssAddress.CARD_IN_ROW);
        gameMenu.setNodeStyle(gameMenu.getWeatherCardPosition(), CssAddress.CARD_ROW);
        gameMenu.getWeatherCardPosition().getChildren().add(weatherCard.getCardView());
        player.playCard(weatherCard);
        updateHandCardNumber(game);
        resetRowStyles(game);
    }

    public void resetRowStyles(Game game) {
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenu.resetStyles(row.getRowView());
        }
    }

    public void updateScores(Game game) {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        gameMenu.setUpScores(player.getRows());
        gameMenu.setUpScores(opponentPlayer.getRows());
        player.getPlayerInformationView().updateTotalScore();
        opponentPlayer.getPlayerInformationView().updateTotalScore();
    }

    public void handleRegularCardEvents(RegularCard card, Game game, Player player1, Player player2) {
        CardView cardView = card.getCardView();

        cardView.setOnMouseClicked(event -> {
            RegularCardPositionType position = card.getPositionType();
            resetRowStyles(game);
            player1.setSelectedCard(card);

            try {
                Method method = RowView.class.getDeclaredMethod("getRow");
                switch (position) {
                    case CLOSE_COMBAT:
                        RowView closeCombatRow = player1.getCloseCombat().getRowView();
                        gameMenu.setNodeStyle(closeCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player1.getCloseCombat());
                        break;
                    case RANGED_COMBAT:
                        RowView rangedCombatRow = player1.getRangedCombat().getRowView();
                        gameMenu.setNodeStyle(rangedCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player1.getRangedCombat());
                        break;
                    case SIEGE:
                        RowView siegeCombatRow = player1.getSiege().getRowView();
                        gameMenu.setNodeStyle(siegeCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player1.getSiege());
                        break;
                    case AGILE:
                        RowView ranged = player1.getRangedCombat().getRowView();
                        RowView closeRow = player1.getCloseCombat().getRowView();
                        gameMenu.setNodeStyle(ranged.getRow(), CssAddress.CARD_ROW);
                        gameMenu.setNodeStyle(closeRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player1.getRangedCombat(), player1.getCloseCombat());
                        break;
                    case OPPONENT_CLOSE_COMBAT:
                        RowView opponentCloseCombatRow = player2.getCloseCombat().getRowView();
                        gameMenu.setNodeStyle(opponentCloseCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player2.getCloseCombat());
                        break;
                    case OPPONENT_RANGED_COMBAT:
                        RowView opponentRangedCombatRow = player2.getRangedCombat().getRowView();
                        gameMenu.setNodeStyle(opponentRangedCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player2.getRangedCombat());
                        break;
                    case OPPONENT_SIEGE:
                        RowView opponentSiegeCombatRow = player2.getSiege().getRowView();
                        gameMenu.setNodeStyle(opponentSiegeCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player2.getSiege());
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //TODO: complete this
//    public void test(RegularCard card, Game game){
//        CardView cardView = card.getCardView();
//        Player player = game.getCurrentPlayer();
//
//        cardView.setOnMouseClicked(event -> {
//            ArrayList<String> methodNames = new ArrayList<>();
//            RegularCardPositionType position = card.getPositionType();
//            switch (position){
//                case CLOSE_COMBAT -> methodNames.add("getCloseCombat");
//                case RANGED_COMBAT -> methodNames.add("getRangedCombat");
//                case SIEGE ->  methodNames.add("getSiege");
//                case AGILE -> {
//                    methodNames.add("getCloseCombat");
//                    methodNames.add("getRangedCombat");
//                }
//            }
//        });
//    }
    public void handleWeatherCardEvents(WeatherCard weatherCard, Game game){
        CardView cardView = weatherCard.getCardView();
        cardView.setOnMouseClicked(event -> {
            resetRowStyles(game);
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
                for (Row row : player2.getRows()) {
                    if (!specialCard.getName().equals("Decoy") && row.getRowView().getSpecialCardPosition().getChildren().isEmpty()) {
                        gameMenu.setNodeStyle(row.getRowView().getSpecialCardPosition(), CssAddress.CARD_ROW);
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
            ((Node) object).setOnMouseClicked(event -> {
                if (card instanceof SpecialCard) {
                    try {
                        handleRegularCardMovement(card, game, row, RowView.class.getDeclaredMethod("getSpecialCardPosition"));
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        handleRegularCardMovement(card, game, row, RowView.class.getDeclaredMethod("getRow"));
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
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
        gameMenu.setUpScores(player.getRows());
        gameMenu.setUpBoard(siege.getRowView(), ranged.getRowView(), close.getRowView(),
                opSiege.getRowView(), opRanged.getRowView(), opClose.getRowView());
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
    private void passTurn(Game game) throws NoSuchMethodException {
        Player currentPlayer = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        PlayerView currentPlayerView = currentPlayer.getPlayerView();
        PlayerView opponentPlayerView = opponentPlayer.getPlayerView();
        //changed here
        gameMenu.getRowsPane().getChildren().removeAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        switchCoordinate(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        gameMenu.getRowsPane().getChildren().addAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
        currentPlayerView.getBoardView().getChildren().clear();
        opponentPlayerView.getBoardView().getChildren().clear();
        switchNodes(currentPlayerView, opponentPlayerView, PlayerView.class.getDeclaredMethod("getDiscardPileView"),
                PlayerView.class.getDeclaredMethod("getDeckView"),
                PlayerView.class.getDeclaredMethod("getPlayerInformationView"));
        setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDeckView(), opponentPlayerView.getDeckView());
        setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getDiscardPileView(), opponentPlayerView.getDiscardPileView());
        setUpAfterSwitch(gameMenu.getPane(), currentPlayerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
        switchRows(currentPlayer, opponentPlayer);
        game.changeTurn();
        gameMenu.handlePassTurn(game);
    }
    private void setUpAfterSwitch(Node pane, Node node1, Node node2){
        if (pane instanceof HBox)
            ((HBox) pane).getChildren().addAll(node1, node2);
        else if (pane instanceof Pane) {
            ((Pane) pane).getChildren().addAll(node1,node2);
        }

    }
    private void switchRows(Player currentPlayer, Player opponentPlayer){
        switchCoordinate(currentPlayer.getCloseCombat().getRowView(), opponentPlayer.getCloseCombat().getRowView());
        switchCoordinate(currentPlayer.getRangedCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView());
        switchCoordinate(currentPlayer.getSiege().getRowView(), opponentPlayer.getSiege().getRowView());
        currentPlayer.getPlayerView().getBoardView().getChildren().addAll(currentPlayer.getSiege().getRowView(),
                currentPlayer.getRangedCombat().getRowView(), currentPlayer.getCloseCombat().getRowView());
        opponentPlayer.getPlayerView().getBoardView().getChildren().addAll(opponentPlayer.getCloseCombat().getRowView(),
                opponentPlayer.getRangedCombat().getRowView(), opponentPlayer.getSiege().getRowView());
    }
    private void switchNodes(PlayerView currentPlayerView, PlayerView opponentPlayerView, Method... method){
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
    private void switchCoordinate(Node component1, Node component2){
        double tempX = component1.getLayoutX();
        double tempY = component1.getLayoutY();
        component1.setLayoutY(component2.getLayoutY());
        component1.setLayoutX(component2.getLayoutX());
        component2.setLayoutY(tempX);
        component2.setLayoutY(tempY);
    }
    public Result vetoCard(String cardName) {
        return null;
    }

    public Result showHand() {
        return null;
    }

    public Result showCardInHandInfo(String cardNumber) {
        return null;
    }

    public Result showRemainingCardsInfo() {
        return null;
    }

    public Result showOutOfPlayCards() {
        return null;
    }

    private String showPlayerOutOfPlayCards(Player player) {
        return null;
    }

    public Result showCardsInRow() {
        return null;
    }

    public Result showSpellCards() {
        return null;
    }

    public Result placeCardInRow(String cardName, String rowNumber) {
        return null;
    }

    public Result showCommanderInfo() {
        return null;
    }

    public Result playCommanderPower() {
        return null;
    }

    public Result showPlayersInfo() {
        return null;
    }

    public Result showPlayerLives() {
        return null;
    }

    public Result showNumberOfCardsInHand() {
        return null;
    }

    public Result showTurnInfo() {
        return null;
    }

    public Result showTotalScore() {
        return null;
    }

    public Result showTotalScoreOfRow(int rowNumber) {
        return null;
    }

    public Result passRound() {
        return null;
    }

    public Result endTurn() {
        return null;
    }

    public Result endGame() {
        return null;
    }

    public Result enterMenu(String menuName) {
        return null;
    }

    public Result exitMenu() {
        return null;
    }

}