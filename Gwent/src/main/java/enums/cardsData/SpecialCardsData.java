package enums.cardsData;

import model.card.specialcard.SpecialCard;

import java.util.ArrayList;

public enum SpecialCardsData {
    CARDS_NAME(0);

    private final int cardsNumber;

    SpecialCardsData(int cardsNumber) {
        this.cardsNumber = cardsNumber;
    }

    private static SpecialCard createCard(SpecialCardsData specialCardsData) {
        return null;
    }

    public static ArrayList<SpecialCard> getAllSpecialCard() {
        return null;
    }
}