package taiyari.Objetos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import taiyari.ConexionBD;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Bitacora {
//    Atributos de la Bitacora
    private String idBitacora;
    private String fechaHora;
    private String emocion;
    private String anotaciones_alumno;
    private String pertenece_alumno;
    
//     Constructor
    public Bitacora(String idBitacora, String fechaHora, String emocion, String anotaciones_alumno, String pertenece_alumno){
        this.idBitacora = idBitacora;
        this.fechaHora = fechaHora;
        this.emocion = emocion;
        this.anotaciones_alumno = anotaciones_alumno;
        this.pertenece_alumno = pertenece_alumno;        
    }

//     Getters y Setters
    public String getIdBitacora()   { return idBitacora; }
    public void setIdBitacora(String idBitacora) { this.idBitacora = idBitacora; }

    public String getFechaHora()     { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEmocion()   { return emocion; }
    public void setEmocion(String emocion) { this.emocion = emocion; }

    public String getAnotacionesAlumno()   { return anotaciones_alumno; }
    public void setAnotacionesAnotacionesAlumno(String anotaciones_alumno) { this.anotaciones_alumno = anotaciones_alumno; }

    public String getPerteneceAlumno()   { return pertenece_alumno; }
    public void setPertneceAlumno(String pertenece_alumno)  { this.pertenece_alumno = pertenece_alumno; }

    
    
//    Método
    public static Bitacora obtenerDatosBitacora(String idBitacora) {
        Connection con = ConexionBD.obtenerConexion();
        Bitacora bitacora = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM bitacora WHERE idAdmin = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, idBitacora);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bitacora = new Bitacora(
                        rs.getString("idBitacora"),
                        rs.getString("fechaHora"),
                        rs.getString("emocion"),
                        rs.getString("anotaciones"),
                        rs.getString("alumno"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener la bitacora: " + e.getMessage());
            }
        }
        return bitacora;
    }
    
    
}