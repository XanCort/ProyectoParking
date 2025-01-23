package com.example.proyectoparking;

import com.example.proyectoparking.utils.Constantes;
import com.example.proyectoparking.utils.PantallaUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PantallaUtils.showEstaPantalla(stage, Constantes.PAGINA_INICIO.getDescripcion(),Constantes.TITULO_INICIO.getDescripcion(),600,450);
    }

    public static void main(String[] args) {
        launch();
    }
}