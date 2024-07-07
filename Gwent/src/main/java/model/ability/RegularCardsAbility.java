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
    private static RegularCardsAbility instance;
    private RegularCardsAbility(){}
    public static RegularCardsAbility getInstance(){
        if (instance == null){
            instance = new RegularCardsAbility();
        }
        return instance;
    }
    public static Method createNewAbilityByName(String name) {
        Method method = null;
        try {
            method = RegularCardsAbility.class.getDeclaredMethod(name, Game.class);
            method.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }
    public void medic(Game currentGame){

    }
    public void commanderHorn(Game currentGame){

    }
    public void moralBoost(Game currentGame){
        Row row = currentGame.getSelectedRow();
        row.addExtraPoint();
    }
    private void muster(Game currentGame){

    }
    public void scorch(Game currentGame){

    }
    public void tightBond(Game currentGame){


    }
    public void spy(Game currentGame){
        Player currentPlayer = currentGame.getCurrentPlayer();
        ArrayList<DecksCard> hand = currentPlayer.getDiscardPile();
        Random random = new Random();
        int firstCardIndex = random.nextInt(hand.size());
        int secondCardIndex = random.nextInt(hand.size());
        while (firstCardIndex == secondCardIndex){
            secondCardIndex = random.nextInt(hand.size());
        }
        DecksCard firstCard = hand.get(firstCardIndex);
        DecksCard secondCard = hand.get(secondCardIndex);
    }
    public void berserker(Game currentGame){

    }
    public void mardroeme(Game currentGame){

    }
    public void transformer(Game currentGame){

    }

}

