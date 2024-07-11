package model.ability;

import model.Game;
import model.Row;

import java.lang.reflect.Method;


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
    public void muster(Game currentGame){

    }
    public void scorch(Game currentGame){

    }

    public void tightBond(Game currentGame){
//        currentGame.getSelectedRow().addToCardDataMap((RegularCard) currentGame.getSelectedCard());
    }

    public void spy(Game currentGame){
//        Player currentPlayer = currentGame.getCurrentPlayer();
//        PlayerView playerView  = currentPlayer.getPlayerView();
//        ArrayList<DecksCard> deck = currentPlayer.getDeck();
//        ArrayList<DecksCard> cards = new ArrayList<>();
//        Random random = new Random();
//        int index = random.nextInt(deck.size());
//        if (deck.isEmpty()) return;
//        if (deck.size() == 1){
//            cards.add(deck.get(index));
//        }else {
//            cards.add(deck.get(index));
//            int secondIndex = random.nextInt(deck.size());
//            while (secondIndex == index){
//                secondIndex = random.nextInt(deck.size());
//            }
//            cards.add(deck.get(secondIndex));
//        }
//        for (DecksCard decksCard : cards){
//            //TODO: need access to game controller
//            currentPlayer.addCardToHand(decksCard);
//            currentPlayer.getPlayerView().addCardToDeck(decksCard);
//            Bounds nodeBounds = decksCard.getCardView().localToScene(decksCard.getCardView().getBoundsInLocal());
//            TranslateTransition translate = AnimationMaker.getInstance().getTranslate(decksCard, nodeBounds,
//                    currentGame.getCurrentPlayer().getPlayerView().getHandView(), 0.4);
//            SequentialTransition sequentialTransition = new SequentialTransition(translate);
//            sequentialTransition.setOnFinished(event -> {
//                playerView.getHandView().getChildren().add(decksCard.getCardView());
//                decksCard.getCardView().setTranslateX(0);
//                decksCard.getCardView().setTranslateY(0);
//            });
//            sequentialTransition.play();
//        }
    }
    public void berserker(Game currentGame){

    }
    public void mardroeme(Game currentGame){

    }
    public void transformer(Game currentGame){

    }
}