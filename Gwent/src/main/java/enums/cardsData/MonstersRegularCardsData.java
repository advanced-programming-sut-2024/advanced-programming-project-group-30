package enums.cardsData;

import enums.FactionType;
import enums.RegularCardPositionType;
import javafx.scene.image.Image;
import model.ability.RegularCardsAbility;
import model.card.RegularCard;

import java.util.ArrayList;
import java.util.Objects;

public enum MonstersRegularCardsData implements RegularCardData {
    DRAUG("Draug", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    IMLERITH("Imlerith", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    LESHEN("Leshen", null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAYRAN("Kayran", "MoralBoost", true, 8, 1, RegularCardPositionType.AGILE),
    TOAD("Toad", "Scorch", false, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    ARACHAS_BEHEMOTH("Arachas Behemoth", "Muster", false, 6, 1, RegularCardPositionType.SIEGE),
    CRONE_BREWESS("Crone:Brewess", "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WEAVESS("Crone:Weavess", "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WHISPESS("Crone:Whispess", "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EARTH_ELEMENTAL("Earth Elemental", null, false, 6, 1, RegularCardPositionType.SIEGE),
    FIEND("Fiend", null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    FIRE_ELEMENTAL("Fire Elemental", null, false, 6, 1, RegularCardPositionType.SIEGE),
    FORKTAIL("Forktail", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    FRIGHTENER("Frightener", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GRAVE_HAG("Grave Hag", null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    GRIFFIN("Griffin", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ICE_GIANT("Ice Giant", null, false, 5, 1, RegularCardPositionType.SIEGE),
    PLAGUE_MAIDEN("Plague Maiden", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_KATAKAN("Vampire:Katakan", "Muster", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    WEREWOLF("Werewolf", null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ARACHAS("Arachas", "Muster", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    BOTCHLING("Botchling", null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_BRUXA("Vampire:Bruxa", "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_EKIMMARA("Vampire:Ekimmara", "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_FLEDER("Vampire:Fleder", "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_GARKAIN("Vampire:Garkain", "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CELAENO_HARPY("Celaeno Harpy", null, false, 2, 1, RegularCardPositionType.AGILE),
    COCKATRICE("Cockatrice", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    ENDREGA("Endrega", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    FOGLET("Foglet", null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GARGOYLE("Gargoyle", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    HARPY("Harpy", null, false, 2, 1, RegularCardPositionType.AGILE),
    NEKKER("Nekker", "Muster", false, 2, 3, RegularCardPositionType.CLOSE_COMBAT),
    WYVERN("Wyvern", null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    GHOUL("Ghoul", "Muster", false, 1, 3, RegularCardPositionType.CLOSE_COMBAT),
    ;

    private final String name;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    MonstersRegularCardsData(String name, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        ArrayList<RegularCard> regularCards = new ArrayList<>();
        for (MonstersRegularCardsData data : MonstersRegularCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                regularCards.add(data.createCard());
        return regularCards;
    }

    @Override
    public Image getLgImage() {
        String subAddress = this.name.toLowerCase().replaceAll(".*:", "").replace(" ", "_");
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/Images/Game/monsters_" + subAddress)));
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
        return new RegularCard(this.name, FactionType.MONSTERS, this, this.isHero, this.point, ability, this.cardPositionType);
    }
}