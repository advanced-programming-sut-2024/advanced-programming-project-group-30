package enums.cardsData;

import enums.Ability;
import javafx.scene.image.Image;
import model.card.SpecialCard;

import java.util.ArrayList;
import java.util.Objects;

public enum SpecialCardsData implements DeckCardData {
    COMMANDER_HORN("CommanderHorn", Ability.SPECIAL_COMMANDER_HORN, 3, false), DECOY("Decoy", Ability.DECOY, 3, false), MARDROEME("Mardroeme", Ability.MARDROEME, 3, false), SCORCH("Scorch", Ability.SPECIAL_SCORCH, 3, true),
    ;

    private final String name;
    private final Ability ability;
    private final int numberOfCard;
    private final boolean isDiscardAfterPlaying;
    private final String lgImageAddress = "/Images/Game/LgCardsImages/special_" + this.toString().toLowerCase() + ".jpg";
    private final String smImageAddress = "/Images/Game/SmCardsImages/special_" + this.toString().toLowerCase() + ".jpg";


    SpecialCardsData(String name, Ability ability, int cardsNumber, boolean isDiscardAfterPlaying) {
        this.name = name;
        this.ability = ability;
        this.numberOfCard = cardsNumber;
        this.isDiscardAfterPlaying = isDiscardAfterPlaying;
    }

    public static ArrayList<SpecialCard> getAllSpecialCard() {
        ArrayList<SpecialCard> specialCards = new ArrayList<>();
        for (enums.cardsData.SpecialCardsData data : enums.cardsData.SpecialCardsData.values()) {
            for (int i = 0; i < data.numberOfCard; i++)
                specialCards.add(data.createCard());
        }
        return specialCards;

    }

    private SpecialCard createCard() {
        return new SpecialCard(name, null, this, this.isDiscardAfterPlaying, ability.getAbility());
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
    public int getNumber() {
        return 0;
    }

    @Override
    public Ability getAbility() {
        return this.ability;
    }
}









