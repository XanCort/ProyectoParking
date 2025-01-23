module com.example.proyectoparking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoparking to javafx.fxml, javafx.controls, javafx.base;
    exports com.example.proyectoparking;
    exports com.example.proyectoparking.controlador;
    opens com.example.proyectoparking.controlador to javafx.base, javafx.controls, javafx.fxml;
    opens com.example.proyectoparking.modelo to javafx.base, javafx.controls, javafx.fxml;
}