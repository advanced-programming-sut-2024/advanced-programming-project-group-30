package view;

import controller.PreGameMenuController;
import enums.cardsData.CardData;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.card.DecksCard;

import java.util.ArrayList;
import java.util.TreeMap;

public class PregameMenu implements Menu {
    private final PreGameMenuController controller = new PreGameMenuController();
    public VBox stage;
    public BorderPane mainPane;
    @FXML
    private FlowPane cardCollection;
    @FXML
    private FlowPane cardsInDeck;

    public void initialize() {
        uploadToCardCollection(controller.preGameData.getCardCollection());
        stage.widthProperty().addListener((Void) -> xsl());
        stage.heightProperty().addListener((Void) -> xsl());
    }

    private void uploadToCardCollection(TreeMap<CardData, ArrayList<DecksCard>> collection) {
        for (CardData cardData : collection.keySet())
            cardCollection.getChildren().add(new PreGameCardView(cardData));
    }

    private void setFaction(MouseEvent mouseEvent) {

    }

    private void changeFaction() {

    }

    private void xsl() {
        if (stage.getWidth() < 0.1 || mainPane.getWidth() < 0.1 || mainPane.getHeight() < 0.1 || stage.getHeight() < 0.1) return;
        double scaleX = stage.getWidth() / mainPane.getWidth();
        double scaleY = stage.getHeight() / mainPane.getHeight();
        double scale = Math.min(scaleY, scaleX);
        mainPane.setScaleX(scale * 0.97);
        mainPane.setScaleY(scale * 0.97);
    }
}