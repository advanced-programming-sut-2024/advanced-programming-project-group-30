package enums.cardsData;

import enums.RegularCardPositionType;
import model.Faction;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.util.ArrayList;

public enum ScoiaTaelRegularCardsData {
    EITHNE("Eithne", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    ISENGRIM_FAOLITARNA("Isengrim Faolitarna", "MoralBoost", true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    LORVETH("Lorveth", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    MILVA("Milva", "MoralBoost", false, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    SEASENTHESSIS("Seasenthessis", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    SCHIRRU("Schirru", "Scorch", false, 8, 1, RegularCardPositionType.SIEGE),
    BARCLAY_ELS("Barclay Els", null, false, 6, 1, RegularCardPositionType.AGILE),
    DENNIS_CRANMER("Dennis Cranmer", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", null, false, 6, 3, RegularCardPositionType.AGILE),
    FILAVANDREL("Filavandrel", null, true, 6, 1, RegularCardPositionType.AGILE),
    IDA_EMEAN_AEP("Ida Emean Aep", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    YAEVINN("Yaevinn", null, false, 6, 1, RegularCardPositionType.AGILE),
    HAVEKAR_SMUGGLER("Havekar Smuggler", "Muster", false, 5, 3, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", null, false, 5, 5, RegularCardPositionType.CLOSE_COMBAT),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", null, false, 5, 2, RegularCardPositionType.AGILE),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    CIARAN_AEP("Ciaran Aep", null, false, 3, 1, RegularCardPositionType.AGILE),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", "Muster", false, 3, 3, RegularCardPositionType.CLOSE_COMBAT),
    ELVEN_SKIRMISHER("Elven Skirmisher", "Muster", false, 2, 3, RegularCardPositionType.RANGED_COMBAT),
    TORUVIEL("Toruviel", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    RIORDIAN("Riordian", null, false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    HAVEKAR_HEALER("Havekar Healer", "Medic", false, 0, 3, RegularCardPositionType.RANGED_COMBAT),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    ScoiaTaelRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard(Faction faction) {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (ScoiaTaelRegularCardsData data : ScoiaTaelRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(createCard(data, faction));
        return regularCards;
    }

    private static RegularCard createCard(ScoiaTaelRegularCardsData data, Faction faction) {
        RegularCardsAbility ability = RegularCardsAbility.createNewAbilityByName(data.abilityName);
        return new RegularCard(data.name, faction, data.isHero, data.point, ability, data.cardPositionType);
    }
}