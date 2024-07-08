package view;

import controller.GameMenuController;
import enums.CoordinateData;
import enums.CssAddress;
import enums.GameNotification;
import enums.SecurityQuestion;
import enums.cardsData.*;
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
import model.card.WeatherCard;

import java.util.ArrayList;

public class GameMenu implements Menu {
    private static final Pane notifPane = new Pane();
    private static final Label notifLabel = new Label();
    private static final ImageView notifImageView = new ImageView();
    private final GameMenuController gameMenuController = new GameMenuController(this);
    @FXML
    private HBox decksCardNumber;
    @FXML
    private HBox opponentDecksCardNumber;
    @FXML
    private VBox centerPane;
    @FXML
    private VBox rowsPane;
    @FXML
    private HBox opponentDeck;
    @FXML
    private HBox deck;
    @FXML
    private Region activeLeaderIcon;
    @FXML
    private Region opponentActiveLeaderIcon;
    @FXML
    private HBox leader;
    @FXML
    private HBox opponentDiscardPile;
    @FXML
    private HBox opponentLeader;
    @FXML
    private HBox discardPile;
    @FXML
    private Label notifText;
    @FXML
    private HBox weatherCardPosition;
    @FXML
    private VBox currentRowArea;
    @FXML
    private VBox opponentRowsArea;
    @FXML
    private Pane pane;
    @FXML
    private Pane notifBox;
    @FXML
    private ImageView notifImage;
    @FXML
    private HBox hand;

    //TODO: ignore initialize method
    @FXML
    public void initialize() {
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user);
        Player opponentPlayer = new Player(user2);
        PlayerView playerView = new PlayerView(player, pane, currentRowArea, discardPile, deck, hand, leader, CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE);
        player.setPlayerView(playerView);
        PlayerView opponentPlayerView = new PlayerView(opponentPlayer, pane, opponentRowsArea, opponentDiscardPile, opponentDeck, hand, opponentLeader, CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE);
        opponentPlayer.setPlayerView(opponentPlayerView);
        pane.getChildren().addAll(playerView.getPlayerInformationView(), opponentPlayerView.getPlayerInformationView());
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);
        gameMenuController.setUpBoard(game);
        ArrayList<DecksCard> allCards = new ArrayList<>();
        ArrayList<RegularCard> cards = NeutralRegularCardsData.getAllRegularCard();
        allCards.addAll(cards);
        allCards.addAll(NorthernRealmsRegularCardsData.getAllRegularCard());
        allCards.addAll(SkelligeRegularCardsData.getAllRegularCard());
        allCards.addAll(WeatherCardsData.getAllWeatherCards());
        allCards.addAll(SpecialCardsData.getAllSpecialCard());
        for (int i = 4; i < 14; i++) {
            DecksCard card = allCards.get(i);
            player.addCardToHand(card);
            CardView cardView = card.getCardView();
//            player.getPlayerView().getHandView().getChildren().add(cardView);
            hand.getChildren().add(cardView);
            if (card instanceof SpecialCard)
                gameMenuController.handleSpecialCardEvents((SpecialCard) card, game, player);
            if (card instanceof RegularCard)
                gameMenuController.handleRegularCardEvents((RegularCard) card, game, player, opponentPlayer);
            if (card instanceof WeatherCard) gameMenuController.handleWeatherCardEvents((WeatherCard) card, game);
        }
        for (int i = 14; i < 24; i++) {
            DecksCard card = allCards.get(i);
            opponentPlayer.addCardToHand(card);
            CardView cardView = card.getCardView();
//            player.getPlayerView().getHandView().getChildren().add(cardView);
            if (card instanceof SpecialCard)
                gameMenuController.handleSpecialCardEvents((SpecialCard) card, game, opponentPlayer);
            if (card instanceof RegularCard)
                gameMenuController.handleRegularCardEvents((RegularCard) card, game, opponentPlayer, player);
            if (card instanceof WeatherCard) gameMenuController.handleWeatherCardEvents((WeatherCard) card, game);
        }
        for (int i = 24; i < 44; i++) {
            player.addCardToDeck(allCards.get(i));
        }
        for (int i = 44; i < 55; i++) {
            opponentPlayer.addCardToDeck(allCards.get(i));
        }
        setUpNotificationBox();
    }

    public HBox getWeatherCardPosition() {
        return weatherCardPosition;
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
    public void endRound(GameNotification gameNotification) {
        showRoundEndNotification(gameNotification);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> {
            showRoundStart();
        }));
        timeline.play();
    }
    private void showRoundStart(){
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel, GameNotification.ROUND_STARTS);
        timeline.play();
    }

    public void handlePassTurn(Game game) {
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
        game.getOpponentPlayer().getPlayerInformationView().getStyleClass().remove("brownBoxShadowed");
        for (DecksCard card : game.getOpponentPlayer().getHand()) {
            hand.getChildren().remove(card.getCardView());
        }
        for (DecksCard card : game.getCurrentPlayer().getHand()) {
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

    @FXML
    private void passTurn() {
        System.out.println(App.getCurrentGame().isRoundPassed());
        gameMenuController.checkRound(App.getCurrentGame());
        App.getCurrentGame().setRoundIsPassed(true);
    }

    private void showPassTurnNotification() {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel, GameNotification.PASS_TURN);
        timeline.play();
    }

    private void showRoundEndNotification(GameNotification notification) {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel, notification);
        timeline.play();
    }
//    private void showWhosTurnNotification(){
//        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel, GameNotification.WHOS_TURN);
//        timeline.play();
//    }

    private void setUpNotificationBox() {
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

    public void showResult(Player winner, GameNotification gameNotification) {
        showRoundEndNotification(gameNotification);
    }

}
