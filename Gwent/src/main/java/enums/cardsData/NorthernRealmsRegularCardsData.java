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

public enum NorthernRealmsRegularCardsData implements RegularCardData{
    ESTERAD_THYSSEN("Esterad Thyssen", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    JOHN_NATALIS("John Natalis", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    VERNON_ROCHE("Vernon Roche", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    PHILIPPA_EILHART("Philippa Eilhart", Ability.TIGHT_BOND, true, 10, 1, RegularCardPositionType.RANGED_COMBAT),
    CATAPULT("Catapult", Ability.TIGHT_BOND, false, 8, 2, RegularCardPositionType.SIEGE),
    BALLISTA("Ballista", null, false, 6, 1, RegularCardPositionType.SIEGE),
    DETHMOLD("Dethmold", null, false, 6, 1, RegularCardPositionType.RANGED_COMBAT),
    SIEGE_TOWER("Siege Tower", null, false, 6, 1, RegularCardPositionType.SIEGE),
    TREBUCHET("Trebuchet", null, false, 6, 1, RegularCardPositionType.SIEGE),
    TREBUCHET_1("Trebuchet", null, false, 6, 1, RegularCardPositionType.SIEGE),
    DRAGON_HUNTER("Dragon Hunter", Ability.TIGHT_BOND, false, 5, 3, RegularCardPositionType.RANGED_COMBAT),
    DUN_BANNER_MEDIC("Dun Banner Medic", Ability.MEDIC, false, 5, 1, RegularCardPositionType.SIEGE),
    KEIRA_METZ("Keira Metz", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    PRINCE_STENNIS("Prince Stennis", Ability.SPY, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SIEGFRIED_OF_DENESLE("Siegfried of Denesle", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    SILE_DE_TANSARVILLE("Sile de Tansarville", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    VES("Ves", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    BLUE_STRIPES_COMMANDO("Blue Stripes Commando", Ability.TIGHT_BOND, false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    SABRINA_GLEVISSING("Sabrina Glevissing", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SHELDON_SKAGGS("Sheldon Skaggs", null, false, 4, 1, RegularCardPositionType.RANGED_COMBAT),
    SIGISMUND_DIJKSTRA("Sigismund Dijkstra", Ability.SPY, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    YARPEN_ZIGRIN("Yarpen Zigrin", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAEDWENI_SIEGE_EXPERT("Kaedweni Siege Expert", Ability.MORAL_BOOST, false, 1, 1, RegularCardPositionType.SIEGE),
    KAEDWENI_SIEGE_EXPERT_1("Kaedweni Siege Expert", Ability.MORAL_BOOST, false, 1, 1, RegularCardPositionType.SIEGE),
    KAEDWENI_SIEGE_EXPERT_2("Kaedweni Siege Expert", Ability.MORAL_BOOST, false, 1, 1, RegularCardPositionType.SIEGE),
    POOR_FUCKING_INFANTRY("Poor fucking Infantry", Ability.TIGHT_BOND, false, 1, 4, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER("Redanian Foot Soldier", null, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    REDANIAN_FOOT_SOLDIER_1("Redanian Foot Soldier", null, false, 1, 1, RegularCardPositionType.CLOSE_COMBAT),
    THALER("Thaler", Ability.SPY, false, 1, 1, RegularCardPositionType.SIEGE),
    ;

    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/realms_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/realms_" + this.toString().toLowerCase() + ".jpg";

    NorthernRealmsRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
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

    public Ability getAbilityByName(String name) {
        for (NorthernRealmsRegularCardsData data : NorthernRealmsRegularCardsData.values()){
            if (data.name.equals(name)){
                return data.ability;
            }
        }
        return null;
    }
    @Override
    public Ability getAbility(){
        return this.ability;
    }

    @Override
    public Image getLgImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(lgImageAddress)));
    }
    @Override
    public Image getSmImage() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(smImageAddress)));
    }


//    @Override
//    public ChosenModelView<DeckCardData> getChooseModelView() {
//        return new ChosenModelView<>(Objects.requireNonNull(
//                this.getClass().getResourceAsStream(lgImageAddress)), this, "",abilityName);
//    }

    private RegularCard createCard() {
        Method ability = null;
        if (this.ability != null)
            ability = this.ability.getAbility();
        return new RegularCard(this.name, FactionType.NORTHERN_REALMS, this, this.isHero, this.point, ability, this.cardPositionType);
    }
}

