package model;

import enums.cardsData.CardData;
import enums.cardsData.SpecialCardsData;
import view.PreGameCardView;

import java.util.Comparator;

public class CardComparator implements Comparator<CardData> {
    private final static CardComparator cardComparator = new CardComparator();

    private CardComparator() {
    }

    public static CardComparator getCardComparator() {
        return cardComparator;
    }

    @Override
    public int compare(CardData a, CardData b) {
        if (a instanceof SpecialCardsData) {
            if (b instanceof SpecialCardsData)
                return a.toString().compareTo(b.toString());
            else return -1;
        }
        if (b instanceof SpecialCardsData) return 1;
        int pointCompare = b.getPoint() - a.getPoint();
        int nameCompare = b.toString().compareTo(a.toString());
        return (pointCompare == 0) ? nameCompare : pointCompare;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
