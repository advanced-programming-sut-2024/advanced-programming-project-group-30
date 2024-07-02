package view;

import controller.PreGameMenuController;
import enums.cardsData.CardData;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.CardCollection;
import model.CardComparator;
import model.card.DecksCard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        // TODO : remove
        uploadToCardCollection(controller.pregameData.getCardCollection());
        stage.widthProperty().addListener((Void) -> scaleMainPane());
        stage.heightProperty().addListener((Void) -> scaleMainPane());
    }

    private void uploadToCardCollection(TreeMap<CardData, ArrayList<DecksCard>> collection) {
        for (CardData cardData : collection.keySet()) {
            PregameCardView cardView = new PregameCardView(cardData);
            cardCollection.getChildren().add(cardView);
            cardView.setOnMouseClicked((Void) -> controller.addCardToDeck(cardView));
        }
    }

    public void addToCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().add(getIndex(cardView, cardCollection.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> controller.addCardToDeck(cardView));
    }

    public void removeFromCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().remove(cardView);
    }

    public void addToCardsInDeck(PregameCardView cardView) {
        cardsInDeck.getChildren().add(getIndex(cardView, cardsInDeck.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> controller.removeCardFromDeck(cardView));
    }

    public void removeFromCardsInDeck(PregameCardView cardView) {
        cardsInDeck.getChildren().remove(cardView);
    }

    public PregameCardView getCollectionCardView(CardData cardData) {
        return getCardView(cardData, cardCollection);
    }

    public PregameCardView getDeckCardView(CardData cardData) {
        return getCardView(cardData, cardsInDeck);
    }

    private PregameCardView getCardView(CardData cardData, FlowPane flowPane) {
        for (Node node : flowPane.getChildren()) {
            PregameCardView cardView = (PregameCardView) node;
            if (cardView.getCardData() == cardData) return cardView;
        }
        return null;
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

    private int getIndex(PregameCardView cardView, List<Node> nodes) {
        ArrayList<CardData> list = new ArrayList<>();
        for (Node node : nodes) {
            PregameCardView card = (PregameCardView) node;
            list.add(card.getCardData());
        }
        list.add(cardView.getCardData());
        list.sort(CardComparator.getCardComparator());
        return list.indexOf(cardView.getCardData());
    }
}