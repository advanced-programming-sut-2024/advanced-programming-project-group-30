package enums;

import enums.cardsData.*;
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
    SCOIA_TAEL("Scoia' TAEL", "Decides who takes first turn.",
            ScoiaTaelRegularCardsData.class, ScoiaTaelLeaderCardsData.class),
    SKELLIGE("Skellige",
            "2 random cards from the graveyard are placed on the battlefield at the start of the third round.",
            SkelligeRegularCardsData.class, SkelligeLeaderCardsData.class),
    ;

    private final String name;
    private final String description;
    private final Class regularCardsData;
    private final Class leaderCardsData;
    private final String lgImageAddress = "/Images/Game/Factions/" + this.toString().toLowerCase() + ".jpg";

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

    public String getName() {
        return name;
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
                this.getClass().getResourceAsStream(lgImageAddress)), this, this.description, this.name);
    }
}