package model.ability;

import enums.Ability;
import enums.CssAddress;
import enums.MenuScene;
import enums.cardsData.RegularCardData;
import javafx.scene.layout.HBox;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import view.GameMenu;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class SpecialCardAbility {
    private static SpecialCardAbility instance;

    public static SpecialCardAbility getInstance() {
        if (instance == null) {
            instance = new SpecialCardAbility();
        }
        return instance;
    }

    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = SpecialCardAbility.class.getDeclaredMethod(name, Game.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    public void commanderHorn(Game game) {
        game.getSelectedRow().setBonus(true);
    }

    public void decoy(Game game) {
        Player player = game.getCurrentPlayer();
        ArrayList<Row> allRows = player.getRows();
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        for (Row row : allRows) {
            ArrayList<DecksCard> cards = new ArrayList<>(row.getCards());
            cards.add(row.getSpecialCard());
            for (DecksCard card : row.getCards()) {
                card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
                card.getCardView().setOnMouseClicked(mouseEvent -> {
                    HBox box;
                    if (card instanceof SpecialCard) {
                        box = row.getRowView().getSpecialCardPosition();
                        row.setSpecialCard((SpecialCard) game.getSelectedCard());
                    } else {
                        box = row.getRowView().getRow();
                        if (!row.getCards().remove(card))
                            System.err.println("Error in remove card from row in decoy");
                        if (!row.getCards().add(game.getSelectedCard()))
                            System.err.println("Error in add decoy to row in decoy");
                    }
                    game.getSelectedCard().getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
                    card.getCardView().setScaleX(1);
                    card.getCardView().setScaleY(1);
                    card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
                    box.getChildren().remove(card.getCardView());
                    player.getPlayerView().getHandView().getChildren().add(card.getCardView());
                    box.getChildren().add(game.getSelectedCard().getCardView());
                    player.getPlayerView().getHandView().getChildren().remove(game.getSelectedCard().getCardView());
                    if (!player.getHand().add(card)) System.err.println("Error in add card to hand in decoy");
                    ArrayList<DecksCard> chosenCards = new ArrayList<>();
                    chosenCards.add(card);
                    gameMenu.setHandCardEventHandler(player, game.getOpponentPlayer(), game, chosenCards);
                    gameMenu.updateGame(game);
                    if (!player.getHand().remove(game.getSelectedCard())) System.err.println("Error in playing card in decoy");
                });
            }
        }
        if (game.getSelectedCard() instanceof SpecialCard)
            game.getSelectedRow().setSpecialCard((SpecialCard) game.getSelectedCard());
        else game.getSelectedRow().addCardToRow((RegularCard) game.getSelectedCard());

    }

    public void scorch(Game game) {
        ArrayList<Row> allRows = new ArrayList<>();
        allRows.addAll(game.getCurrentPlayer().getRows());
        allRows.addAll(game.getOpponentPlayer().getRows());
        HashMap<RegularCard, Row> maxCards = new HashMap<>();
        int maxPoint = 0;
        for (Row row : allRows) {
            for (DecksCard card : row.getCards()) {
                if (card instanceof RegularCard regularCard && !regularCard.isHero() && regularCard.getPointInGame() >= maxPoint) {
                    if (regularCard.getPointInGame() > maxPoint) {
                        maxCards.clear();
                    }
                    maxCards.put(regularCard, row);
                    maxPoint = regularCard.getPointInGame();
                }
            }
        }
        for (RegularCard card : maxCards.keySet()) {
            Row row = maxCards.get(card);
            row.getCards().remove(card);
            game.getCurrentPlayer().getPlayerView().discardCard(card);
            row.getRowView().getRow().getChildren().remove(card.getCardView());
        }
    }

    public void mardroeme(Game currentGame) {
        for (Row row : currentGame.getCurrentPlayer().getRows()) {
            for (DecksCard card : row.getCards()) {
                if (card instanceof RegularCard regularCard &&
                        (((RegularCardData) regularCard.getCardData())).getAbility().equals(Ability.BERKSER)) {
                    currentGame.selectCard(card);
                    currentGame.selectRow(row);
                    card.run(currentGame);
                }
            }
        }
    }

}