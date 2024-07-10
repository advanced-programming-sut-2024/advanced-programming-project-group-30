package model.ability;

import enums.MenuScene;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Game;
import model.Player;
import model.Row;
import model.card.DecksCard;
import model.card.RegularCard;
import view.AnimationMaker;
import view.GameMenu;
import view.PlayerView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class RegularCardsAbility {
    private static RegularCardsAbility instance;

    private RegularCardsAbility() {
    }

    public static RegularCardsAbility getInstance() {
        if (instance == null) {
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

    public void medic(Game currentGame) {

    }

    public void commanderHorn(Game currentGame) {

    }

    public void moralBoost(Game currentGame) {
        Row row = currentGame.getSelectedRow();
        row.addExtraPoint();
    }

    public void muster(Game currentGame) {

    }

    public void scorch(Game currentGame) {
        Player opponentPlayer = currentGame.getOpponentPlayer();
        Row row = opponentPlayer.getCloseCombat();
        ArrayList<DecksCard> cards = new ArrayList<>();
        int sum = 0;
        for (DecksCard card : row.getCards())
            if (card instanceof RegularCard regularCard) sum += regularCard.getPointInGame();
        if (sum < 10) return;
        int maxPoint = 0;
        for (DecksCard card : row.getCards())
            if (card instanceof RegularCard regularCard && regularCard.getPointInGame() > maxPoint)
                maxPoint = regularCard.getPointInGame();
        for (DecksCard card : row.getCards())
            if (card instanceof RegularCard regularCard && !regularCard.isHero() &&
                    regularCard.getPointInGame() == maxPoint)
                cards.add(regularCard);
        ImageView imageView = new ImageView
                (new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/Images/Icons/anim_scorch.png"))));
        for (DecksCard card : cards) {
            row.getCards().remove(card);
            opponentPlayer.discardCard(card);
            AnimationMaker.getInstance().explosionAnimation(card, imageView, row.getRowView().getRow(),
                    opponentPlayer.getPlayerView().getDiscardPileView());
        }
    }

    //TODO: added this method
    public void tightBond(Game currentGame) {
        currentGame.getSelectedRow().addToCardDataMap((RegularCard) currentGame.getSelectedCard());
    }

    public void spy(Game currentGame) {
        Player currentPlayer = currentGame.getCurrentPlayer();
        PlayerView playerView = currentPlayer.getPlayerView();
        ArrayList<DecksCard> deck = currentPlayer.getDeck();
        ArrayList<DecksCard> cards = new ArrayList<>();
        Random random = new Random();
        int index = random.nextInt(deck.size());
        if (deck.isEmpty()) return;
        if (deck.size() == 1) {
            cards.add(deck.get(index));
        } else {
            cards.add(deck.get(index));
            int secondIndex = random.nextInt(deck.size());
            while (secondIndex == index) {
                secondIndex = random.nextInt(deck.size());
            }
            cards.add(deck.get(secondIndex));
        }
        GameMenu gameMenu = (GameMenu) MenuScene.GAME_SCENE.getMenu();
        for (DecksCard decksCard : cards) {
            currentPlayer.addCardToHand(decksCard);
            currentPlayer.getPlayerView().addCardToDeck(decksCard);
            AnimationMaker.getInstance().cardPlaceAnimation(decksCard, playerView.getDiscardPileView(), playerView.getHandView(), currentGame, gameMenu, false);
        }
        gameMenu.setHandCardEventHandler(currentPlayer, currentGame.getOpponentPlayer(), currentGame, cards);
    }

    public void berserker(Game currentGame) {

    }

    public void mardroeme(Game currentGame) {

    }

    public void transformer(Game currentGame) {

    }

}

