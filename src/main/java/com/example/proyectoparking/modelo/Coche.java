package com.example.proyectoparking.modelo;

import java.time.LocalDateTime;

public class Coche {
    private String matricula;
    private LocalDateTime entrada;


    public Coche(String matricula) {
        this.matricula = matricula;
        entrada = LocalDateTime.now();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
