package view;

import enums.CssAddress;
import enums.FactionType;
import enums.SecurityQuestion;
import enums.cardsData.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.App;
import model.Game;
import model.Player;
import model.User;
import model.card.RegularCard;

import java.util.ArrayList;

public class GameMenu implements Menu{
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
    private Label opponentRangedRowScore;
    @FXML
    private Label opponentCloseRowScore;
    @FXML
    private Label closeRowScore;
    @FXML
    private Label rangeRowScore;
    @FXML
    private Label siegeRowScore;
    @FXML
    private Label opponentSiegeRowScore;
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
    private HBox rangedRow;
    @FXML
    private HBox closedCombatRow;
    @FXML
    private HBox opponentCloseCombatRow;
    @FXML
    private HBox opponentRangedRow;
    @FXML
    private HBox opponentSiegeRow;
    @FXML
    private HBox siegeRow;
    @FXML
    private Region opponentSiegeSideCardPosition;
    @FXML
    private Region opponentRangeSideCardPosition;
    @FXML
    private Region opponentCloseSideCardPosition;
    @FXML
    private Region closeSideCardPosition;
    @FXML
    private Region siegeSideCardPosition;
    @FXML
    private Region rangedSideCardPosition;
    @FXML
    private Pane pane;
    @FXML
    private Region opponentLeader;
    @FXML
    private Region leader;
    @FXML
    public void initialize() {
        User user = new User("bahar", "123", "bahar", "bahar", SecurityQuestion.QUESTION_1, "blue");
        User user2 = new User("fatemeh", "123", "fatemeh", "fatemeh", SecurityQuestion.QUESTION_1, "blue");
        Game game = new Game();
        App.setCurrentGame(game);
        Player player = new Player(user, game);
        Player opponentPlayer = new Player(user2, game);
        game.setCurrentPlayer(player);
        game.setOpponentPlayer(opponentPlayer);

        ArrayList<RegularCard> cards = NorthernRealmsRegularCardsData.getAllRegularCard();
        for (int i = 0; i < 5; i++) {
            RegularCard card = cards.get(i);
            player.addCardToHand(card);
            hand.getChildren().add(card.getCardView());
            card.getCardView().getStyleClass().add(CssAddress.GAME_HAND_SM_CARD.getStyleClass());
//            card.getCardView().setOnMouseClicked(this::handleCardClick);
        }
        setUpDeck(player, deck);
        setUpDeck(opponentPlayer, opponentDeck);
        setUpUserInformation(player, username, faction, totalScore, cardNumber, leftGem, rightGem);
        setUpUserInformation(opponentPlayer, opponentUsername, opponentFaction, opponentTotalScore, opponentCardNumber, opponentLeftGem, opponentRightGem);
        setUpScores(siegeRowScore, rangeRowScore, closeRowScore, player);
        setUpScores(opponentSiegeRowScore, opponentRangedRowScore, opponentCloseRowScore, opponentPlayer);
    }

//    private void handleCardClick(MouseEvent mouseEvent) {
//        CardView cardView = (CardView) mouseEvent.getSource();
//        if (cardView.getCard().isInHand()) {
//            cardView.getCard().setInHand(false);
//            hand.getChildren().remove(cardView);
//            if (hand.getChildren().size() == 0) {
//                passedLabel.setVisible(true);
//            }
//        }
//    }

    private void setUpUserInformation(Player player, Label username, Label faction, Label totalScore, Label cardNumber, Region leftGem, Region rightGem) {
        User user = player.getUser();
        username.setText(user.getUsername());
        rightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        leftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        faction.setText(user.getSelectedFaction().toString());
        totalScore.setText(String.valueOf(player.getPoint()));
        cardNumber.setText(String.valueOf(player.getHand().size()));
    }
    private void setUpDeck(Player player, Region deck){
        FactionType factionType = player.getUser().getSelectedFaction();
        String factionName = factionType.toString().toLowerCase().replaceAll("_", "-");
        System.out.println(factionName);
        deck.getStyleClass().add(factionName + "-faction");
    }
    private void setUpScores(Label siegeRowScore, Label rangeRowScore, Label closeRowScore, Player player){
        siegeRowScore.setText(String.valueOf(player.getSiege().getRowPoint()));
        rangeRowScore.setText(String.valueOf(player.getRangedCombat().getRowPoint()));
        closeRowScore.setText(String.valueOf(player.getCloseCombat().getRowPoint()));
    }

    public void passTurn() {
    }

}
