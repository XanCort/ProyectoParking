package com.example.proyectoparking;

import com.example.proyectoparking.modelo.Coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerPlazas {

    private ArrayList<Coche> coches = new ArrayList<>();
    private int libres;

    @FXML
    private Button bttnEntrar;

    @FXML
    private Label labelContador;

    @FXML
    private Label labellLibre;

    @FXML
    public void initialize(){
        libres = 10;
        labelContador.setText("00"+libres);
    };

    @FXML
    void onButtonEntrar(ActionEvent event) throws IOException {
        //Creo y lanzo el nuevo controlador
        libres =libres-1;
        labelContador.setText(libres+"");
        if(libres==0){
            bttnEntrar.setDisable(true);
            labellLibre.setText("COMPLETO");
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registro_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.show();
    }
    void actualizarLabel(){
        libres++;
        labelContador.setText(libres+"");
    }

}