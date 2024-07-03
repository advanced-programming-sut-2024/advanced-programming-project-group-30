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
    public HBox siegeRow;
    public HBox rangedRow;
    public HBox closedCombatRow;
    public Pane rowsPane;
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
    @FXML
    public void initialize() {
        //TODO: handle initialize
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user, game);
        Player opponentPlayer = new Player(user2, game);
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);
        ArrayList<RegularCard> cards = NorthernRealmsRegularCardsData.getAllRegularCard();
        for (int i = 0; i < 7; i++){
            RegularCard card = cards.get(i);
            player.addCardToHand(card);
            CardView cardView = card.getCardView();
            hand.getChildren().add(cardView);
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
            gameMenuController.handleRegularCardEvents(card, game);
        }
        setUpDeck(user, deck);
        setUpDeck(user2, opponentDeck);
        setUpUserInformation(player, username, faction, totalScore, cardNumber, leftGem, rightGem);
        setUpUserInformation(opponentPlayer, opponentUsername, opponentFaction, opponentTotalScore, opponentCardNumber, opponentLeftGem, opponentRightGem);
        gameMenuController.setUpBoard(game);
    }

    public void resetRowStyles(RowView rowView) {
        rowView.getRowView().getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
    }
    private void setUpUserInformation(Player player, Label username, Label faction, Label totalScore, Label cardNumber, Region leftGem, Region rightGem) {
        User user = player.getUser();
        username.setText(user.getUsername());
        rightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        leftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        faction.setText(user.getSelectedFaction().toString());
        totalScore.setText(String.valueOf(player.getPoint()));
        cardNumber.setText(String.valueOf(player.getHand().size()));
    }

    private void setUpLeader(User user){

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
        rowsPane.getChildren().addAll(siege.getRowView(), ranged.getRowView(), close.getRowView());
    }
}
