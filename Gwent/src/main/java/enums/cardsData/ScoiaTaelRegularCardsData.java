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

public enum ScoiaTaelRegularCardsData implements RegularCardData {
    EITHNE("Eithne", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    IORVETH("Iorveth", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    ISENGRIM_FAOLITARNA("Isengrim Faolitarna", Ability.MORAL_BOOST, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MILVA("Milva", Ability.MORAL_BOOST, false, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    SAESENTHESSIS("Saesenthessis", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    SCHIRRU("Schirru", Ability.SCORCH, false, 8, 1, RegularCardPositionType.SIEGE),
    BARCLAY_ELS("Barclay Els", null, false, 6, 1, RegularCardPositionType.AGILE),
    DENNIS_CRANMER("Dennis Cranmer", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    DOL_BLATHANNA_SCOUT("Dol Blathanna Scout", null, false, 6, 1, RegularCardPositionType.AGILE),
    DOL_BLATHANNA_SCOUT_1("Dol Blathanna Scout", null, false, 6, 1, RegularCardPositionType.AGILE),
    DOL_BLATHANNA_SCOUT_2("Dol Blathanna Scout", null, false, 6, 1, RegularCardPositionType.AGILE),
    FILAVANDREL_AEN_FIDHAIL("Filavandrel aen Fidhail", null, true, 6, 1, RegularCardPositionType.AGILE),
    IDA_EMEAN_AEP_SIVNEY("Ida Emean aep Sivney", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    YAEVINN("Yaevinn", null, false, 6, 1, RegularCardPositionType.AGILE),
    HAVEKAR_SMUGGLER("Havekar Smuggler", Ability.MUSTER, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    HAVEKAR_SMUGGLER_1("Havekar Smuggler", Ability.MUSTER, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    HAVEKAR_SMUGGLER_2("Havekar Smuggler", Ability.MUSTER, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER("Mahakaman Defender", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER_1("Mahakaman Defender", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER_2("Mahakaman Defender", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER_3("Mahakaman Defender", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    MAHAKAMAN_DEFENDER_4("Mahakaman Defender", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    VRIHEDD_BRIGADE_VETERAN("Vrihedd Brigade Veteran", null, false, 5, 1, RegularCardPositionType.AGILE),
    VRIHEDD_BRIGADE_VETERAN_1("Vrihedd Brigade Veteran", null, false, 5, 1, RegularCardPositionType.AGILE),
    DOL_BLATHANNA_ARCHER("Dol Blathanna Archer", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    VRIHEDD_BRIGADE_RECRUIT("Vrihedd Brigade Recruit", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    CIARAN_AEP_EASNILLIEN("Ciaran aep Easnillien", null, false, 3, 1, RegularCardPositionType.AGILE),
    DWARVEN_SKIRMISHER("Dwarven Skirmisher", Ability.MUSTER, false, 3, 1, RegularCardPositionType.CLOSE_COMBAT),
    DWARVEN_SKIRMISHER_1("Dwarven Skirmisher", Ability.MUSTER, false, 3, 1, RegularCardPositionType.CLOSE_COMBAT),
    DWARVEN_SKIRMISHER_2("Dwarven Skirmisher", Ability.MUSTER, false, 3, 1, RegularCardPositionType.CLOSE_COMBAT),
    ELVEN_SKIRMISHER("Elven Skirmisher", Ability.MUSTER, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    ELVEN_SKIRMISHER_1("Elven Skirmisher", Ability.MUSTER, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    ELVEN_SKIRMISHER_2("Elven Skirmisher", Ability.MUSTER, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    TORUVIEL("Toruviel", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    RIORDAIN("Riordain", null, false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    HAVEKAR_HEALER("Havekar Healer", Ability.MEDIC, false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    HAVEKAR_HEALER_1("Havekar Healer", Ability.MEDIC, false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    HAVEKAR_HEALER_2("Havekar Healer", Ability.MEDIC, false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    ;

    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/scoiatael_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/scoiatael_" + this.toString().toLowerCase() + ".jpg";


    ScoiaTaelRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (ScoiaTaelRegularCardsData data : ScoiaTaelRegularCardsData.values())
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
        return new RegularCard(this.name, FactionType.SCOIA_TAEL, this, this.isHero, ability, this.cardPositionType);
    }
}