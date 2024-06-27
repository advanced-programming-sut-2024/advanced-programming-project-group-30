package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum SkelligeRegularCardsData {
    BERSKER(),
    VIDKAARL(),
    SVANRIGE(),
    UDALRYK(),
    DONAR_AN_HINDAR(),
    CLAN_AN_CRAITE(),
    BLUEBOY_LUGOS(),
    MADMAN_LUGOS(),
    CERYS(),
    KAMBI(),
    BIRNA_BRAN(),
    CLAN_DRUMMOND_SHIELDMAIDE(),
    CLAN_TORDARROCH_ARMORSMITH(),
    CLAN_DIMUN_PIRATE(),
    CLAN_BROKVAR_ARCHER(),
    ERMION(),
    HJALMAR(),
    YOUNG_BERSEKER(),
    //TODO: add the transforming ability
//    YOUNG_VIDKAARL("Young Vidkaarl", null, ),
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
