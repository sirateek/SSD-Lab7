module com.example.intelij {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.intelij to javafx.fxml;
    exports com.example.intelij;
}