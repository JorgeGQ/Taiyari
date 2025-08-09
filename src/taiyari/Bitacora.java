//package taiyari;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.
//
//public class Bitacora {
//    Atributos de la Bitacora
//    private String idBitacora;
//    private String fechaHora;
//    private String emocion;
//    private String anotaciones2;
//    private String alumno2;
//    
//     Constructor
//    public Bitacora(String idBitacora, String fechaHora, String emocion String anotaciones2, String alumno2){
//        this.idBitacora = idBitacora;
//        this.fechaHora = fechaHora;
//        this.emocion = emocion;
//        this.anotaciones2 = anotaciones2;
//        this.alumno2 = alumno2;        
//    }
//
//     Getters y Setters
//    public String getIdBitacora()   { return idBitacora; }
//    public void setBitacora(String matricula) { this.idBitacora = idBitacora; }
//
//    public String getFechaHora()     { return fechaHora; }
//    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
//
//    public String getEmocion()   { return emocion; }
//    public void setEmocion(String emocion) { this.emocion = emocion; }
//
//    public String getAnotaciones2()   { return anotaciones2; }
//    public void setAnotaciones2(String anotaciones2) { this.anotaciones2 = anotaciones2; }
//
//    public int getAlumno()           { return Alumno; }
//    public void setAlumno(String Alumno)  { this.alumno = alumno; }
//
//    
//    
//    Método
//    public static Admin obtenerDatosAdmin(String idAdmin) {
//        Connection con = ConexionBD.obtenerConexion();
//        Admin admin = null;
//
//        if (con != null) {
//            try {
//                String sql = "SELECT * FROM admin WHERE idAdmin = ?";
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, idAdmin);
//
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    admin = new Admin(
//                        rs.getString("idAdmin"),
//                        rs.getString("nombres"),
//                        rs.getString("apellidoP"),
//                        rs.getString("apellidoM"),
//                        rs.getInt("edad"));
//                }
//
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (SQLException e) {
//                System.out.println("❌ Error al obtener la bitacora: " + e.getMessage());
//            }
//        }
//        return admin;
//    }
//    
//    
//}