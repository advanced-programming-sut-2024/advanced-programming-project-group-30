package view;

import controller.GameMenuController;
import enums.*;
import enums.cardsData.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.*;
import model.card.*;

import java.io.IOException;
import java.util.*;

public class GameMenu implements Menu{
    private final GameMenuController gameMenuController = new GameMenuController(this);
    private static Pane notifPane = new Pane();
    private static Label notifLabel = new Label();
    private static ImageView notifImageView = new ImageView();
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
    @FXML
    public void initialize() throws IOException {
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user, game);
        Player opponentPlayer = new Player(user2, game);
        PlayerView playerView = new PlayerView(player, pane,currentRowArea, discardPile, deck,  hand,  leader, CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE);
        player.setPlayerView(playerView);
        PlayerView opponentPlayerView = new PlayerView(opponentPlayer, pane,opponentRowsArea, opponentDiscardPile, opponentDeck, hand, opponentLeader,CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE);
        opponentPlayer.setPlayerView(opponentPlayerView);
        pane.getChildren().addAll(playerView.getPlayerInformationView(),opponentPlayerView.getPlayerInformationView());
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);
        gameMenuController.setUpBoard(game);
        System.out.println(player);
        ArrayList<DecksCard> allCards = new ArrayList<>();
        ArrayList<RegularCard> cards = NeutralRegularCardsData.getAllRegularCard();
        allCards.addAll(cards);
        allCards.addAll(MonstersRegularCardsData.getAllRegularCard());
        allCards.addAll(NorthernRealmsRegularCardsData.getAllRegularCard());
        allCards.addAll(SkelligeRegularCardsData.getAllRegularCard());
        allCards.addAll(NeutralRegularCardsData.getAllRegularCard());
        allCards.addAll(WeatherCardsData.getAllWeatherCards());
        allCards.addAll(SpecialCardsData.getAllSpecialCard());
        Random random = new Random();
        int j = random.nextInt(allCards.size() - 10);
        for (int i = 0; i < j; i++){
            DecksCard card = allCards.get(i);
            player.addCardToHand(card);
            CardView cardView = card.getCardView();
//            player.getPlayerView().getHandView().getChildren().add(cardView);
            hand.getChildren().add(cardView);
            if (card instanceof SpecialCard)
                gameMenuController.handleSpecialCardEvents((SpecialCard) card, game, player, opponentPlayer);
            if (card instanceof RegularCard)
                gameMenuController.handleRegularCardEvents((RegularCard) card, game, player, opponentPlayer);
            if (card instanceof WeatherCard)
                gameMenuController.handleWeatherCardEvents((WeatherCard) card, game);
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        }
        centerPane.getChildren().add(hand);
        for (int i = j; i < j + 10; i++){
            DecksCard card = allCards.get(i);
            opponentPlayer.addCardToHand(card);
            CardView cardView = card.getCardView();
//            player.getPlayerView().getHandView().getChildren().add(cardView);
            if (card instanceof SpecialCard)
                gameMenuController.handleSpecialCardEvents((SpecialCard) card, game, opponentPlayer, player);
            if (card instanceof RegularCard)
                gameMenuController.handleRegularCardEvents((RegularCard) card, game, opponentPlayer, player);
            if (card instanceof WeatherCard)
                gameMenuController.handleWeatherCardEvents((WeatherCard) card, game);
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        }
        centerPane.getChildren().addAll(playerView.getHandView());
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
        setUpNotificationBox();
    }
    public HBox getWeatherCardPosition(){
        return weatherCardPosition;
    }
    public void resetStyles(RowView rowView) {
        rowView.getRow().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
        rowView.getSpecialCardPosition().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
        weatherCardPosition.getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
    }
    public void setUpScores(ArrayList<Row> allRows){
        for (Row row : allRows){
            row.updateRowScore();
        }
    }
    public void setNodeStyle(Node node, CssAddress cssAddress){
        node.getStyleClass().add(cssAddress.getStyleClass());
    }
    public void removeNodeStyle(Node node, CssAddress cssAddress){
        node.getStyleClass().remove(cssAddress.getStyleClass());
    }
    @FXML
    private void passTurn() {
        pane.getChildren().add(notifPane);
        notifImageView.getStyleClass().add(GameNotification.PASS_TURN.getNotificationImage());
        notifLabel.setText(GameNotification.PASS_TURN.getNotification());
        pane.setDisable(true);
        pane.getStyleClass().add("rootPaneNotifStyle");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),event -> {
            pane.getStyleClass().remove("rootPaneNotifStyle");
            notifImageView.getStyleClass().remove(GameNotification.PASS_TURN.getNotificationImage());
            notifLabel.setText("");
            pane.setDisable(false);
            pane.getChildren().remove(notifPane);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void setUpBoard(RowView siege, RowView ranged, RowView close, RowView opSiege, RowView opRanged, RowView opClose){
        opponentRowsArea.getChildren().addAll(opSiege, opRanged, opClose);
        currentRowArea.getChildren().addAll(close, ranged, siege);
    }
    public void setUpUserInformation(Label usernameLabel, String username) {
        usernameLabel.setText(username);
    }
    public void setUpFaction(Region factionRegion, String factionName, Label factionLabel) {
//        factionRegion.getStyleClass().add(factionName + "-faction");
        factionLabel.setText(factionName);
    }
    public void updateHandCardNumber(Label cardNumber, int number){
        cardNumber.setText(String.valueOf(number));
    }
    public void setUpAfterSwitch(Node pane, Node node1, Node node2){
        if (pane instanceof HBox)
            ((HBox) pane).getChildren().addAll(node1, node2);
        else if (pane instanceof Pane) {
            ((Pane) pane).getChildren().addAll(node1,node2);
        }

    }
    public void handlePassTurn(Game game) {
        passTurn();
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
        game.getOpponentPlayer().getPlayerInformationView().getStyleClass().remove("brownBoxShadowed");
        hand.getChildren().clear();
        for (DecksCard card : game.getCurrentPlayer().getHand())
            hand.getChildren().add(card.getCardView());

    }
    public VBox getCurrentRowArea(){
        return currentRowArea;
    }
    public VBox getOpponentRowsArea(){
        return opponentRowsArea;
    }
    public VBox getRowsPane(){
        return rowsPane;
    }
    public Pane getPane(){
        return pane;
    }
    private void setUpNotificationBox(){
        notifPane.getStyleClass().add(CssAddress.NOTIF_BOX.getStyleClass());
        notifPane.setLayoutY(notifBox.getLayoutY());
        notifLabel.getStyleClass().add(CssAddress.NOTIFICATION_LABEL.getStyleClass());
        notifLabel.setLayoutX(notifText.getLayoutX());
        notifLabel.setLayoutY(notifText.getLayoutY());
        notifImageView.setLayoutY(notifImage.getLayoutY());
        notifImageView.setLayoutX(notifImage.getLayoutX());
        notifImageView.setFitWidth(notifImage.getFitWidth());
        notifImageView.setFitHeight(notifImage.getFitHeight());
        notifPane.getChildren().addAll(notifLabel,notifImageView);
    }

}
