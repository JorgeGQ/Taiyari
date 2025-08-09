package taiyari;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter.FilterBypass;
 import javax.swing.*;
import javax.swing.text.*;

//  @author XiCode, desarolladora por:  Jorge García,   Uriel Gonzalez,     Yhostin Ramirez    y Cristopher Escamilla.

public class Ayudas {
//  MÉTODOS

    public static void maximizarVentana(JFrame ventana) {
        // Obtener tamaño de pantalla
        Dimension tamañoPantalla = Toolkit.getDefaultToolkit().getScreenSize();

        //  Establece la proporción del tamaño
        int ancho = (int) (tamañoPantalla.width * 1);
        int alto = (int) (tamañoPantalla.height * 0.95);

        //  Aplica la proprción del tamaño y centra la ventana(frame)
        ventana.setSize(ancho, alto);
        ventana.setLocationRelativeTo(null);

        //  Componentes que se adaptan
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
    }
    
    
    public void volverAInicioSesion(JFrame ventanaActual) {
        int opcion = JOptionPane.showConfirmDialog(
            ventanaActual,
            "¿Estás seguro de que deseas Cerrar Sesión?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            ventanaActual.dispose();
            frmInicioSesion inicioSesion = new frmInicioSesion();   inicioSesion.setVisible(true);
        }
    }
    

    // Método que aplica el filtro al JTextField
    public static void limitarLongitud(JTextField campoTexto, int longitudMaxima) {
        AbstractDocument doc = (AbstractDocument) campoTexto.getDocument();
        doc.setDocumentFilter(new FiltroLongitud(longitudMaxima));
    }

    // Clase que controla cuántos caracteres se pueden insertar
    static class FiltroLongitud extends DocumentFilter {
        private final int max;

        public FiltroLongitud(int max) {
            this.max = max;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (fb.getDocument().getLength() + string.length() <= max) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (fb.getDocument().getLength() - length + text.length() <= max) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    
    public static void validarEdad(JTextField campo) {
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String texto = campo.getText().trim();
                if (!texto.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(texto);
                        if (valor < 17 || valor > 99) {
                            mostrarAlerta(campo);
                            campo.setText(""); // Opcional: limpia el campo
                        }
                    } catch (NumberFormatException ex) {
                        mostrarAlerta(campo);
                        campo.setText("");
                    }
                }
            }

            private void mostrarAlerta(JTextField campo) {
                JOptionPane.showMessageDialog(
                    campo,
                    "Por favor ingresa un número entre 17 y 99.",
                    "Edad inválida",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }
    
    
    public static void soloTexto(JTextField campo) {
        AbstractDocument doc = (AbstractDocument) campo.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (esSoloDigitos(string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (esSoloDigitos(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean esSoloDigitos(String texto) {
                return texto.matches("\\d*"); // Solo dígitos del 0 al 9
            }
        });
    }
    
    
}
