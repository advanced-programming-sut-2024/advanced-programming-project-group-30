module Gwent {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires com.google.gson;
    requires jdk.compiler;
    requires java.sql;
    requires java.naming;

    exports view;
    exports model;
    exports enums;
    opens view to javafx.fxml, com.google.gson;
    opens model to com.google.gson;
    opens model.card to com.google.gson;
    opens model.ability to com.google.gson;
    opens enums to com.google.gson;
    exports network;
    opens network to com.google.gson;
}