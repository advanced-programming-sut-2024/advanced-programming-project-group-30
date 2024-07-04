package enums.cardsData;

import view.ChosenModelView;

public interface DeckCardData extends CardData {
    int getNumber();

    ChosenModelView getChooseModelView();
}
