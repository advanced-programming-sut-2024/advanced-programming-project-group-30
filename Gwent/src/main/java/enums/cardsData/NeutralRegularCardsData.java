package enums.cardsData;

import enums.RegularCardPositionType;
import model.Faction;
import model.card.RegularCard;

import java.util.ArrayList;

public enum NeutralRegularCardsData {
    DANDELION("Dandelion", null, "HornCommander", false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    COW("Cow", null, "Transformers", false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    EMIEL_REGIS("EmielRegis", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM("Gaunter O'Dimm", null, "Muster", false, 2, 1, RegularCardPositionType.SIEGE),
    GAUNYER_ODIMM_DARKNESS("Gaunter O'Dimm Darkness", null, "Muster", false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    GERALT_OF_RIVIA("Geralt of Rivia", null, null, true, 15, 1, RegularCardPositionType.CLOSE_COMBAT),
    MYSTERIOUS_ELF("Mysterious Elf", null, "Spy", true, 0, 1, RegularCardPositionType.CLOSE_COMBAT),
    OLGIERD_VON_EVERC("Olgierd von Everc", null, "MoralBoost", false, 6, 1, RegularCardPositionType.AGILE),
    TRISS_MERIGOLD("Triss Merigold", null, null, true, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    VESEMIR("Vesemir", null, null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    VILLENTRETENMERTH("Villentretenmerth", null, "Scorch", false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", null, "Medic", true, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    ZOLTAN_CHIVAY("Zoltan Chivay", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT);
    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    NeutralRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(NeutralRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
