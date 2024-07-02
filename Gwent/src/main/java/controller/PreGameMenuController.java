package controller;

import enums.FactionType;
import model.PregameData;
import model.Result;
import model.User;
import view.PregameCardView;
import view.PregameMenu;

public class PreGameMenuController {
    public PregameData pregameData = new PregameData(new User("", "", "", "", null, ""));
    private final PregameMenu menu;

    public PreGameMenuController(PregameMenu menu) {
        this.menu = menu;
    }

    public Result createGame(String username) {
        return null;
    }

    public void selectFaction(FactionType faction) {
        pregameData.setFaction(faction);
    }

    public Result saveDeckWithName(String deckName) {
        return null;
    }

    public Result saveDeckInFile(String fileName) {
        return null;
    }

    public Result selectLeader(String leader) {
        return null;
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

    public Result startGame() {
        return null;
    }
}
