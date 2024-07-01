package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum NorthernRealmsRegularCardsData implements CardData{
    ESTERAD_THYSSEN("Esterad Thyssen", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    JOHN_NATALIS("John Natalis", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    VERNON_ROCHE("Vernon Roche", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    PHILIPPA_EILHART("Philippa Eilhart", "tightBond", true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    CATAPULT("Catapult", "tightBond", false, 8, 2, RegularCardPositionType.SIEGE),
    BALLISTA("Ballista", null, false, 6, 2, RegularCardPositionType.SIEGE),
    DETHMOLD("Dethmold", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TOWER("Siege Tower", null, false, 6, 1, RegularCardPositionType.SIEGE),
    TREBUCHET("Trebuchet", null, false, 6, 2, RegularCardPositionType.SIEGE),
    TREBUCHET_1("Trebuchet", null, false, 6, 2, RegularCardPositionType.SIEGE),
    CRINFRID_REAVERS_DRAGON_HUNTER("Crinfrid Reavers Dragon Hunter", "tightBond", false, 5, 3, RegularCardPositionType.RANGED_COMBAT),
    DUN_BANNER_MEDIC("Dun Banner Medic", "medic", false, 5, 1, RegularCardPositionType.SIEGE),
    KEIRA_METZ("Keira Metz", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    PRINCE_STENNIS("Prince Stennis", "spy", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SILE_DE_TANSARVILLE("Sile de Tansarville", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    VES("Ves", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", "tightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    SABRINA_GLEVISSING("Sabrina Glevissing", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SHELDON_SKAGGS("Sheldon Skaggs", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", "spy", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    YARPEN_ZIGRIN("Yarpen Zigrin", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", "moralBoost", false, 1, 3, RegularCardPositionType.SIEGE),
    KAEDWENI_SIEGE_EXPERT_1("Kaedweni Siege Expert", "moralBoost", false, 1, 3, RegularCardPositionType.SIEGE),
    KAEDWENI_SIEGE_EXPERT_2("Kaedweni Siege Expert", "moralBoost", false, 1, 3, RegularCardPositionType.SIEGE),
    POOR_FUCKING_INFANTRY("Poor fucking Infantry", "tightBond", false, 1, 4, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", null, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER_1("Redanian Foot Soldier", null, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    THALER("Thaler", "spy", false, 1, 1, RegularCardPositionType.SIEGE),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    NorthernRealmsRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (NorthernRealmsRegularCardsData data : NorthernRealmsRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(data.createCard());
        return regularCards;
    }

    private RegularCard createCard() {
        Method ability = RegularCardsAbility.createNewAbilityByName(this.abilityName);
        return new RegularCard(this.name, FactionType.NORTHERN_REALMS, this, this.isHero, this.point, ability, this.cardPositionType);
    }

    @Override
    public Image getLgImage() {
        String address = "/Images/Game/LgCardsImages/realms_" + this.toString().toLowerCase() + ".jpg";
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
    }
    @Override
    public Image getSmImage() {
        String address;
        for (NorthernRealmsRegularCardsData data : NorthernRealmsRegularCardsData.values()) {
               address ="/Images/Game/SmCardsImages/realms_" + data.toString().toLowerCase() + ".jpg";
            System.out.println(address);
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
        }
//        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
        return null;
    }

    @Override
    public int getNumber() {
        return numberOfCard;
    }
}

