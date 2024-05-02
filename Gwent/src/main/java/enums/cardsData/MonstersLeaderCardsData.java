package enums.cardsData;


import model.card.Leader;

import java.util.ArrayList;

public enum MonstersLeaderCardsData {
    CARDS_NAME("card name", "card explanation", "faction name", "ability name");
    private final String name;
    private final String explanation;
    private final String factionName;
    private final String abilityName;

    MonstersLeaderCardsData(String name, String explanation, String factionName, String abilityName) {
        this.name = name;
        this.explanation = explanation;
        this.factionName = factionName;
        this.abilityName = abilityName;
    }

    private static Leader createLeader(String leaderName) {
        return null;
    }

    public static ArrayList<Leader> getAllLeader() {
        return null;
    }
}