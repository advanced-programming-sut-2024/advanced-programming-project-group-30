package view;

import controller.PreGameMenuController;
import enums.cardsData.CardData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.card.DecksCard;

import java.util.ArrayList;
import java.util.TreeMap;

public class PregameMenu implements Menu {
    private final PreGameMenuController controller = new PreGameMenuController(this);
    @FXML
    private VBox stage;
    @FXML
    private BorderPane mainPane;
    @FXML
    private FlowPane cardCollection;
    @FXML
    private FlowPane cardsInDeck;

    public void initialize() {
        uploadToCardCollection(controller.pregameData.getCardCollection());
        stage.widthProperty().addListener((Void) -> scaleMainPane());
        stage.heightProperty().addListener((Void) -> scaleMainPane());
    }

    private void uploadToCardCollection(TreeMap<CardData, ArrayList<DecksCard>> collection) {
        for (CardData cardData : collection.keySet()) {
            PreGameCardView cardView = new PreGameCardView(cardData);
            cardCollection.getChildren().add(cardView);
            cardView.setOnMouseClicked((Void) -> controller.addCardToDeck(cardView));
        }
    }

    public void removeFromCardCollection(PreGameCardView cardView) {
        cardCollection.getChildren().remove(cardView);
    }

    public void addToCardCollection(PreGameCardView cardView) {
        cardCollection.getChildren().add(cardView);
    }

    public void removeFromCardsInDeck(PreGameCardView cardView) {
        cardsInDeck.getChildren().remove(cardView);
    }

    public void addToCardsInDeck(PreGameCardView cardView) {
        cardsInDeck.getChildren().add(cardView);
    }

    private void scaleMainPane() {
        if (stage.getWidth() < 0.1 || mainPane.getWidth() < 0.1 || mainPane.getHeight() < 0.1 || stage.getHeight() < 0.1)
            return;
        double scaleX = stage.getWidth() / mainPane.getWidth();
        double scaleY = stage.getHeight() / mainPane.getHeight();
        double scale = Math.min(scaleY, scaleX);
        mainPane.setScaleX(scale * 0.97);
        mainPane.setScaleY(scale * 0.97);
    }

    public PreGameCardView getCollectionCardView(CardData cardData) {
        return getCardView(cardData, cardCollection);
    }

    public PreGameCardView getDeckCardView(CardData cardData) {
        return getCardView(cardData, cardsInDeck);
    }

    private PreGameCardView getCardView(CardData cardData, FlowPane flowPane) {
        for (Node node : flowPane.getChildren()) {
            PreGameCardView cardView = (PreGameCardView) node;
            if (cardView.getCardData() == cardData) return cardView;
        }
        return null;
    }
}