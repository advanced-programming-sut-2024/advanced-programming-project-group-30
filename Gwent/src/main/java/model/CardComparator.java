package model;

import enums.cardsData.DeckCardData;
import enums.cardsData.RegularCardData;
import enums.cardsData.SpecialCardsData;

import java.util.Comparator;

public class CardComparator implements Comparator<DeckCardData> {
    private final static CardComparator cardComparator = new CardComparator();

    private CardComparator() {
    }

    public static CardComparator getCardComparator() {
        return cardComparator;
    }

    @Override
    public int compare(DeckCardData a, DeckCardData b) {
        if (a instanceof SpecialCardsData) {
            if (b instanceof SpecialCardsData)
                return a.toString().compareTo(b.toString());
            else return -1;
        }
        if (b instanceof SpecialCardsData) return 1;
        int pointCompare = ((RegularCardData) b).getPoint() - ((RegularCardData) a).getPoint();
        int nameCompare = b.toString().compareTo(a.toString());
        return (pointCompare == 0) ? nameCompare : pointCompare;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}