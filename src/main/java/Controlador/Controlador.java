/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estudiante;
import Modelo.GestorArchivo;
import Vista.Vista;
import Modelo.*;
import java.awt.event.*;

/**
 *
 * @author FelipeNuevo
 */
public class Controlador implements ActionListener {
    private Vista vista;
    private GestorArchivo gestor;

    public Controlador(Vista vista, GestorArchivo gestor) {
        this.vista = vista;
        this.gestor = gestor;
        // Vincular botones
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() {
        vista.setVisible(true);
        reporteFinal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnRegistrar) registrar();
            if (e.getSource() == vista.btnConsultar) consultar();
            if (e.getSource() == vista.btnActualizar) actualizar();
            if (e.getSource() == vista.btnEliminar) borrar();
            if (e.getSource() == vista.btnReporte) reporteFinal();
            if (e.getSource() == vista.btnLimpiar) limpiar();
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("ERROR: Ingrese solo números en Código y Notas.");
        } catch (Exception ex) {
            vista.mostrarMensaje("Error inesperado: " + ex.getMessage());
        }
    }

    public void registrar() {
        // RESTRICCIÓN 1: Código > 21000
        int cod = vista.pedirCodigo();
        if (cod <= 21000) {
            vista.mostrarMensaje("RESTRICCIÓN: El código debe ser superior a 21000.");
            return;
        }

        // Validación: No duplicados
        if (gestor.buscarEstudiante(cod) != null) {
            vista.mostrarMensaje("El estudiante con código " + cod + " ya existe.");
            return;
        }

        String nom = vista.pedirNombre();
        if (nom.isEmpty()) {
            vista.mostrarMensaje("El nombre no puede estar vacío.");
            return;
        }

        // RESTRICCIÓN 2: Notas entre 0 y 5
        double nDes = vista.pedirNota("Desarrollo");
        double nMat = vista.pedirNota("Matematica");

        if (nDes < 0 || nDes > 5 || nMat < 0 || nMat > 5) {
            vista.mostrarMensaje("RESTRICCIÓN: Las notas deben estar entre 0.0 y 5.0");
            return;
        }

        gestor.agregarEstudiante(new Estudiante(cod, nom, nDes, nMat));
        reporteFinal();
        vista.mostrarMensaje("Estudiante registrado exitosamente.");
        limpiar();
    }

    public void consultar() {
        int cod = vista.pedirCodigoBusqueda();
        if (cod == -1) return;

        Estudiante est = gestor.buscarEstudiante(cod);
        if (est != null) {
            vista.mostrarEstudiante(
                est.getRol(), est.getCodigo(), est.getNombre(),
                est.getNotaDesarrollo(), est.getNotaMatematica(),
                est.calcularDefinitiva(), est.obtenerEstadoAprobacion()
            );
        } else {
            vista.mostrarMensaje("Estudiante no encontrado.");
        }
    }

    public void actualizar() {
        int cod = vista.pedirCodigo();
        Estudiante est = gestor.buscarEstudiante(cod);
        
        if (est != null) {
            double nDes = vista.pedirNota("Desarrollo");
            double nMat = vista.pedirNota("Matematica");
            
            if (nDes < 0 || nDes > 5 || nMat < 0 || nMat > 5) {
                vista.mostrarMensaje("Notas inválidas (0-5).");
                return;
            }
            
            est.setNombre(vista.pedirNombre());
            est.setNotaDesarrollo(nDes);
            est.setNotaMatematica(nMat);
            gestor.guardarDatos();
            reporteFinal();
            vista.mostrarMensaje("Datos actualizados.");
        } else {
            vista.mostrarMensaje("Para actualizar, primero ingrese un código existente en el campo Código.");
        }
    }

    public void borrar() {
        int cod = vista.pedirCodigo();
        if (gestor.eliminarEstudiante(cod)) {
            reporteFinal();
            vista.mostrarMensaje("Estudiante eliminado.");
            limpiar();
        } else {
            vista.mostrarMensaje("No se encontró el estudiante para eliminar.");
        }
    }

    public void reporteFinal() {
        vista.modeloTabla.setRowCount(0);
        for (Estudiante e : gestor.getListaEstudiantes()) {
            vista.modeloTabla.addRow(new Object[]{
                e.getCodigo(), 
                e.getNombre(), 
                e.getNotaDesarrollo(), 
                e.getNotaMatematica(), 
                e.calcularDefinitiva(), 
                e.obtenerEstadoAprobacion()
            });
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtDesarrollo.setText("");
        vista.txtMatematica.setText("");
    }
}