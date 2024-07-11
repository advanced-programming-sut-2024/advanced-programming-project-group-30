package model.ability;

import enums.Ability;
import enums.CssAddress;
import enums.MenuScene;
import enums.RegularCardPositionType;
import enums.cardsData.NeutralRegularCardsData;
import enums.cardsData.RegularCardData;
import enums.cardsData.SkelligeRegularCardsData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        currentGame.getSelectedRow().setBonus(true);
    }

    public void moralBoost(Game currentGame) {
        Row row = currentGame.getSelectedRow();
        row.addExtraPoint();
    }

    public void muster(Game currentGame) {
        Player currentPlayer = currentGame.getCurrentPlayer();
        DecksCard decksCard = currentGame.getSelectedCard();
        String name = decksCard.getName();
        String[] nameParts;
        if (name.contains(":"))nameParts = name.split(":");
        else nameParts = name.split(" ");
        name = nameParts[0];
        ArrayList<DecksCard> allCards = new ArrayList<>();
        allCards.addAll(currentPlayer.getDeck());
        allCards.addAll(currentPlayer.getHand());
        ArrayList<RegularCard> cardsToBeRemovedFromHand = new ArrayList<>();
        for (DecksCard card : allCards) {
            if (card instanceof RegularCard && (card.getName().contains(name) ||
                    card.getName().equals(name))) {
                boolean isDecksCard = currentPlayer.getDeck().contains(card);
                Row row = findCardRow(card, currentGame);
                if (row.getCards().add((RegularCard) card)) System.err.println("Error in adding cards to row in muster");;
                card.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
                if (isDecksCard) {
                    AnimationMaker.getInstance().cardPlaceAnimation(card, currentPlayer.getPlayerView().getDeckView(), row.getRowView().getRow(), currentGame,
                            (GameMenu) MenuScene.GAME_SCENE.getMenu(), false);
                    currentPlayer.getDeck().remove(card);
                } else {
                    AnimationMaker.getInstance().cardPlaceAnimation(card, currentPlayer.getPlayerView().getHandView(), row.getRowView().getRow(), currentGame,
                            (GameMenu) MenuScene.GAME_SCENE.getMenu(), false);
                    cardsToBeRemovedFromHand.add((RegularCard) card);
                }
            }
        }

        for (RegularCard regularCard : cardsToBeRemovedFromHand) {
            if (!currentPlayer.getHand().remove((DecksCard) regularCard))
                System.err.println("Error in removing card from hand in muster");;
        }

    }

    private Row findCardRow(DecksCard decksCard, Game currentGame) {
        RegularCardPositionType positionType = ((RegularCard) decksCard).getPositionType();
        return switch (positionType) {
            case CLOSE_COMBAT -> currentGame.getCurrentPlayer().getCloseCombat();
            case RANGED_COMBAT -> currentGame.getCurrentPlayer().getRangedCombat();
            default -> currentGame.getCurrentPlayer().getSiege();
        };
    }

    public void scorch(Game currentGame) {
        System.out.println("in scorch");
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
            if (card instanceof RegularCard regularCard && !regularCard.isHero() && regularCard.getPointInGame() == maxPoint)
                if (!cards.add(regularCard)) System.err.println("Error adding card to card array list in regular scorch");;
        for (DecksCard card : cards) {
            if (!row.getCards().remove(card)) System.err.println("Error removing card from row in regular scorch");;
            opponentPlayer.discardCard(card);
            row.getRowView().getRow().getChildren().remove(card.getCardView());
            opponentPlayer.getPlayerView().getDiscardPileView().getChildren().clear();
            if (opponentPlayer.getPlayerView().getDiscardPileView().getChildren().isEmpty())
                opponentPlayer.getPlayerView().getDiscardPileView().getChildren().add(card.getCardView());
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
        RegularCard regularCard;
        if (currentGame.getSelectedCard().getName().contains("Young"))
            regularCard = SkelligeRegularCardsData.TRANSFORMED_YOUNG_VILDKAARL.createCard();
        else regularCard = SkelligeRegularCardsData.TRANSFORMED_VILDKAARL.createCard();
        regularCard.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
        currentGame.getSelectedRow().getRowView().getRow().getChildren().add(regularCard.getCardView());
        currentGame.getSelectedRow().addCardToRow(regularCard);

    }

    public void mardroeme(Game currentGame) {
        for (Row row : currentGame.getCurrentPlayer().getRows()) {
            for (DecksCard card : row.getCards()) {
                if (card instanceof RegularCard regularCard && (((RegularCardData) regularCard.getCardData())).getAbility().equals(Ability.BERKSER)) {
                    row.getCards().remove(currentGame.getSelectedCard());
                    row.getRowView().getRow().getChildren().remove(currentGame.getSelectedCard().getCardView());
                    currentGame.selectCard(card);
                    currentGame.selectRow(row);
                    card.run(currentGame);
                }
            }
        }
    }

    public void transformer(Game currentGame) {
        RegularCard card = (RegularCard) currentGame.getSelectedCard();
        RegularCard regularCard;
        if (card.getName().equals("Cow")) {
            regularCard = NeutralRegularCardsData.BOVINE_DEFENSE_FORCE.createCard();
        }else regularCard = SkelligeRegularCardsData.ERMION.createCard();
        Row row = currentGame.getSelectedRow();
        regularCard.getCardView().getStyleClass().add(CssAddress.CARD_IN_ROW.getStyleClass());
        row.getRowView().getRow().getChildren().remove(card.getCardView());
        row.getRowView().getRow().getChildren().add(regularCard.getCardView());
        row.addCardToRow(regularCard);
        row.getCards().remove(card);
    }

}

