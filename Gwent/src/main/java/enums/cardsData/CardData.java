package enums.cardsData;

import javafx.scene.image.Image;
import view.ChosenModelView;

public interface CardData {
    Image getLgImage();

    Image getSmImage();

    ChosenModelView getChooseModelView();
}