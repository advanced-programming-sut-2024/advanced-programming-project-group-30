package model;

import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import model.card.DecksCard;
import view.LgCard;
import view.PreGameCardView;

import java.util.ArrayList;
import java.util.Collections;

public class PreGameData {
    private final User user;
    private final ArrayList<DecksCard> cardCollectionList;
    private final ArrayList<DecksCard> preDeck = new ArrayList<>();
    private final FlowPane cardCollection = new FlowPane();
    private final FlowPane cardInDeck = new FlowPane();

    public PreGameData(User user) {
        this.user = user;
        this.cardCollectionList = user.getCardCollection().getCardsByFactionsName(user.getSelectedFaction());
        cardCollection.setStyle("-fx-padding: 4.72; -fx-vgap: 4.72; -fx-hgap: 4.72");
        cardInDeck.setStyle("-fx-padding: 4.72; -fx-vgap: 4.72; -fx-hgap: 4.72");
        ArrayList<DecksCard> cards = new ArrayList<>();
        outsideFor:
        for (int i = 0; i < cardCollectionList.size(); i++) {
            while (i < cardCollectionList.size() - 1 && cardCollectionList.get(i).sames(cardCollectionList.get(i + 1))) {
                cards.add(cardCollectionList.get(i));
                continue outsideFor;
            }
            cards.add(cardCollectionList.get(i));
            PreGameCardView preGameCardView = new PreGameCardView(new LgCard(cardCollectionList.get(i).getCardData(), true),
                    cardCollectionList.get(i).getCardData().getNumber(), cards);
            cardCollection.getChildren().add(preGameCardView);
            preGameCardView.setOnMouseClicked((Void) -> {
                chooseFromCollection(preGameCardView);
            });
            cards.clear();
        }

    }

    private void chooseFromCollection(PreGameCardView preGameCardView) {
        preGameCardView.setNumber(preGameCardView.getNumber() - 1);
        DecksCard card = preGameCardView.getCards().remove(0);
        cardCollectionList.remove(card);
        preDeck.add(card);
        boolean test = false;
        for (Node node : cardInDeck.getChildren()) {
            PreGameCardView preGameCardView2 = (PreGameCardView) node;
            if (preGameCardView2.getCard().getCardData() == card.getCardData()) {
                test = true;
                preGameCardView2.getCards().add(card);
                preGameCardView2.setNumber(preGameCardView2.getNumber() + 1);
                break;
            }
        }
        if (!test) {
            PreGameCardView preGameCardView2 =
                    new PreGameCardView(new LgCard(preGameCardView.getCard().getCardData(),true), 1, new ArrayList<>(Collections.singleton(card)));
            cardInDeck.getChildren().add(preGameCardView2);
            preGameCardView2.setOnMouseClicked((Void) -> chooseFromDeck(preGameCardView2));
        }
        if (preGameCardView.getNumber() == 0) {
            cardCollection.getChildren().remove(preGameCardView);
        }
    }

    private void chooseFromDeck(PreGameCardView preGameCardView) {
        preGameCardView.setNumber(preGameCardView.getNumber() - 1);
        DecksCard card = preGameCardView.getCards().remove(0);
        preDeck.remove(card);
        cardCollectionList.add(card);
        boolean test = false;
        for (Node node : cardCollection.getChildren()) {
            PreGameCardView preGameCardView2 = (PreGameCardView) node;
            if (preGameCardView2.getCard().getCardData() == card.getCardData()) {
                test = true;
                preGameCardView2.getCards().add(card);
                preGameCardView2.setNumber(preGameCardView2.getNumber() + 1);
                break;
            }
        }
        if (!test) {
            PreGameCardView preGameCardView2 =
                    new PreGameCardView(new LgCard(preGameCardView.getCard().getCardData(),true), 1, new ArrayList<>(Collections.singleton(card)));
            cardCollection.getChildren().add(preGameCardView2);
            preGameCardView2.setOnMouseClicked((Void) -> chooseFromDeck(preGameCardView2));
        }
        if (preGameCardView.getNumber() == 0) {
            cardInDeck.getChildren().remove(preGameCardView);
        }
    }

    public FlowPane getCardCollection() {
        return cardCollection;
    }

    public FlowPane getCardInDeck() {
        return cardInDeck;
    }
}