package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum ScoiaTaelRegularCardsData {
    ELVEN_SKIRMISHER(),
    LORVETH(),
    YAEVINN(),
    CIARAN_AEP(),
    DENNIS_CRANMER(),
    DOL_BLATHANNA_SCOUT(),
    DOL_BLATHANNA_ARCHER(),
    DWARVEN_SKIRMISHER(),
    FILAVANDREL(),
    HAVEKAR_HEALER(),
    HAVEKAR_SMUGGLER(),
    IDA_EMEAN_AEP(),
    RIORDIAN(),
    TORUVIEL(),
    VRIHEDD_BRIGADE_RECRUIT(),
    MAHAKAMAN_DEFENDER(),
    VRIHEDD_BRIAGDE_VETERAN(),
    MILVA(),
    SEASENTHESSIS(),
    SCHIRRU(),
    BARCLAY_ELS(),
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
