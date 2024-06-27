package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum SkelligeRegularCardsData {
    BERSKER("Berserker", null, "Berserker", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VIDKAARL("Vidkaar", null, "MoralBoost", false, 14, 0, RegularCardPositionType.CLOSE_COMBAT),
    SVANRIGE("Svanrige", null, null, false,4,1, RegularCardPositionType.CLOSE_COMBAT),
    UDALRYK("Udlaryk", null, null, false, 4,1,RegularCardPositionType.CLOSE_COMBAT),
    DONAR_AN_HINDAR("Donar an Hindar", null, null, false, 4,1,RegularCardPositionType.CLOSE_COMBAT),
    CLAN_AN_CRAITE("Clan A Craite", null, "TightBond", false,6, 3,RegularCardPositionType.CLOSE_COMBAT ),
    BLUEBOY_LUGOS("Blueboy Lugos", null, null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    MADMAN_LUGOS("Madman Lugos", null, null, false, 6,1, RegularCardPositionType.CLOSE_COMBAT),
    CERYS("Cerys", null, "Muster", true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAMBI("Kambi", null, "Transformers", true, 11,1, RegularCardPositionType.CLOSE_COMBAT),
    BIRNA_BRAN("Birna Bran", null, "Medic", false, 2,1 , RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELDMAIDE("Clan Drummond Shieldmaide", null, "TightBond", false,4,3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armosrsmith",   null, null, false, 4,1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", null, "Scorch", false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", null, null, false, 6, 3, RegularCardPositionType.RANGED_COMBAT),
    ERMION("Ermion", null, "Mardroeme", true, 8, 1, RegularCardPositionType.RANGED_COMBAT),
    HJALMAR("Hjalmar", null, null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    YOUNG_BERSEKER("Young Berserker", null, "Berserker", false, 2, 3, RegularCardPositionType.RANGED_COMBAT),
    YOUNG_VIDKAARL("Young Vidkaarl", null, "TightBond", false, 8,0,RegularCardPositionType.RANGED_COMBAT),
    LIGHT_LONGSHIP("Light Longship", null, "Muster", false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    HOLGER_BLACKHAND("Holger Blackhand",  null, null, false, 4,1, RegularCardPositionType.SIEGE),
    WAR_LONGSHIP("War Longship", null, "TightBond", false, 6, 3, RegularCardPositionType.SIEGE),
    DRAIG_BON_DHU("Draig Bon-Dhu", null, "HornCommander", false, 6, 3, RegularCardPositionType.SIEGE),
    OLAF("Olaf", null, "MoralBoost", false, 12, 1, RegularCardPositionType.AGILE);



    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    SkelligeRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(SkelligeRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
