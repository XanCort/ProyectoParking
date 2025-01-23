package com.example.proyectoparking.modelo;

import java.time.LocalDateTime;

public class Coche {
    //Patrón de las matrículas españolas
    private static final String PATRON_MATRICULA = "^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$";



    private String matricula;
    private LocalDateTime entrada;


    public Coche(String matricula) {
        this.matricula = matricula;
        entrada = LocalDateTime.now();
    }
    //Comprobación de que la matrícula tiene el formato correcto y no es nula
    public boolean matriculaValida(){
        return matricula.matches(PATRON_MATRICULA) && matricula!=null;
    };

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }


}
