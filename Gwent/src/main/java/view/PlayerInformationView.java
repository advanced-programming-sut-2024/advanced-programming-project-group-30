package view;

import enums.CoordinateData;
import enums.CssAddress;
import enums.FactionType;
import enums.SizeData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Player;

public class PlayerInformationView extends Pane {
    private final Player player;
    private final CssAddress cssAddress;
    private ImageView profileImage;
    private ImageView handCardImage;
    private Label handCardNumber;
    private Label username;
    private HBox lives;
    private Region rightGem;
    private Region leftGem;
    private ImageView profileFrame;
    private VBox userInfoBox;
    private Label totalScore;
    private ImageView factionImage;
    private ImageView totalScoreImage;
    private Label passedLabel;
    private CoordinateData coordinateData;

    public PlayerInformationView(Player player, CoordinateData coordinateData, CssAddress cssAddress) {
        this.player = player;
        this.cssAddress = cssAddress;
        this.coordinateData = coordinateData;
        design();
        this.getStyleClass().add(CssAddress.INFORMATION_BOX.getStyleClass());
        this.setLayoutX(coordinateData.getX());
        this.setLayoutY(coordinateData.getY());
        this.getChildren().addAll(profileImage, profileFrame, handCardImage, handCardNumber, userInfoBox, lives, totalScoreImage, totalScore, factionImage);
    }

    public void updateTotalScore() {
        int score = player.getPoint();
        totalScore.setText(String.valueOf(score));
    }
    private void setPaneCoordinate(){
        this.setLayoutX(coordinateData.getX());
        this.setLayoutY(coordinateData.getY());
    }

    public Label getUsernameLabel() {
        return username;
    }

    public Label getHandCardNumber() {
        return handCardNumber;
    }

    //TODO:needs debug
    public void setFirstRoundOfLoss() {
//        lives.getChildren().remove(rightGem);
//        this.getChildren().remove(lives);
//        lives = new HBox();
//        setLivesBoxCoordinate();
//        rightGem = new Region();
//        rightGem.getStyleClass().add(CssAddress.GEM_OFF_IMAGE.getStyleClass());
//        lives.getChildren().addAll(rightGem, leftGem);
//        this.getChildren().add(lives);
        rightGem.getStyleClass().remove(CssAddress.GEM_ON_IMAGE.getStyleClass());
        rightGem.getStyleClass().add(CssAddress.GEM_OFF_IMAGE.getStyleClass());
    }

    public void setSecondRoundOfLoss() {
        this.getChildren().remove(lives);
        lives.getChildren().remove(leftGem);
        leftGem = new Region();
        lives = new HBox();
        setLivesBoxCoordinate();
        leftGem.getStyleClass().add(CssAddress.GEM_OFF_IMAGE.getStyleClass());
        lives.getChildren().addAll(rightGem,leftGem);
        this.getChildren().add(lives);
        System.out.println("in second round");
    }

    public void resetRound() {
        totalScore.setText("0");
    }
    public void updateHandCardNumber() {
        handCardNumber.setText(String.valueOf(player.getHand().size()));
    }
    //TODO: added these


    private void design() {
        setUpProfileImage();
        setUpHandCardImage();
        setUpHandCardNumber();
        setUpInformationVbox();
        setUpUsername();
        setUpFaction();
        setUpProfileFrame();
        setUpLives();
        setUpScore();
        setUpFactionIcon();
    }

    private void setUpFactionIcon() {
        factionImage = new ImageView();
        FactionType factionType = player.getUser().getSelectedFaction();
        factionImage.setImage(factionType.getShieldIcon());
        factionImage.setLayoutY(CoordinateData.FACTION_ICON.getY());
        factionImage.setLayoutX(CoordinateData.FACTION_ICON.getX());
        factionImage.setFitHeight(SizeData.FACTION_ICON.getHeight());
        factionImage.setFitWidth(SizeData.FACTION_ICON.getWidth());
    }

    private void setUpProfileImage() {
        profileImage = new ImageView();
        profileImage.setLayoutX(CoordinateData.INFORMATION_BOX_PROFILE_IMAGE.getX());
        profileImage.setLayoutY(CoordinateData.INFORMATION_BOX_PROFILE_IMAGE.getY());
        profileImage.setFitWidth(SizeData.PROFILE_MENU_IMAGE.getWidth());
        profileImage.setFitHeight(SizeData.PROFILE_MENU_IMAGE.getHeight());
        profileImage.getStyleClass().add(CssAddress.PROFILE_IMAGE.getStyleClass());
    }

