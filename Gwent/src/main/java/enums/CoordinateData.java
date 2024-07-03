package enums;

public enum CoordinateData {
    SIEGE_ROW_PANE("siege",0, 430),
    SCORE_LABEL("scoreLabel",2, 20),
    SPECIAL_CARD_POSITION("specialCardPosition",38, 0),
    RANGED_ROW_PANE("rangedCombat",0, 346),
    CLOSE_COMBAT_ROW_PANE("closeCombat",0, 264),
    ROW("row",114, 0),
    USER_INFORMATION_VBOX("userInformationVBox",105, 7),
    INFORMATION_CARD_NUMBER_IMAGE("informationCardNumberImage",100, 57),
    INFORMATION_CARD_NUMBER_LABEL("informationCardNumberLabel",123, 60),
    TOTAL_SCORE_LABEL("totalScoreLabel",256, 39),
    FACTION_ICON("factionIcon",3, 7),
    INFORMATION_BOX_PROFILE_IMAGE("informationBoxProfileImage",14, 12),
    OPPONENT_INFORMATION_BOX("userInformationBox",0, 150),
    PLAYER_INFORMATION_BOX("opponentInformationBox",0, 396),
    PROFILE_FRAME("profileFrame",6, 3),
    LIVES_HBOX("livesHBox", 167,60),
    TOTALSCORE_IMAGE("totalScoreImage", 243, 34)
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
