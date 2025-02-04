package com.example.proyectoparking.modelo;

import com.example.proyectoparking.controlador.ConexionBaseDatos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CocheDAOTest {
    Connection c;
    @BeforeEach
    void comprobarConexion(){
        c = ConexionBaseDatos.establecerConexion();
        assertNotNull(c);
    }

    @Test
    void insertarCoche() {
        Coche c = new Coche("1234LKL");
        assertTrue(c.insertarCoche());
        //assertFalse(c.insertarCoche());
    }


    @Test
    void insertarCocheTotal(){
        Connection conexion = ConexionBaseDatos.establecerConexion();
        String query = "INSERT INTO coche (matricula, fechaEntrada) VALUES (?, ?)";

        try (PreparedStatement pstmt =
                     conexion.prepareStatement(query)) {
            pstmt.setString(1, "2456CDB");
            pstmt.setString(2, LocalDateTime.now().toString());
            int filasAfectadas = pstmt.executeUpdate();
            assertNotEquals(0,filasAfectadas);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @AfterEach
    void recuperarTodosCoches() throws SQLException {

        ArrayList<Coche> coches = CocheDAO.recuperarTodosCoches();
        assertNotNull(coches);
        assertEquals(true, coches.size()>0);
        assertEquals("1111DCD", coches.get(0).getMatricula());
        //assertEquals("3456DCD", coches.get(0).getMatricula());
        c.close();

    }
}