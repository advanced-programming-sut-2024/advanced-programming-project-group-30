package model.ability;

import model.Game;
import model.Player;
import model.Row;
import model.card.Card;
import model.card.DecksCard;
import model.card.RegularCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;


public class RegularCardsAbility {
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = RegularCardsAbility.class.getDeclaredMethod(name, Game.class, RegularCard.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    private void medic(Game currentGame, RegularCard card){

    }
    private void commanderHorn(Game currentGame, RegularCard card){
        Row row = currentGame.getSelectedRow();
        ArrayList<DecksCard> cards = row.getCards();
        for(DecksCard decksCard : cards){
            if (decksCard instanceof RegularCard)
                ((RegularCard) decksCard).setPointInGame(((RegularCard)decksCard).getPoint() * 2);
        }
    }
    private void moralBoost(Game currentGame, RegularCard card){
        Row row = currentGame.getSelectedRow();
        ArrayList<DecksCard> cards = row.getCards();
        for(DecksCard decksCard : cards){
            if (decksCard instanceof RegularCard)
                ((RegularCard) decksCard).setPointInGame(((RegularCard)decksCard).getPoint() + 1);
        }
    }
    private void muster(Game currentGame, RegularCard card){

    }
    private void scorch(Game currentGame, RegularCard card){

    }
    private void tightBond(Game currentGame, RegularCard card){


    }
    private void spy(Game currentGame, RegularCard card){
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Player currentPlayer = currentGame.getCurrentPlayer();
        opponentPlayer.updatePoint(card.getPoint());
        Random random = new Random();
        for (int i = 0; i < 2; i++){
            int index = random.nextInt(opponentPlayer.getDeck().size());
            DecksCard decksCard = currentPlayer.getDeck().get(index);
            currentPlayer.addCardToHand(decksCard);
            currentPlayer.removeCardFromDeck(decksCard);
        }
    }
    private void berserker(Game currentGame, RegularCard card){

    }
    private void mardroeme(Game currentGame, RegularCard card){

    }
    private void transformer(Game currentGame, RegularCard card){

    }

}

