module com.example.proyectoparking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoparking to javafx.fxml, javafx.controls, javafx.base;
    exports com.example.proyectoparking;
}