package enums.cardsData;
import enums.Ability;
import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
public enum NilfgaardianEmpireRegularCardsData implements RegularCardData {
    BLACK_INFANTRY_ARCHER("Black Infantry Archer", null, false, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    BLACK_INFANTRY_ARCHER_1("Black Infantry Archer", null, false, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    HEAVY_ZERRIKANIAN_FIRE_SCORPION("Heavy Zerrikanian Fire Scorpion", null, false, 10, 1, RegularCardPositionType.SIEGE),
    LETHO_OF_GULET("Letho of Gulet", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MENNO_COEHOORN("Menno Coehoorn", Ability.MEDIC, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    MORVRAN_VOORHIS("Morvran Voorhis", null, true, 10, 1, RegularCardPositionType.SIEGE),
    TIBOR_EGGEBRACHT("Tibor Eggebracht", null, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    STEFAN_SKELLEN("Stefan Skellen", Ability.SPY, false, 9, 1, RegularCardPositionType.CLOSE_COMBAT),
    SHILARD_FITZ_OESTERLEN("Shilard Fitz-Oesterlen", Ability.SPY, false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    ASSIRE_VAR_ANAHID("Assire var Anahid", Ability.MEDIC, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    CAHIR_MAWR_DYFFRYN_AEP_CEALLACH("Cahir Mawr Dyffryn aep Ceallach", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    FRINGILLA_VIGO("Fringilla Vigo", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_ENGINEER("Siege Engineer", null, false, 6, 1, RegularCardPositionType.SIEGE),
    RENUALD_AEP_MATSEN("Renuald aep Matsen", Ability.SPY, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    YOUNG_EMISSARY("Young Emissary", Ability.TIGHT_BOND, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    YOUNG_EMISSARY_1("Young Emissary", Ability.TIGHT_BOND, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ZERRIKANIAN_FIRE_SCORPION("Zerrikanian Fire Scorpion", null, false, 5, 1, RegularCardPositionType.SIEGE),
    CYNTHIA("Cynthia", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    RAINFARN("rainfarn", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VANHEMAR("Vanhemar", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    VATTIER_DE_RIDEAUX("Vattier de Rideaux", Ability.SPY, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    IMPERA_BRIGADE_GUARD("Impera Brigade Guard", Ability.TIGHT_BOND, false, 3, 4, RegularCardPositionType.CLOSE_COMBAT),
    MORTEISEN("Morteisen", null, false, 3, 1, RegularCardPositionType.SIEGE),
    PUTTKAMMER("Puttkammer", null, false, 3, 1, RegularCardPositionType.RANGED_COMBAT),
    ROTTEN_MANGONEL("Rotten Mangonel", null, false, 3, 1, RegularCardPositionType.SIEGE),
    ALBRICH("Albrich", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    NAUSICAA_CAVALRY_RIDER("Nausicaa Cavalry Rider", Ability.TIGHT_BOND, false, 2, 3, RegularCardPositionType.CLOSE_COMBAT),
    SWEERS("Sweers", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    VREEMDE("Vreemde", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    ETOLIAN_AUXILIARY_ARCHERS("Etolian Auxiliary Archer",  Ability.MEDIC, false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    ETOLIAN_AUXILIARY_ARCHERS_1("Etolian Auxiliary Archer",  Ability.MEDIC, false, 1, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TECHNICIAN("Siege Technician",  Ability.MEDIC, false, 0, 1, RegularCardPositionType.SIEGE),
    ;
    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/nilfgaard_" + this.toString().toLowerCase() + ".jpg";
    private final String  smImageAddress = "/Images/Game/SmCardsImages/nifgaard_" + this.toString().toLowerCase() + ".jpg";

    NilfgaardianEmpireRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
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
    private RegularCard createCard() {
        Method ability = null;
        if (this.ability != null)
            ability = this.ability.getAbility();
        return new RegularCard(this.name, FactionType.NILFGAARDIAN_EMPIRE, this, this.isHero, this.point, ability, this.cardPositionType);
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
    public int getNumber() {
        return numberOfCard;
    }
    @Override
    public int getPoint(){
        return this.point;
    }

    @Override
    public boolean isHero() {
        return isHero;
    }

    public static Ability getAbilityByName(String name){
        for (NilfgaardianEmpireRegularCardsData data : NilfgaardianEmpireRegularCardsData.values())
            if (data.name.equals(name))
                return data.ability;
        return null;
    }
    @Override
    public Ability getAbility(){
        return this.ability;
    }
}