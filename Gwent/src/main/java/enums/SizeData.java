package enums;

public enum SizeData {
    PRE_GAME_CARD(210, 110, 15),
    GAME_LG_CARD(405, 250, 30),
    NUMBER_OF_CARD_TYPE(14, 11, 0);

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