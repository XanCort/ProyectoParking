package com.example.proyectoparking.utils;
public enum Constantes {
    // Pantallas y vistas
    PAGINA_INICIO("plazas_view.fxml"),
    PAGINA_ADMINISTRADOR("admin_view.fxml"),
    PAGINA_REGISTRO("registro_view.fxml"),
    PAGINA_APARCADO("aparcado_view.fxml"),

    // Títulos de ventanas
    TITULO_INICIO("Parking!"),
    TITULO_ADMINISTRADOR("Vista Administrador"),
    TITULO_REGISTRO("pantallaRegistro"),
    TITULO_APARCADO("Temporizador"),

    // Mensajes de alerta
    ALERTA_MATRICULA_INCORRECTA("Matrícula incorrecta"),
    MENSAJE_MATRICULA_INVALIDA("La matrícula introducida no es válida por favor introduzca una con el formato estándar español NNNNCCC"),
    ALERTA_COCHE_RETIRADO("Coche retirado"),
    MENSAJE_COCHE_RETIRADO("El precio para retirar su coche ha sido de {0}€.\nGracias por confiar en nosotros, esperamos que vuelva pronto"),
    ALERTA_EXPULSION("EXPULSADO"),
    MENSAJE_EXPULSION("Su coche ha sido retirado del parking! \nPara obtener más información póngase en contacto con el teléfono que le proporcionaremos a continuación"),

    // Otros textos
    ESTADO_COMPLETO("COMPLETO"),
    ESTADO_LIBRE("LIBRE"),

    // Estilos
    ESTILO_FULL("full");

    private final String descripcion;

    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
