package enums;

import enums.cardsData.*;
import model.card.RegularCard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public enum FactionType {
    NORTHERN_REALMS("Northern Realms", "", NorthernRealmsRegularCardsData.class, RealmsNorthernLeaderCardsData.class),
    NILFGAARDIAN_EMPIRE("Nilfgaardian Empire", "", NilfgaardianEmpireRegularCardsData.class, EmpireNilfgaardianLeaderCardsData.class),
    MONSTERS("Monsters", "", MonstersRegularCardsData.class, MonstersRegularCardsData.class),
    SCOIA_TAEL("Scoia' TAEL", "", ScoiaTaelRegularCardsData.class, ScoiaTaelLeaderCardsData.class),
    SKELLIGE("Skellige", "", SkelligeRegularCardsData.class, SkelligeLeaderCardsData.class),
    ;

    private final String name;
    private final String description;
    private final Class regularCardsData;
    private final Class leaderCardsData;

    FactionType(String name, String description, Class regularCardsData, Class leaderCardsData) {
        this.name = name;
        this.description = description;
        this.regularCardsData = regularCardsData;
        this.leaderCardsData = leaderCardsData;
    }

    public ArrayList<RegularCard> getFactionRegularCards() {
        try {
            return (ArrayList<RegularCard>) this.regularCardsData.getDeclaredMethod("getAllRegularCard").invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}