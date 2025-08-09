package taiyari;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class UsuarioDAO {
//  MÉTODOS    
    
    public String iniciarSesionYObtenerRol(String usuario, String contraseña) {
        try (Connection con = ConexionBD.obtenerConexion()) {
            String sql = "SELECT nombreUsuario, rol FROM usuario WHERE nombreUsuario = ? AND contraseña = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            String nombre = rs.getString("nombreUsuario");
            String rol = rs.getString("rol");
            Sesion.setNombreUsuario(nombre);
            Sesion.setRolUsuario(rol);
            return rol;
        } else{
                return null;
            }
        }catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
            return null;
        }
    }

    
    public boolean registrarUsuario(String nombreUsuario, String contraseña, String rol) {
        String sql = "INSERT INTO Usuario (nombreUsuario, contraseña, rol) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            ps.setString(3, rol);
            ps.executeUpdate();
            return true;
        }catch (SQLException ex) {
            System.out.println("Error al registrar usuario: " + ex.getMessage());
            return false;
        }
    }
    
    
    public static boolean insertarCita(String solicitadaPor, String motivo, LocalDateTime fechaHora, String estado, String pacienteId, String tutorId) {
        String sql = "INSERT INTO Cita (solicitadaPor, motivo, fechaHora, estado, paciente, tutorGrupal) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Validar que la fecha no esté en el pasado
            if (fechaHora.isBefore(LocalDateTime.now())) {
                System.out.println("⚠️ No se puede registrar una cita en el pasado.");
                return false;
            }

            // Formatear la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fechaHora.format(formatter);

            // Asignar valores
            ps.setString(1, solicitadaPor);
            ps.setString(2, motivo);
            ps.setString(3, fechaFormateada);
            ps.setString(4, estado);
            ps.setString(5, pacienteId);
            ps.setString(6, tutorId);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("🛑 Error al insertar cita: " + e.getMessage());
            return false;
        }
    }
    
    
    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
    try (Connection con = ConexionBD.obtenerConexion()) {
        String sql = "SELECT nombreUsuario, contraseña, rol FROM usuario WHERE nombreUsuario = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombreUsuario);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Usuario u = new Usuario();
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setContraseña(rs.getString("contraseña"));
            u.setRol(rs.getString("rol"));
            return u;
        } else {
            return null;
        }
    } catch (SQLException e) {
        System.out.println("❌ Error al obtener usuario: " + e.getMessage());
        return null;
    }
}
    
    
    public boolean actualizarContrasenia(Usuario usuario) {
    try (Connection con = ConexionBD.obtenerConexion()) {
        String sql = "UPDATE usuario SET contraseña = ? WHERE nombreUsuario = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, usuario.getContraseña());
        ps.setString(2, usuario.getNombreUsuario());
        int filas = ps.executeUpdate();
        return filas > 0;
    } catch (SQLException e) {
        System.out.println("❌ Error al actualizar usuario: " + e.getMessage());
        return false;
    }
}

    
    public static void registrarBitacora(String emocion, String anotaciones, String alumno) {
        Connection con = ConexionBD.obtenerConexion();
        
        if (con != null) {
            String sql = "INSERT INTO bitacora (fechaHora, emocion, anotaciones, alumno) VALUES (NOW(), ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, emocion);
                ps.setString(2, anotaciones);
                ps.setString(3, alumno);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha registrado correctamente en  tu bitacora! ");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Error al insertar bitácora: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null,"No se pudo establecer la conexión con la base de datos.");
        }
    }
    
    
    public static boolean nombreUsuarioDisponible(String nombreUsuario) {
        String consulta = "SELECT nombreUsuario FROM usuario WHERE nombreUsuario = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            return !rs.next(); // true si NO está en uso
        } catch (SQLException e) {
            System.out.println("Error al verificar nombreUsuario: " + e.getMessage());
        }
        return false;
    }

    public static void agregarVerificadorNombreUsuario(JTextField campo) {
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String nombreIngresado = campo.getText().trim();
                if (!nombreIngresado.isEmpty()) {
                    boolean disponible = nombreUsuarioDisponible(nombreIngresado);
                    if (disponible) {
                        JOptionPane.showMessageDialog(null,
                            "✅ El nombre '" + nombreIngresado + "' está disponible.");
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "❌ El nombre '" + nombreIngresado + "' ya está en uso. Elige otro.");
                        campo.setText(""); 
                    }
                }
            }
        });
    }
}


    








