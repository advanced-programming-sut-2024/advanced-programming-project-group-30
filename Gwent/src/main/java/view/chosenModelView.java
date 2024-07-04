package view;

import javafx.scene.image.Image;

import java.io.InputStream;

public class chosenModelView<T> extends Image {
    private final T model;
    private final String descriptionTitle;
    private final String description;

    public chosenModelView(InputStream inputStream, T type, String descriptionTitle, String description) {
        super(inputStream);
        this.model = type;
        this.descriptionTitle  = descriptionTitle;
        this.description = description;
    }

    public T getModel() {
        return model;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public String getDescription() {
        return description;
    }
}