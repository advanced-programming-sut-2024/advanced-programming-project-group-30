package view;

import enums.FactionType;
import enums.cardsData.CardData;
import javafx.scene.image.Image;

import java.io.InputStream;

public class LargeCardView<T> extends Image {
    private final T type;
    private final String descriptionTitle;
    private final String description;

    public LargeCardView(InputStream inputStream, T type, String descriptionTitle, String description) {
        super(inputStream);
        if (!(type instanceof CardData) && !(type instanceof FactionType)) throw new RuntimeException("invalid type");
        this.type = type;
        this.descriptionTitle  = descriptionTitle;
        this.description = description;
    }

    public T getType() {
        return type;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public String getDescription() {
        return description;
    }
}