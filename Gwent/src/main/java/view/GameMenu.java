package view;

import controller.GameMenuController;
import enums.Ability;
import enums.CssAddress;
import enums.GameNotification;
import enums.cardsData.RegularCardData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class GameMenu implements Menu {
    private final Pane notifPane = new Pane();
    private final Label notifLabel = new Label();
    private final ImageView notifImageView = new ImageView();
    private final GameMenuController gameMenuController = new GameMenuController(this);
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
        for (DecksCard card : game.getCurrentPlayer().getHand()) {
            if (card instanceof RegularCard) {
                if (((RegularCard) card).getAbility() != null){
                    if (((RegularCard) card).getAbility().equals(Ability.MUSTER))
                        System.out.println("hand adding " + card);
                }
            }
            hand.getChildren().add(card.getCardView());
        }
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
}