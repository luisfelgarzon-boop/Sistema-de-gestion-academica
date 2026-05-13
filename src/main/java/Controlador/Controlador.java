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
        this.vista = vista;
        this.gestor = gestor;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> consultar();
                case 3 -> actualizar();
                case 4 -> borrar();
                case 5 -> reporteFinal();
                case 6 -> vista.mostrarMensaje("Saliendo del sistema.");
                default -> vista.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 6);
    }

    private void registrar() {
        try {
            int codigo = vista.pedirCodigo();
            if (codigo <= 21000) {
                vista.mostrarMensaje("El codigo debe ser mayor a 21000.");
                return;
            }
            if (gestor.buscarEstudiante(codigo) != null) {
                vista.mostrarMensaje("Ya existe un estudiante con ese codigo.");
                return;
            }
            String nombre = vista.pedirNombre();
            if (nombre == null || nombre.trim().isEmpty()) {
                vista.mostrarMensaje("El nombre no puede estar vacio.");
                return;
            }
            double nDes = vista.pedirNota("Desarrollo");
            double nMat = vista.pedirNota("Matematica");
            gestor.agregarEstudiante(new Estudiante(codigo, nombre.trim(), nDes, nMat));
            vista.mostrarMensaje("Estudiante registrado exitosamente.");
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese valores numericos validos.");
        }
    }

    private void consultar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontro el estudiante.");
                return;
            }
            vista.mostrarEstudiante(
                e.getRol(), e.getCodigo(), e.getNombre(),
                e.getNotaDesarrollo(), e.getNotaMatematica(),
                e.calcularDefinitiva(), e.obtenerEstadoAprobacion()
            );
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Codigo invalido.");
        }
    }

    private void actualizar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontro el estudiante.");
                return;
            }
            String nombre = vista.pedirNombre();
            if (nombre == null || nombre.trim().isEmpty()) {
                vista.mostrarMensaje("El nombre no puede estar vacio.");
                return;
            }
            double nDes = vista.pedirNota("Desarrollo");
            double nMat = vista.pedirNota("Matematica");
            e.setNombre(nombre.trim());
            e.setNotaDesarrollo(nDes);
            e.setNotaMatematica(nMat);
            gestor.guardarDatos();
            vista.mostrarMensaje("Estudiante actualizado exitosamente.");
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Valores invalidos.");
        }
    }

    private void borrar() {
        try {
            int codigo = vista.pedirCodigoBusqueda();
            boolean eliminado = gestor.eliminarEstudiante(codigo);
            vista.mostrarMensaje(eliminado
                ? "Estudiante eliminado exitosamente."
                : "No se encontro el estudiante.");
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Codigo invalido.");
        }
    }

    private void reporteFinal() {
        if (gestor.getListaEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-20s %-12s %-12s %-12s %-12s\n",
            "Codigo", "Nombre", "Desarrollo", "Matematica", "Definitiva", "Estado"));
        sb.append("-".repeat(80)).append("\n");
        for (Estudiante e : gestor.getListaEstudiantes()) {
            sb.append(String.format("%-10d %-20s %-12.2f %-12.2f %-12.2f %-12s\n",
                e.getCodigo(), e.getNombre(),
                e.getNotaDesarrollo(), e.getNotaMatematica(),
                e.calcularDefinitiva(), e.obtenerEstadoAprobacion()));
        }
        vista.mostrarMensaje(sb.toString());
    }
}