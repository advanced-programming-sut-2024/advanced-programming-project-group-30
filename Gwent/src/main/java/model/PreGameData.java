package model;

import enums.FactionType;
import javafx.scene.layout.FlowPane;
import model.card.DecksCard;
import view.LgCard;

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
        cardCollection.setStyle("-fx-padding: 4.72; -fx-vgap: 4.72; -fx-hgap: 4.72");
        cardInDeck.setStyle("-fx-padding: 4.72; -fx-vgap: 4.72; -fx-hgap: 4.72");
        outsideFor:
        for (int i = 0; i < cardCollectionList.size(); i++) {
            while (i < cardCollectionList.size() - 1 && cardCollectionList.get(i).sames(cardCollectionList.get(i + 1)))
                continue outsideFor;
            cardCollection.getChildren().add(new LgCard(cardCollectionList.get(i).getCardData(), true));
        }

    }

    private void createCardCollection(FactionType faction) {

    }

    public FlowPane getCardCollection() {
        return cardCollection;
    }
}