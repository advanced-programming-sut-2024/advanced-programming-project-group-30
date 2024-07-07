package enums;

import java.util.Objects;

public enum CssAddress {
    NUMBER_OF_CARD_TYPE_ICON("numberOfCardTypeIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
    NUMBER_OF_CARD_TYPE_LABEL("numberOfCardTypeLabel",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
    PREGAME_CARD_VIEW("preGameCard",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
    DESCRIPTION_BOX("descriptionBox",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
    DESCRIPTION_BOX_TEXT("descriptionBoxText",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
    DESCRIPTION_BOX_TITLE("descriptionBoxTitle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/PregameNodesStyle.css")).toExternalForm()),
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