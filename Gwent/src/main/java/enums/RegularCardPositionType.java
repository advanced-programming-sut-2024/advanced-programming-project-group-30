package enums;

public enum RegularCardPositionType {
    CLOSE_COMBAT(),
    RANGED_COMBAT(),
    SIEGE(),
    AGILE();

    public boolean checkPositionCompatibility(String positionName){
        return false;
    }

    public boolean checkPositionCompatibility(int positionNumber){
        return false;
    }
}