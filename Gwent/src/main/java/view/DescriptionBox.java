package view;

import enums.CssAddress;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DescriptionBox extends VBox {
    private final Label title = new Label();
    private final ArrayList<Label> description = new ArrayList<>();

    public DescriptionBox(String descriptionTitle, String description) {
        this.title.setText(descriptionTitle);
        this.getChildren().add(title);
        String[] lines = description.split("\\n");
        for (String line : lines)
            this.description.add(new Label(line));
        this.getChildren().addAll(this.description);
        setStyle();
    }

    private void setStyle() {
        this.getStyleClass().add(CssAddress.DESCRIPTION_BOX.getStyleClass());
        this.title.getStyleClass().add(CssAddress.DESCRIPTION_BOX_TITLE.getStyleClass());
        for (Label line : this.description)
            line.getStyleClass().add(CssAddress.DESCRIPTION_BOX_TEXT.getStyleClass());
    }
}