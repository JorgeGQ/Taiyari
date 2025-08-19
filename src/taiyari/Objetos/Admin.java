package taiyari.Objetos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import taiyari.ConexionBD;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Admin {
   // Atributos del Admnistrador
    private String idAdmin;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private int edad;
    
    // Constructor
    public Admin(String idAdmin, String nombres, String apellidoP, String apellidoM, int edad){
        this.idAdmin = idAdmin;
        this.nombres = nombres;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;        
    }

    // Getters y Setters
    public String getIdAdmin()   { return idAdmin; }
    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }

    public String getNombres()     { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidoP()   { return apellidoP; }
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }

    public String getApellidoM()   { return apellidoM; }
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }

    public int getEdad()           { return edad; }
    public void setEdad(int edad)  { this.edad = edad; }

    
    
    //Método
    public static Admin obtenerDatosAdmin(String idAdmin) {
        Connection con = ConexionBD.obtenerConexion();
        Admin admin = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM administrador WHERE idAdmin = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, idAdmin);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    admin = new Admin(
                        rs.getString("idAdmin"),
                        rs.getString("nombres"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getInt("edad"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener admin: " + e.getMessage());
            }
        }
        return admin;
    }
    
    
    public static void eliminarAdmin(String nombreUsuario) {
    int confirmacion = JOptionPane.showConfirmDialog(null,
        "¿Estás seguro de que deseas eliminar al usuario y su administrador asociado?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        Connection con = ConexionBD.obtenerConexion();

        if (con != null) {
            try {
                con.setAutoCommit(false); // Inicia la transacción

                // Buscar el idAdmin asociado al nombreUsuario
                String sqlBuscar = "SELECT idAdmin FROM usuario WHERE nombreUsuario = ?";
                PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
                psBuscar.setString(1, nombreUsuario);
                ResultSet rs = psBuscar.executeQuery();

                String idAdmin = null;
                if (rs.next()) {
                    idAdmin = rs.getString("idAdmin");
                }

                rs.close();
                psBuscar.close();

                if (idAdmin != null) {
                    // Eliminar el usuario
                    String sqlEliminarUsuario = "DELETE FROM usuario WHERE nombreUsuario = ?";
                    PreparedStatement psEliminarUsuario = con.prepareStatement(sqlEliminarUsuario);
                    psEliminarUsuario.setString(1, nombreUsuario);
                    psEliminarUsuario.executeUpdate();
                    psEliminarUsuario.close();

                    // Eliminar el administrador
                    String sqlEliminarAdmin = "DELETE FROM administrador WHERE idAdmin = ?";
                    PreparedStatement psEliminarAdmin = con.prepareStatement(sqlEliminarAdmin);
                    psEliminarAdmin.setString(1, idAdmin);
                    psEliminarAdmin.executeUpdate();
                    psEliminarAdmin.close();

                    con.commit(); // Confirma la transacción
                    JOptionPane.showMessageDialog(null, "✅ Usuario y administrador eliminados correctamente.");
                } else {
                    con.rollback(); // Revierte si no se encuentra el admin
                    JOptionPane.showMessageDialog(null, "⚠️ No se encontró el administrador asociado al usuario.");
                }

                con.close();
            } catch (SQLException e) {
                try {
                    con.rollback(); // Revierte en caso de error
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "❌ Error al revertir cambios: " + ex.getMessage());
                }
                JOptionPane.showMessageDialog(null, "❌ Error al eliminar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "❌ No se pudo establecer conexión con la base de datos.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Operación cancelada.");
    }
}
    
}
