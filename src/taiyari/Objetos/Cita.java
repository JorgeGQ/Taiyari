package taiyari.Objetos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import taiyari.ConexionBD;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Cita {
//    Atributos de la Bitacora
    private int idCita;
    private String solicitada_por;
    private String motivo;
    private String fechaHora;
    private String estado;
    private String paciente;
    private String tutor_grupal;
    
//     Constructor
    public Cita(int idCita, String solicitada_por, String motivo, String fechaHora, String estado, String paciente, String tutor_grupal){
        this.idCita = idCita;
        this.solicitada_por = solicitada_por;
        this.motivo = motivo;
        this.fechaHora = fechaHora;
        this.estado = estado;        
        this.paciente = paciente;        
        this.tutor_grupal = tutor_grupal;        
    }

//     Getters y Setters
    public int getIdCita()   { return idCita; }
    public void setIdCita(int idBitacora) { this.idCita = idBitacora; }

    public String getSolicitadaPor()   { return solicitada_por; }
    public void setEmocion(String emocion) { this.solicitada_por = solicitada_por; }

    public String getMotivo()   { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getFechaHora()     { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    
    public String getEstado()   { return estado; }
    public void setEstado(String estado)  { this.estado = estado; }
    
    public String getPaciente()   { return paciente; }
    public void setPaciente(String paciente)  { this.paciente = paciente; }
    
    public String getTutorGrupal()   { return tutor_grupal; }
    public void setTutorGrupal(String tutor_grupal)  { this.tutor_grupal = tutor_grupal; }

    
    
//    Método
    public static Cita obtenerDatosCita(String idCita) {
        Connection con = ConexionBD.obtenerConexion();
        Cita cita = null;

        if (con != null) {
            try {
                String sql = "SELECT * FROM cita WHERE idCita = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, idCita);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cita = new Cita(
                        rs.getInt("idCita"),
                        rs.getString("solicitadaPor"),
                        rs.getString("motivo"),
                        rs.getString("fechaHora"),
                        rs.getString("estado"),
                        rs.getString("paciente"),
                        rs.getString("tutorGrupal"));
                }

                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al obtener la cita: " + e.getMessage());
            }
        }
        return cita;
    }
    
    
}