package enums.cardsData;

import enums.Ability;
import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.card.RegularCard;
import view.ChosenModelView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum SkelligeRegularCardsData implements RegularCardData {
    TRANSFORMED_VILDKAARL("Transformed Vildkaarl", Ability.MORAL_BOOST, false, 14, 0, RegularCardPositionType.CLOSE_COMBAT),
    OLAF("Olaf", Ability.MORAL_BOOST, false, 12, 1, RegularCardPositionType.AGILE),
    CERYS("Cerys", Ability.MUSTER, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    HJALMAR("Hjalmar", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    ERMION("Ermion", Ability.MARDROEME, true, 8, 1, RegularCardPositionType.RANGED_COMBAT),
    TRANSFORMED_YOUNG_VILDKAARL("Transformed Young Vildkaarl", Ability.TIGHT_BOND, false, 8, 0, RegularCardPositionType.RANGED_COMBAT),
    BLUEBOY_LUGOS("Blueboy Lugos", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_AN_CRAITE_WARRIOR("Clan an Craite Warrior", Ability.TIGHT_BOND, false, 6, 3, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_BROKVAR_ARCHER("Clan Brokvar Archer", null, false, 6, 2, RegularCardPositionType.RANGED_COMBAT),
    CLAN_DIMUN_PIRATE("Clan Dimun Pirate", Ability.SCORCH, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    MADMAN_LUGOS("Madman Lugos", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    WAR_LONGSHIP("War Longship", Ability.TIGHT_BOND, false, 6, 2, RegularCardPositionType.SIEGE),
    BERSERKER("Berserker", Ability.BERKSER, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN("Clan Drummond Shieldmaide", Ability.TIGHT_BOND, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN_1("Clan Drummond Shieldmaide", Ability.TIGHT_BOND, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_DRUMMOND_SHIELD_MAIDEN_2("Clan Drummond Shieldmaide", Ability.TIGHT_BOND, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_HEYMAEY_SKALD("Clan Heymaey Skald", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CLAN_TORDARROCH_ARMORSMITH("Clan Tordarroch Armorsmith", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    DONAR_AN_HINDAR("Donar an Hindar", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    HOLGER_BLACKHAND("Holger Blackhand", null, false, 4, 1, RegularCardPositionType.SIEGE),
    LIGHT_LONGSHIP("Light Longship", Ability.MUSTER, false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    SVANRIGE("Svanrige", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    UDALRYK("Udalryk", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    BIRNA_BRAN("Birna Bran", Ability.MEDIC, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    DRAIG_BON_DHU("Draig Bon-Dhu", Ability.HORN_COMMANDER, false, 2, 1, RegularCardPositionType.SIEGE),
    YOUNG_BERSERKER("Young Berserker", Ability.BERKSER, false, 2, 3, RegularCardPositionType.RANGED_COMBAT),
    KAMBI("Kambi", Ability.TRANSFORMER, true, 0, 1, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/skellige_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/skellige_" + this.toString().toLowerCase() + ".jpg";


    SkelligeRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
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
    public Ability getAbility() {
        return this.ability;
    }

    @Override
    public boolean isHero() {
        return isHero;
    }

    @Override
    public int getPoint() {
        return this.point;
    }

    @Override
    public int getNumber() {
        return numberOfCard;
    }

    @Override
    public Image getLgImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(lgImageAddress)));
    }

    @Override
    public Image getSmImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(smImageAddress)));
    }

    @Override
    public ChosenModelView<DeckCardData> getChooseModelView() {
        return new ChosenModelView<>(Objects.requireNonNull(
                this.getClass().getResourceAsStream(lgImageAddress)), this, ability.getExplanation(), ability.getAbilityMethodName());
    }

    private RegularCard createCard() {
        Method ability = null;
        if (this.ability != null)
            ability = this.ability.getAbility();
        return new RegularCard(this.name, FactionType.MONSTERS, this, this.isHero, ability, this.cardPositionType);
    }
}