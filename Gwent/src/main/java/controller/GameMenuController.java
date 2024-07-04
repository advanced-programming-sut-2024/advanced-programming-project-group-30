package controller;

import enums.CssAddress;
import enums.RegularCardPositionType;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import model.Game;
import model.Player;
import model.Result;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import view.CardView;
import view.GameMenu;
import view.PlayerInformationView;
import view.RowView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public class GameMenuController {
    private final GameMenu gameMenu;
    public GameMenuController(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }
    public void handleRegularCardMovement(DecksCard card, Game game, Row row, Method method) throws InvocationTargetException, IllegalAccessException {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        HBox hbox =(HBox) method.invoke(row.getRowView());
        resetRowStyles(game);
        gameMenu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        if (!hbox.getChildren().contains(card.getCardView())) {
            row.addCard(card, !(card instanceof RegularCard));
            if (player.getRows().contains(row)){
                player.updatePoint(card.getCardData().getPoint());
            }else opponentPlayer.updatePoint(card.getCardData().getPoint());
            hbox.getChildren().add(card.getCardView());
            gameMenu.handlePassTurn(game);
            updateScores(game);
        }
        player.setSelectedCard(null);
        updateHandCardNumber(game);
        resetRowStyles(game);
    }
    public void resetRowStyles(Game game){
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenu.resetRowStyles(row.getRowView());
        }
    }
    public void updateScores(Game game){
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        gameMenu.setUpScores(player.getRows());
        player.getPlayerInformationView().updateTotalScore();
        opponentPlayer.getPlayerInformationView().updateTotalScore();
    }
    public void handleRegularCardEvents(RegularCard card, Game game){
        CardView cardView = card.getCardView();
        Player player = game.getCurrentPlayer();
        cardView.setOnMouseClicked(event -> {
            RegularCardPositionType position = card.getPositionType();
            resetRowStyles(game);
            player.setSelectedCard(card);
            try {
                Method method = RowView.class.getDeclaredMethod("getRow");
                switch (position) {
                    case CLOSE_COMBAT:
                        RowView closeCombatRow = player.getCloseCombat().getRowView();
                        gameMenu.setNodeStyle(closeCombatRow.getRow(), CssAddress.CARD_ROW);
                       handleRowEvents(card, game, method,player.getCloseCombat());
                        break;
                    case RANGED_COMBAT:
                        RowView rangedCombatRow = player.getRangedCombat().getRowView();
                        gameMenu.setNodeStyle(rangedCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method,player.getRangedCombat());
                        break;
                    case SIEGE:
                        RowView siegeCombatRow = player.getSiege().getRowView();
                        gameMenu.setNodeStyle(siegeCombatRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method,player.getSiege());
                        break;
                    default:
                        RowView ranged = player.getRangedCombat().getRowView();
                        RowView closeRow = player.getCloseCombat().getRowView();
                        gameMenu.setNodeStyle(ranged.getRow(), CssAddress.CARD_ROW);
                        gameMenu.setNodeStyle(closeRow.getRow(), CssAddress.CARD_ROW);
                        handleRowEvents(card, game, method, player.getRangedCombat(), player.getCloseCombat());
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    public void handleSpecialCardEvents(SpecialCard specialCard, Game game) {
        CardView cardView = specialCard.getCardView();
        Player player = game.getCurrentPlayer();
        player.setSelectedCard(specialCard);
        try {
            Method method = RowView.class.getDeclaredMethod("getSpecialCardPosition");
            cardView.setOnMouseClicked(event -> {
                resetRowStyles(game);
                for (Row row : player.getRows()) {
                    if (row.getRowView().getSpecialCardPosition().getChildren().isEmpty()) {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleRowEvents(DecksCard card, Game game, Method method , Row... rows) throws InvocationTargetException, IllegalAccessException {
        for (Row row: rows) {
            RowView rowView = row.getRowView();
            Object object = method.invoke(rowView);
            ((Node)object).setOnMouseClicked(event -> {
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
                }
                else {
                    try {
                        handleRegularCardMovement(card, game, row, RowView.class.getDeclaredMethod("getRow"));
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
    public void setUpBoard(Game game){
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
    public void setUpUsername(Game game){
        Player player = game.getCurrentPlayer();
        PlayerInformationView playerInformationView = player.getPlayerInformationView();
        gameMenu.setUpUserInformation(playerInformationView.getUsernameLabel(), player.getUser().getUsername());
    }
    public void updateHandCardNumber(Game game){
        Player player = game.getCurrentPlayer();
        PlayerInformationView playerInformationView = player.getPlayerInformationView();
        gameMenu.updateHandCardNumber(playerInformationView.getHandCardNumber(), player.getHand().size());
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

    private static String showPlayerInfo(Player player) {
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

    private static int showPlayerTotalScore(Player player) {
        return 1;
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