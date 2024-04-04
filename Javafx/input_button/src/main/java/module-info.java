module com.juanda.frame {
    requires javafx.controls;
    requires javafx.fxml;

    
    opens com.juanda.frame to javafx.fxml;
    opens com.juanda.frame.controllers to  javafx.fxml;
    exports com.juanda.frame;
}
