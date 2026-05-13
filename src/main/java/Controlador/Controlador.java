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
        // Listeners
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() { vista.setVisible(true); reporteFinal(); }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnRegistrar) registrar();
            if (e.getSource() == vista.btnConsultar) consultar();
            if (e.getSource() == vista.btnActualizar) actualizar();
            if (e.getSource() == vista.btnEliminar) borrar();
            if (e.getSource() == vista.btnReporte) reporteFinal();
            if (e.getSource() == vista.btnLimpiar) {
                vista.txtCodigo.setText(""); vista.txtNombre.setText("");
                vista.txtDesarrollo.setText(""); vista.txtMatematica.setText("");
            }
        } catch (Exception ex) { vista.mostrarMensaje("Error: Datos inválidos."); }
    }

    public void registrar() {
        int c = vista.pedirCodigo();
        if (c <= 21000) { vista.mostrarMensaje("Código debe ser > 21000"); return; }
        if (gestor.buscarEstudiante(c) != null) { vista.mostrarMensaje("Código ya existe."); return; }
        double n1 = vista.pedirNota("Desarrollo");
        double n2 = vista.pedirNota("Matematica");
        gestor.agregarEstudiante(new Estudiante(c, vista.pedirNombre(), n1, n2));
        reporteFinal();
        vista.mostrarMensaje("Estudiante registrado.");
    }

    public void consultar() {
        int c = vista.pedirCodigoBusqueda();
        Estudiante est = gestor.buscarEstudiante(c);
        if (est != null) {
            vista.mostrarEstudiante(est.getRol(), est.getCodigo(), est.getNombre(), 
                    est.getNotaDesarrollo(), est.getNotaMatematica(), est.calcularDefinitiva(), est.obtenerEstadoAprobacion());
        } else vista.mostrarMensaje("No encontrado.");
    }

    public void actualizar() {
        int c = vista.pedirCodigo();
        Estudiante est = gestor.buscarEstudiante(c);
        if (est != null) {
            est.setNombre(vista.pedirNombre());
            est.setNotaDesarrollo(vista.pedirNota("Desarrollo"));
            est.setNotaMatematica(vista.pedirNota("Matematica"));
            gestor.guardarDatos();
            reporteFinal();
            vista.mostrarMensaje("Actualizado.");
        }
    }

    public void borrar() {
        if (gestor.eliminarEstudiante(vista.pedirCodigo())) {
            reporteFinal();
            vista.mostrarMensaje("Eliminado.");
        }
    }

    public void reporteFinal() {
        vista.modeloTabla.setRowCount(0);
        for (Estudiante e : gestor.getListaEstudiantes()) {
            vista.modeloTabla.addRow(new Object[]{
                e.getCodigo(), e.getNombre(), e.getNotaDesarrollo(), 
                e.getNotaMatematica(), String.format("%.2f", e.calcularDefinitiva()), e.obtenerEstadoAprobacion()
            });
        }
    }
}