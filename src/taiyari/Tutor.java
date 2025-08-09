package taiyari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Tutor {
     // Atributos del Tutor
    private String noTrabajador;
    private String registradoPor;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private int edad;
    private String telefono;
    private String correo;
    private String grupoTutorado;
    private String area;
    

    // Constructor
    public Tutor(String noTrabajador, String registradoPor,  String nombres, String apellidoP, String apellidoM,
                  int edad, String telefono, String correo, String grupoTutorado,
                  String area) {
        this.noTrabajador = noTrabajador;
        this.registradoPor = registradoPor;
        this.nombres = nombres;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.grupoTutorado = grupoTutorado;
        this.area = area;        
    }

    // Getters y Setters
    public String getnoTrabajador()   { return noTrabajador; }
    public void setnoTrabajador(String noTrabajador) { this.noTrabajador = noTrabajador; }
    
    public String getRegistradoPor()   { return registradoPor; }
    public void setRegistradoPor(String registradoPor) { this.registradoPor = registradoPor; }

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

    public String getGrupoTutorado()       { return grupoTutorado; }
    public void setGrupoTutorado(String grupoTutorado) { this.grupoTutorado = grupoTutorado; }

    public String getArea()        { return area; }
    public void setArea(String area) { this.area = area; }

  
    
    //Método
    public static Tutor obtenerDatosTutor(String noTrabajador) {
        Connection con = ConexionBD.obtenerConexion();
        Tutor tutor = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM tutor WHERE noTrabajador = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, noTrabajador);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tutor = new Tutor(
                        rs.getString("noTrabajador"),
                        rs.getString("registradoPor"),
                        rs.getString("nombres"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("grupoTutorado"),
                        rs.getString("area"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener tutor: " + e.getMessage());
            }
        }
        return tutor;
    }
    
    
}
