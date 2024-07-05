package view;

import enums.CoordinateData;
import enums.CssAddress;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerView {
    private Player player;
    private PlayerInformationView playerInformationView;
    private HBox handView;
    private HBox deckView;
    private HBox discardPileView;
    private HBox leaderView;
    private VBox boardView;
    private Pane pane;
    public PlayerView(Player player, Pane pane, VBox boardView, HBox discardPileView, HBox deckView, HBox handView, HBox leaderView, CoordinateData coordinateData, CssAddress cssAddress){
        playerInformationView = new PlayerInformationView(player, coordinateData, cssAddress);
        this.boardView = boardView;
        this.pane = pane;
        this.player = player;
        this.handView = new HBox(handView);
        this.handView.getStyleClass().add("hand-padding");
        this.leaderView = new HBox(leaderView);
        this.deckView = new HBox(deckView);
        System.out.println(this.deckView.getLayoutX());
        this.discardPileView = new HBox(discardPileView);
        setUpDeck(deckView);
    }
    public HBox getDeckView(){
        return deckView;
    }
    public VBox getBoardView(){
        return boardView;
    }
    public HBox getDiscardPileView(){
        return discardPileView;
    }
    public HBox getHandView(){
        return handView;
    }
    public PlayerInformationView getPlayerInformationView() {
        return playerInformationView;
    }
    private void setUpDeck(HBox deck){
        clone(deckView, deck);
        Image image =  player.getUser().getSelectedFaction().getLgImage();
        ImageView imageView = new ImageView(image);
        deckView.getChildren().add(imageView);
        imageView.setFitHeight(90);
        imageView.setFitWidth(61);
        pane.getChildren().add(deckView);
    }
    private HBox clone(HBox node1, HBox node2){
        node1.getChildren().clear();
        for (Node node : node2.getChildren()) {
            node1.getChildren().add(node);
        }
        node1.setLayoutX(node2.getLayoutX());
        node1.setLayoutY(node2.getLayoutY());
        node1.setPrefWidth(node2.getPrefWidth());
        node1.setPrefHeight(node2.getPrefHeight());
        return node1;
    }
}
