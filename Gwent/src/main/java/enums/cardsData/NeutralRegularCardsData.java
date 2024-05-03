package enums.cardsData;

import model.card.Leader;
import model.card.RegularCard;

import java.util.ArrayList;

public enum NeutralRegularCardsData {
    CARDS_NAME("card name", "card explanation", "ability name", false, 0, 1);
    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;

    NeutralRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
    }

    private static RegularCard createLeader(String leaderName) {
        return null;
    }

    public static ArrayList<RegularCard> getAllLeader() {
        return null;
    }
}