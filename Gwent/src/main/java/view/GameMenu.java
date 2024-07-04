package view;

import controller.GameMenuController;
import enums.*;
import enums.cardsData.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.*;
import model.card.RegularCard;
import model.card.SpecialCard;

import java.util.*;

public class GameMenu implements Menu{
    private final GameMenuController gameMenuController = new GameMenuController(this);
    private static Pane notifPane = new Pane();
    private static Label notifLabel = new Label();
    private static ImageView notifImageView = new ImageView();
    @FXML
    private Label notifText;
    @FXML
    private HBox weatherCardPosition;
    @FXML
    private VBox currentRowArea;
    @FXML
    private VBox opponentRowsArea;
    @FXML
    private VBox rowsPane;
    @FXML
    private Pane pane;
    @FXML
    private Pane notifBox;
    @FXML
    private ImageView notifImage;
    @FXML
    private Label notifTextLabel;
    @FXML
    private Label opponentPassedLabel;
    @FXML
    private Label passedLabel;
    @FXML
    private Region opponentActiveLeaderIcon;
    @FXML
    private Region activeLeaderIcon;
    @FXML
    private Region highScore;
    @FXML
    private Label faction;
    @FXML
    private Label username;
    @FXML
    private Label cardNumber;
    @FXML
    private Region factionRegion;
    @FXML
    private Region opponentHighScoreRegion;
    @FXML
    private Region specialWeather;
    @FXML
    private Label opponentCardNumber;
    @FXML
    private Region opponentFactionRegion;
    @FXML
    private Label opponentFaction;
    @FXML
    private Label opponentTotalScore;
    @FXML
    private Label totalScore;
    @FXML
    private Label opponentUsername;
    @FXML
    private Region opponentLeftGem, opponentRightGem, leftGem, rightGem;
    @FXML
    private Region deck;
    @FXML
    private Region opponentDeck;
    @FXML
    private HBox hand;
    @FXML
    private Region opponentDiscardPile;
    @FXML
    private Region discardPile;
    @FXML
    private Region opponentLeader;
    @FXML
    private Region leader;
    private PlayerInformationView currentPlayerInformationView;
    private PlayerInformationView oppoenentPlayerInformationView;
    @FXML
    public void initialize() {
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user, game);
        Player opponentPlayer = new Player(user2, game);
        PlayerInformationView playerInformationView = new PlayerInformationView(player, CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE);
        player.setInformationView(playerInformationView);
        PlayerInformationView opponentInformationView = new PlayerInformationView(opponentPlayer, CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE);
        opponentPlayer.setInformationView(opponentInformationView);
        pane.getChildren().addAll(playerInformationView,opponentInformationView);
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);
        gameMenuController.setUpBoard(game);
        ArrayList<RegularCard> cards = NorthernRealmsRegularCardsData.getAllRegularCard();
        for (int i = 0; i < 4; i++){
            RegularCard card = cards.get(i);
            player.addCardToHand(card);
            CardView cardView = card.getCardView();
            hand.getChildren().add(cardView);
            gameMenuController.handleRegularCardEvents(card, game);
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        }
        ArrayList<SpecialCard> specialCards = SpecialCardsData.getAllSpecialCard();
        for (int i = 0; i < 8; i++) {
            SpecialCard specialCard = specialCards.get(i);
            player.addCardToHand(specialCard);
            CardView cardView = specialCard.getCardView();
            hand.getChildren().add(cardView);
            gameMenuController.handleSpecialCardEvents(specialCard, game);
            specialCard.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        }
        setUpDeck(user, deck);
        setUpDeck(user2, opponentDeck);
        setUpNotificationBox();
    }
    public void resetRowStyles(RowView rowView) {
        rowView.getRow().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
        rowView.getSpecialCardPosition().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2),event -> {
            notifImageView.getStyleClass().remove(GameNotification.PASS_TURN.getNotificationImage());
            notifLabel.setText("");
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
    private void setUpDeck(User player, Region deck){
        FactionType factionType = player.getSelectedFaction();
        String factionName = factionType.toString().toLowerCase().replaceAll("_", "-");
        deck.getStyleClass().add(factionName + "-faction");
    }
    public void handlePassTurn(Game game){
        passTurn();
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().remove("brownBoxShadowed");
        game.getOpponentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
    }


}
