package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum EmpireNilfgaardianRegularCardsData {
    IMPERA_BRIDGE_GUARD("Impera Brigade Guard", null, "TightBond", false, 3, 4, RegularCardPositionType.CLOSE_COMBAT),
    STEFAN_SKELLEN("Stefan Skellen", null, "Spy", false, 9, 1, RegularCardPositionType.CLOSE_COMBAT),
    SHILARD_FITZ_OSTERLEN("Shilard Fitz-Osterlen", null, "Spy", false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    YOUNG_EMISSARY("Young Emissary", null, "TightBond", false, 5, 2, RegularCardPositionType.CLOSE_COMBAT),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", null, null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", null, "Spy", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    MENNO_COEHOORN("Menno Coehoorn", null, "Medic", true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    PUTTKAMMER("Puttkammer", null,  null, false, 3, 1, RegularCardPositionType.RANGED_COMBAT),
    ASSIRE_VAR_ANAHID("Assire var Anahid", null, "Medic", false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", null, null, false, 10, 2, RegularCardPositionType.RANGED_COMBAT),
    TIBOR_EGGEbracht("Tibor Eggebracht", null, null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", null, "Spy", false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    FRINFILLA_VIGO("Frinfilla Vigo", null, null, false, 6,1, RegularCardPositionType.RANGED_COMBAT),
    ROTTEN_MANGONEL("Rotten Mangonel", null, null, false, 3, 1, RegularCardPositionType.SIEGE),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpio", null, null, false, 10, 1, RegularCardPositionType.SIEGE),
    ZERIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", null, null, false, 5, 1, RegularCardPositionType.SIEGE),
    SIEGE_ENGINEER("Siege Engineer", null, null, false, 6, 1, RegularCardPositionType.SIEGE),
    MORVRAN_VOORHIS("Morvan Voorhis", null, null, true, 10, 1, RegularCardPositionType.SIEGE),
    ALBRICH("Albrich", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    CYNTHIA("Cynthia", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    ETOLIAN_AUXILIARY_ARCHER("Etolian Auxiliary Archer", null, "Medic", false, 1, 2, RegularCardPositionType.RANGED_COMBAT),
    LETHO_OF_GULET("Letho of Gulet", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MORTEISEN("Morteisen", null, null, false, 3, 1, RegularCardPositionType.SIEGE),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", null, "TightBond", false, 2, 3, RegularCardPositionType.CLOSE_COMBAT),
    RAINFARN("Rainfarn", null, null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    SEIGE_TECHNICIAN("Seige Technician", null, "Medic", false, 0,1,RegularCardPositionType.SIEGE),
    SWEERS("Sweers", null, null, false, 2,1,RegularCardPositionType.RANGED_COMBAT),
    VANHEMAR("Vanhemar", null, null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    VREEMDE("Vreemde", null, null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT);

    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    EmpireNilfgaardianRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(EmpireNilfgaardianRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
