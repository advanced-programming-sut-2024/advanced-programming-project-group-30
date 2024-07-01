package enums.cardsData;

import javafx.scene.image.Image;
import model.card.Leader;

import java.util.ArrayList;

public enum SkelligeLeaderCardsData implements CardData{
    CARDS_NAME("card name", "card explanation", "ability name");

    private final String name;
    private final String explanation;
    private final String abilityName;

    SkelligeLeaderCardsData(String name, String explanation, String abilityName) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
    }

    private static Leader createLeader(SkelligeLeaderCardsData data) {
        return null;
    }

    public static ArrayList<Leader> getAllLeader() {
        return null;
    }

    @Override
    public Image getLgImage() {
        return null;
    }

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public int getPoint() {
        return 0;
    }
}

