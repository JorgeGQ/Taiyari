package  taiyari.Objetos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import taiyari.ConexionBD;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Psicologo {
    // Atributos del Psicologo
    private String noPsicologo;
    private String cedulaProfesional;
    private String ingresadoPor;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private int edad;
    private String telefono;
    private String correo;

    // Constructor
    public Psicologo(String noPsicologo,String cedulaProfesional, String ingresadoPor, String nombres, String apellidoP, String apellidoM,
                  int edad, String telefono, String correo){
        this.noPsicologo = noPsicologo;
        this.cedulaProfesional = cedulaProfesional;
        this.ingresadoPor = ingresadoPor;
        this.nombres = nombres;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
    }
    

    // Getters y Setters
    public String getNoPsicologo()   { return noPsicologo; }
    public void setNoPsicologo(String noPsicologo) { this.noPsicologo = noPsicologo; }
    
    public String getCedulaProfesional()   { return cedulaProfesional; }
    public void setCedulaProfesional(String cedulaProfesional) { this.cedulaProfesional = cedulaProfesional; }
   
    public String getIngresadoPor()   { return ingresadoPor; }
    public void setIngresadoPor(String ingresadoPor) { this.ingresadoPor = ingresadoPor; }

    public String getNombres()     { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidoP()   { return apellidoP; }
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }

    public String getApellidoM()   { return apellidoM; }
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }

    public int getEdad()           { return edad; }
    public void setEdad(int edad)  { this.edad = edad; }

    public String getTelefono()    { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo()      { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

        
    //Método
    public static Psicologo obtenerDatosPsicologo(String matricula){
        Connection con = ConexionBD.obtenerConexion();
        Psicologo psicologo = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM psicologo WHERE noPsicologo = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, matricula);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    psicologo = new Psicologo(
                        rs.getString("noPsicologo"),
                        rs.getString("cedulaProfesional"),
                        rs.getString("ingresadoPor"),
                        rs.getString("nombres"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener psicologo: " + e.getMessage());
            }
        }
        return psicologo;
    }
    
    
}
