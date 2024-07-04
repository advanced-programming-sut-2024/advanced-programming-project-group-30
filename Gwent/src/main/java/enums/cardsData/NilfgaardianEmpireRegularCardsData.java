package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;
import view.chosenModelView;

import java.util.ArrayList;
import java.util.Objects;

public enum NilfgaardianEmpireRegularCardsData implements RegularCardData {
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", null, false, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    BLACK_INFANTRY_ARCHER_1("Black Infantry Archer", null, false, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpion", null, false, 10, 1, RegularCardPositionType.SIEGE),
    LETHO_OF_GULET("Letho of Gulet", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MENNO_COEHOORN("Menno Coehoorn", "Medic", true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MORVRAN_VOORHIS("Morvran Voorhis", null, true, 10, 1, RegularCardPositionType.SIEGE),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    STEFAN_SKELLEN("Stefan Skellen", "Spy", false, 9, 1, RegularCardPositionType.CLOSE_COMBAT),
    SHILARD_FITZ_OESTERLEN("Shilard Fitz-Oesterlen", "Spy", false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    ASSIRE_VAR_ANAHID("Assire var Anahid", "Medic", false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    FRINGILLA_VIGO("Fringilla Vigo", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_ENGINEER("Siege Engineer", null, false, 6, 1, RegularCardPositionType.SIEGE),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", "Spy", false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    YOUNG_EMISSARY("Young Emissary", "TightBond", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    YOUNG_EMISSARY_1("Young Emissary", "tightBond", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", null, false, 5, 1, RegularCardPositionType.SIEGE),
    CYNTHIA("Cynthia", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    RAINFARN("rainfarn", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VANHEMAR("Vanhemar", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", "Spy", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", "TightBond", false, 3, 4, RegularCardPositionType.CLOSE_COMBAT),
    MORTEISEN("Morteisen", null, false, 3, 1, RegularCardPositionType.SIEGE),
    PUTTKAMMER("Puttkammer", null, false, 3, 1, RegularCardPositionType.RANGED_COMBAT),
    ROTTEN_MANGONEL("Rotten Mangonel", null, false, 3, 1, RegularCardPositionType.SIEGE),
    ALBRICH("Albrich", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", "TightBond", false, 2, 3, RegularCardPositionType.CLOSE_COMBAT),
    SWEERS("Sweers", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    VREEMDE("Vreemde", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archer", "Medic", false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    ETOLIAN_AUXILIARY_ARCHERS_1("Etolian Auxiliary Archer", "Medic", false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TECHNICIAN("Siege Technician", "Medic", false, 0, 1, RegularCardPositionType.SIEGE),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/nilfgaard_" + this.toString().toLowerCase() + ".jpg";

    NilfgaardianEmpireRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (NilfgaardianEmpireRegularCardsData data : NilfgaardianEmpireRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(data.createCard());
        return regularCards;
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

    @Override
    public Image getLgImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(lgImageAddress)));
    }

    @Override
    public chosenModelView getChooseModelView() {
        return new chosenModelView<>(Objects.requireNonNull(
                this.getClass().getResourceAsStream(lgImageAddress)), this, "", abilityName);
    }

    private RegularCard createCard() {
        RegularCardsAbility ability = RegularCardsAbility.createNewAbilityByName(this.abilityName);
        return new RegularCard(this.name, FactionType.NILFGAARDIAN_EMPIRE, this, this.isHero, this.point, ability, this.cardPositionType);
    }
}