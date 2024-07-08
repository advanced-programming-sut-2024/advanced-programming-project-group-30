package enums;

import javafx.scene.image.Image;

import java.util.Objects;

public enum RegularCardPositionType {
    CLOSE_COMBAT(),
    RANGED_COMBAT(),
    SIEGE(),
    AGILE(),
    OPPONENT_CLOSE_COMBAT(),
    OPPONENT_RANGED_COMBAT(),
    OPPONENT_SIEGE(),
    ;

    private final String iconAddress = "/Images/Icons/" + this.toString().toLowerCase() + ".png";

    public Image getIconImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(iconAddress)));
    }

    public boolean checkPositionCompatibility(String positionName) {
        return false;
    }

    public boolean checkPositionCompatibility(int positionNumber) {
        return false;
    }
}