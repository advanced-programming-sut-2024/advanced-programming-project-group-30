package view;

import controller.PregameMenuController;
import enums.cardsData.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.PregameData;

import java.util.ArrayList;

public class PregameMenu implements Menu {
    private final PregameMenuController controller = new PregameMenuController(this);
    @FXML
    private VBox stage;
    @FXML
    private BorderPane mainPane;
    @FXML
    private FlowPane cardCollection;
    @FXML
    private FlowPane cardsInDeck;
    @FXML
    private Label cardsInDeckNumber;
    @FXML
    private Label unitCardsNumber;
    @FXML
    private Label specialCardsNumber;
    @FXML
    private Label totalStrength;
    @FXML
    private Label heroCardsNumber;

    public void initialize() {
        controller.uploadToCardCollection(controller.getPregameData().getCardCollection()); // TODO : remove
        stage.widthProperty().addListener((Void) -> scaleMainPane());
        stage.heightProperty().addListener((Void) -> scaleMainPane());
        stage.getChildren().remove(mainPane);
        ArrayList<LargeCardView> largeCardViews = new ArrayList<>();
        largeCardViews.add(NeutralRegularCardsData.AVALLACH.getLargeCardView());
            largeCardViews.add(NeutralRegularCardsData.ZOLTAN_CHIVAY.getLargeCardView());
        largeCardViews.add(NeutralRegularCardsData.GAUNTER_ODIMM_DARKNESS.getLargeCardView());
        largeCardViews.add(NeutralRegularCardsData.EMIEL_REGIS.getLargeCardView());
        largeCardViews.add(NeutralRegularCardsData.VESEMIR.getLargeCardView());
        largeCardViews.add(NeutralRegularCardsData.OLGIERD_VON_EVEREC.getLargeCardView());
        stage.getChildren().add(new CardChoosePage(largeCardViews,2));
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

    public void updateNumberData() {
        PregameData pregameData = controller.getPregameData();
        cardsInDeckNumber.setText("×" + pregameData.getCardsInDeckNumber());
        if (pregameData.isUnitCardsNumberValid()) {
            unitCardsNumber.setText("×" + pregameData.getUnitCardsNumber());
            unitCardsNumber.setStyle("");
        } else {
            this.unitCardsNumber.setText(pregameData.getUnitCardsNumber() + "/22");
            unitCardsNumber.setStyle("-fx-text-fill: #b83c27;");
        }
        if (pregameData.isSpecialCardsNumberValid()) specialCardsNumber.setStyle("");
        else specialCardsNumber.setStyle("-fx-text-fill: #b83c27;");
        this.specialCardsNumber.setText(pregameData.getSpecialCardsNumber() + "/10");
        this.totalStrength.setText(pregameData.getTotalCardsStrength() + "");
        this.heroCardsNumber.setText("×" + pregameData.getHeroCardsNumber());
    }

    private void scaleMainPane() {
        Double scale = controller.getScale(stage.getWidth(), stage.getHeight(), mainPane.getWidth(), mainPane.getHeight());
        if (scale == null) return;
        mainPane.setScaleX(scale);
        mainPane.setScaleY(scale);
    }
}