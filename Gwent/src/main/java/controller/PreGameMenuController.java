package controller;

import enums.cardsData.CardData;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import model.CardComparator;
import model.PregameData;
import model.User;
import model.card.DecksCard;
import view.PregameCardView;
import view.PregameMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PreGameMenuController {
    private PregameData pregameData = new PregameData(new User("", "", "", "", null, ""));
    private final PregameMenu menu;

    public PreGameMenuController(PregameMenu menu) {
        this.menu = menu;
    }

    public PregameData getPregameData() {
        return pregameData;
    }

    public void setPregameData(PregameData pregameData) {
        this.pregameData = pregameData;
    }

    public void uploadToCardCollection(TreeMap<CardData, ArrayList<DecksCard>> collection) {
        for (CardData cardData : collection.keySet())
            menu.addToCardCollection(new PregameCardView(cardData));
    }

    public void addCardToDeck(PregameCardView cardView) {
        pregameData.addToPreDeck(cardView.getCardData());
        cardView.setNumber(cardView.getNumber() - 1);
        if (cardView.getNumber() < 1) menu.removeFromCardCollection(cardView);
        PregameCardView deckCardView = menu.getDeckCardView(cardView.getCardData());
        if (deckCardView == null) {
            deckCardView = new PregameCardView(cardView.getCardData());
            deckCardView.setNumber(1);
            menu.addToCardsInDeck(deckCardView);
        } else deckCardView.setNumber(deckCardView.getNumber() + 1);
    }

    public void removeCardFromDeck(PregameCardView cardView) {
        pregameData.removeFromPreDeck(cardView.getCardData());
        cardView.setNumber(cardView.getNumber() - 1);
        if (cardView.getNumber() < 1) menu.removeFromCardsInDeck(cardView);
        PregameCardView collectionCardView = menu.getCollectionCardView(cardView.getCardData());
        if (collectionCardView == null) {
            collectionCardView = new PregameCardView(cardView.getCardData());
            collectionCardView.setNumber(1);
            menu.addToCardCollection(collectionCardView);
        } else collectionCardView.setNumber(collectionCardView.getNumber() + 1);
    }

    public PregameCardView getCardView(CardData cardData, FlowPane flowPane) {
        for (Node node : flowPane.getChildren()) {
            if (!(node instanceof PregameCardView cardView)) continue;
            if (cardView.getCardData() == cardData) return cardView;
        }
        return null;
    }

    public int getIndex(PregameCardView cardView, List<Node> nodes) {
        ArrayList<CardData> list = new ArrayList<>();
        for (Node node : nodes) {
            PregameCardView card = (PregameCardView) node;
            list.add(card.getCardData());
        }
        list.add(cardView.getCardData());
        list.sort(CardComparator.getCardComparator());
        return list.indexOf(cardView.getCardData());
    }

    public Double getScale(double stageWidth, double stageHeight, double paneWidth, double paneHeight) {
        if (stageWidth < 0.1 || stageHeight < 0.1 || paneWidth < 0.1 || paneHeight < 0.1) return null;
        double scaleX = stageWidth / paneWidth;
        double scaleY = stageHeight / paneHeight;
        return Math.min(scaleY, scaleX) * 0.97;
    }
}