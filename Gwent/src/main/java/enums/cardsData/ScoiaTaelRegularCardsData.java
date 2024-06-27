package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum ScoiaTaelRegularCardsData {
    ELVEN_SKIRMISHER("Elven Skirmisher", null, "Muster", false, 2, 3, RegularCardPositionType.RANGED_COMBAT),
    LORVETH("Lorveth", null, null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    YAEVINN("Yaevinn", null, null, false, 6, 1, RegularCardPositionType.AGILE),
    CIARAN_AEP("Ciaran Aep", null, null, false, 3, 1, RegularCardPositionType.AGILE),
    DENNIS_CRANMER("Dennis Cranmer", null, null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", null, null, false, 6, 3, RegularCardPositionType.AGILE),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", null, "Muster", false, 3, 3, RegularCardPositionType.CLOSE_COMBAT),
    FILAVANDREL("Filavandrel", null, null, true, 6, 1, RegularCardPositionType.AGILE),
    HAVEKAR_HEALER("Havekar Healer", null, "Medic", false, 0, 3, RegularCardPositionType.RANGED_COMBAT),
    HAVEKAR_SMUGGLER("Havekar Smuggler", null, "Muster", false, 5, 3, RegularCardPositionType.CLOSE_COMBAT),
    IDA_EMEAN_AEP("Ida Emean Aep", null, null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    RIORDIAN("Riordian", null, null, false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    TORUVIEL("Toruviel", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", null, null, false, 5, 5, RegularCardPositionType.CLOSE_COMBAT),
    VRIHEDD_BRIAGDE_VETERAN("Vrihedd Brigade Veteran", null, null, false, 5, 2, RegularCardPositionType.AGILE),
    MILVA("Milva", null, "MoralBoost", false, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    SEASENTHESSIS("Seasenthesiss", null, null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    SCHIRRU("Schirru", null, "Scorch", false, 8, 1, RegularCardPositionType.SIEGE),
    BARCLAY_ELS("Barclay Els", null, null, false, 6,1, RegularCardPositionType.AGILE),
    EITHNE("Eithne", null, null, true, 10,1, RegularCardPositionType.RANGED_COMBAT),
    ISENGRIM_FOAILTIARNA("Isengrim Faoiltiarna", null, "MoralBoost", true, 10,1, RegularCardPositionType.CLOSE_COMBAT);

    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    ScoiaTaelRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(ScoiaTaelRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
