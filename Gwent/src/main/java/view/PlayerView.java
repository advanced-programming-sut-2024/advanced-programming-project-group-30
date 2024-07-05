package view;

import enums.CoordinateData;
import enums.CssAddress;
import javafx.scene.layout.HBox;
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
    public PlayerView(Player player, VBox boardView, HBox discardPileView, HBox deckView, HBox handView,CoordinateData coordinateData, CssAddress cssAddress){
        playerInformationView = new PlayerInformationView(player, coordinateData, cssAddress);
        this.boardView = boardView;
        this.handView = new HBox(handView);
        this.handView.getStyleClass().add("hand-padding");

        this.deckView = deckView;
        this.discardPileView = discardPileView;
        leaderView = new HBox();
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
    public void setBoardView(VBox boardView){
        this.boardView = boardView;
    }

    public PlayerInformationView getPlayerInformationView() {
        return playerInformationView;
    }
}
