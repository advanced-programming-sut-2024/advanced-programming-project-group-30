package enums;

import javax.swing.*;

public enum SizeData {
    PRE_GAME_CARD(212, 110, 15),
    GAME_LG_CARD(0, 0, 0),
    GAME_SM_CARD(77,55,0),
    GAME_SMALL_CARD_POSITION(15,15,0),
    GAME_SMALL_CARD_POINT_LABEL(15,15,0),
    GAME_SMALL_CARD_POINT(27, 27, 0),
    GAME_SMALL_CARD_ABILITY(15, 15, 0),
    GAME_SMALL_CARD_SPECIAL_ABILITY(30, 30, 0),
    SPECIAL_CARD_REGION(75,74,0),
    ROW(68,464,0),
    SCORE_LABEL(28,27,0),;
    private final double height;
    private final double width;
    private final double radius;
    SizeData(double height, double width, double radius) {
        this.height = height;
        this.width = width;
        this.radius = radius;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public double getRadius() {
        return radius;
    }
}
