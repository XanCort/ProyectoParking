package com.example.proyectoparking.controlador;

import com.example.proyectoparking.modelo.Coche;
import com.example.proyectoparking.utils.Constantes;
import com.example.proyectoparking.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Pantalla principal de la aplicación.
 * Esta pantalla estará siempre activa mostrando el número de plazas libres.
 * Desde ella se puede introducir un vehículo o entrar en el modo administrador para otras gestiones
 */
public class ControllerPantallaInicioParking {

    //Lista de pantallas de temporizador actualmente activas, o lo que es lo mismo de vehículos en el parking a los que se les está contabilizando el tiempo
    private ArrayList<ControllerPantallaTresTimer> listaTimers = new ArrayList<>();
    private ArrayList<Coche> coches = new ArrayList<>();
    private int libres;
    private ControllerAdminView pantallaAdmin;

    @FXML
    private Button bttnEntrar;

    @FXML
    private Button bttnAdmin;

    @FXML
    private Label labelContador;

    @FXML
    private Label labellLibre;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Establece el número de plazas disponibles
     */
    @FXML
    public void initialize() {
        libres = 9;
        actualizarLabel();
    }

    /**
     * Método que se ejecuta al pulsar el botón de modo administrador
     * Lanza la ventana del modo administrador
     * @param event el evento del botón
     * @throws IOException
     */
    @FXML
    void openAdminMode(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = PantallaUtils.showEstaPantalla(
                stage,
                Constantes.PAGINA_ADMINISTRADOR.getDescripcion(),
                Constantes.TITULO_ADMINISTRADOR.getDescripcion(),
                600,
                600
        );
        pantallaAdmin = fxmlLoader.getController();
        pantallaAdmin.setControllerInicio(this);
    }

    /**
     * Método que se ejecuta al pulsar el botón entrar.
     * Lanza la ventana de registro del vehículo y reduce en 1 el número de plazas libres del parking
     * @param event
     * @throws IOException
     */
    @FXML
    void onButtonEntrar(ActionEvent event) throws IOException {
        libres = libres - 1;
        //Modificamos el "cartel" que indica el número de vacantes
        setTextoLableLibres();
        //Si quedan 0 epacios libres, deshabilitamos el botón de entrar para no aceptar más vehiculos y cambiamos el texto a rojo
        if (libres == 0) {
            bttnEntrar.setDisable(true);
            labellLibre.setText(Constantes.ESTADO_COMPLETO.getDescripcion());
            labellLibre.getStyleClass().add(Constantes.ESTILO_FULL.getDescripcion());
            labelContador.getStyleClass().add(Constantes.ESTILO_FULL.getDescripcion());
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = PantallaUtils.showEstaPantalla(
                stage,
                Constantes.PAGINA_REGISTRO.getDescripcion(),
                Constantes.TITULO_REGISTRO.getDescripcion(),
                600,
                600
        );
        ControllerPantallaDosRegistroDatos pantalla2 = fxmlLoader.getController();
        pantalla2.setControladorInicio(this);
    }

    /**
     * Método que se ejecuta cuando se libera un espacio del parking.
     * Puede ejecutarse cuando un coche sale, cuando se cancela el registro o cuando se expulsa el vehículo.
     */
    void actualizarLabel() {
        libres++;
        setTextoLableLibres();
    }

    /**
     * Método puramente estetico, sirve para mostrar siempre 4 digitos en el cartel de vacantes del parking.
     * Comprueba el número de plazas libres y rellena con 0 hacia la izquierda hasta llegar a las 4 cifras
     */
    private void setTextoLableLibres() {
        String plazasLibres = "";
        for (int i = String.valueOf(libres).length(); i < 4; i++) {
            plazasLibres += "0";
        }
        plazasLibres += libres;
        labelContador.setText(plazasLibres);
    }

    /**
     * Método que añade un coche y actualiza la tabla del administrador
     * @param c el coche que se acaba de crear
     */
    void addCoche(Coche c) {
        coches.add(c);
        if (pantallaAdmin != null) {
            actualizarAdmin();
        }
    }

    /**
     * Método que elimina un coche y actualiza la tabla del administrador
     * @param c coche que se acaba de eliminar
     */
    void removeCoche(Coche c) {
        coches.remove(c);
        if (pantallaAdmin != null) {
            actualizarAdmin();
        }
    }

    /**
     * Método que añade a la lista una pantalla timer
     * @param c el controlador de la nueva pantalla que se acaba de crearr
     */
    void addTimer(ControllerPantallaTresTimer c) {
        listaTimers.add(c);
    }

    /**
     * Método que elimina una pantalla timer
     * Elimina tambien su coche asociado
     * @param c
     */
    void removeTimer(ControllerPantallaTresTimer c) {
        listaTimers.remove(c);
        removeCoche(c.getCocheAsociado());
        c.expulsar();
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void setCoches(ArrayList<Coche> coches) {
        this.coches = coches;
    }

    /**
     * Método para actualizar la tabla del administrador
     */
    public void actualizarAdmin() {
        pantallaAdmin.actualizarTabla();
    }

    public ArrayList<ControllerPantallaTresTimer> getListaTimers() {
        return listaTimers;
    }

    public void setListaTimers(ArrayList<ControllerPantallaTresTimer> listaTimers) {
        this.listaTimers = listaTimers;
    }
}
