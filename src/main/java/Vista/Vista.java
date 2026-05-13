/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

/**
 *
 * @author FelipeNuevo
 */
public class Vista extends JFrame {
    public JTextField txtCodigo, txtNombre, txtDesarrollo, txtMatematica;
    public JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnReporte, btnLimpiar;
    public DefaultTableModel modeloTabla;
    public JTable tabla;

    public Vista() {
        setTitle("UAO - SISTEMA DE GESTIÓN ACADÉMICA");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        // --- PANEL LATERAL (FORMULARIO) ---
        JPanel pnlLateral = new JPanel(new GridLayout(12, 1, 10, 10));
        pnlLateral.setBackground(new Color(28, 40, 51)); // Azul noche
        pnlLateral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblLogo = new JLabel("NOTAS UAO", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setForeground(new Color(236, 240, 241));
        
        txtCodigo = crearCampo();
        txtNombre = crearCampo();
        txtDesarrollo = crearCampo();
        txtMatematica = crearCampo();

        pnlLateral.add(lblLogo);
        pnlLateral.add(crearL("CÓDIGO (>21000)")); pnlLateral.add(txtCodigo);
        pnlLateral.add(crearL("NOMBRE COMPLETO")); pnlLateral.add(txtNombre);
        pnlLateral.add(crearL("DESARROLLO (0-5)")); pnlLateral.add(txtDesarrollo);
        pnlLateral.add(crearL("MATEMÁTICA (0-5)")); pnlLateral.add(txtMatematica);
        pnlLateral.setPreferredSize(new Dimension(320, 0));

        // --- PANEL CENTRAL (TABLA Y BOTONES) ---
        JPanel pnlCentral = new JPanel(new BorderLayout(20, 20));
        pnlCentral.setBackground(new Color(242, 244, 244));
        pnlCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tabla con diseño limpio
        modeloTabla = new DefaultTableModel(new String[]{"ID", "ESTUDIANTE", "DES", "MAT", "DEF", "ESTADO"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(35);
        tabla.getTableHeader().setBackground(new Color(33, 47, 61));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(174, 214, 241));
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        // Panel de Botones (Estilo Moderno)
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnlBotones.setOpaque(false);

        btnRegistrar = crearB("REGISTRAR", new Color(46, 204, 113));
        btnConsultar = crearB("CONSULTAR", new Color(52, 152, 219));
        btnActualizar = crearB("ACTUALIZAR", new Color(241, 196, 15));
        btnEliminar = crearB("ELIMINAR", new Color(231, 76, 60));
        btnReporte = crearB("REPORTE FINAL", new Color(155, 89, 182));
        btnLimpiar = crearB("LIMPIAR", Color.WHITE);

        pnlBotones.add(btnRegistrar); pnlBotones.add(btnConsultar); 
        pnlBotones.add(btnActualizar); pnlBotones.add(btnEliminar); 
        pnlBotones.add(btnReporte); pnlBotones.add(btnLimpiar);

        pnlCentral.add(scroll, BorderLayout.CENTER);
        pnlCentral.add(pnlBotones, BorderLayout.SOUTH);

        add(pnlLateral, BorderLayout.WEST);
        add(pnlCentral, BorderLayout.CENTER);
    }

    private JTextField crearCampo() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setBorder(BorderFactory.createCompoundBorder(f.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return f;
    }

    private JLabel crearL(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(new Color(189, 195, 199));
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
        return l;
    }

    private JButton crearB(String t, Color c) {
        JButton b = new JButton(t);
        b.setPreferredSize(new Dimension(150, 50));
        b.setBackground(c);
        b.setForeground(Color.BLACK);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(c.darker()));
        return b;
    }

    // El recuadro de entrada para eliminar/consultar
    public int pedirDato(String tit, String msg) {
        String r = JOptionPane.showInputDialog(this, msg, tit, JOptionPane.QUESTION_MESSAGE);
        try { return (r != null) ? Integer.parseInt(r.trim()) : -1; } catch (Exception e) { return -1; }
    }
}