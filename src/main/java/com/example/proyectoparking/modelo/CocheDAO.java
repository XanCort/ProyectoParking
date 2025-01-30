package com.example.proyectoparking.modelo;

import com.example.proyectoparking.controlador.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CocheDAO {
    boolean insertarCoche();
    Coche buscarCoche(int id);
    boolean retirarCoche();


    static ArrayList<Coche> recuperarTodosCoches() {
        String query = "SELECT matricula, fechaEntrada FROM coche ";
        Connection conexion = ConexionBaseDatos.establecerConexion();
        ArrayList<Coche> coches = new ArrayList<>();
        if(conexion!=null){
            try{
                PreparedStatement pstmt = conexion.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    coches.add(new Coche(rs.getString("matricula"),rs.getString("fechaEntrada")));
                }


            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }
        return coches;
    }
    static ArrayList<Coche> recuperarCochesActivos() {
        String query = "SELECT * FROM coche WHERE fechaSalida IS NULL ";
        Connection conexion = ConexionBaseDatos.establecerConexion();
        ArrayList<Coche> coches = new ArrayList<>();
        if(conexion!=null){
            try{
                PreparedStatement pstmt = conexion.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    coches.add(new Coche(rs.getString("matricula"),rs.getString("fechaEntrada")));
                }


            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }
        return coches;
    }

}
