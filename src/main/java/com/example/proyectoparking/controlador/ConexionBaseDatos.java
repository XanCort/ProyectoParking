package com.example.proyectoparking.controlador;
import java.sql.*;
public class ConexionBaseDatos {
    public static Connection establecerConexion(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:data/BaseDatosParking.db");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return null;
    }

    // public Usuario selectUserByName(String name){
    public static void recuperarCoches() {
        //Connection conn = ConexionSQLite.conectar();
        String url = "jdbc:sqlite:data/BaseDatosParking.db";
        String query = "SELECT matricula, fechaEntrada FROM coche ";
        // Conexión a la base de datos y ejecución de la consulta
        try {
            Connection conn =
                    DriverManager.getConnection(url);
            PreparedStatement pstmt =
                    conn.prepareStatement(query);
            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();
            // Verificar si el usuario fue encontrado
            while (rs.next()) {
                // Obtener los valores del resultado
                String matricula = rs.getString("matricula");
                String fechaEntrada = rs.getString("fechaEntrada");
                // Mostrar los resultados
                System.out.println("Usuario: " + matricula);
                System.out.println("Contraseña: " + fechaEntrada);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void insertarCoche(){
        // URL de conexión a la base de datos SQLite
        String url = "jdbc:sqlite:data/BaseDatosParking.db";
        //Nombre del archivo de la base de datos SQLite
        // Datos a insertar
        String matricula = "1234BCD";
        String fechaEntrada = "17/08/2016";
        // Conectar a la base de datos y realizar la inserción
        try (Connection conn =
                     DriverManager.getConnection(url)) {
            // Sentencia SQL para insertar una nueva fila
            String sql = "INSERT INTO coche (matricula, fechaEntrada) VALUES (?, ?)";
            // Preparar la sentencia con los valores
            try (PreparedStatement pstmt =
                         conn.prepareStatement(sql)) {
                pstmt.setString(1, matricula);
                pstmt.setString(2, fechaEntrada);
                // Ejecutar la inserción
                int filasAfectadas = pstmt.executeUpdate();
                System.out.println("Filas insertadas: " +
                        filasAfectadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
