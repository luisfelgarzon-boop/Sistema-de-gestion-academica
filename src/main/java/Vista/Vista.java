/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.JOptionPane;

/**
 *
 * @author FelipeNuevo
 */
public class Vista {

    public Vista() {}

    public int mostrarMenu() {
        String[] opciones = {
            "1. Registrar estudiante",
            "2. Consultar estudiante",
            "3. Actualizar estudiante",
            "4. Borrar estudiante",
            "5. Reporte final",
            "6. Salir"
        };
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione una opción:",
            "Sistema de Gestión Académica",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        return seleccion + 1;
    }

    public int pedirCodigo() {
        String input = JOptionPane.showInputDialog(
            null,
            "Ingrese el código del estudiante (debe ser mayor a 21000):",
            "Código",
            JOptionPane.QUESTION_MESSAGE
        );
        if (input == null) return -1;
        return Integer.parseInt(input.trim());
    }

    public int pedirCodigoBusqueda() {
        String input = JOptionPane.showInputDialog(
            null,
            "Ingrese el código del estudiante a buscar:",
            "Buscar",
            JOptionPane.QUESTION_MESSAGE
        );
        if (input == null) return -1;
        return Integer.parseInt(input.trim());
    }

    public String pedirNombre() {
        return JOptionPane.showInputDialog(
            null,
            "Ingrese el nombre completo del estudiante:",
            "Nombre",
            JOptionPane.QUESTION_MESSAGE
        );
    }

    public double pedirNota(String materia) {
        String input = JOptionPane.showInputDialog(
            null,
            "Ingrese la nota de " + materia + " (0.0 a 5.0):",
            "Nota de " + materia,
            JOptionPane.QUESTION_MESSAGE
        );
        if (input == null) return 0.0;
        return Double.parseDouble(input.trim());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
            null,
            mensaje,
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void mostrarEstudiante(String rol, int cod, String nom,
                                   double nDes, double nMat,
                                   double def, String est) {
        String info = String.format(
            "Rol:              %s\n" +
            "Código:           %d\n" +
            "Nombre:           %s\n" +
            "Nota Desarrollo:  %.2f\n" +
            "Nota Matemática:  %.2f\n" +
            "Nota Definitiva:  %.2f\n" +
            "Estado:           %s",
            rol, cod, nom, nDes, nMat, def, est
        );
        JOptionPane.showMessageDialog(
            null,
            info,
            "Datos del Estudiante",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
