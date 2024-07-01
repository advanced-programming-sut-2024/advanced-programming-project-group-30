package view;

import enums.CssAddress;
import enums.FactionType;
import enums.cardsData.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.App;
import model.Game;
import model.card.DecksCard;
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
        opponentRightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        rightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        opponentLeftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        leftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        hand.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        });
        ArrayList<RegularCard> cards = NorthernRealmsRegularCardsData.getAllRegularCard();
        for (int i = 0; i < 5; i++) {
            RegularCard card = cards.get(i);
            hand.getChildren().add(card.getCardView());
        }
    }

    public void passTurn() {
    }

}
