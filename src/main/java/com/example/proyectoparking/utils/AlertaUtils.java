package com.example.proyectoparking.utils;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import static com.example.proyectoparking.utils.Constantes.*;

public class AlertaUtils {
    // Método para mostrar alerta informativa
    public static void showAlertaInformacion(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para mostrar información sobre la retirada del coche
    public static void showAlertaRetirado(double tiempo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constantes.ALERTA_COCHE_RETIRADO.getDescripcion());
        alert.setHeaderText(null);
        alert.setContentText(Constantes.MENSAJE_COCHE_RETIRADO.getDescripcion()
                .replace("{0}", String.valueOf(CalculoUtils.calcularPrecio(tiempo))));
        alert.show();
    }

    // Método para mostrar alerta de expulsión
    public static void showAlertaExpulsion() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(Constantes.ALERTA_EXPULSION.getDescripcion());
        alert.setHeaderText(null);
        alert.setContentText(Constantes.MENSAJE_EXPULSION.getDescripcion());
        alert.show();
    }

    public static boolean showConfirmacionExpulsion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(ALERTA_CONFIRMACION_EXPULSION_TITULO.getDescripcion());
        alert.setHeaderText(null);
        alert.setContentText(MENSAJE_CONFIRMACION_EXPULSION.getDescripcion());
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }

    public static boolean showConfirmacionRegistro(String matricula){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(ALERTA_CONFIRMACION_REGISTRO_TITULO.getDescripcion());
        alert.setHeaderText(null);
        alert.setContentText(MENSAJE_CONFIRMACION_REGISTRO.getDescripcion().replace("{0}",matricula));
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }
}
