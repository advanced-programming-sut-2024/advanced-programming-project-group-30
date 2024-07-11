package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import enums.Ability;
import model.card.RegularCard;
import view.ChosenModelView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum NeutralRegularCardsData implements RegularCardData {
    CIRILLA_FIONA_ELEN_RIANNON("Cirilla fiona elen riannon", null, true, 15, 1, RegularCardPositionType.CLOSE_COMBAT),
    GERALT_OF_RIVIA("Geralt of Rivia", null, true, 15, 1, RegularCardPositionType.CLOSE_COMBAT),
    BOVINE_DEFENSE_FORCE("Bovine Defense Force", null, false, 8, 0, RegularCardPositionType.CLOSE_COMBAT),
    TRISS_MERIGOLD("Triss Merigold", null, true, 7, 0, RegularCardPositionType.CLOSE_COMBAT),
    VILLENTRETENMERTH("Villentretenmerth", Ability.SCORCH, false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", Ability.MEDIC, true, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    OLGIERD_VON_EVEREC("Olgierd von Everec", Ability.MORAL_BOOST, false, 6, 1, RegularCardPositionType.AGILE),
    VESEMIR("Vesemir", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EMIEL_REGIS("EmielRegis", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ZOLTAN_CHIVAY("Zoltan Chivay", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM_DARKNESS("Gaunter O'Dimm:Darkness", Ability.MUSTER, false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    DANDELION("Dandelion", Ability.HORN_COMMANDER, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM("Gaunter O'Dimm", Ability.MUSTER, false, 2, 1, RegularCardPositionType.SIEGE),
    COW("Cow", Ability.TRANSFORMER, false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    AVALLACH("Avallach", Ability.SPY, true, 0, 1, RegularCardPositionType.OPPONENT_CLOSE_COMBAT),
    ;

    private final String name;
    private final Ability ability;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/neutral_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/neutral_" + this.toString().toLowerCase() + ".jpg";

    NeutralRegularCardsData(String name, Ability ability, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.ability = ability;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (NeutralRegularCardsData data : NeutralRegularCardsData.values())
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

    public RegularCard createCard() {
        Method ability = null;
        if (this.ability != null)
            ability = this.ability.getAbility();
        return new RegularCard(this.name, null, this, this.isHero, ability, this.cardPositionType);
    }
}
