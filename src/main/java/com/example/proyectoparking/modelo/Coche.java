package com.example.proyectoparking.modelo;

import com.example.proyectoparking.controlador.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Coche implements CocheDAO {
    //Patrón de las matrículas españolas
    private static final String PATRON_MATRICULA = "^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$";



    private String matricula;
    private LocalDateTime entrada;


    public Coche(String matricula) {
        this.matricula = matricula;
        entrada = LocalDateTime.now();
    }

    public Coche(String matricula, String entrada){
        this.matricula=matricula;
        this.entrada = LocalDateTime.parse(entrada);
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


    @Override
    public boolean insertarCoche() {
        Connection conexion = ConexionBaseDatos.establecerConexion();
        String query = "INSERT INTO coche (matricula, fechaEntrada) VALUES (?, ?)";

        try (PreparedStatement pstmt =
                     conexion.prepareStatement(query)) {
            pstmt.setString(1, this.matricula);
            pstmt.setString(2, this.entrada.toString());
            // Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas==1){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Coche buscarCoche(int id) {
        return null;
    }

    @Override
    public boolean retirarCoche() {
        Connection conexion = ConexionBaseDatos.establecerConexion();
        String query = "UPDATE coche SET fechaSalida = ? WHERE matricula = ?";
        System.out.println(this.matricula);
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, LocalDateTime.now().toString()); // Establece la fecha de salida actual
            pstmt.setString(2, this.matricula); // Filtra por la matrícula del coche actual

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas == 1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar fecha de salida", ex);
        }
    }


}
