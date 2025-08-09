package taiyari;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

//Guarda el nombredeUsuario y su rol.
public class Sesion {
    private static String nombreUsuario;
    private static String rolUsuario;

    public static void setNombreUsuario(String nombre) {
        nombreUsuario = nombre;
    }

    
    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    
    public static void setRolUsuario(String rol) {
        rolUsuario = rol;
    }

    
    public static String getRolUsuario() {
        return rolUsuario;
    }

    
}


