package enums;

public enum SizeData {
    PRE_GAME_CARD(210, 110, 15),
    GAME_LG_CARD(400, 260, 30),
    NUMBER_OF_CARD_TYPE(14, 11, 0),
    GAME_SM_CARD(77, 55, 0),
    GAME_SMALL_CARD_POSITION(15, 15, 0),
    GAME_SMALL_CARD_POINT_LABEL(15, 15, 0),
    GAME_SMALL_CARD_POINT(27, 27, 0),
    GAME_SMALL_CARD_ABILITY(15, 15, 0),
    GAME_SMALL_CARD_SPECIAL_ABILITY(30, 30, 0),
    SPECIAL_CARD_REGION(75, 74, 0),
    ROW(68, 464, 0),
    SCORE_LABEL(28, 27, 0),
    USER_INFORMATION_VBOX(47, 112, 0),
    INFORMATION_LIVES_REGION(30, 63, 0),
    FACTION_ICON(32, 32, 0),
    PROFILE_MENU_IMAGE(75, 75, 0),
    PROFILE_FRAME(94, 90, 0),
    CARD_NUMBER_IMAGE(30, 20, 0),
    TOTAL_SCORE_IMAGE(38, 38, 0),
    ;

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