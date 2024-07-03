package enums;

public enum CoordinateData {
    SIEGE_ROW_PANE("siege",0, 430),
    SCORE_LABEL("scoreLabel",2, 20),
    SPECIAL_CARD_POSITION("specialCardPosition",38, 0),
    RANGED_ROW_PANE("rangedCombat",0, 346),
    CLOSE_COMBAT_ROW_PANE("closeCombat",0, 264),
    ROW("row",114, 0),
    ;

    private final String name;
    private final double x;
    private final double y;
    CoordinateData(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public static CoordinateData getCoordinateData(String name){
        for (CoordinateData coordinateData : CoordinateData.values()){
            if (coordinateData.name.equals(name)){
                return coordinateData;
            }
        }
        return null;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
