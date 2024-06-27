package enums.cardsData;

import enums.RegularCardPositionType;
import model.card.RegularCard;

import java.util.ArrayList;

public enum MonstersRegularCardsData {
    DRAUG("Draug", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    IMLERITH("Imlerith", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    LESHEN("Leshen", null, null, true, 10, 1, RegularCardPositionType.CLOSE_COMBAT),
    KAYRAN("Kayran", null, "MoralBoost", true, 8, 1, RegularCardPositionType.AGILE),
    TOAD("Toad", null, "Scorch", false, 7, 1, RegularCardPositionType.RANGED_COMBAT),
    ARACHAS_BEHEMOTH("Arachas Behemoth", null, "Muster", false, 6, 1, RegularCardPositionType.SIEGE),
    CRONE_BREWESS("Crone:Brewess", null, "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WEAVESS("Crone:Weavess", null, "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    CRONE_WHISPESS("Crone:Whispess", null, "Muster", false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    EARTH_ELEMENTAL("Earth Elemental", null, null, false, 6, 1, RegularCardPositionType.SIEGE),
    FIEND("Fiend", null, null, false, 6, 1, RegularCardPositionType.CLOSE_COMBAT),
    FIRE_ELEMENTAL("Fire Elemental", null, null, false, 6, 1, RegularCardPositionType.SIEGE),
    FORKTAIL("Forktail", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    FIGHTENER("Frightener", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    GRAVE_HAG("Grave Hag", null, null, false, 5, 1, RegularCardPositionType.RANGED_COMBAT),
    GRIFFIN("Griffin", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ICE_GIANT("Ice Giant", null, null, false, 5, 1, RegularCardPositionType.SIEGE),
    PLAGUE_MAIDEN("Plague Maiden", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_KATAKAN("Vampire:Katakan", null, "Muster", false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    WEREWOLF("Werewolf", null, null, false, 5, 1, RegularCardPositionType.CLOSE_COMBAT),
    ARACHAS("Arachas", null, "Muster", false, 4, 3, RegularCardPositionType.CLOSE_COMBAT),
    BOTCHILING("Botchling", null, null, false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_BRUXA("Vanpire:Bruxa", null, "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_EKIMMARA("Vampire:Ekimmara", null, "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_FLEDER("Vampire:Fleder", null, "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    VAMPIRE_GARKAIN("Vampire:Garkain", null, "Muster", false, 4, 1, RegularCardPositionType.CLOSE_COMBAT),
    CELAENO_HARPY("Celaeno Harpy", null, null, false, 2, 1, RegularCardPositionType.AGILE),
    COCKATRICE("Cockatrice", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    ENDREGA("Endrega", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    FOGLET("Foglet", null, null, false, 2, 1, RegularCardPositionType.CLOSE_COMBAT),
    GARGOYLE("Gargoyle", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    HARPY("Harpy", null, null, false, 2, 1, RegularCardPositionType.AGILE),
    NEKKER("Nekker", null, "Muster", false, 2, 3, RegularCardPositionType.CLOSE_COMBAT),
    WYVERN("Wyvern", null, null, false, 2, 1, RegularCardPositionType.RANGED_COMBAT),
    GHOUL("Ghoul", null, "Muster", false, 1, 3, RegularCardPositionType.CLOSE_COMBAT),;



    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;
    private final RegularCardPositionType cardPositionType;

    MonstersRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard, RegularCardPositionType cardPositionType) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
        this.cardPositionType = cardPositionType;
    }

    private static RegularCard createCard(MonstersRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
