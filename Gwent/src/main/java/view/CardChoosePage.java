package view;

import enums.SizeData;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CardChoosePage extends HBox {
    private final int number = 5;
    private final ArrayList<Rectangle> regions;
    private final ArrayList<ChosenModelView> chosenModelViews;
    private int index;

    public CardChoosePage(ArrayList<ChosenModelView> chosenModelViews, int index) {
        setup();
        this.regions = createRegions();
        this.getChildren().addAll(regions);
        this.chosenModelViews = chosenModelViews;
        setIndex(index);
    }

    private void setup() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
    }

    private ArrayList<Rectangle> createRegions() {
        ArrayList<Rectangle> regions = new ArrayList<>();
        int scaleNumber = number / 2;
        for (int i = 0; i < number; i++) {
            Rectangle region = new Rectangle();
            setSize(region, Math.pow(0.8, Math.abs(scaleNumber - i)));
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
            if (j < 0 || j >= chosenModelViews.size()) regions.get(i).setFill(Color.TRANSPARENT);
            else regions.get(i).setFill(chosenModelViews.get(j).getImagePattern());
        }
    }

    private void setSize(Rectangle region, double scale) {
        region.setArcHeight(SizeData.GAME_LG_CARD.getRadius() * scale);
        region.setArcWidth(SizeData.GAME_LG_CARD.getRadius() * scale);
        region.setHeight(SizeData.GAME_LG_CARD.getHeight() * scale);
        region.setWidth(SizeData.GAME_LG_CARD.getWidth() * scale);
    }
}