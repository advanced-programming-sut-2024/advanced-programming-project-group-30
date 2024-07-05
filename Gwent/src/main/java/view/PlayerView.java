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
    public PlayerView(Player player, VBox boardView, ArrayList<CoordinateData> coordinateDatas, CssAddress cssAddress){
        playerInformationView = new PlayerInformationView(player, coordinateDatas.get(0), cssAddress);
        this.boardView = boardView;
        handView = new HBox();
        deckView = new HBox();
        discardPileView = new HBox();
        leaderView = new HBox();
    }
    private void setUp(){

    }
    private void setUpDeckView(){
        deckView.getStyleClass().add(CssAddress.DECK_VIEW.getStyleClass());

    }



}
