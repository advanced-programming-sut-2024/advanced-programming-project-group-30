package view;

import controller.GameMenuController;
import enums.*;
import enums.cardsData.RegularCardData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.*;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameMenu implements Menu {
    private final Pane notifPane = new Pane();
    private final Label notifLabel = new Label();
    private final ImageView notifImageView = new ImageView();
    private final GameMenuController gameMenuController = new GameMenuController(this);
    @FXML
    private Label result;
    @FXML
    private Pane endGamePane;
    @FXML
    private Label loser;
    @FXML
    private Label winnerRound1;
    @FXML
    private Label loserRound1;
    @FXML
    private Label winnerRound2;
    @FXML
    private Label loserRound2;
    @FXML
    private Label winnerRound3;
    @FXML
    private Label loserRound3;
    @FXML
    private Label winner;
    @FXML
    private VBox root;
    @FXML
    private Pane pane;
    @FXML
    private VBox centerPane;
    @FXML
    private VBox rowsPane;
    @FXML
    private VBox currentRowArea;
    @FXML
    private VBox opponentRowsArea;
    @FXML
    private HBox weatherCardPosition;
    @FXML
    private HBox hand;
    @FXML
    private HBox deck;
    @FXML
    private HBox decksCardNumber;
    @FXML
    private HBox opponentDeck;
    @FXML
    private HBox opponentDecksCardNumber;
    @FXML
    private Region activeLeaderIcon;
    @FXML
    private Region opponentActiveLeaderIcon;
    @FXML
    private HBox leader;
    @FXML
    private HBox opponentLeader;
    @FXML
    private HBox discardPile;
    @FXML
    private HBox opponentDiscardPile;
    @FXML
    private Label notifText;
    @FXML
    private Pane notifBox;
    @FXML
    private ImageView notifImage;

    @FXML
    public void initialize() {
        root.widthProperty().addListener((Void) -> scalePanes());
        root.heightProperty().addListener((Void) -> scalePanes());
    }

    public void setup() {
        gameMenuController.setup();
    }

    public void addInformationViews(Pane currentPlayerInfo, Pane opponentPlayerInfo) {
        pane.getChildren().addAll(currentPlayerInfo, opponentPlayerInfo);
    }

    public void setHand(ArrayList<DecksCard> hand) {
        for (DecksCard card : hand)
            this.hand.getChildren().add(card.getCardView());
    }

    public Node[] getCurrentPlayerViewField() {
        return new Node[]{pane, currentRowArea, discardPile, deck, hand, leader};
    }

    public Node[] getOpponentPlayerViewField() {
        return new Node[]{pane, opponentRowsArea, opponentDiscardPile, opponentDeck, hand, opponentLeader};
    }

    public HBox getWeatherCardPosition() {
        return weatherCardPosition;
    }

    public void disablePane() {
        pane.setDisable(true);
    }

    public void resetStyles(RowView rowView) {
        for (CssAddress cssAddress : rowView.getStyles()) {
            rowView.getRow().getStyleClass().remove(cssAddress.getStyleClass());
            rowView.getSpecialCardPosition().getStyleClass().remove(cssAddress.getStyleClass());
        }
        weatherCardPosition.getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
    }

    public void updateGame(Game game) {
        gameMenuController.updateGame(game);
    }

    public void updateScores(ArrayList<Row> allRows) {
        for (Row row : allRows) {
            row.updateRowScore();
        }
    }

    public void setNodeStyle(Node node, CssAddress cssAddress) {
        node.getStyleClass().add(cssAddress.getStyleClass());
    }

    public void removeNodeStyle(Node node, CssAddress cssAddress) {
        node.getStyleClass().remove(cssAddress.getStyleClass());
    }

    public void setUpBoard(RowView siege, RowView ranged, RowView close, RowView opSiege, RowView opRanged, RowView opClose) {
        opponentRowsArea.getChildren().addAll(opSiege, opRanged, opClose);
        currentRowArea.getChildren().addAll(close, ranged, siege);
    }

    public void setUpUserInformation(Label usernameLabel, String username) {
        usernameLabel.setText(username);
    }

    public void updateHandCardNumber(Label cardNumber, int number) {
        cardNumber.setText(String.valueOf(number));
    }

    public void setUpAfterSwitch(Node pane, Node node1, Node node2) {
        if (pane instanceof HBox) ((HBox) pane).getChildren().addAll(node1, node2);
        else if (pane instanceof Pane) {
            ((Pane) pane).getChildren().addAll(node1, node2);
        }
    }

    //TODO: added these
    public void endRound(String message) {
        showRoundEndNotification(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> {
            showRoundStart();
        }));
        timeline.play();
    }

    public void handlePassTurn(Game game) {
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
        game.getOpponentPlayer().getPlayerInformationView().getStyleClass().remove("brownBoxShadowed");
        for (DecksCard card : game.getOpponentPlayer().getHand())
            hand.getChildren().remove(card.getCardView());
        for (DecksCard card : game.getCurrentPlayer().getHand())
            hand.getChildren().add(card.getCardView());

        showPassTurnNotification();
    }

    public VBox getRowsPane() {
        return rowsPane;
    }

    public Pane getPane() {
        return pane;
    }
    public void setUpNotificationBox() {
        notifPane.getStyleClass().add(CssAddress.NOTIF_BOX.getStyleClass());
        notifPane.setLayoutY(notifBox.getLayoutY());
        notifLabel.getStyleClass().add(CssAddress.NOTIFICATION_LABEL.getStyleClass());
        notifLabel.setLayoutX(notifText.getLayoutX());
        notifLabel.setLayoutY(notifText.getLayoutY());
        notifImageView.setLayoutY(notifImage.getLayoutY());
        notifImageView.setLayoutX(notifImage.getLayoutX());
        notifImageView.setFitWidth(notifImage.getFitWidth());
        notifImageView.setFitHeight(notifImage.getFitHeight());
        notifPane.getChildren().addAll(notifLabel, notifImageView);
    }

    public void setHandCardEventHandler(Player currentPlayer, Player opponentPlayer, Game game ,ArrayList<DecksCard> cards){
        gameMenuController.handelHandsCardEvent(cards, game, currentPlayer, opponentPlayer);
    }
    public void showMedicCardOptions(ArrayList<RegularCard> cards, Game game) {
        ArrayList<ChosenModelView<RegularCard>> chosenModelViews = new ArrayList<>();
        for (RegularCard card : cards) {
            RegularCardData data = (RegularCardData) card.getCardData();
//            ChosenModelView<RegularCard> chosenModelView = new ChosenModelView<>(Objects.requireNonNull(this.getClass().getResourceAsStream(data.lgImageAddress)));
        }
//        SelectionPage<RegularCard> selectionPage = new SelectionPage<>(, Math.min(2, chosenModelViews.size())-1,  SizeData.GAME_LG_CARD);
    }
    public void setUpLeaderView(){

    }
    public void endGame(String message,String winner, int[] winnerRoundPoint, String loser, int[] loserRoundsPoint) {
        pane.getChildren().remove(endGamePane);
        pane.getChildren().add(endGamePane);
        endGamePane.setVisible(true);
        result.setText(message);
        this.winner.setText(winner);
        this.loser.setText(loser);
        winnerRound1.setText(String.valueOf(winnerRoundPoint[0]));
        winnerRound2.setText(String.valueOf(winnerRoundPoint[1]));
        winnerRound3.setText(String.valueOf(winnerRoundPoint[2]));
        loserRound1.setText(String.valueOf(loserRoundsPoint[0]));
        loserRound2.setText(String.valueOf(loserRoundsPoint[1]));
        loserRound3.setText(String.valueOf(loserRoundsPoint[2]));
    }
