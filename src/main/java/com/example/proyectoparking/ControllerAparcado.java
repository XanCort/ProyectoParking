package com.example.proyectoparking;

import com.example.proyectoparking.modelo.Coche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class ControllerAparcado {

    @FXML
    private Button bttnSalir;

    @FXML
    private Spinner<Integer> spinnerMinutos;

    @FXML
    private Spinner<Integer> spinnerSegundos;

    @FXML
    public void initialize(){
        spinnerMinutos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0));
        spinnerSegundos.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0));

        Thread contador = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        spinnerSegundos.increment(1);
                        System.out.println("Vuelta");
                        if(spinnerSegundos.getValue().equals(60)){
                            spinnerSegundos.decrement(60);
                            spinnerMinutos.increment(1);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }});
        contador.setDaemon(true);
        contador.start();


    }


    @FXML
    void onSalirButtonClick(ActionEvent event) {

    }




}
