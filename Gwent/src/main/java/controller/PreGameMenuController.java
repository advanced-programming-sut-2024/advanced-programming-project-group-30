package controller;

import enums.FactionType;
import enums.cardsData.CardData;
import model.PregameData;
import model.Result;
import model.User;
import view.PreGameCardView;
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

    public void addCardToDeck(PreGameCardView cardView) {
        pregameData.addToPreDeck(cardView.getCardData());
        cardView.setNumber(cardView.getNumber() - 1);
        if (cardView.getNumber() < 1) menu.removeFromCardCollection(cardView);
        PreGameCardView deckCardView = menu.getDeckCardView(cardView.getCardData());
        if (deckCardView == null) {
            deckCardView = new PreGameCardView(cardView.getCardData());
            deckCardView.setNumber(1);
            menu.addToCardsInDeck(deckCardView);
        } else deckCardView.setNumber(deckCardView.getNumber() + 1);
    }

    public void deleteCardFromDeck(CardData card) {
        pregameData.removeFromPreDeck(card);
    }

    public Result startGame() {
        return null;
    }
}
