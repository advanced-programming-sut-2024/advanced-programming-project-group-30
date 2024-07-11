package controller;

import enums.FactionType;
import enums.SizeData;
import enums.cardsData.CardData;
import enums.cardsData.DeckCardData;
import enums.cardsData.LeaderCardData;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import model.*;
import model.card.DecksCard;
import view.PregameCardView;
import view.PregameMenu;
import view.SelectionPage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PregameMenuController {
    private PregameData pregameData;
    private final PregameMenu menu;

    public PregameMenuController(PregameMenu menu) {
        this.menu = menu;
    }

    public void setup(PregameData pregameData) {
        this.pregameData = pregameData;
        setup();
    }

    private void setup() {
        menu.updateFactionsFields(pregameData.getFaction());
        menu.setNickname(pregameData.getUser().getNickname());
        uploadToCardCollection(pregameData.getCardCollection());
        SelectionPage<FactionType> factionSelectionPage = new SelectionPage<>(FactionType.getAllChooseModelView(),
                FactionType.getFactionIndex(pregameData.getFaction()), SizeData.FATION_CARD);
        menu.setFactionSelectionPage(factionSelectionPage);
        SelectionPage<LeaderCardData> leaderSelectionPage = new SelectionPage<>(LeaderCardData.getFactionsLeaderChooseView(pregameData.getFaction()),
                0, SizeData.GAME_LG_CARD);
        pregameData.setLeader(leaderSelectionPage.getSelectedModel());
        menu.setLeaderSelectionPage(leaderSelectionPage);
    }

    public PregameData getPregameData() {
        return pregameData;
    }

    public Result changeTurn() {
        if (!pregameData.isUnitCardsNumberValid()) return new Result(false, "choose at least 22 unit cards");
        if (!pregameData.isSpecialCardsNumberValid())
            return new Result(false, "you can't choose more than 10 special cards");
        pregameData.changeTurn();
        setup();
        return new Result(true, "");
    }

    public Result startGame() {
        if (!pregameData.isUnitCardsNumberValid()) return new Result(false, "choose at least 22 unit cards");
        if (!pregameData.isSpecialCardsNumberValid())
            return new Result(false, "you can't choose more than 10 special cards");
        Game newGame = new Game(pregameData);
        App.getSceneManager().goToGame(newGame);
        return new Result(true, "");
    }

    public void uploadToCardCollection(TreeMap<DeckCardData, ArrayList<DecksCard>> collection) {
        menu.clearCardsPane();
        for (DeckCardData cardData : collection.keySet())
            menu.addToCardCollection(new PregameCardView(cardData));
        menu.updateNumberData();
    }

    public void changeFation(FactionType faction) {
        pregameData.setFaction(faction);
        SelectionPage<LeaderCardData> leaderSelectionPage = new SelectionPage<>(LeaderCardData.getFactionsLeaderChooseView(pregameData.getFaction()),
                0, SizeData.GAME_LG_CARD);
        menu.setLeaderSelectionPage(leaderSelectionPage);
        pregameData.setLeader(leaderSelectionPage.getSelectedModel());
        uploadToCardCollection(pregameData.getCardCollection());
    }

    public void selectLeader(LeaderCardData leaderCardData) {
        pregameData.setLeader(leaderCardData);
    }

    public int getIndex(PregameCardView cardView, List<Node> nodes) {
        ArrayList<DeckCardData> list = new ArrayList<>();
        for (Node node : nodes) {
            PregameCardView card = (PregameCardView) node;
            list.add(card.getCardData());
        }
        list.add(cardView.getCardData());
        list.sort(CardComparator.getCardComparator());
        return list.indexOf(cardView.getCardData());
    }
}