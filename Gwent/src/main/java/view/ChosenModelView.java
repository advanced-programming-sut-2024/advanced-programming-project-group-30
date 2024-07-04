package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.InputStream;

public class ChosenModelView<T> extends Image {
    private final T model;
    private final ImagePattern imagePattern;
    private final String descriptionTitle;
    private final String description;

    public ChosenModelView(InputStream inputStream, T type, String descriptionTitle, String description) {
        super(inputStream);
        this.model = type;
        this.imagePattern = new ImagePattern(this);
        this.descriptionTitle  = descriptionTitle;
        this.description = description;
    }

    public T getModel() {
        return model;
    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }

    public String getDescriptionTitle() {
        return descriptionTitle;
    }

    public String getDescription() {
        return description;
    }
}