    private void setUpProfileFrame() {
        profileFrame = new ImageView();
        profileFrame.setLayoutX(CoordinateData.PROFILE_FRAME.getX());
        profileFrame.setLayoutY(CoordinateData.PROFILE_FRAME.getY());
        profileFrame.setFitWidth(SizeData.PROFILE_FRAME.getWidth());
        profileFrame.setFitHeight(SizeData.PROFILE_FRAME.getHeight());
        profileFrame.getStyleClass().add(CssAddress.PROFILE_FRAME.getStyleClass());
    }

    private void setUpHandCardImage() {
        handCardImage = new ImageView();
        handCardImage.getStyleClass().add(CssAddress.CARD_NUMBER_IMAGE.getStyleClass());
        handCardImage.setLayoutX(CoordinateData.INFORMATION_CARD_NUMBER_IMAGE.getX());
        handCardImage.setLayoutY(CoordinateData.INFORMATION_CARD_NUMBER_IMAGE.getY());
        handCardImage.setFitWidth(SizeData.CARD_NUMBER_IMAGE.getWidth());
        handCardImage.setFitHeight(SizeData.CARD_NUMBER_IMAGE.getHeight());
        handCardImage.getStyleClass().add(CssAddress.CARD_NUMBER_IMAGE.getStyleClass());
    }

    private void setUpHandCardNumber() {
        handCardNumber = new Label();
        handCardNumber.setLayoutX(CoordinateData.INFORMATION_CARD_NUMBER_LABEL.getX());
        handCardNumber.setLayoutY(CoordinateData.INFORMATION_CARD_NUMBER_LABEL.getY());
        handCardNumber.getStyleClass().add(CssAddress.CARD_NUMBER_LABEL.getStyleClass());
        handCardNumber.setText(String.valueOf(player.getHand().size()));
    }

    private void setUpInformationVbox() {
        userInfoBox = new VBox();
        userInfoBox.setPrefHeight(SizeData.USER_INFORMATION_VBOX.getHeight());
        userInfoBox.setPrefWidth(SizeData.USER_INFORMATION_VBOX.getWidth());
        userInfoBox.setLayoutX(CoordinateData.USER_INFORMATION_VBOX.getX());
        userInfoBox.setLayoutY(CoordinateData.USER_INFORMATION_VBOX.getY());
    }

    private void setUpUsername() {
        username = new Label();
        username.setText(player.getUser().getUsername());
        username.getStyleClass().add(CssAddress.CARD_NUMBER_LABEL.getStyleClass());
        userInfoBox.getChildren().add(username);
    }

    private void setUpFaction() {
        Label faction = new Label();
        faction.setText(player.getUser().getSelectedFaction().toString());
        faction.getStyleClass().add(CssAddress.INFORMATION_FACTION_LABEL.getStyleClass());
        userInfoBox.getChildren().add(faction);
    }
    private void setLivesBoxCoordinate(){
        lives.setLayoutX(CoordinateData.LIVES_HBOX.getX());
        lives.setLayoutY(CoordinateData.LIVES_HBOX.getY());
        lives.getStyleClass().add(CssAddress.GEMS_BOX.getStyleClass());
    }

    private void setUpLives() {
        lives = new HBox();
        setLivesBoxCoordinate();
        rightGem = new Region();
        leftGem = new Region();
        rightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        leftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
        lives.getChildren().addAll(rightGem, leftGem);
    }

    private void setUpScore() {
        totalScore = new Label("0");
        totalScore.getStyleClass().add(CssAddress.TOTAL_SCORE_LABEL.getStyleClass());
        totalScore.setLayoutX(CoordinateData.TOTAL_SCORE_LABEL.getX());
        totalScore.setLayoutY(CoordinateData.TOTAL_SCORE_LABEL.getY());
        totalScoreImage = new ImageView();
        totalScoreImage.getStyleClass().add(cssAddress.getStyleClass());
        totalScoreImage.setLayoutX(CoordinateData.TOTAL_SCORE_IMAGE.getX());
        totalScoreImage.setLayoutY(CoordinateData.TOTAL_SCORE_IMAGE.getY());
        totalScoreImage.setFitHeight(SizeData.TOTAL_SCORE_IMAGE.getHeight());
        totalScoreImage.setFitWidth(SizeData.TOTAL_SCORE_IMAGE.getWidth());
    }

    public void resetLeftGem() {
        leftGem.getStyleClass().remove(CssAddress.GEM_OFF_IMAGE.getStyleClass());
        leftGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
    }
    public void resetRightGem(){
        rightGem.getStyleClass().remove(CssAddress.GEM_OFF_IMAGE.getStyleClass());
        rightGem.getStyleClass().add(CssAddress.GEM_ON_IMAGE.getStyleClass());
    }
}