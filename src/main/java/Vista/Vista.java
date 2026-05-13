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
    private Scanner entrada; // Obligatorio por UML
    public JTextField txtCodigo, txtNombre, txtDesarrollo, txtMatematica;
    public JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnReporte, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public Vista() {
        this.entrada = new Scanner(System.in);
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("SISTEMA DE GESTIÓN ACADÉMICA - UAO");
        setSize(1100, 600); // Un poco más de ancho para los botones
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 245, 245)); 
    }

    private void inicializarComponentes() {
        // --- PANEL IZQUIERDO: FORMULARIO ---
        JPanel pnlLateral = new JPanel(new GridLayout(10, 1, 5, 5));
        pnlLateral.setBackground(new Color(44, 62, 80)); // Azul oscuro
        pnlLateral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("DATOS", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtDesarrollo = new JTextField();
        txtMatematica = new JTextField();

        pnlLateral.add(lblTitulo);
        pnlLateral.add(crearLabelForm("Código Estudiante:")); pnlLateral.add(txtCodigo);
        pnlLateral.add(crearLabelForm("Nombre Completo:")); pnlLateral.add(txtNombre);
        pnlLateral.add(crearLabelForm("Nota Desarrollo:")); pnlLateral.add(txtDesarrollo);
        pnlLateral.add(crearLabelForm("Nota Matemática:")); pnlLateral.add(txtMatematica);

        // --- PANEL INFERIOR: BOTONES (Letras Negras y Tamaño Corregido) ---
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        pnlBotones.setBackground(new Color(220, 220, 220)); // Fondo gris claro para los botones

        // Colores de fondo llamativos pero con letra NEGRA
        btnRegistrar = crearBotonNegro("Registrar", new Color(174, 255, 174)); // Verde claro
        btnConsultar = crearBotonNegro("Consultar", new Color(174, 214, 241)); // Azul claro
        btnActualizar = crearBotonNegro("Actualizar", new Color(255, 245, 157)); // Amarillo claro
        btnEliminar = crearBotonNegro("Eliminar", new Color(255, 174, 174)); // Rojo claro
        btnReporte = crearBotonNegro("Reporte Final", new Color(215, 189, 226)); // Morado claro
        btnLimpiar = crearBotonNegro("Limpiar", Color.WHITE); // Blanco

        pnlBotones.add(btnRegistrar);
        pnlBotones.add(btnConsultar);
        pnlBotones.add(btnActualizar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnReporte);
        pnlBotones.add(btnLimpiar);

        // --- PANEL CENTRAL: TABLA ---
        String[] columnas = {"CÓDIGO", "NOMBRE", "DESARROLLO", "MATE", "DEFINITIVA", "ESTADO"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(pnlLateral, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
        add(pnlBotones, BorderLayout.SOUTH);
    }

    // MÉTODO PARA BOTONES CON LETRA NEGRA
    private JButton crearBotonNegro(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(140, 45)); // Tamaño generoso
        boton.setBackground(colorFondo);
        boton.setForeground(Color.BLACK); // <--- LETRA NEGRA
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return boton;
    }

    private JLabel crearLabelForm(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return l;
    }

    // --- MÉTODOS OBLIGATORIOS UML ---
    public int mostrarMenu() { return 0; }
    public int pedirCodigo() { return Integer.parseInt(txtCodigo.getText().trim()); }
    public int pedirCodigoBusqueda() {
        String res = JOptionPane.showInputDialog(this, "Ingrese código:");
        return (res != null) ? Integer.parseInt(res.trim()) : -1;
    }
    public String pedirNombre() { return txtNombre.getText().trim(); }
    public double pedirNota(String materia) {
        if(materia.equalsIgnoreCase("Desarrollo")) return Double.parseDouble(txtDesarrollo.getText());
        return Double.parseDouble(txtMatematica.getText());
    }
    public void mostrarMensaje(String mensaje) { JOptionPane.showMessageDialog(this, mensaje); }
    public void mostrarEstudiante(String rol, int cod, String nom, double nDes, double nMat, double def, String est) {
        String info = String.format("Rol: %s\nCódigo: %d\nNombre: %s\nDefinitiva: %.2f\nEstado: %s", rol, cod, nom, def, est);
        JOptionPane.showMessageDialog(this, info, "Consulta", JOptionPane.INFORMATION_MESSAGE);
    }
}