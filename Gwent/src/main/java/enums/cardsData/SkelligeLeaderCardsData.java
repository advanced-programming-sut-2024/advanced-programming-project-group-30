package enums.cardsData;

import javafx.scene.image.Image;
import model.card.Leader;

import java.util.ArrayList;
import java.util.Objects;

public enum SkelligeLeaderCardsData{
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

    public Image getLgImage() {
        return null;
    }
    public Image getSmImage() {
        return null;
    }
    public int getNumber() {
        return 1;
    }
}
