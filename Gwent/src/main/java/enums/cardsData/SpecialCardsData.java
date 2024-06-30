package enums.cardsData;

import javafx.scene.image.Image;
import model.card.specialCard.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.DoubleAccumulator;

public enum SpecialCardsData implements CardData {
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

    public static ArrayList<SpecialCard> getAllSpecialCard() {
        ArrayList<SpecialCard> specialCards = new ArrayList<>();
        for (SpecialCardsData data : SpecialCardsData.values())
            for (int i = 0; i < data.numberOfCard; i++)
                specialCards.add(data.createCard());
        return specialCards;
    }

    private SpecialCard createCard() {
        SpecialCard newSpecialCard;
        try {
            newSpecialCard = (SpecialCard) Class.forName("model.card.specialCard." + this.name)
                    .getConstructor(String.class, String.class, CardData.class).newInstance(this.name, this.description, this);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return newSpecialCard;
    }

    @Override
    public Image getLgImage() {
        String subAddress = this.name.toLowerCase().replaceAll(".*:", "").replace(" ", "_");
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/Images/Game/special_" + subAddress)));
    }
}