package view;

import controller.PregameMenuController;
import enums.FactionType;
import enums.cardsData.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.App;
import model.PregameData;
import model.Result;

public class PregameMenu implements Menu {
    private final PregameMenuController controller = new PregameMenuController(this);
    @FXML
    private Pane root;
    @FXML
    private BorderPane mainPane;
    private SelectionPage<FactionType> factionSelectionPage;
    private SelectionPage<LeaderCardData> leaderSelectionPage;
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
    private Rectangle leaderRegion;
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
    @FXML
    private Button changeTurnButton;
    @FXML
    private Button startGameButton;
    @FXML
    private Label errorMessage;
    @FXML
    private Label nicknameLabel;

    @FXML
    public void initialize() {
        root.widthProperty().addListener((Void) -> scalePanes());
        root.heightProperty().addListener((Void) -> scalePanes());
        helperPane.setOnMouseClicked((Void) -> closeFactionSelectionPage());
        changeTurnButton.setOnMouseClicked((Void) -> changeTurn());
        startGameButton.setOnMouseClicked((Void) -> startGame());
    }

    public void setup(PregameData pregameData) {
        scalePanes();
        startGameButton.setDisable(true);
        changeTurnButton.setDisable(false);
        changeTurnButton.setVisible(true);
        startGameButton.setVisible(false);
        errorMessage.setText("");
        controller.setup(pregameData);
    }

    public void setNickname(String nickname) {
        nicknameLabel.setText("hi " + nickname + ". please choose your deck.");
    }

    public void setFactionSelectionPage(SelectionPage<FactionType> selectionPage) {
        factionSelectionPage = selectionPage;
        selectionPage.getMainRegion().setOnMouseClicked((Void) -> selectFaction());
        selectionPage.setOnMouseClicked((event -> {
            if (!selectionPage.isInTheBoundOfSubRegions(event.getX(), event.getY())) closeFactionSelectionPage();
        }));
    }

    public void setLeaderSelectionPage(SelectionPage<LeaderCardData> selectionPage) {
        leaderSelectionPage = selectionPage;
        leaderRegion.setFill(new ImagePattern(selectionPage.getSelectedModel().getLgImage()));
        selectionPage.getMainRegion().setOnMouseClicked((Void) -> selectLeader());
        selectionPage.setOnMouseClicked((event -> {
            if (!selectionPage.isInTheBoundOfSubRegions(event.getX(), event.getY())) closeLeaderSelectionPage();
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
        errorMessage.setText("");
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

    @FXML
    private void openLeaderSelectionPage() {
        mainPane.setDisable(true);
        helperPane.setVisible(true);
        helperPane.setDisable(false);
        root.getChildren().add(leaderSelectionPage);
    }

    private void changeTurn() {
        Result changeTurnResult = controller.changeTurn();
        errorMessage.setText(changeTurnResult.toString());
        if (changeTurnResult.isNotSuccessful()) return;
        changeTurnButton.setDisable(true);
        errorMessage.setText("");
        changeTurnButton.setVisible(false);
        startGameButton.setVisible(true);
        startGameButton.setDisable(false);
    }

    private void startGame() {
        Result startGameResult = controller.startGame();
        errorMessage.setText(startGameResult.toString());
    }

    private void closeFactionSelectionPage() {
        mainPane.setDisable(false);
        helperPane.setVisible(false);
        helperPane.setDisable(true);
        root.getChildren().remove(factionSelectionPage);
    }

    private void closeLeaderSelectionPage() {
        mainPane.setDisable(false);
        helperPane.setVisible(false);
        helperPane.setDisable(true);
        root.getChildren().remove(leaderSelectionPage);
    }

    private void selectFaction() {
        controller.changeFation(factionSelectionPage.getSelectedModel());
        leaderRegion.setFill(new ImagePattern(leaderSelectionPage.getSelectedModel().getLgImage()));
        updateFactionsFields(factionSelectionPage.getSelectedModel());
        closeFactionSelectionPage();
    }

    private void selectLeader() {
        controller.selectLeader(leaderSelectionPage.getSelectedModel());
        leaderRegion.setFill(new ImagePattern(leaderSelectionPage.getSelectedModel().getLgImage()));
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