package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.util.ArrayList;
import java.util.Objects;

public enum SkelligeRegularCardsData implements RegularCardData {
    TRANSFORMED_VILDKAARL("Transformed Vildkaarl", "moralBoost", false, 14, 0, RegularCardPositionType.CLOSE_COMBAT),
    OLAF("Olaf", "moralBoost", false, 12, 1, RegularCardPositionType.AGILE),
    CERYS("Cerys", "muster", true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    HJALMAR("Hjalmar", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    ERMION("Ermion", "mardroeme", true, 8, 1, RegularCardPositionType.RANGED_COMBAT),
    TRANSFORMED_YOUNG_VILDKAARL("Transformed Young Vildkaarl", "tightBond", false, 8, 0, RegularCardPositionType.RANGED_COMBAT),
    BLUEBOY_LUGOS("Blueboy Lugos", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_AN_CRAITE_WARRIOR("Clan an Craite Warrior", "tightBond", false, 6, 3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", null, false, 6, 3, RegularCardPositionType.RANGED_COMBAT),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", "scorch", false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    MADMAN_LUGOS("Madman Lugos", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    WAR_LONGSHIP("War Longship", "TightBond", false, 6, 3, RegularCardPositionType.SIEGE),
    BERSERKER("Berserker", "Berserker", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN("Clan Drummond Shieldmaide", "tightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN_1("Clan Drummond Shieldmaide", "tightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN_2("Clan Drummond Shieldmaide", "tightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armorsmith", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    DONAR_AN_HINDAR("Donar an Hindar", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    HOLGER_BLACKHAND("Holger Blackhand", null, false, 4, 1, RegularCardPositionType.SIEGE),
    LIGHT_LONGSHIP("Light Longship", "muster", false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    SVANRIGE("Svanrige", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    UDALRYK("Udalryk", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    BIRNA_BRAN("Birna Bran", "medic", false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    DRAIG_BON_DHU("Draig Bon-Dhu", "hornCommander", false, 2, 1, RegularCardPositionType.SIEGE),
    YOUNG_BERSERKER("Young Berserker", "berserker", false, 2, 3, RegularCardPositionType.RANGED_COMBAT),
    KAMBI("Kambi", "transformers", true, 0, 1, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    SkelligeRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (SkelligeRegularCardsData data : SkelligeRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(data.createCard());
        return regularCards;
    }

    @Override
    public Image getLgImage() {
        String address = "/Images/Game/LgCardsImages/skellige_" + this.toString().toLowerCase() + ".jpg";
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
    }

    @Override
    public int getNumber() {
        return numberOfCard;
    }

    @Override
    public int getPoint() {
        return this.point;
    }

    @Override
    public boolean isHero() {
        return isHero;
    }

    private RegularCard createCard() {
        RegularCardsAbility ability = RegularCardsAbility.createNewAbilityByName(this.abilityName);
        return new RegularCard(this.name, FactionType.SKELLIGE, this, this.isHero, this.point, ability, this.cardPositionType);
    }
}