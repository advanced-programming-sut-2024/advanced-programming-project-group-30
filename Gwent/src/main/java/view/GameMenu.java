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
import javafx.util.Duration;
import model.*;
import model.card.RegularCard;

import java.util.*;

public class GameMenu implements Menu{
    private final GameMenuController gameMenuController = new GameMenuController(this);
    @FXML
    private Pane rowsPane;
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
        //TODO: handle initialize
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user, game);
        Player opponentPlayer = new Player(user2, game);
        PlayerInformationView playerInformationView = new PlayerInformationView(player, CoordinateData.PLAYER_INFORMATION_BOX, CssAddress.CURRENT_PLAYER_TOTAL_SCORE_IMAGE);
        PlayerInformationView opponentInformationView = new PlayerInformationView(opponentPlayer, CoordinateData.OPPONENT_INFORMATION_BOX, CssAddress.OPPONENT_PLAYER_TOTAL_SCORE_IMAGE);
        pane.getChildren().addAll(playerInformationView,opponentInformationView);
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);
        ArrayList<Row> rows = player.getRows();
        for (Row row: rows){
            rowsPane.getChildren().add(row.getRowView());
        }
        ArrayList<RegularCard> cards = NorthernRealmsRegularCardsData.getAllRegularCard();
        for (int i = 0; i < 7; i++){
            RegularCard card = cards.get(i);
            player.addCardToHand(card);
            CardView cardView = card.getCardView();
            gameMenuController.handleRegularCardEvents(card, game);
            hand.getChildren().add(cardView);
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
        }
        setUpDeck(user, deck);
        setUpDeck(user2, opponentDeck);

//        setUpUserInformation(player, username, faction, totalScore, cardNumber, leftGem, rightGem);
//        setUpUserInformation(opponentPlayer, opponentUsername, opponentFaction, opponentTotalScore, opponentCardNumber, opponentLeftGem, opponentRightGem);
        gameMenuController.setUpBoard(game);
    }

    public void resetRowStyles(RowView rowView) {
        rowView.getRow().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
    }
    private void setUpDeck(User player, Region deck){
        FactionType factionType = player.getSelectedFaction();
        String factionName = factionType.toString().toLowerCase().replaceAll("_", "-");
        deck.getStyleClass().add(factionName + "-faction");
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
    public void passTurn() {
        notifBox.setVisible(true);
        notifImage.getStyleClass().add(GameNotification.PASS_TURN.getNotificationImage());
        notifTextLabel.setText(GameNotification.PASS_TURN.getNotification());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2),event -> {
            notifBox.setVisible(false);
            notifImage.getStyleClass().remove(GameNotification.PASS_TURN.getNotificationImage());
            notifTextLabel.setText("");
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void setUpBoard(RowView siege, RowView ranged, RowView close){
        rowsPane.getChildren().addAll(siege.getRow(), ranged.getRow(), close.getRow());
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

}
