package controller;

import enums.CssAddress;
import enums.RegularCardPositionType;
import javafx.scene.layout.HBox;
import model.Game;
import model.Player;
import model.Result;
import model.Row;
import model.card.RegularCard;
import view.CardView;
import view.GameMenu;
import view.RowView;

public class GameMenuController {
    private final GameMenu gameMenu;
    public GameMenuController(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }

    public void handleRegularCardMovement(RegularCard card, Game game, HBox box){
        Player player = game.getCurrentPlayer();
        gameMenu.removeNodeStyle(box, CssAddress.CARD_ROW);
        gameMenu.removeNodeStyle(card.getCardView(), CssAddress.GAME_HAND_SM_CARD);
        gameMenu.setNodeStyle(card.getCardView(), CssAddress.CARD_IN_ROW);
        if (!box.getChildren().contains(card.getCardView()))
            box.getChildren().add(card.getCardView());
        player.setSelectedCard(null);
        updateScores(game);
        resetRowStyles(game);
    }
    public void resetRowStyles(Game game){
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenu.resetRowStyles(row.getRowView());
        }
    }
    public void updateScores(Game game){
        Player player = game.getCurrentPlayer();
        gameMenu.setUpScores(player.getRows());
    }
    public void handleRegularCardEvents(RegularCard card, Game game){
        CardView cardView = card.getCardView();
        Player player = game.getCurrentPlayer();
        cardView.setOnMouseClicked(event -> {
            RegularCardPositionType position = card.getPositionType();
            resetRowStyles(game);
            player.setSelectedCard(card);
            switch (position) {
                case CLOSE_COMBAT:
                    RowView closeCombatRow = player.getCloseCombat().getRowView();
                    gameMenu.setNodeStyle(closeCombatRow, CssAddress.CARD_ROW);
                    handleRowEvents(card,game ,closeCombatRow.getRowView());
                    break;
                case RANGED_COMBAT:
                    RowView rangedCombatRow = player.getRangedCombat().getRowView();
                    gameMenu.setNodeStyle(rangedCombatRow, CssAddress.CARD_ROW);
                    handleRowEvents(card,game, rangedCombatRow.getRowView());
                    break;
                case SIEGE:
                    RowView siegeCombatRow = player.getSiege().getRowView();
                    gameMenu.setNodeStyle(siegeCombatRow, CssAddress.CARD_ROW);
                    handleRowEvents(card,game, siegeCombatRow.getRowView());
                    break;
                default:
                    RowView siegeRow = player.getSiege().getRowView();
                    RowView closeRow = player.getCloseCombat().getRowView();
                    gameMenu.setNodeStyle(siegeRow, CssAddress.CARD_ROW);
                    gameMenu.setNodeStyle(closeRow, CssAddress.CARD_ROW);
                    handleRowEvents(card, game,siegeRow.getRowView(), closeRow.getRowView());
                    break;
            }
        });
    }
    public void handleRowEvents(RegularCard card, Game game, HBox... boxes){
        for (HBox box : boxes) {
            box.setOnMouseClicked(event -> {
                handleRegularCardMovement(card, game, box);
            });
        }
    }
    public void setUpBoard(Game game){
        Player player = game.getCurrentPlayer();
        Row siege = player.getSiege();
        Row ranged = player.getRangedCombat();
        Row close = player.getCloseCombat();
        gameMenu.setUpScores(player.getRows());
        gameMenu.setUpBoard(siege.getRowView(), ranged.getRowView(), close.getRowView());
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