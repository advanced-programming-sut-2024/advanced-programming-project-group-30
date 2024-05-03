package enums.cardsData;

import model.card.RegularCard;

import java.util.ArrayList;

public enum EmpireNilfgaardianRegularCardsData {
    CARDS_NAME("card name", "card explanation", "ability name", false, 0, 1);

    private final String name;
    private final String explanation;
    private final String abilityName;
    private final boolean isHero;
    private final int point;
    private final int numberOfCard;

    EmpireNilfgaardianRegularCardsData(String name, String explanation, String abilityName, boolean isHero, int point, int numberOfCard) {
        this.name = name;
        this.explanation = explanation;
        this.abilityName = abilityName;
        this.isHero = isHero;
        this.point = point;
        this.numberOfCard = numberOfCard;
    }

    private static RegularCard createCard(EmpireNilfgaardianRegularCardsData data) {
        return null;
    }

    public static ArrayList<RegularCard> getAllRegularCard() {
        return null;
    }
}
