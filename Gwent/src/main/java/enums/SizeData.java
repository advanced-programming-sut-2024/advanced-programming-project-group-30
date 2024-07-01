package enums;

import javax.swing.*;

public enum SizeData {
    PRE_GAME_CARD(212, 110, 15),
    GAME_LG_CARD(0, 0, 0),
    GAME_SM_CARD(77,55,0),
    GAME_SMALL_CARD_POSITION(15,15,0),
    GAME_SMALL_CARD_POINT_LABEL(20,20,0),
    GAME_SMALL_CARD_POINT(27, 27, 0);
    private final double height;
    private final double width;
    private final double redius;
    SizeData(double height, double width, double redius) {
        this.height = height;
        this.width = width;
        this.redius = redius;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public double getRedius() {
        return redius;
    }
}
