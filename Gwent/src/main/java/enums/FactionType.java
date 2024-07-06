package enums;

import enums.cardsData.*;
import javafx.scene.image.Image;
import model.card.RegularCard;
import view.ChosenModelView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;

public enum FactionType {
    NORTHERN_REALMS("Northern Realms", "Draw a card from your deck whenever you win a round.",
            NorthernRealmsRegularCardsData.class, NorthernRealmsLeaderCardsData.class),
    NILFGAARDIAN_EMPIRE("Nilfgaardian Empire", "Wins any round that ends in a draw.",
            NilfgaardianEmpireRegularCardsData.class, NilfgaardianEmpireLeaderCardsData.class),
    MONSTERS("Monsters", "Keeps a random Unit Card out after each round.",
            MonstersRegularCardsData.class, MonstersRegularCardsData.class),
    SCOIA_TAEL("Scoia'tael", "Decides who takes first turn.",
            ScoiaTaelRegularCardsData.class, ScoiaTaelLeaderCardsData.class),
    SKELLIGE("Skellige",
            "2 random cards from the graveyard are placed on\n the battlefield at the start of the third round.",
            SkelligeRegularCardsData.class, SkelligeLeaderCardsData.class),
    ;

    private final String name;
    private final String description;
    private final Class regularCardsData;
    private final Class leaderCardsData;
    private final String lgImageAddress = "/Images/Game/Factions/" + this.toString().toLowerCase() + ".jpg";
    private final String shieldImageAddress = "/Images/Game/Factions/shield_" + this.toString().toLowerCase() + ".png";

    FactionType(String name, String description, Class regularCardsData, Class leaderCardsData) {
        this.name = name;
        this.description = description;
        this.regularCardsData = regularCardsData;
        this.leaderCardsData = leaderCardsData;
    }

    public static ArrayList<ChosenModelView<FactionType>> getAllChooseModelView() {
        ArrayList<ChosenModelView<FactionType>> allChooseModelView = new ArrayList<>();
        for (FactionType factionType : FactionType.values())
            allChooseModelView.add(factionType.getChooseModelView());
        return allChooseModelView;
    }

    public static int getFactionIndex(FactionType factionType) {
        for (int i = 0; i < FactionType.values().length; i++) {
            if (factionType == FactionType.values()[i]) return i;
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getShieldIcon() {
        return new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(this.shieldImageAddress)));
    }

    public ArrayList<RegularCard> getFactionRegularCards() {
        try {
            return (ArrayList<RegularCard>) this.regularCardsData.getDeclaredMethod("getAllRegularCard").invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private ChosenModelView<FactionType> getChooseModelView() {
        return new ChosenModelView<>(Objects.requireNonNull(
                this.getClass().getResourceAsStream(lgImageAddress)), this, this.name, this.description);
    }
}