package model.ability;

import model.Game;
import model.Row;
import model.card.Card;
import model.card.DecksCard;
import model.card.RegularCard;
import view.AnimationMaker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SpecialCardAbility {
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = SpecialCardAbility.class.getDeclaredMethod(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    public void commanderHorn(){

    }
    public void decoy(){

    }
    public void scorch(Game game){
        ArrayList<Row> allRows = new ArrayList<>();
        allRows.addAll(game.getCurrentPlayer().getRows());
        allRows.addAll(game.getOpponentPlayer().getRows());
        HashMap<RegularCard, Row> maxCards = new HashMap<>();
        int maxPoint = 0;
        for (Row row : allRows) {
            for (DecksCard card : row.getCards()) {
                if (card instanceof RegularCard regularCard) {
                    if (regularCard.getPointInGame() >= maxPoint) {
                        maxCards.put(regularCard, row);
                    }
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
