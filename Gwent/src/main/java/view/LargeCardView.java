package view;

import enums.FactionType;
import enums.cardsData.CardData;
import javafx.scene.image.Image;

import java.io.InputStream;

public class LargeCardView<T> extends Image {
    private final T type;

    public LargeCardView(InputStream i, T type) {
        super(i);
        if (!(type instanceof CardData) && !(type instanceof FactionType)) throw new RuntimeException("invalid type");
        this.type = type;
    }

    public T getType() {
        return type;
    }
}