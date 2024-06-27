package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum RealmsNorthernRegularCardsData {
    BALLISTA("Ballista", null, null, false, 6, 2, RegularCardPositionType.SIEGE),
    BLUE_STRIPES_COMMIANDO("Blue Stripes Commando", null, "TightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    CATAPULT("Catapult", null, "TightBond", false, 8, 2, RegularCardPositionType.SIEGE),
    DRAGON_HUNTER("Dragon Hunter", null, "TightBond", false, 5, 3, RegularCardPositionType.RANGED_COMBAT),
    DETHMOLD("Dethmold", null, null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    DUN_BANNER_MEDIC("Dun Banner Medic", null, "Medic", false, 5, 1, RegularCardPositionType.SIEGE),
    ESTERAD_THYSSEN("Esterad Thyssen", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    JOHN_NATALIS("John Natalis", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", null, "MoralBoost", false, 1, 3, RegularCardPositionType.SIEGE),
    KEIRA_METZ("Keira Metz", null, null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    PHILIPPA_EILHART("Philippa Eilhart", null, "TightBond",true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    POOR_INFANTRY("Poor Infantry", null, "TightBond", false, 1, 4, RegularCardPositionType.CLOSE_COMBAT),
    PRINCE_STENNIS("Prince Stennis", null, "Spy", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", null, null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    SABRINA_GLEVISSING("Sabrina Glevissing", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SHELDON_SKAGGS("Sheldon Skaggs", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TOWER("Siege Tower", null, null, false, 6, 1, RegularCardPositionType.SIEGE),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", null, "Spy", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    SILE_DE_TANSARVILLE("Sile de Tansarville", null, null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    THALER("Thaler", null, "Spy", false, 1, 1, RegularCardPositionType.SIEGE),
    TREBUCHET("Trebucher", null, null, false, 6, 2, RegularCardPositionType.SIEGE),
    VERNON_ROCHE("Vernon Roche", null, null, true, 6,1, RegularCardPositionType.CLOSE_COMBAT),
    VES("Ves", null, null, false, 5,1,RegularCardPositionType.CLOSE_COMBAT),
    YARPEN_ZIGRIN("Yarpen Zigrin", null, null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),;

    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    RealmsNorthernRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(RealmsNorthernRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}

