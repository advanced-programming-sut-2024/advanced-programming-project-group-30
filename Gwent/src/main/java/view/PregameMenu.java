package view;

import controller.PreGameMenuController;
import enums.cardsData.CardData;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

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
        controller.uploadToCardCollection(controller.getPregameData().getCardCollection()); // TODO : remove
        stage.widthProperty().addListener((Void) -> scaleMainPane());
        stage.heightProperty().addListener((Void) -> scaleMainPane());
    }

    public void addToCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().add(controller.getIndex(cardView, cardCollection.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> controller.addCardToDeck(cardView));
    }

    public void removeFromCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().remove(cardView);
    }

    public void addToCardsInDeck(PregameCardView cardView) {
        cardsInDeck.getChildren().add(controller.getIndex(cardView, cardsInDeck.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> controller.removeCardFromDeck(cardView));
    }

    public void removeFromCardsInDeck(PregameCardView cardView) {
        cardsInDeck.getChildren().remove(cardView);
    }

    public PregameCardView getCollectionCardView(CardData cardData) {
        return controller.getCardView(cardData, cardCollection);
    }

    public PregameCardView getDeckCardView(CardData cardData) {
        return controller.getCardView(cardData, cardsInDeck);
    }

    private void scaleMainPane() {
        Double scale = controller.getScale(stage.getWidth(), stage.getHeight(), mainPane.getWidth(), mainPane.getHeight());
        if (scale == null) return;
        mainPane.setScaleX(scale);
        mainPane.setScaleY(scale);
    }
}