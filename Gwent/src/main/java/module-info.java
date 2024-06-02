module Gwent {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;

    exports view;
    opens view to javafx.fxml;
}