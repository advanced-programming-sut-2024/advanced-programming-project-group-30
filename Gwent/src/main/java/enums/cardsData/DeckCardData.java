package enums.cardsData;

import enums.Ability;

public interface DeckCardData extends CardData {
    int getNumber();

    Ability getAbility();
}