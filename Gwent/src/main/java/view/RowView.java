package view;

import enums.CoordinateData;
import enums.CssAddress;
import enums.SizeData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.Row;
import model.card.DecksCard;
import model.card.SpecialCard;

public class RowView extends HBox {
    private final Row row;
    private final HBox rowView;
    private final HBox specialCardPosition;
    private final Label rowScore;
    private final VBox rowScoreRegion;
    public RowView(Row row, CoordinateData coordinateData){
        this.row = row;
        rowView = new HBox();
        rowScore = new Label();
        rowScoreRegion = new VBox();
        specialCardPosition = new HBox();
        setUpRowView();
        this.getStyleClass().add("rowPaneStyle");
    }
    private void setUpRowView(){
        rowView.getStyleClass().add(CssAddress.ROW_STYLE.getStyleClass());
        setUpRowScore();
        updateRowScore();
        specialCardPosition.getStyleClass().add("specialCardPosition");
        this.getChildren().addAll(rowScoreRegion, specialCardPosition, rowView);
    }
    private void setUpRowScore(){
        rowScoreRegion.getStyleClass().add("rowSideScoreLabelArea");
        rowScore.setPrefWidth(SizeData.SCORE_LABEL.getWidth());
        rowScore.setPrefHeight(SizeData.SCORE_LABEL.getHeight());
        rowScoreRegion.getChildren().add(rowScore);
        rowScore.getStyleClass().add("scoreLabel");
    }

    public void updateRowScore() {
        rowScore.setText(String.valueOf(row.getRowPoint()));
    }
    public HBox getRow(){
        return rowView;
    }
    public HBox getSpecialCardPosition(){
        return specialCardPosition;
    }
    public void addCardToRow(DecksCard decksCard, boolean isSpecial){
        if (isSpecial) addCardToSpecialPosition(decksCard);
        else rowView.getChildren().add(decksCard.getCardView());
    }
    public void addCardToSpecialPosition(DecksCard decksCard){
        if (!specialCardPosition.getChildren().contains(DecksCard.class)){
            specialCardPosition.getChildren().add(decksCard.getCardView());
        }
    }
}
