package enums.cardsData;

import model.Faction;
import model.card.specialCard.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public enum SpecialCardsData {
    COMMANDER_HORN("CommanderHorn", "Doubles the strength of all unit cards in that row. Limited to 1 per row.", 3),
    DECOY("Decoy", "Swap with a card on the battlefield to return it to your hand.", 3),
    MARDROEME("Mardroeme", "Triggers transformation of all Berserker cards on the same row.", 3),
    SCORCH("Scorch", "Triggers transformation of all Berserker cards on the same row.", 3),
    BITING_FROST("BitingFrost", "Sets the strength of all Close Combat cards to 1 for both players.", 3),
    CLEAR_WEATHER("ClearWeather", "Removes all Weather Cards (Biting Frost, Impenetrable Fog and Torrential Rain) effects.", 2),
    IMPENETRABLE_FOG("ImpenetrableFog", "Sets the strength of all Ranged Combat cards to 1 for both players.", 3),
    SKELLIEGE_STORM("SkelliegeStorm", "Sets the strength of all Ranged Combat cards to 1 for both players.", 3),
    TORRENTIAL_RAIN("TorrentialRain", "Sets the strength of all Siege Combat cards to 1 for both players.", 3),
    ;

    private final String name;
    private final String description;
    private final int numberOfCard;

    SpecialCardsData(String name, String description, int cardsNumber) {
        this.name = name;
        this.description = description;
        this.numberOfCard = cardsNumber;
    }

    public static ArrayList<SpecialCard> getAllSpecialCard(Faction faction) {
        ArrayList<SpecialCard> specialCards = new ArrayList<>();
        for (SpecialCardsData data : SpecialCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                specialCards.add(createCard(data, faction));
        return specialCards;
    }

    private static SpecialCard createCard(SpecialCardsData data, Faction faction) {
        SpecialCard newSpecialCard;
        try {
            newSpecialCard = (SpecialCard) Class.forName("model.card.specialCard." + data.name)
                    .getConstructor(String.class, String.class, Faction.class).newInstance(data.name, data.description, faction);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return newSpecialCard;
    }
}