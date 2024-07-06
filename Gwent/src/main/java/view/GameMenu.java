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
    private static final Pane notifPane = new Pane();
    private static final Label notifLabel = new Label();
    private static final ImageView notifImageView = new ImageView();
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
    public void initialize(){
        setUpNotificationBox();
    }
    public HBox getWeatherCardPosition(){
        return weatherCardPosition;
    }
    public void resetStyles(RowView rowView) {
        for (CssAddress cssAddress : rowView.getStyles()){
            rowView.getRow().getStyleClass().remove(cssAddress.getStyleClass());
            rowView.getSpecialCardPosition().getStyleClass().remove(cssAddress.getStyleClass());
        }
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
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel, GameNotification.PASS_TURN);
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
