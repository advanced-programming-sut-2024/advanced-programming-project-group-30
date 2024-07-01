package enums;

public enum SizeData {
    preGameCard(212, 110, 15),
    gameLgCard(0, 0, 0),
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