//    public void setupViewsAfterSwitch(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
//        setUpAfterSwitch(pane, currentPlayerView.getDeckView(), opponentPlayerView.getDeckView());
//        setUpAfterSwitch(pane, currentPlayerView.getDiscardPileView(), opponentPlayerView.getDiscardPileView());
//        setUpAfterSwitch(pane, currentPlayerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
//        setUpAfterSwitch(pane, currentPlayerView.getLeaderView(), opponentPlayerView.getHandView());
//    }
//    public void switchBoard(Game game) throws NoSuchMethodException {
//        Player currentPlayer = game.getCurrentPlayer();
//        Player opponentPlayer = game.getOpponentPlayer();
//        PlayerView currentPlayerView = currentPlayer.getPlayerView();
//        PlayerView opponentPlayerView = opponentPlayer.getPlayerView();
//        gameMenuController.resetRowStyles(game);
//
//        swapBoardViews(currentPlayerView, opponentPlayerView);
//        clearBoardViews(currentPlayerView, opponentPlayerView);
//
//        swapPlayerViews(currentPlayerView, opponentPlayerView, PlayerView.class.getDeclaredMethod("getDiscardPileView"),
//                PlayerView.class.getDeclaredMethod("getDeckView"),
//                PlayerView.class.getDeclaredMethod("getPlayerInformationView"),
//                PlayerView.class.getDeclaredMethod("getLeaderView"));
//
//        setupViewsAfterSwitch(currentPlayerView, opponentPlayerView);
//
//        swapRows(currentPlayer, opponentPlayer);
//    }
//
//
//    private void swapBoardViews(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
//        rowsPane.getChildren().removeAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
//        switchCoordinates(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
//        rowsPane.getChildren().addAll(currentPlayerView.getBoardView(), opponentPlayerView.getBoardView());
//    }
//
//    private void clearBoardViews(PlayerView currentPlayerView, PlayerView opponentPlayerView) {
//        currentPlayerView.getBoardView().getChildren().clear();
//        opponentPlayerView.getBoardView().getChildren().clear();
//    }
//
//    private void swapPlayerViews(PlayerView currentPlayerView, PlayerView opponentPlayerView, Method... methods) {
//        try {
//            for (Method method : methods) {
//                Node node1 = (Node) method.invoke(currentPlayerView);
//                Node node2 = (Node) method.invoke(opponentPlayerView);
//                pane.getChildren().removeAll(node1, node2);
//                switchCoordinates(node1, node2);
//            }
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            throw new RuntimeException("Failed to switch player views", e);
//        }
//    }
//
//    private void swapRows(Player currentPlayer, Player opponentPlayer) {
//        switchCoordinates(currentPlayer.getCloseCombat().getRowView(), opponentPlayer.getCloseCombat().getRowView());
//        switchCoordinates(currentPlayer.getRangedCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView());
//        switchCoordinates(currentPlayer.getSiege().getRowView(), opponentPlayer.getSiege().getRowView());
//        currentPlayer.getPlayerView().getBoardView().getChildren().addAll(currentPlayer.getSiege().getRowView(), currentPlayer.getRangedCombat().getRowView(), currentPlayer.getCloseCombat().getRowView());
//        opponentPlayer.getPlayerView().getBoardView().getChildren().addAll(opponentPlayer.getCloseCombat().getRowView(), opponentPlayer.getRangedCombat().getRowView(), opponentPlayer.getSiege().getRowView());
//    }
//
//    private void switchCoordinates(Node component1, Node component2) {
//        double tempX = component1.getLayoutX();
//        double tempY = component1.getLayoutY();
//        component1.setLayoutX(component2.getLayoutX());
//        component1.setLayoutY(component2.getLayoutY());
//        component2.setLayoutX(tempX);
//        component2.setLayoutY(tempY);
//    }
    @FXML
    private void passTurn() {
        gameMenuController.checkRound(App.getCurrentGame());
    }

    private void showPassTurnNotification() {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                GameNotification.PASS_TURN.getNotification(),
                GameNotification.PASS_TURN.getNotificationImage(),2);
        timeline.play();
    }

    private void showRoundEndNotification(String message) {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                    message, GameNotification.WIN_ROUND.getNotificationImage(), 2);
        timeline.play();
    }

    private void scalePanes() {
        scalePane(pane, 1);
    }

    private void scalePane(Pane pane, double scaleCoef) {
        Double scale = App.getSceneManager().getScale(root.getWidth(), root.getHeight(), 1100, 660, scaleCoef);
        if (scale == null) return;
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        pane.setLayoutX((root.getWidth() - 1100) / 2);
        pane.setLayoutY((root.getHeight() - 660) / 2 + 5 * scaleCoef);
    }
    private void showRoundStart() {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                GameNotification.ROUND_STARTS.getNotification(), GameNotification.ROUND_STARTS.getNotificationImage(), 1);
        timeline.play();
    }
    @FXML
    private void goToMainMenu() {
        App.getSceneManager().goToMainMenu();
    }

}