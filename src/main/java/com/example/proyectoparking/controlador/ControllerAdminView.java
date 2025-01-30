package com.example.proyectoparking.controlador;

import com.example.proyectoparking.modelo.Coche;
import com.example.proyectoparking.modelo.CocheDAO;
import com.example.proyectoparking.utils.Constantes;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Controlador de la ventana de administrador
 * Permite ver a tiempo real los coches que van entrando y saliendo del parking mostrando su matrícula y su hora de entrada
 * Permite expulsar vehículos
 */
public class ControllerAdminView {

    private ControllerPantallaInicioParking controllerInicio;
    private Coche cocheSeleccionado;


    @FXML
    private TableView<Coche> TablaCoches;

    @FXML
    private Button bttnExpulsar;

    @FXML
    private TableColumn<Coche, String> colEntrada;

    @FXML
    private TableColumn<Coche, String> colMatricula;

    /**
     * Método asignado al botón expulsar
     * Recupera el vehículo seleccionado en la tabla, lo busca en la lista de vehículos del controlador principal y le indica que debe eliminarse
     * @param event
     */
    @FXML
    void onBttnExpulsarClick(ActionEvent event) {
        boolean cocheEncontrado = false;
        cocheSeleccionado = TablaCoches.getSelectionModel().getSelectedItem();
        for (ControllerPantallaTresTimer c : controllerInicio.getListaTimers()) {
            System.out.println(Constantes.MENSAJE_EXPULSION.getDescripcion());
            if (c.getCocheAsociado().getMatricula().equals(cocheSeleccionado.getMatricula())) {
                System.out.println(Constantes.ALERTA_EXPULSION.getDescripcion());
                controllerInicio.removeTimer(c);
                cocheEncontrado = true;
                break;
            }
        }
        if(!cocheEncontrado){
            cocheSeleccionado.retirarCoche();
            actualizarTabla();
        }

    }

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Configura la tabla y establece los valores que tiene cuando se crea la ventana
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            colEntrada.setCellValueFactory(new PropertyValueFactory<>("entrada"));
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            actualizarTabla();//controllerInicio.getCoches());
        });
    }

    /**
     * Método para actualizar la tabla.
     * Primero la limpia y luego la rellena con los nuevos datos
     */
    public void actualizarTabla() {
        TablaCoches.getItems().clear();

        for (Coche c : CocheDAO.recuperarCochesActivos()) {
            TablaCoches.getItems().add(c);
        }
    }

    /**
     * Establece el controlador principal para que se pueda recuperar y mandar información
     * @param controllerInicio
     */
    public void setControllerInicio(ControllerPantallaInicioParking controllerInicio) {
        this.controllerInicio = controllerInicio;
    }
}
