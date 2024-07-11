package view;

import controller.GameMenuController;
import enums.Ability;
import enums.CssAddress;
import enums.GameNotification;
import enums.SizeData;
import enums.cardsData.*;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.*;
import model.ability.LeaderAbility;
import model.ability.WeatherCardAbility;
import model.card.DecksCard;
import model.card.RegularCard;
import model.card.SpecialCard;
import model.card.WeatherCard;

import javax.management.MBeanAttributeInfo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameMenu implements Menu {
    private final Pane notifPane = new Pane();
    private final Label notifLabel = new Label();
    private final ImageView notifImageView = new ImageView();
    private final GameMenuController gameMenuController = new GameMenuController(this);
    public Pane cheatMenu;
    public TextField cheatMenuTextField;
    @FXML
    private Label deckNumber;
    @FXML
    private Label opponentDeckNumber;
    @FXML
    private Label result;
    @FXML
    private Pane endGamePane;
    @FXML
    private Label loser;
    @FXML
    private Label winnerRound1;
    @FXML
    private Label loserRound1;
    @FXML
    private Label winnerRound2;
    @FXML
    private Label loserRound2;
    @FXML
    private Label winnerRound3;
    @FXML
    private Label loserRound3;
    @FXML
    private Label winner;
    @FXML
    private VBox root;
    @FXML
    private Pane pane;
    @FXML
    private VBox centerPane;
    @FXML
    private VBox rowsPane;
    @FXML
    private VBox currentRowArea;
    @FXML
    private VBox opponentRowsArea;
    @FXML
    private HBox weatherCardPosition;
    @FXML
    private HBox hand;
    @FXML
    private HBox deck;
    @FXML
    private HBox opponentDeck;
    @FXML
    private Region activeLeaderIcon;
    @FXML
    private Region opponentActiveLeaderIcon;
    @FXML
    private HBox leader;
    @FXML
    private HBox opponentLeader;
    @FXML
    private HBox discardPile;
    @FXML
    private HBox opponentDiscardPile;
    @FXML
    private Label notifText;
    @FXML
    private Pane notifBox;
    @FXML
    private ImageView notifImage;

    @FXML
    public void initialize() {
        root.widthProperty().addListener((Void) -> scalePanes());
        root.heightProperty().addListener((Void) -> scalePanes());
    }

    public void setup() {
        gameMenuController.setup();
    }

    public void addInformationViews(Pane currentPlayerInfo, Pane opponentPlayerInfo) {
        pane.getChildren().addAll(currentPlayerInfo, opponentPlayerInfo);
    }

    public void setHand(ArrayList<DecksCard> hand) {
        for (DecksCard card : hand)
            this.hand.getChildren().add(card.getCardView());
    }

    public Node[] getCurrentPlayerViewField() {
        return new Node[]{pane, currentRowArea, discardPile, deck, hand, leader, deckNumber};
    }

    public Node[] getOpponentPlayerViewField() {
        return new Node[]{pane, opponentRowsArea, opponentDiscardPile, opponentDeck, hand, opponentLeader, opponentDeckNumber};
    }

    public HBox getWeatherCardPosition() {
        return weatherCardPosition;
    }

    public void disablePane() {
        pane.setDisable(true);
    }

    public void resetStyles(RowView rowView) {
        for (CssAddress cssAddress : rowView.getStyles()) {
            rowView.getRow().getStyleClass().remove(cssAddress.getStyleClass());
            rowView.getSpecialCardPosition().getStyleClass().remove(cssAddress.getStyleClass());
        }
        weatherCardPosition.getStyleClass().remove(CssAddress.CARD_ROW.getStyleClass());
    }
    public void updateGame(Game game) {
        updateScores(game);
        updateHandCardNumber(game);
        updateDeckNumber(game);
        ArrayList<Row> allRows = new ArrayList<>();
        allRows.addAll(game.getCurrentPlayer().getRows());
        allRows.addAll(game.getOpponentPlayer().getRows());
        updateScores(allRows);
        if (!game.isRoundPassed()) passTurn(game);
    }
    private void updateScores(Game game) {
        Player player = game.getCurrentPlayer();
        Player opponentPlayer = game.getOpponentPlayer();
        for (Row row : game.getCurrentPlayer().getRows()) {
            gameMenuController.setCardsPoint(row);
        }
        for (Row row : game.getOpponentPlayer().getRows()) {
            gameMenuController.setCardsPoint(row);
        }
        player.getPlayerInformationView().updateTotalScore();
        opponentPlayer.getPlayerInformationView().updateTotalScore();
    }
    public void updateDeckNumber(Game game){
        game.getCurrentPlayer().updateDeckCardNumber();
        game.getOpponentPlayer().updateDeckCardNumber();
    }
    public void updateScores(ArrayList<Row> allRows) {
        for (Row row : allRows) {
            row.updateRowScore();
        }
    }

    public void setNodeStyle(Node node, CssAddress cssAddress) {
        node.getStyleClass().add(cssAddress.getStyleClass());
    }

    public void removeNodeStyle(Node node, CssAddress cssAddress) {
        node.getStyleClass().remove(cssAddress.getStyleClass());
    }

    public void setUpBoard(RowView siege, RowView ranged, RowView close, RowView opSiege, RowView opRanged, RowView opClose) {
        opponentRowsArea.getChildren().addAll(opSiege, opRanged, opClose);
        currentRowArea.getChildren().addAll(close, ranged, siege);
    }

    private void updateHandCardNumber(Game game) {
        Player player = game.getCurrentPlayer();
        player.updateHandCardNumber();
    }

    public void setUpAfterSwitch(Node pane, Node node1, Node node2) {
        if (pane instanceof HBox) ((HBox) pane).getChildren().addAll(node1, node2);
        else if (pane instanceof Pane) {
            ((Pane) pane).getChildren().addAll(node1, node2);
        }
    }
    public void endRound(String message) {
        pane.setDisable(true);
        showRoundEndNotification(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), actionEvent -> {
            showRoundStart();
        }));
        timeline.play();
    }

    public void handlePassTurn(Game game) {
        game.getCurrentPlayer().getPlayerInformationView().getStyleClass().add("brownBoxShadowed");
        game.getOpponentPlayer().getPlayerInformationView().getStyleClass().remove("brownBoxShadowed");
        for (DecksCard card : game.getOpponentPlayer().getHand())
            hand.getChildren().remove(card.getCardView());
        for (DecksCard card : game.getCurrentPlayer().getHand()) {
            if (!hand.getChildren().contains(card.getCardView()))
                hand.getChildren().add(card.getCardView());
            else System.err.println("Error in adding card to hand in pass turn");
        }
        showPassTurnNotification();
    }

    public VBox getRowsPane() {
        return rowsPane;
    }
    public void setUpNotificationBox() {
        notifPane.getStyleClass().add(CssAddress.NOTIF_BOX.getStyleClass());
        notifPane.setLayoutY(notifBox.getLayoutY());
        notifLabel.getStyleClass().add(CssAddress.NOTIFICATION_LABEL.getStyleClass());
        notifLabel.setLayoutX(notifText.getLayoutX());
        notifLabel.setLayoutY(notifText.getLayoutY());
        notifImageView.setLayoutY(notifImage.getLayoutY());
        notifImageView.setLayoutX(notifImage.getLayoutX());
        notifImageView.setFitWidth(notifImage.getFitWidth());
        notifImageView.setFitHeight(notifImage.getFitHeight());
        if (!notifPane.getChildren().contains(notifLabel) && !notifPane.getChildren().contains(notifImageView))
            notifPane.getChildren().addAll(notifLabel, notifImageView);
    }

    public void setHandCardEventHandler(Player currentPlayer, Player opponentPlayer, Game game ,ArrayList<DecksCard> cards){
        gameMenuController.handelHandsCardEvent(cards, game, currentPlayer, opponentPlayer);
    }
    public void endGame(String message,String winner, int[] winnerRoundPoint, String loser, int[] loserRoundsPoint) {
        pane.getChildren().remove(endGamePane);
        pane.getChildren().add(endGamePane);
        endGamePane.setVisible(true);
        result.setText(message);
        this.winner.setText(winner);
        this.loser.setText(loser);
        winnerRound1.setText(String.valueOf(winnerRoundPoint[0]));
        winnerRound2.setText(String.valueOf(winnerRoundPoint[1]));
        winnerRound3.setText(String.valueOf(winnerRoundPoint[2]));
        loserRound1.setText(String.valueOf(loserRoundsPoint[0]));
        loserRound2.setText(String.valueOf(loserRoundsPoint[1]));
        loserRound3.setText(String.valueOf(loserRoundsPoint[2]));
    }
    public void handleLeader(Player player, Game game){
        if (game.getOpponentPlayer().getLeader().equals(LeaderCardData.NILFGAARD_EMHYR_BRONZE)) return;
        player.getPlayerView().getLeaderView().setOnMouseClicked(mouseEvent -> {
            try {
                if (!player.getLeader().isAbilityOneTime() || !player.hasPlayedLeader()) {
                    player.getLeader().getAbility().invoke(LeaderAbility.getInstance(), game);
                    player.playLeader();
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void addWeatherCard(WeatherCard weatherCard){
        if (!weatherCardPosition.getChildren().contains(weatherCard.getCardView()))
            weatherCardPosition.getChildren().add(weatherCard.getCardView());
    }
    @FXML
    private void passTurn() {
        gameMenuController.checkRound(App.getCurrentGame());
    }
    public void passTurn(Game game) {
        disablePane();
        if (!game.isRoundPassed()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                try {
                    gameMenuController.switchBoard(game);
                    game.changeTurn();
                    handlePassTurn(game);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    private void showPassTurnNotification() {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                GameNotification.PASS_TURN.getNotification(),
                GameNotification.PASS_TURN.getNotificationImage(),2);
        timeline.play();
    }

    private void showRoundEndNotification(String message) {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                    message, GameNotification.WIN_ROUND.getNotificationImage(), 2);
        timeline.play();
    }

    private void scalePanes() {
        scalePane(pane, 1);
    }

    private void scalePane(Pane pane, double scaleCoef) {
        Double scale = App.getSceneManager().getScale(root.getWidth(), root.getHeight(), 1100, 660, scaleCoef);
        if (scale == null) return;
        pane.setScaleX(scale);
        pane.setScaleY(scale);
        pane.setLayoutX((root.getWidth() - 1100) / 2);
        pane.setLayoutY((root.getHeight() - 660) / 2 + 5 * scaleCoef);
    }
    private void showRoundStart() {
        Timeline timeline = AnimationMaker.getInstance().getNotificationTimeline(pane, notifPane, notifImageView, notifLabel,
                GameNotification.ROUND_STARTS.getNotification(), GameNotification.ROUND_STARTS.getNotificationImage(), 1);
        timeline.play();
    }
    @FXML
    private void goToMainMenu() throws IllegalAccessException {
        App.getCurrentGame().resetGame();
        App.getCurrentGame().getCurrentPlayer().resetLives();
        App.getCurrentGame().getCurrentPlayer().resetLives();
        pane.getChildren().remove(notifPane);
        pane.getChildren().remove(endGamePane);
        evacuateBoard();
        App.getSceneManager().goToMainMenu();
    }
    private void evacuateBoard() throws IllegalAccessException {
        discardPile.getChildren().clear();
        opponentDiscardPile.getChildren().clear();
        hand.getChildren().clear();
        weatherCardPosition.getChildren().clear();
        removeNodes(App.getCurrentGame().getCurrentPlayer().getPlayerView());
        removeNodes(App.getCurrentGame().getOpponentPlayer().getPlayerView());
        App.getCurrentGame().getCurrentPlayer().getPlayerView().getBoardView().getChildren().clear();
        App.getCurrentGame().getOpponentPlayer().getPlayerView().getBoardView().getChildren().clear();
    }
    private void removeNodes(PlayerView playerview) throws IllegalAccessException {
        Field[] fields = playerview.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            Object value = field.get(playerview);
            if (value instanceof Node node) pane.getChildren().remove(node);
        }
    }
    public void evacuateWeatherCards(Game game) {
        Player currentPlayer = game.getCurrentPlayer();
        for (Node cardView : weatherCardPosition.getChildren()) {
            if (!currentPlayer.getDiscardPile().add(((WeatherCard) ((CardView) cardView).getCard())))
                System.err.println("Error in removing card from discard pile in steel forged");
        }
        weatherCardPosition.getChildren().clear();
        WeatherCardAbility.getInstance().clearWeather(game);
    }
    @FXML
    private void cheatMenu() {
        pane.getChildren().remove(cheatMenu);
        cheatMenu.setVisible(true);
        cheatMenuTextField.clear();
        pane.getChildren().add(cheatMenu);

    }
    @FXML
    private void confirmCheat() {
        int code = Integer.parseInt(cheatMenuTextField.getText());
        switch (code) {
            case 1: createRandomCard();
                break;
            case 2: addToPlayerLife();
                break;
            case 3 : addCommanderHorn(App.getCurrentGame().getCurrentPlayer().getCloseCombat());
                break;
            case 4 : addCommanderHorn(App.getCurrentGame().getCurrentPlayer().getRangedCombat());
                break;
            case 5: addCommanderHorn(App.getCurrentGame().getCurrentPlayer().getSiege());
                break;
            case 6: {
                App.getCurrentGame().setRoundIsPassed(false);
                gameMenuController.endRound(App.getCurrentGame());
            }
            break;
            case 7:
                createRain();
                break;
        }
        pane.getChildren().remove(cheatMenu);

    }
    private void addCommanderHorn(Row row){
        SpecialCard specialCard = SpecialCardsData.COMMANDER_HORN.createCard();
        if (row.getSpecialCard() == null) {
            row.setSpecialCard(specialCard);
            row.getRowView().getSpecialCardPosition().getChildren().add(specialCard.getCardView());
            row.setBonus(true);
        }
    }
    private void createRain(){
        WeatherCard weatherCard = WeatherCardsData.TORRENTIAL_RAIN.createCard();
        if (!weatherCardPosition.getChildren().contains(weatherCard.getCardView()))
            weatherCardPosition.getChildren().add(weatherCard.getCardView());
        weatherCard.run(App.getCurrentGame());
    }
    private void createRandomCard(){
        DecksCard decksCard = MonstersRegularCardsData.ARACHAS.createCard();
        if (!App.getCurrentGame().getCurrentPlayer().getDeck().add(decksCard))
            System.err.println("Error adding card to deck in cheat code");
        App.getCurrentGame().getCurrentPlayer().updateDeckCardNumber();
    }
    private void addToPlayerLife(){
        int life = gameMenuController.addPlayerLife();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (life == 1) player.getPlayerInformationView().resetRightGem();
        if (life == 2) player.getPlayerInformationView().resetLeftGem();
    }
    @FXML
    private void backToGame() {
        pane.getChildren().remove(cheatMenu);
    }

    public Pane getPane() {
        return pane;
    }
}