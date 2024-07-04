module Gwent {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;

    exports view;
    opens view to javafx.fxml;
}