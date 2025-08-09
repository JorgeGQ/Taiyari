/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taiyari;

/**
 *
 * @author jorge
 */
public class TestUsuarioDisponible {
    public static void main(String[] args) {
        String nombreIngresado = "Olaf"; // cambia por el nombre que quieres probar

        if (UsuarioDAO.nombreUsuarioDisponible(nombreIngresado)) {
            System.out.println("✅ El nombre '" + nombreIngresado + "' está disponible para usar.");
        } else {
            System.out.println("❌ El nombre '" + nombreIngresado + "' ya está en uso. Intenta con otro.");
        }
    }
}

