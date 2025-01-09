package com.example.proyectoparking;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ControllerRegistro {
    /*
    public ControllerRegistro() {
        super();
        ArrayList<Stage> plazas = new ArrayList<>();
        Thread recuento =  new Thread(() -> {
            while (true){
                for(int i=0;i<plazas.size())
            }
        });
    }
    */
    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtMatricula;

    @FXML
    void bttnEntrarOnClick(ActionEvent event) throws IOException {
        System.out.println("Si home si");

        Stage stage = (Stage)btnEntrar.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aparcado_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Registro");
        stage.setScene(scene);
        stage.show();


    }

}
