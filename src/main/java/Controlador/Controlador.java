/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estudiante;
import Modelo.GestorArchivo;
import Vista.Vista;
import java.util.ArrayList;

/**
 *
 * @author FelipeNuevo
 */
public class Controlador {
    private Vista vista;
    private GestorArchivo gestor;

    public Controlador(Vista vista, GestorArchivo gestor) {
        this.vista  = vista;
        this.gestor = gestor;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1: registrar();   break;
                case 2: consultar();   break;
                case 3: actualizar();  break;
                case 4: borrar();      break;
                case 5: reporteFinal(); break;
                case 6: vista.mostrarMensaje("¡Hasta luego!"); break;
                default: vista.mostrarMensaje("Opción no válida."); break;
            }
        } while (opcion != 6);
    }

    private void registrar() {
        try {
            int codigo = vista.pedirCodigo();
            if (codigo == -1) return;

            if (codigo <= 21000) {
                vista.mostrarMensaje("Error: El código debe ser mayor a 21000.");
                return;
            }
            if (gestor.buscarEstudiante(codigo) != null) {
                vista.mostrarMensaje("Error: Ya existe un estudiante con ese código.");
                return;
            }

            String nombre = vista.pedirNombre();
            if (nombre == null || nombre.trim().isEmpty()) {
                vista.mostrarMensaje("Error: El nombre no puede estar vacío.");
                return;
            }

            double nDes = vista.pedirNota("Desarrollo");
            double nMat = vista.pedirNota("Matemática");

            Estudiante e = new Estudiante(codigo, nombre, nDes, nMat);
            gestor.agregarEstudiante(e);
            vista.mostrarMensaje("Estudiante registrado exitosamente.");

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Error: Ingrese valores numéricos válidos.");
        }
    }

    private void consultar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            if (codigo == -1) return;

            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontró ningún estudiante con ese código.");
                return;
            }
            vista.mostrarEstudiante(
                e.getRol(), e.getCodigo(), e.getNombre(),
                e.getNotaDesarrollo(), e.getNotaMatematica(),
                e.calcularDefinitiva(), e.obtenerEstadoAprobacion()
            );
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Error: Código inválido.");
        }
    }

    private void actualizar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            if (codigo == -1) return;

            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontró ningún estudiante con ese código.");
                return;
            }

            String nombre = vista.pedirNombre();
            if (nombre == null || nombre.trim().isEmpty()) {
                vista.mostrarMensaje("Error: El nombre no puede estar vacío.");
                return;
            }

            double nDes = vista.pedirNota("Desarrollo");
            double nMat = vista.pedirNota("Matemática");

            e.setNombre(nombre);
            e.setNotaDesarrollo(nDes);
            e.setNotaMatematica(nMat);
            gestor.guardarDatos();
            vista.mostrarMensaje("Estudiante actualizado exitosamente.");

        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Error: Valores inválidos.");
        }
    }

    private void borrar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            if (codigo == -1) return;

            boolean eliminado = gestor.eliminarEstudiante(codigo);
            if (eliminado) {
                vista.mostrarMensaje("Estudiante eliminado exitosamente.");
            } else {
                vista.mostrarMensaje("No se encontró ningún estudiante con ese código.");
            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Error: Código inválido.");
        }
    }

    private void reporteFinal() {
        ArrayList<Estudiante> lista = gestor.getListaEstudiantes();
        if (lista.isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("======= REPORTE FINAL =======\n\n");
        for (Estudiante e : lista) {
            sb.append(String.format("Código:          %d\n", e.getCodigo()));
            sb.append(String.format("Nombre:          %s\n", e.getNombre()));
            sb.append(String.format("Nota Desarrollo: %.2f\n", e.getNotaDesarrollo()));
            sb.append(String.format("Nota Matemática: %.2f\n", e.getNotaMatematica()));
            sb.append(String.format("Nota Definitiva: %.2f\n", e.calcularDefinitiva()));
            sb.append(String.format("Estado:          %s\n", e.obtenerEstadoAprobacion()));
            sb.append("-----------------------------\n");
        }
        vista.mostrarMensaje(sb.toString());
    }
}
