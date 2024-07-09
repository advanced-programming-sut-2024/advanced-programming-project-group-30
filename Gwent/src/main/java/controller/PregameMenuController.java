package controller;

import enums.FactionType;
import enums.cardsData.CardData;
import enums.cardsData.DeckCardData;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import model.*;
import model.card.DecksCard;
import view.PregameCardView;
import view.PregameMenu;
import view.SelectionPage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PregameMenuController {
    private PregameData pregameData;
    private final PregameMenu menu;

    public PregameMenuController(PregameMenu menu) {
        this.menu = menu;
    }

    public void setup(PregameData pregameData) {
        this.pregameData = pregameData;
        setup();
    }

    private void setup() {
        menu.updateFactionsFields(pregameData.getFaction());
        menu.setNickname(pregameData.getUser().getNickname());
        uploadToCardCollection(pregameData.getCardCollection());
        SelectionPage<FactionType> selectionPage = new SelectionPage<>(FactionType.getAllChooseModelView(),
                FactionType.getFactionIndex(pregameData.getFaction()));
        menu.setFactionSelectionPage(selectionPage);
    }

    public PregameData getPregameData() {
        return pregameData;
    }

    public Result changeTurn() {
        if (!pregameData.isUnitCardsNumberValid()) return new Result(false, "choose at least 22 unit cards");
        if (!pregameData.isSpecialCardsNumberValid())
            return new Result(false, "you can't choose more than 10 special cards");
        pregameData.changeTurn();
        setup();
        return new Result(true, "");
    }

    public Result startGame() {
        if (!pregameData.isUnitCardsNumberValid()) return new Result(false, "choose at least 22 unit cards");
        if (!pregameData.isSpecialCardsNumberValid())
            return new Result(false, "you can't choose more than 10 special cards");
        Game newGame = new Game(pregameData);
        App.getSceneManager().goToGame(newGame);
        return new Result(true, "");
    }

    public void uploadToCardCollection(TreeMap<DeckCardData, ArrayList<DecksCard>> collection) {
        menu.clearCardsPane();
        for (DeckCardData cardData : collection.keySet())
            menu.addToCardCollection(new PregameCardView(cardData));
        menu.updateNumberData();
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
        menu.updateNumberData();
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
        menu.updateNumberData();
    }

    public void changeFation(FactionType faction) {
        pregameData.setFaction(faction);
        uploadToCardCollection(pregameData.getCardCollection());
    }

    public PregameCardView getCardView(CardData cardData, FlowPane flowPane) {
        for (Node node : flowPane.getChildren()) {
            if (!(node instanceof PregameCardView cardView)) continue;
            if (cardView.getCardData() == cardData) return cardView;
        }
        return null;
    }

    public int getIndex(PregameCardView cardView, List<Node> nodes) {
        ArrayList<DeckCardData> list = new ArrayList<>();
        for (Node node : nodes) {
            PregameCardView card = (PregameCardView) node;
            list.add(card.getCardData());
        }
        list.add(cardView.getCardData());
        list.sort(CardComparator.getCardComparator());
        return list.indexOf(cardView.getCardData());
    }
}