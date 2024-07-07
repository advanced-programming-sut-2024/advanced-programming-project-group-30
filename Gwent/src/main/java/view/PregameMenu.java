package view;

import controller.PregameMenuController;
import enums.FactionType;
import enums.cardsData.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import model.App;
import model.PregameData;


public class PregameMenu implements Menu {
    private final PregameMenuController controller = new PregameMenuController(this);
    @FXML
    private Pane root;
    @FXML
    private BorderPane mainPane;
    private SelectionPage<FactionType> factionSelectionPage;
    @FXML
    private Pane helperPane;
    @FXML
    private Label factionLabel;
    @FXML
    private ImageView rightFactionIcon;
    @FXML
    private ImageView leftFactionIcon;
    @FXML
    private Label descriptionLabel;
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
        root.widthProperty().addListener((Void) -> scalePanes());
        root.heightProperty().addListener((Void) -> scalePanes());
        helperPane.setOnMouseClicked((Void) -> closeFactionSelectionPage());
    }

    public void setup(PregameData pregameData) {
        controller.setup(pregameData);
    }

    public void setFactionSelectionPage(SelectionPage<FactionType> selectionPage) {
        factionSelectionPage = selectionPage;
        selectionPage.getMainRegion().setOnMouseClicked((Void) -> selectFaction());
        selectionPage.setOnMouseClicked((event -> {
            if (!selectionPage.isInTheBoundOfSubRegions(event.getX(), event.getY())) closeFactionSelectionPage();
        }));
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

    public void clearCardsPane() {
        cardCollection.getChildren().clear();
        cardsInDeck.getChildren().clear();
    }

    public PregameCardView getCollectionCardView(CardData cardData) {
        return controller.getCardView(cardData, cardCollection);
    }

    public PregameCardView getDeckCardView(CardData cardData) {
        return controller.getCardView(cardData, cardsInDeck);
    }

    public void updateFactionsFields(FactionType faction) {
        factionLabel.setText(faction.getName());
        descriptionLabel.setText(faction.getDescription());
        rightFactionIcon.setImage(faction.getShieldIcon());
        leftFactionIcon.setImage(faction.getShieldIcon());
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

    @FXML
    private void openFactionSelectionPage() {
        mainPane.setDisable(true);
        helperPane.setVisible(true);
        helperPane.setDisable(false);
        root.getChildren().add(factionSelectionPage);
    }

    private void closeFactionSelectionPage() {
        mainPane.setDisable(false);
        helperPane.setVisible(false);
        helperPane.setDisable(true);
        root.getChildren().remove(factionSelectionPage);
    }

    private void selectFaction() {
        controller.changeFation(factionSelectionPage.getSelectedModel());
        updateFactionsFields(factionSelectionPage.getSelectedModel());
        closeFactionSelectionPage();
    }

    private void scalePanes() {
        scalePane(mainPane, 0.97);
        scalePane(helperPane, 0.97);
        scalePane(factionSelectionPage, 0.9);
    }

    private void scalePane(Pane pane, double scaleCoef) {
        Double scale = App.getSceneManager().getScale(root.getWidth(), root.getHeight(), 1150, 660, scaleCoef);
        if (scale == null) return;
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        pane.setLayoutX((root.getWidth() - 1150) / 2);
        pane.setLayoutY((root.getHeight() - 660) / 2 + 5 * scaleCoef);
    }
}