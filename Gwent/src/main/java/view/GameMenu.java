package view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.App;

public class GameMenu implements Menu{
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
        double x = opponentLeader.getLayoutX();
        double y = opponentLeader.getLayoutY();
        double width = opponentLeader.getPrefWidth();
        double height = opponentLeader.getPrefHeight();
        Rectangle rectangle = new Rectangle();
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(new ImagePattern(new Image(this.getClass().getResource("/Images/nilfgaard_assire.jpg").toExternalForm())));
        pane.getChildren().add(rectangle);
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setLayoutX(leader.getLayoutX());
        rectangle1.setLayoutY(leader.getLayoutY());
        rectangle1.setWidth(leader.getPrefWidth());
        rectangle1.setHeight(leader.getPrefHeight());
        rectangle1.setFill(new ImagePattern(new Image(this.getClass().getResource("/Images/nilfgaard_assire.jpg").toExternalForm())));
        pane.getChildren().add(rectangle1);
        Rectangle[] rectangles = new Rectangle[6];
        for (int i = 0; i < 6; i++){
            rectangles[i] = new Rectangle();
        }
        rectangles[0].setLayoutX(opponentSiegeSideCardPosition.getLayoutX());
        rectangles[0].setLayoutY(opponentSiegeSideCardPosition.getLayoutY());
        rectangles[0].setWidth(opponentSiegeSideCardPosition.getPrefWidth());
        rectangles[0].setHeight(opponentSiegeSideCardPosition.getPrefHeight());
        rectangles[1].setLayoutX(opponentRangeSideCardPosition.getLayoutX());
        rectangles[1].setLayoutY(opponentRangeSideCardPosition.getLayoutY());
        rectangles[1].setWidth(opponentRangeSideCardPosition.getPrefWidth());
        rectangles[1].setHeight(opponentRangeSideCardPosition.getPrefHeight());
        rectangles[2].setLayoutX(opponentCloseSideCardPosition.getLayoutX());
        rectangles[2].setLayoutY(opponentCloseSideCardPosition.getLayoutY());
        rectangles[2].setWidth(opponentCloseSideCardPosition.getPrefWidth());
        rectangles[2].setHeight(opponentCloseSideCardPosition.getPrefHeight());
        rectangles[3].setLayoutX(closeSideCardPosition.getLayoutX());
        rectangles[3].setLayoutY(closeSideCardPosition.getLayoutY());
        rectangles[3].setWidth(closeSideCardPosition.getPrefWidth());
        rectangles[3].setHeight(closeSideCardPosition.getPrefHeight());
        rectangles[4].setLayoutX(siegeSideCardPosition.getLayoutX());
        rectangles[4].setLayoutY(siegeSideCardPosition.getLayoutY());
        rectangles[4].setWidth(siegeSideCardPosition.getPrefWidth());
        rectangles[4].setHeight(siegeSideCardPosition.getPrefHeight());
        rectangles[5].setLayoutX(rangedSideCardPosition.getLayoutX());
        rectangles[5].setLayoutY(rangedSideCardPosition.getLayoutY());
        rectangles[5].setWidth(rangedSideCardPosition.getPrefWidth());
        rectangles[5].setHeight(rangedSideCardPosition.getPrefHeight());
        for (int i = 0; i < 6; i++){
            rectangles[i].setFill(new ImagePattern(new Image(this.getClass().getResource("/Images/special_horn_sm.jpg").toExternalForm())));
            pane.getChildren().add(rectangles[i]);
        }
        Rectangle[] rectangles1 = new Rectangle[4];
        for (int i = 0; i < 4; i++){
            rectangles1[i] = new Rectangle();
        }
        rectangles1[0].setLayoutX(opponentDeck.getLayoutX());
        rectangles1[0].setLayoutY(opponentDeck.getLayoutY());
        rectangles1[0].setWidth(opponentDeck.getPrefWidth());
        rectangles1[0].setHeight(opponentDeck.getPrefHeight());
        rectangles1[1].setLayoutX(deck.getLayoutX());
        rectangles1[1].setLayoutY(deck.getLayoutY());
        rectangles1[1].setWidth(deck.getPrefWidth());
        rectangles1[1].setHeight(deck.getPrefHeight());
        rectangles1[2].setLayoutX(opponentDiscardPile.getLayoutX());
        rectangles1[2].setLayoutY(opponentDiscardPile.getLayoutY());
        rectangles1[2].setWidth(opponentDiscardPile.getPrefWidth());
        rectangles1[2].setHeight(opponentDiscardPile.getPrefHeight());
        rectangles1[3].setLayoutX(discardPile.getLayoutX());
        rectangles1[3].setLayoutY(discardPile.getLayoutY());
        rectangles1[3].setWidth(discardPile.getPrefWidth());
        rectangles1[3].setHeight(discardPile.getPrefHeight());
        for (int i = 2; i < 4; i++){
            rectangles1[i].setFill(new ImagePattern(new Image(this.getClass().getResource("/Images/monsters_kayran.jpg").toExternalForm())));
            pane.getChildren().add(rectangles1[i]);
        }
        for (int i = 0; i < 2; i++){
            rectangles1[i].setFill(new ImagePattern(new Image(this.getClass().getResource("/Images/faction_nilfgaard.jpg").toExternalForm())));
            pane.getChildren().add(rectangles1[i]);
        }
        Rectangle[] rectangles2 = new Rectangle[10];
        for (int i = 0; i < 10; i++){
            rectangles2[i] = new Rectangle();
        }

    }

}
