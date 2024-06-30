package model;

import enums.FactionType;
import javafx.scene.layout.FlowPane;
import model.card.DecksCard;

import java.util.ArrayList;

public class PreGameData {
    private final User user;
    private final ArrayList<DecksCard> cardCollectionList;
    private final ArrayList<DecksCard> preDeck = new ArrayList<>();
    private final FlowPane cardCollection = new FlowPane();
    private final FlowPane cardInDeck = new FlowPane();

    public PreGameData(User user) {
        this.user = user;
        this.cardCollectionList = user.getCardCollection().getCardsByFactionsName(user.getSelectedFaction());
    }

    private void createCardCollection(FactionType faction) {

    }
}