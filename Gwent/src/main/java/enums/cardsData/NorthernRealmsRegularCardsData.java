package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.util.ArrayList;
import java.util.Objects;

public enum NorthernRealmsRegularCardsData implements RegularCardData {
    ESTERAD_THYSSEN("Esterad Thyssen", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    JOHN_NATALIS("John Natalis", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    VERNON_ROCHE("Vernon Roche", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    PHILIPPA_EILHART("Philippa Eilhart", "TightBond", true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    CATAPULT("Catapult", "TightBond", false, 8, 2, RegularCardPositionType.SIEGE),
    BALLISTA("Ballista", null, false, 6, 2, RegularCardPositionType.SIEGE),
    DETHMOLD("Dethmold", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TOWER("Siege Tower", null, false, 6, 1, RegularCardPositionType.SIEGE),
    TREBUCHET("Trebuchet", null, false, 6, 2, RegularCardPositionType.SIEGE),
    DRAGON_HUNTER("Dragon Hunter", "TightBond", false, 5, 3, RegularCardPositionType.RANGED_COMBAT),
    DUN_BANNER_MEDIC("Dun Banner Medic", "Medic", false, 5, 1, RegularCardPositionType.SIEGE),
    KEIRA_METZ("Keira Metz", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    PRINCE_STENNIS("Prince Stennis", "Spy", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SILE_DE_TANSARVILLE("Sile de Tansarville", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    VES("Ves", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", "TightBond", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    SABRINA_GLEVISSING("Sabrina Glevissing", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SHELDON_SKAGGS("Sheldon Skaggs", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", "Spy", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    YARPEN_ZIGRIN("Yarpen Zigrin", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", "MoralBoost", false, 1, 3, RegularCardPositionType.SIEGE),
    POOR_INFANTRY("Poor Infantry", "TightBond", false, 1, 4, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", null, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    THALER("Thaler", "Spy", false, 1, 1, RegularCardPositionType.SIEGE),
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

    @Override
    public Image getLgImage() {
        String address = "/Images/Game/LgCardsImages/realms_" + this.toString().toLowerCase() + ".jpg";
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
        return new RegularCard(this.name, FactionType.NORTHERN_REALMS, this, this.isHero, this.point, ability, this.cardPositionType);
    }
}