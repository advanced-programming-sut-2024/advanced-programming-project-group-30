package enums.cardsData;

import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public enum NeutralRegularCardsData implements CardData {
    GERALT_OF_RIVIA("Geralt of Rivia", null, true, 15, 1, RegularCardPositionType.CLOSE_COMBAT),
    TRISS_MERIGOLD("Triss Merigold", null, true, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    VILLENTRETENMERTH("Villentretenmerth", "scorch", false, 7, 1, RegularCardPositionType.CLOSE_COMBAT),
    YENNEFER_OF_VENGERBERG("Yennefer of Vengerberg", "medic", true, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    OLGIERD_VON_EVEREC("Olgierd von Everec", "moralBoost", false, 6, 1, RegularCardPositionType.AGILE),
    VESEMIR("Vesemir", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EMIEL_REGIS_ROHELLEC_TERZIEFF("Emiel Regis Rohellec Terzieff", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ZOLTAN_CHIVAY("Zoltan Chivay", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM_DARKNESS("Gaunter O'Dimm: Darkness", "muster", false, 4, 3, RegularCardPositionType.RANGED_COMBAT),
    DANDELION("Dandelion", "hornCommander", false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GAUNTER_ODIMM("Gaunter O'Dimm", "muster", false, 2, 1, RegularCardPositionType.SIEGE),
    COW("Cow", "transformers", false, 0, 1, RegularCardPositionType.RANGED_COMBAT),
    AVALLACH("Avallac'h", "spy", true, 0, 1, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    NeutralRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
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

    private RegularCard createCard() {
        Method ability = RegularCardsAbility.createNewAbilityByName(this.abilityName);
        return new RegularCard(this.name, null, this, this.isHero, this.point, ability, this.cardPositionType);
    }

    @Override
    public Image getLgImage() {
        String address = "/Images/Game/LgCardsImages/neutral_" + this.toString().toLowerCase() + ".jpg";
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
    }
    @Override
    public Image getSmImage() {
        String address = "/Images/Game/SmCardsImages/neutral_" + this.toString().toLowerCase() + ".jpg";
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(address)));
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
    public String getAbilityName() {
        if (this.abilityName == null)
            return "";
        return this.abilityName;
    }
}