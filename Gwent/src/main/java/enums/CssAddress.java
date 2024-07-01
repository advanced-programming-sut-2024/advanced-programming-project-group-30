package enums;

import java.util.Objects;

public enum CssAddress {
    NUMBER_OF_CARD_TYPE_ICON("numberOfCardTypeIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NUMBER_OF_CARD_TYPE_LABEL("numberOfCardTypeLabel",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_SIEGE_ROW_ICON("cardSiegeRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_RANGED_COMBAT_ROW_ICON("cardRangedCombatRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_CLOSE_COMBAT_ROW_ICON("cardCloseCombatRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_AGILE_ICON("cardAgileIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    SM_CARD_POINT_ICON("smCardPointIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    SM_CARD_POINT_LABEL("smCardPointLabel",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GAME_HAND_SM_CARD("handCard",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GEM_ON_IMAGE("gem-on-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GEM_OFF_IMAGE("gem-off-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    ;



    private final String styleClass;
    private final String styleSheet;
    CssAddress(String styleClass, String styleSheet) {
        this.styleClass = styleClass;
        this.styleSheet = styleSheet;
    }
    public String getStyleClass() {
        return styleClass;
    }
    public String getStyleSheet() {
        return styleSheet;
    }
}
