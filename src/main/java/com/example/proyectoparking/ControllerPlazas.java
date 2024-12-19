package com.example.proyectoparking;

import com.example.proyectoparking.modelo.Coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

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
    void onButtonEntrar(ActionEvent event) {
        //Creo y lanzo el nuevo controlador
        libres =libres-1;
        labelContador.setText(libres+"");
        if(libres==0){
            bttnEntrar.setDisable(true);
            labellLibre.setText("COMPLETO");
        }
    }


}