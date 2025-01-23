package com.example.proyectoparking.controlador;

import com.example.proyectoparking.modelo.Coche;
import com.example.proyectoparking.utils.AlertaUtils;
import com.example.proyectoparking.utils.Constantes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Pantalla que muestra un temporizador indicando el tiempo que lleva el vehículo en el parking
 */
public class ControllerPantallaTresTimer {

    private Stage stage;
    private int segundosTranscurridos = 0;

    private Thread contador;
    private ControllerPantallaInicioParking pantallaInicioParking;
    private Coche cocheAsociado;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Coche getCocheAsociado() {
        return cocheAsociado;
    }

    public void setCocheAsociado(Coche cocheAsociado) {
        this.cocheAsociado = cocheAsociado;
    }

    public void setPantallaInicioParking(ControllerPantallaInicioParking pantallaInicioParking) {
        this.pantallaInicioParking = pantallaInicioParking;
    }

    @FXML
    private Button bttnSalir;

    @FXML
    private Label labelHoras;

    @FXML
    private Label labelMinutos;

    @FXML
    private Label labelSegundos;

    /**
     * Método que se ejecuta al inicializar la ventana
     * Establece e inicia el hilo(Thread) que sirve de temporizador para cada coche
     */
    @FXML
    public void initialize() {
        //Establecemos que si se cierra la ventana de forma manual se ejecute lo mismo que pulsando el botón "salir"
        Platform.runLater(() -> {
            stage.setOnCloseRequest(e -> {
                sacarCoche();
            });
        });
        //Iniciamos el contador y lo establecemos Daemon para que se detenga si ordenamos que el programa se cierre
        contador = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    segundosTranscurridos++;
                    int horas = segundosTranscurridos / 3600;
                    int minutos = segundosTranscurridos / 60;
                    int segundos = segundosTranscurridos % 60;

                    // Actualizar Labels
                    Platform.runLater(() -> {
                        labelHoras.setText(String.format("%02d", horas));
                        labelMinutos.setText(String.format("%02d", minutos));
                        labelSegundos.setText(String.format("%02d", segundos));
                    });

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Constantes.ALERTA_COCHE_RETIRADO.getDescripcion());
                }
            }
        });
        contador.setDaemon(true);
        contador.start();
    }

    /**
     * Método que se ejecuta al pulsar el botón Salir
     * Muestra una notificacion y cierra la ventana
     * @param event
     */
    @FXML
    void onSalirButtonClick(ActionEvent event) {
        sacarCoche();
        ((Stage) bttnSalir.getScene().getWindow()).close();
    }

    /**
     * Método que se utiliza para informar de que el vehículo se ha retirado.
     * Convierte los temporizadores en horas para hacer el calculo del precio
     * Y notifica la salida al controlador principal
     */
    private void sacarCoche() {
        double tiempoTotal = (Double.parseDouble(labelHoras.getText()) * 3600) +
                (Double.parseDouble(labelMinutos.getText()) * 60) +
                (Double.parseDouble(labelSegundos.getText())) / 3600;
        AlertaUtils.showAlertaRetirado(tiempoTotal);
        notificarSalida();
    }

    /**
     * Método que notifica la salida al controlador principal
     */
    private void notificarSalida() {
        pantallaInicioParking.actualizarLabel();
        pantallaInicioParking.removeCoche(cocheAsociado);
        contador.interrupt();
    }

    /**
     * Método que notifica de que el vehículo ha sido expulsado.
     * Para el temporizador y cierra la ventana
     */
    public void expulsar() {
        AlertaUtils.showAlertaExpulsion();
        notificarSalida();
        stage.close();
    }
}
