package model.ability;

import enums.MenuScene;
import javafx.scene.layout.HBox;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import view.AnimationMaker;
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

    }

    public void decoy(Game game) {
        System.out.println("in decoy");
        Player player = game.getCurrentPlayer();
        ArrayList<Row> allRows = player.getRows();
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        for (Row row : allRows){
            for (DecksCard card : row.getCards()){
                card.getCardView().setOnMouseClicked(mouseEvent -> {
                    HBox box;
                    if (card instanceof SpecialCard) {
                        box = row.getRowView().getSpecialCardPosition();
                    }
                    else box = row.getRowView().getRow();
                    AnimationMaker.getInstance().
                            cardPlaceAnimation(card,
                                   box, player.getPlayerView().getHandView(),
                                    game, gameMenu, false);
                    AnimationMaker.getInstance().cardPlaceAnimation(game.getSelectedCard(), player.getPlayerView().getHandView(),
                            box, game, gameMenu, false);
                    game.selectCard(card);
                    game.selectRow(row);
                });
            }
        }
        if (game.getSelectedCard() instanceof SpecialCard) game.getSelectedRow().setSpecialCard((SpecialCard) game.getSelectedCard());
        else game.getSelectedRow().addCardToRow((RegularCard) game.getSelectedCard());

    }

    public void scorch(Game game){
        ArrayList<Row> allRows = new ArrayList<>();
        allRows.addAll(game.getCurrentPlayer().getRows());
        allRows.addAll(game.getOpponentPlayer().getRows());
        HashMap<RegularCard, Row> maxCards = new HashMap<>();
        int maxPoint = 0;
        for (Row row : allRows) {
            for (DecksCard card : row.getCards()) {
                if (card instanceof RegularCard regularCard && !regularCard.isHero()
                        && regularCard.getPointInGame() >= maxPoint) {
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
}