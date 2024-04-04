module com.juanda.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.juanda.example to javafx.fxml;
    opens com.juanda.example.controller to javafx.fxml;
    exports com.juanda.example to javafx.graphics;

}
