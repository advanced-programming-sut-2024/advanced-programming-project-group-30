package enums.cardsData;

import enums.RegularCardPositionType;
import model.Faction;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.util.ArrayList;

public enum NeutralRegularCardsData {
    GERALT_OF_RIVIA("Geralt of Rivia", null, true, 15, 1, RegularCardPositionType.CLOSE_COMBAT),
    TRISS_MERIGOLD("Triss Merigold", null, true, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    VILLENTRETENMERTH("Villentretenmerth", "Scorch", false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", "Medic", true, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    OLGIERD_VON_EVERC("Olgierd von Everc", "MoralBoost", false, 6, 1, RegularCardPositionType.AGILE),
    VESEMIR("Vesemir", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EMIEL_REGIS("EmielRegis", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ZOLTAN_CHIVAY("Zoltan Chivay", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM_DARKNESS("Gaunter O'Dimm Darkness", "Muster", false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    DANDELION("Dandelion", "HornCommander", false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM("Gaunter O'Dimm", "Muster", false, 2, 1, RegularCardPositionType.SIEGE),
    COW("Cow", "Transformers", false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    MYSTERIOUS_ELF("Mysterious Elf", "Spy", true, 0, 1, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    NeutralRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard(Faction faction) {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (NeutralRegularCardsData data : NeutralRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(createCard(data, faction));
        return regularCards;
    }

    private static RegularCard createCard(NeutralRegularCardsData data, Faction faction) {
        RegularCardsAbility ability = RegularCardsAbility.createNewAbilityByName(data.abilityName);
        return new RegularCard(data.name, faction, data.isHero, data.point, ability, data.cardPositionType);
    }
}