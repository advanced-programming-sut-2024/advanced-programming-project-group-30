package enums;

import java.util.Objects;

public enum CssAddress {
//    NUMBER_OF_CARD_TYPE_ICON("numberOfCardTypeIcon",
//            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
//    NUMBER_OF_CARD_TYPE_LABEL("numberOfCardTypeLabel",
//            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_SIEGE_ROW_ICON("cardSiegeRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_RANGED_COMBAT_ROW_ICON("cardRangedCombatRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_CLOSE_COMBAT_ROW_ICON("cardCloseCombatRowIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_AGILE_ICON("cardAgileIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NORMAL_CARD_POINT_ICON("normalCardPointIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NORMAL_CARD_POINT_LABEL("normalCardPointLabel",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    HERO_CARD_POINT_LABEL("powerCardPointLabel",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GAME_HAND_SM_CARD("handCard",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GEM_ON_IMAGE("gem-on-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GEM_OFF_IMAGE("gem-off-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    STORM_WEATHER_ICON("skelligeStormAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    SCORCH_ABILITY_ICON("scorchAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    RAIN_WEATHER_ICON("torrentialRainAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    MUSTER_ABILITY_ICON("musterAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    MORAL_BOOST_ABILITY_ICON("moralBoostAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    MARDROEME_ABILITY_ICON("mardroemeAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    HORN_COMMANDER_ABILITY_ICON("hornCommanderAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    COMMANDERS_HORN_ABILITY_ICON("commanderHornAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    FROST_WEATHER_ICON("bitingFrostAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    FOG_WEATHER_ICON("impenetrableFogAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    DECOY_ABILITY_ICON("decoyAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CLEAR_WEATHER_ICON("clearWeatherAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    BERSERKER_ABILITY_ICON("berserkerAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    AVENGER_ABILITY_ICON("avengerAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    SPY_ABILITY_ICON("spyAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    TIGHT_BOND_ABILITY_ICON("tightBondAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    AGILE_ABILITY_ICON("agileAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    MEDIC_ABILITY_ICON("medicAbilityIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    POWER_HERO_ICON("powerHeroIcon",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    MONSTERS_FACTION_ICON("monsters-faction",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NORTHERN_REALMS_FACTION_ICON("northern-realms-faction",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NILFGAARDIAN_EMPIRE_FACTION_ICON("nilfgaardian-faction",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    SCOIA_TAEL_FACTION_ICON("scoia-tael-faction",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    PASS_TURN_IMAGE("round-passed-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_ROW("card-available-row",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    ROW_STYLE("rowStyle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_IN_ROW("cardInRowStyle" ,
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    INFORMATION_BOX("brownBox",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NOTIFICATION_BOX("notificationBoxStyle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NOTIFICATION_MESSAGE("notificationMessageStyle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    PROFILE_IMAGE("profile-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    PROFILE_FRAME("player-border",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_NUMBER_IMAGE("card-number-image",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CARD_NUMBER_LABEL("yellowish-arial-label",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    INFORMATION_FACTION_LABEL("faction-label",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    CURRENT_PLAYER_TOTAL_SCORE_IMAGE("yellowScoreImage",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    OPPONENT_PLAYER_TOTAL_SCORE_IMAGE("blueScoreImage",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    GEMS_BOX("gemsBox",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    TOTAL_SCORE_LABEL("black-bold-arial-label",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NOTIF_BOX("notificationBoxStyle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    NOTIFICATION_LABEL("notificationMessageStyle",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    DECK_VIEW("deck-position",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    LEADER_POSITION("leader-position",
            Objects.requireNonNull(CssAddress.class.getResource("/CSS/GameNodesStyle.css")).toExternalForm()),
    DISCARD_PILE_POSITION("discardPile-position",
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
    public static String getCssAddress(String styleClass) {
        for (CssAddress cssAddress : CssAddress.values()) {
            if (cssAddress.getStyleClass().equals(styleClass)) {
                return cssAddress.styleClass;
            }
        }
        return null;
    }
}
