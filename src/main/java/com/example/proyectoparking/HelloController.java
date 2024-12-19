package com.example.proyectoparking;

import com.example.proyectoparking.modelo.Coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {

    private ArrayList<Coche> coches = new ArrayList<>();

    @FXML
    private Button bttnAparcar;

    @FXML
    private TextField campoMatricula;

    @FXML
    private Label labelMatricula;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        coches.add(new Coche(campoMatricula.getText()));
    }


}
