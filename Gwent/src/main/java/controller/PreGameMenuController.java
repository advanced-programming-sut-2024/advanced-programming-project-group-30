package controller;

import enums.FactionType;
import enums.cardsData.CardData;
import model.PreGameData;
import model.Result;
import model.User;

public class PreGameMenuController {
    public PreGameData preGameData = new PreGameData(new User("","","","",null,""));

    public Result createGame(String username) {
        return null;
    }

    public void selectFaction(FactionType faction) {
        preGameData.setFaction(faction);
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

    public void addCardToDeck(CardData card) {
        preGameData.addToPreDeck(card);
    }

    public void deleteCardFromDeck(CardData card) {
        preGameData.removeFromPreDeck(card);
    }

    public Result startGame() {
        return null;
    }
}
