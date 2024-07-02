package enums.cardsData;

import javafx.scene.image.Image;
import model.card.Leader;

import java.util.ArrayList;

public enum ScoiaTaelLeaderCardsData implements LeaderCardData{
    CARDS_NAME("card name", "card explanation", "ability name");

    private final String name;
    private final String explanation;
    private final String abilityName;

    ScoiaTaelLeaderCardsData(String name, String explanation, String abilityName) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
    }

    private static Leader createLeader(ScoiaTaelLeaderCardsData data) {
        return null;
    }

    public static ArrayList<Leader> getAllLeader() {
        return null;
    }

    @Override
    public Image getLgImage() {
        return null;
    }
}