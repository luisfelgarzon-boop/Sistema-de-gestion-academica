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

    public void registrar() {
        try {
            int codigo = Integer.parseInt(vista.getTxtCodigo().trim());
            if (codigo <= 21000) {
                vista.mostrarMensaje("El código debe ser mayor a 21000.");
                return;
            }
            if (gestor.buscarEstudiante(codigo) != null) {
                vista.mostrarMensaje("Ya existe un estudiante con ese código.");
                return;
            }
            String nombre = vista.getTxtNombre().trim();
            if (nombre.isEmpty()) {
                vista.mostrarMensaje("El nombre no puede estar vacío.");
                return;
            }
            double nDes = Double.parseDouble(vista.getTxtDesarrollo().trim());
            double nMat = Double.parseDouble(vista.getTxtMatematica().trim());

            gestor.agregarEstudiante(new Estudiante(codigo, nombre, nDes, nMat));
            vista.mostrarMensaje("Estudiante registrado exitosamente.");
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese valores numéricos válidos.");
        }
    }

    public void consultar() {
        try {
            int codigo = Integer.parseInt(vista.getTxtCodigo().trim());
            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontró el estudiante.");
                return;
            }
            vista.mostrarMensaje(
                "Rol: "         + e.getRol()                              + "\n" +
                "Código: "      + e.getCodigo()                           + "\n" +
                "Nombre: "      + e.getNombre()                           + "\n" +
                "Desarrollo: "  + e.getNotaDesarrollo()                   + "\n" +
                "Matemática: "  + e.getNotaMatematica()                   + "\n" +
                "Definitiva: "  + String.format("%.2f", e.calcularDefinitiva()) + "\n" +
                "Estado: "      + e.obtenerEstadoAprobacion()
            );
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Código inválido.");
        }
    }

    public void actualizar() {
        try {
            int codigo = Integer.parseInt(vista.getTxtCodigo().trim());
            Estudiante e = gestor.buscarEstudiante(codigo);
            if (e == null) {
                vista.mostrarMensaje("No se encontró el estudiante.");
                return;
            }
            String nombre = vista.getTxtNombre().trim();
            if (nombre.isEmpty()) {
                vista.mostrarMensaje("El nombre no puede estar vacío.");
                return;
            }
            double nDes = Double.parseDouble(vista.getTxtDesarrollo().trim());
            double nMat = Double.parseDouble(vista.getTxtMatematica().trim());

            e.setNombre(nombre);
            e.setNotaDesarrollo(nDes);
            e.setNotaMatematica(nMat);
            gestor.guardarDatos();
            vista.mostrarMensaje("Estudiante actualizado exitosamente.");
            vista.limpiarCampos();
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Valores inválidos.");
        }
    }

    public void borrar() {
        try {
            int codigo = Integer.parseInt(vista.getTxtCodigo().trim());
            boolean eliminado = gestor.eliminarEstudiante(codigo);
            vista.mostrarMensaje(eliminado
                ? "Estudiante eliminado exitosamente."
                : "No se encontró el estudiante.");
            vista.limpiarCampos();
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("Código inválido.");
        }
    }

    public void reporteFinal() {
        if (gestor.getListaEstudiantes().isEmpty()) {
            vista.mostrarMensaje("No hay estudiantes registrados.");
            return;
        }
        vista.cargarTabla(gestor.getListaEstudiantes());
    }
}