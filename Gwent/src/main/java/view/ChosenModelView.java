package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.InputStream;

public class ChosenModelView<T> extends Image {
    private final T model;
    private final ImagePattern imagePattern;
    private final DescriptionBox descriptionBox;

    public ChosenModelView(InputStream inputStream, T type, String descriptionTitle, String description) {
        super(inputStream);
        this.model = type;
        this.imagePattern = new ImagePattern(this);
        this.descriptionBox = new DescriptionBox(descriptionTitle,description);
    }

    public T getModel() {
        return model;
    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }

    public DescriptionBox getDescriptionBox() {
        return descriptionBox;
    }
}