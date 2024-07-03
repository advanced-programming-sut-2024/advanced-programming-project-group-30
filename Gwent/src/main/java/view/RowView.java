package view;

import enums.CoordinateData;
import enums.CssAddress;
import enums.SizeData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.Row;
public class RowView extends Pane {
    private final Row row;
    private final HBox rowView;
    private final Region specialCardPosition;
    private final Label rowScore;
    private final Group rowItems;
    public RowView(Row row, CoordinateData coordinateData){
        this.row = row;
        rowView = new HBox();
        rowScore = new Label();
        rowItems = new Group();
        specialCardPosition = new Region();
        setUpRowView();
        this.setLayoutX(coordinateData.getX());
        this.setLayoutY(coordinateData.getY());
        this.setPrefHeight(75);
        this.setPrefWidth(580);
    }
    private void setUpRowView(){
        rowView.setLayoutX(CoordinateData.ROW.getX());
        rowView.setLayoutY(CoordinateData.ROW.getY());
        rowView.getStyleClass().add(CssAddress.ROW_STYLE.getStyleClass());
        rowScore.setLayoutX(CoordinateData.SCORE_LABEL.getX());
        rowScore.setLayoutY(CoordinateData.SCORE_LABEL.getY());
        rowScore.setPrefWidth(SizeData.SCORE_LABEL.getWidth());
        rowScore.setPrefHeight(SizeData.SCORE_LABEL.getHeight());
        updateRowScore();
        specialCardPosition.setLayoutX(CoordinateData.SPECIAL_CARD_POSITION.getX());
        specialCardPosition.setLayoutY(CoordinateData.SPECIAL_CARD_POSITION.getY());
        specialCardPosition.setPrefHeight(SizeData.SPECIAL_CARD_REGION.getHeight());
        specialCardPosition.setPrefWidth(SizeData.SPECIAL_CARD_REGION.getWidth());
        rowItems.getChildren().addAll(rowView, rowScore, specialCardPosition);
        this.getChildren().addAll(rowItems);
    }

    public void updateRowScore() {
        rowScore.setText(String.valueOf(row.getRowPoint()));
        rowScore.getStyleClass().add("scoreLabel");
    }
    public HBox getRow(){
        return rowView;
    }
}
