package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.App;

public class GameMenu implements Menu{
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
    private static final String GEM_ON_IMAGE_CLASS = "gem-on-image";
    private static final String GEM_OFF_IMAGE_CLASS = "gem-off-image";
    @FXML
    public void initialize() {
        opponentRightGem.getStyleClass().add(GEM_ON_IMAGE_CLASS);
        rightGem.getStyleClass().add(GEM_ON_IMAGE_CLASS);
        opponentLeftGem.getStyleClass().add(GEM_ON_IMAGE_CLASS);
        leftGem.getStyleClass().add(GEM_ON_IMAGE_CLASS);
    }

    public void passTurn() {
    }
}
