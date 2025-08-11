package taiyari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Alumno {
    // Atributos del alumno
    private String matricula;
    private String nombres;
    private String apellidoP;
    private String apellidoM;
    private int edad;
    private String telefono;
    private String correo;
    private String grupo;
    private String area;
    private String carrera;
    private String tutor;

    
    // Constructor
    public Alumno(String matricula, String nombres, String apellidoP, String apellidoM,
                  int edad, String telefono, String correo, String grupo,
                  String area, String carrera, String tutor){
        this.matricula = matricula;
        this.nombres = nombres;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.grupo = grupo;
        this.area = area;
        this.carrera = carrera;
        this.tutor = tutor;
    }

    
    // Getters y Setters
    public String getMatricula()   { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

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

    public String getGrupo()       { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getArea()        { return area; }
    public void setArea(String area) { this.area = area; }

    public String getCarrera()     { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getTutor()       { return tutor; }
    public void setTutor(String tutor) { this.tutor = tutor; }
    
    //Método para Mostrar los del Datos del Alumno.
    public static Alumno obtenerDatosAlumno(String matricula){
        Connection con = ConexionBD.obtenerConexion();
        Alumno alumno = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM alumno WHERE matricula = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, matricula);

                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    alumno = new Alumno(
                        rs.getString("matricula"),
                        rs.getString("nombres"),
                        rs.getString("apellidoP"),
                        rs.getString("apellidoM"),
                        rs.getInt("edad"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("grupo"),
                        rs.getString("area"),
                        rs.getString("carrera"),
                        rs.getString("tutor"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener alumno: " + e.getMessage());
            }
        }
        return alumno;
    }
    
    
}