package enums.cardsData;

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
}

