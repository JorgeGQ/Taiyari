package taiyari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void setMatricula(String matricula) { this.idAdmin = matricula; }

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
                String sql = "SELECT * FROM admin WHERE idAdmin = ?";
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
    
    
}
