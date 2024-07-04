package enums;

public enum RegularCardPositionType {
    CLOSE_COMBAT(),
    RANGED_COMBAT(),
    SIEGE(),
    AGILE(),
    OPPONENT_CLOSE_COMBAT(),
    OPPONENT_RANGED_COMBAT(),
    OPPONENT_SIEGE(),;
    public boolean checkPositionCompatibility(String positionName){
        return false;
    }

    public boolean checkPositionCompatibility(int positionNumber){
        return false;
    }
}
