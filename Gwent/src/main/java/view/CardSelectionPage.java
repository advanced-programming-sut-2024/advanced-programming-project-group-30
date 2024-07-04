package view;

import enums.SizeData;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.effect.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CardSelectionPage extends VBox {
    private final HBox cardsBox = new HBox();
    private final int number = 5;
    private final ArrayList<Rectangle> regions;
    private final ArrayList<ChosenModelView> chosenModelViews;
    private int index;

    public CardSelectionPage(ArrayList<ChosenModelView> chosenModelViews, int index) {
        this.regions = createRegions();
        this.chosenModelViews = chosenModelViews;
        setup();
    }

    private void setup() {
        this.getChildren().add(cardsBox);
        this.getChildren().add(chosenModelViews.get(index).getDescriptionBox());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        cardsBox.getChildren().addAll(regions);
        cardsBox.setAlignment(Pos.CENTER);
        cardsBox.setSpacing(15);
        regions.get(number / 2).setEffect(createEffect());
        setIndex(index);
    }

    private ArrayList<Rectangle> createRegions() {
        ArrayList<Rectangle> regions = new ArrayList<>();
        int scaleNumber = number / 2;
        for (int i = 0; i < number; i++) {
            Rectangle region = new Rectangle();
            setSize(region, Math.pow(0.75, Math.abs(scaleNumber - i)));
            regions.add(region);
            region.setOnMouseClicked((Void) -> {
                if (region.getFill() != Color.TRANSPARENT) setIndex(index - number / 2 + regions.indexOf(region));
            });
        }
        return regions;
    }

    private void setIndex(int index) {
        this.index = index;
        for (int i = 0; i < number; i++) {
            int j = index + i - number / 2;
            if (j < 0 || j >= chosenModelViews.size()) {
                regions.get(i).setFill(Color.TRANSPARENT);
                regions.get(i).setCursor(Cursor.DEFAULT);
            } else {
                regions.get(i).setFill(chosenModelViews.get(j).getImagePattern());
                regions.get(i).setCursor(Cursor.HAND);
            }
        }
        this.getChildren().remove(1);
        this.getChildren().add(chosenModelViews.get(index).getDescriptionBox());
    }

    private void setSize(Rectangle region, double scale) {
        region.setArcHeight(SizeData.GAME_LG_CARD.getRadius() * scale);
        region.setArcWidth(SizeData.GAME_LG_CARD.getRadius() * scale);
        region.setHeight(SizeData.GAME_LG_CARD.getHeight() * scale);
        region.setWidth(SizeData.GAME_LG_CARD.getWidth() * scale);
    }

    private DropShadow createEffect() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.05);
        colorAdjust.setHue(-0.015);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.web("#d59f42"));
        dropShadow.setHeight(500f);
        dropShadow.setWidth(500f);
        dropShadow.setRadius(5f);
        dropShadow.setSpread(0.8f);
        dropShadow.setInput(colorAdjust);
        return dropShadow;
    }
}