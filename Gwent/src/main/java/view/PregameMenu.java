package view;

import enums.FactionType;
import enums.SizeData;
import enums.cardsData.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.*;
import model.card.DecksCard;
import network.Client;
import network.ClientMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class PregameMenu implements Menu {
    private final Client client = ClientView.getClient();
    private PregameData pregameData;
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
    private Label errorMessage;
    @FXML
    private Label nicknameLabel;

    @FXML
    private void initialize() {
        root.widthProperty().addListener((Void) -> scalePanes());
        root.heightProperty().addListener((Void) -> scalePanes());
        helperPane.setOnMouseClicked((Void) -> closeFactionSelectionPage());
    }

    public void setup(FactionType faction) {
        scalePanes();
        errorMessage.setText("");
        nicknameLabel.setText("hi " + App.getLoggedInUsersNickname() + ". please choose your deck.");
        ClientMessage clientMessage = new ClientMessage("PregameController", "getUserCardCollection",
                new ArrayList<>(Collections.singleton(App.getLoggedInUsersUsername())));
        client.sendMessageToServer(clientMessage);
        this.pregameData = new PregameData(App.getLoggedInUsersUsername(), (CardCollection) client.getLastServerData(CardCollection.class), faction);
        updateFactionsFields(pregameData.getFaction());
        uploadToCardCollection(pregameData.getCardCollection());
        SelectionPage<FactionType> factionSelectionPage = new SelectionPage<>(FactionType.getAllChooseModelView(),
                FactionType.getFactionIndex(pregameData.getFaction()), SizeData.FATION_CARD);
        setFactionSelectionPage(factionSelectionPage);
        SelectionPage<LeaderCardData> leaderSelectionPage = new SelectionPage<>(LeaderCardData.getFactionsLeaderChooseView(pregameData.getFaction()),
                0, SizeData.GAME_LG_CARD);
        pregameData.setLeader(leaderSelectionPage.getSelectedModel());
        setLeaderSelectionPage(leaderSelectionPage);
    }

    private void uploadToCardCollection(TreeMap<DeckCardData, ArrayList<DecksCard>> collection) {
        clearCardsPane();
        for (DeckCardData cardData : collection.keySet())
            addToCardCollection(new PregameCardView(cardData));
        updateNumberData();
    }

    private void addCardToDeck(PregameCardView cardView) {
        pregameData.addToPreDeck(cardView.getCardData());
        cardView.setNumber(cardView.getNumber() - 1);
        if (cardView.getNumber() < 1) removeFromCardCollection(cardView);
        PregameCardView deckCardView = getCardView(cardView.getCardData(), cardsInDeck);
        if (deckCardView == null) {
            deckCardView = new PregameCardView(cardView.getCardData());
            deckCardView.setNumber(1);
            addToCardsInDeck(deckCardView);
        } else deckCardView.setNumber(deckCardView.getNumber() + 1);
        updateNumberData();
    }

    private void removeCardFromDeck(PregameCardView cardView) {
        pregameData.removeFromPreDeck(cardView.getCardData());
        cardView.setNumber(cardView.getNumber() - 1);
        if (cardView.getNumber() < 1) cardsInDeck.getChildren().remove(cardView);
        PregameCardView collectionCardView = getCardView(cardView.getCardData(), cardCollection);
        if (collectionCardView == null) {
            collectionCardView = new PregameCardView(cardView.getCardData());
            collectionCardView.setNumber(1);
            addToCardCollection(collectionCardView);
        } else collectionCardView.setNumber(collectionCardView.getNumber() + 1);
        updateNumberData();
    }

    private void changeFation(FactionType faction) {
        ClientMessage clientMessage = new ClientMessage("UserInformationController", "setUserFaction",
                new ArrayList<>(List.of(new Object[]{App.getLoggedInUsersUsername(), faction.name()})));
        client.sendMessageToServer(clientMessage);
        pregameData.setFaction(faction);
        SelectionPage<LeaderCardData> leaderSelectionPage = new SelectionPage<>(LeaderCardData.getFactionsLeaderChooseView(pregameData.getFaction()),
                0, SizeData.GAME_LG_CARD);
        setLeaderSelectionPage(leaderSelectionPage);
        pregameData.setLeader(leaderSelectionPage.getSelectedModel());
        uploadToCardCollection(pregameData.getCardCollection());
    }

    private PregameCardView getCardView(CardData cardData, FlowPane flowPane) {
        for (Node node : flowPane.getChildren()) {
            if (!(node instanceof PregameCardView cardView)) continue;
            if (cardView.getCardData() == cardData) return cardView;
        }
        return null;
    }

    private int getIndex(PregameCardView cardView, List<Node> nodes) {
        ArrayList<DeckCardData> list = new ArrayList<>();
        for (Node node : nodes) {
            PregameCardView card = (PregameCardView) node;
            list.add(card.getCardData());
        }
        list.add(cardView.getCardData());
        list.sort(CardComparator.getCardComparator());
        return list.indexOf(cardView.getCardData());
    }

    private void setFactionSelectionPage(SelectionPage<FactionType> selectionPage) {
        factionSelectionPage = selectionPage;
        selectionPage.getMainRegion().setOnMouseClicked((Void) -> selectFaction());
        selectionPage.setOnMouseClicked((event -> {
            if (!selectionPage.isInTheBoundOfSubRegions(event.getX(), event.getY())) closeFactionSelectionPage();
        }));
    }

    private void setLeaderSelectionPage(SelectionPage<LeaderCardData> selectionPage) {
        leaderSelectionPage = selectionPage;
        scalePane(leaderSelectionPage, 0.93);
        leaderRegion.setFill(new ImagePattern(selectionPage.getSelectedModel().getLgImage()));
        selectionPage.getMainRegion().setOnMouseClicked((Void) -> selectLeader());
        selectionPage.setOnMouseClicked((event -> {
            if (!selectionPage.isInTheBoundOfSubRegions(event.getX(), event.getY())) closeLeaderSelectionPage();
        }));
    }

    private void addToCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().add(getIndex(cardView, cardCollection.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> addCardToDeck(cardView));
    }

    private void removeFromCardCollection(PregameCardView cardView) {
        cardCollection.getChildren().remove(cardView);
    }

    private void addToCardsInDeck(PregameCardView cardView) {
        cardsInDeck.getChildren().add(getIndex(cardView, cardsInDeck.getChildren()), cardView);
        cardView.setOnMouseClicked((Void) -> removeCardFromDeck(cardView));
    }

    private void clearCardsPane() {
        cardCollection.getChildren().clear();
        cardsInDeck.getChildren().clear();
    }

    private void updateFactionsFields(FactionType faction) {
        factionLabel.setText(faction.getName());
        descriptionLabel.setText(faction.getDescription());
        rightFactionIcon.setImage(faction.getShieldIcon());
        leftFactionIcon.setImage(faction.getShieldIcon());
    }

    private void updateNumberData() {
        errorMessage.setText("");
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

    @FXML
    private void startGameWithFriend() {
//        Result result = checkPregameData();
//        if (result.isNotSuccessful()) {
//            errorMessage.setText(result.toString());
//            return;
//        }
    }

    @FXML
    private void startRandomGame() {
        Result result = checkPregameData();
        if (result.isNotSuccessful()) {
            errorMessage.setText(result.toString());
            return;
        }
        errorMessage.setText("waiting...");
        errorMessage.setStyle("-fx-text-fill: rgba(182,175,97,0.9)");
        client.requestForRandomGame(pregameData);
        errorMessage.setStyle("");
        errorMessage.setText("");
    }

    private Result checkPregameData() {
        if (!pregameData.isSpecialCardsNumberValid())
            return new Result(false, "you can't choose more than 10 special cards");
        if (!pregameData.isUnitCardsNumberValid())
            return new Result(false, "choose at least 22 unit cards");
        return new Result(true, "");
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
        changeFation(factionSelectionPage.getSelectedModel());
        leaderRegion.setFill(new ImagePattern(leaderSelectionPage.getSelectedModel().getLgImage()));
        updateFactionsFields(factionSelectionPage.getSelectedModel());
        closeFactionSelectionPage();
    }

    private void selectLeader() {
        pregameData.setLeader(leaderSelectionPage.getSelectedModel());
        leaderRegion.setFill(new ImagePattern(leaderSelectionPage.getSelectedModel().getLgImage()));
    }

    private void scalePanes() {
        scalePane(mainPane, 0.97);
        scalePane(helperPane, 0.97);
        scalePane(factionSelectionPage, 0.9);
        scalePane(leaderSelectionPage, 0.93);
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