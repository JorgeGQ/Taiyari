package taiyari;

import java.sql.*;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class ConexionBD {
    private static Connection con = null;

    public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3308/Taiyari";
        String usuario = "root";
        String clave = "1234";

        try {
            con = DriverManager.getConnection(url, usuario, clave);
            return con;
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    
    public static Connection getConexion() {
        return con;
    }
    
    
}


