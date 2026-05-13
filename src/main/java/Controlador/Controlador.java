/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Estudiante;
import Modelo.GestorArchivo;
import Vista.Vista;
import Modelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        // Asignación de eventos
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnReporte.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
        actualizarTablaPrincipal();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == vista.btnRegistrar) registrar();
            else if (e.getSource() == vista.btnConsultar) consultar();
            else if (e.getSource() == vista.btnActualizar) actualizar();
            else if (e.getSource() == vista.btnEliminar) eliminar();
            else if (e.getSource() == vista.btnReporte) mostrarVentanaReporte();
            else if (e.getSource() == vista.btnLimpiar) limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error en los datos.");
        }
    }

    private void registrar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        if (cod <= 21000) { JOptionPane.showMessageDialog(vista, "Código inválido (>21000)"); return; }
        double n1 = Double.parseDouble(vista.txtDesarrollo.getText());
        double n2 = Double.parseDouble(vista.txtMatematica.getText());
        
        gestor.agregarEstudiante(new Estudiante(cod, vista.txtNombre.getText(), n1, n2));
        actualizarTablaPrincipal();
        limpiar();
    }

    private void consultar() {
        int cod = vista.pedirDato("CONSULTA", "Ingrese el código a buscar:");
        Estudiante est = gestor.buscarEstudiante(cod);
        if (est != null) {
            JOptionPane.showMessageDialog(vista, "Estudiante: " + est.getNombre() + "\nDefinitiva: " + est.calcularDefinitiva());
        } else JOptionPane.showMessageDialog(vista, "No encontrado.");
    }

    private void actualizar() {
        int cod = Integer.parseInt(vista.txtCodigo.getText());
        Estudiante est = gestor.buscarEstudiante(cod);
        if (est != null) {
            est.setNombre(vista.txtNombre.getText());
            est.setNotaDesarrollo(Double.parseDouble(vista.txtDesarrollo.getText()));
            est.setNotaMatematica(Double.parseDouble(vista.txtMatematica.getText()));
            gestor.guardarDatos();
            actualizarTablaPrincipal();
            JOptionPane.showMessageDialog(vista, "Actualizado.");
        }
    }

    private void eliminar() {
        int cod = vista.pedirDato("ELIMINACIÓN", "Código del estudiante a eliminar:");
        if (cod != -1 && gestor.eliminarEstudiante(cod)) {
            actualizarTablaPrincipal();
            JOptionPane.showMessageDialog(vista, "Eliminado correctamente.");
        } else if (cod != -1) JOptionPane.showMessageDialog(vista, "Código no existe.");
    }

    private void actualizarTablaPrincipal() {
        vista.modeloTabla.setRowCount(0);
        for (Estudiante est : gestor.getListaEstudiantes()) {
            vista.modeloTabla.addRow(new Object[]{
                est.getCodigo(), est.getNombre(), est.getNotaDesarrollo(),
                est.getNotaMatematica(), est.calcularDefinitiva(), est.obtenerEstadoAprobacion()
            });
        }
    }

    // --- RECUADRO DE REPORTE FINAL ESTILIZADO ---
    private void mostrarVentanaReporte() {
        JDialog win = new JDialog(vista, "REPORTE GENERAL DE CALIFICACIONES", true);
        win.setSize(800, 500);
        win.setLocationRelativeTo(vista);
        win.setLayout(new BorderLayout());

        // Tabla del reporte
        String[] col = {"CÓDIGO", "NOMBRE", "DEF", "ESTADO"};
        DefaultTableModel mod = new DefaultTableModel(col, 0);
        JTable t = new JTable(mod);
        t.setRowHeight(30);
        
        for (Estudiante e : gestor.getListaEstudiantes()) {
            mod.addRow(new Object[]{e.getCodigo(), e.getNombre(), e.calcularDefinitiva(), e.obtenerEstadoAprobacion()});
        }

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(155, 89, 182));
        pnlHeader.add(new JLabel("<html><h2 style='color:white'>REPORTE CONSOLIDADO</h2></html>"));

        win.add(pnlHeader, BorderLayout.NORTH);
        win.add(new JScrollPane(t), BorderLayout.CENTER);
        
        JButton btnCerrar = new JButton("CERRAR REPORTE");
        btnCerrar.addActionListener(x -> win.dispose());
        win.add(btnCerrar, BorderLayout.SOUTH);

        win.setVisible(true);
    }

    private void limpiar() {
        vista.txtCodigo.setText(""); vista.txtNombre.setText("");
        vista.txtDesarrollo.setText(""); vista.txtMatematica.setText("");
    }
}