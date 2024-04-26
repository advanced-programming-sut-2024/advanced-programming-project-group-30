module com.example.gwent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gwent to javafx.fxml;
    exports com.example.gwent;
}