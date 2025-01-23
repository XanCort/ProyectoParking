package com.example.proyectoparking.controlador;

import com.example.proyectoparking.modelo.Coche;
import com.example.proyectoparking.utils.AlertaUtils;
import com.example.proyectoparking.utils.Constantes;
import com.example.proyectoparking.utils.PantallaUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Pantalla de registro de vehículos.
 * Pantalla intermedia que sirve para introducir la matrícula del vehículo, se comprueba su validez y se notifica al controlador principal.
 * Despues lanza la pantalla de timer.
 */
public class ControllerPantallaDosRegistroDatos {
    @FXML
    private Parent root;

    private Stage stage;

    private ControllerPantallaInicioParking controladorInicio;

    @FXML
    private Button btnEntrar;

    @FXML
    private TextField txtMatricula;

    /**
     * Método que se lanza al hacer click en el botón entrar.
     * Comprueba si la matrícula es válida y lanza la siguiente pantalla
     * @param event
     * @throws IOException
     */
    @FXML
    void bttnEntrarOnClick(ActionEvent event) throws IOException {
        Coche cocheRegistrado = new Coche(txtMatricula.getText());
        if (!cocheRegistrado.matriculaValida()) {
            //Si no es válida se lanza una alerta notificando del modelo que debe seguir
            AlertaUtils.showAlertaInformacion(
                    Constantes.ALERTA_MATRICULA_INCORRECTA.getDescripcion(),
                    Constantes.MENSAJE_MATRICULA_INVALIDA.getDescripcion()
            );
        } else {
            controladorInicio.addCoche(cocheRegistrado);
            Stage stage = PantallaUtils.cerrarEstaPantalla(btnEntrar);
            FXMLLoader fxmlLoader = PantallaUtils.showEstaPantalla(
                    stage,
                    Constantes.PAGINA_APARCADO.getDescripcion(),
                    Constantes.TITULO_APARCADO.getDescripcion(),
                    1000,
                    600
            );
            //Tras crear la nueva pantalla le asociamos el controlador principal para que pueda notificarle cuando el vehículo abandona el parking
            ControllerPantallaTresTimer c = fxmlLoader.getController();
            c.setCocheAsociado(cocheRegistrado);
            c.setPantallaInicioParking(controladorInicio);
            controladorInicio.addTimer(c);
            c.setStage(stage);
        }
    }

    /**
     * Método que se ejecuta al inicializar la ventana
     * Establece una notificacion al controlador principal en caso de que esta se cierre
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            stage = (Stage) root.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                controladorInicio.actualizarLabel();
            });
        });
    }

    /**
     * Establecemos el controlador principal para poder notificarle cambios
     * @param c controlador principal
     */
    public void setControladorInicio(ControllerPantallaInicioParking c) {
        this.controladorInicio = c;
    }
}
