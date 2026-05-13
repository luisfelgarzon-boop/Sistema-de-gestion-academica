/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import vistaa.vista;
/**
 *
 * @author FelipeNuevo
 */
public class Vista extends javax.swing.JFrame {

    private Controlador.Controlador controlador;

    public Vista() {
        initComponents();
        setTitle("Sistema de Gestión Académica");
        setLocationRelativeTo(null);
    }

    public void setControlador(Controlador.Controlador controlador) {
        this.controlador = controlador;
    }

    public String getTxtCodigo() {
        return txtCodigo.getText();
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtDesarrollo() {
        return txtDesarrollo.getText();
    }

    public String getTxtMatematica() {
        return txtMatematica.getText();
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDesarrollo.setText("");
        txtMatematica.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarTabla(ArrayList<Modelo.Estudiante> lista) {
        String[] columnas = {"Código", "Nombre", "Desarrollo", "Matemática", "Definitiva", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (Modelo.Estudiante e : lista) {
            Object[] fila = {
                e.getCodigo(),
                e.getNombre(),
                String.format("%.2f", e.getNotaDesarrollo()),
                String.format("%.2f", e.getNotaMatematica()),
                String.format("%.2f", e.calcularDefinitiva()),
                e.obtenerEstadoAprobacion()
            };
            modelo.addRow(fila);
        }
        tablaEstudiantes.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo      = new javax.swing.JLabel();
        lblCodigo      = new javax.swing.JLabel();
        lblNombre      = new javax.swing.JLabel();
        lblDesarrollo  = new javax.swing.JLabel();
        lblMatematica  = new javax.swing.JLabel();
        txtCodigo      = new javax.swing.JTextField();
        txtNombre      = new javax.swing.JTextField();
        txtDesarrollo  = new javax.swing.JTextField();
        txtMatematica  = new javax.swing.JTextField();
        btnRegistrar   = new javax.swing.JButton();
        btnConsultar   = new javax.swing.JButton();
        btnActualizar  = new javax.swing.JButton();
        btnBorrar      = new javax.swing.JButton();
        btnReporte     = new javax.swing.JButton();
        jScrollPane1   = new javax.swing.JScrollPane();
        tablaEstudiantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Sistema de Gestión Académica");

        lblCodigo.setText("Código:");
        lblNombre.setText("Nombre:");
        lblDesarrollo.setText("Nota Desarrollo:");
        lblMatematica.setText("Nota Matemática:");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(evt -> controlador.registrar());

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(evt -> controlador.consultar());

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(evt -> controlador.actualizar());

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(evt -> controlador.borrar());

        btnReporte.setText("Reporte Final");
        btnReporte.addActionListener(evt -> controlador.reporteFinal());

        tablaEstudiantes.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Nombre", "Desarrollo", "Matemática", "Definitiva", "Estado"}
        ));
        jScrollPane1.setViewportView(tablaEstudiantes);

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                            .addComponent(lblCodigo)
                            .addComponent(lblNombre)
                            .addComponent(lblDesarrollo)
                            .addComponent(lblMatematica))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(txtCodigo, 150, 150, 150)
                            .addComponent(txtNombre, 150, 150, 150)
                            .addComponent(txtDesarrollo, 150, 150, 150)
                            .addComponent(txtMatematica, 150, 150, 150)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addGap(10)
                        .addComponent(btnConsultar)
                        .addGap(10)
                        .addComponent(btnActualizar)
                        .addGap(10)
                        .addComponent(btnBorrar)
                        .addGap(10)
                        .addComponent(btnReporte))
                    .addComponent(jScrollPane1))
                .addGap(20))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(15)
                .addComponent(lblTitulo, 30, 30, 30)
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo).addComponent(txtCodigo, 25, 25, 25))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre).addComponent(txtNombre, 25, 25, 25))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDesarrollo).addComponent(txtDesarrollo, 25, 25, 25))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatematica).addComponent(txtMatematica, 25, 25, 25))
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnConsultar)
                    .addComponent(btnActualizar)
                    .addComponent(btnBorrar)
                    .addComponent(btnReporte))
                .addGap(20)
                .addComponent(jScrollPane1, 150, 150, 150)
                .addGap(20)
        );

        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDesarrollo = new javax.swing.JLabel();
        txtMatematica = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnActualizar4 = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("Sistema de Gestión Académica");

        txtCodigo.setText("txtCodigo");

        jLabel3.setText("txtNombre");

        txtDesarrollo.setText("txtDesarrollo");

        txtMatematica.setText("txtMatematica");

        btnRegistrar.setText("btnRegistrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnConsultar.setText("btnConsultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnActualizar4.setText("btnActualizar");
        btnActualizar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar4ActionPerformed(evt);
            }
        });

        btnBorrar.setText("ñ");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnReporte.setText("jButton5");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtDesarrollo)
                        .addGap(34, 34, 34))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(txtCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnRegistrar)
                        .addGap(43, 43, 43)
                        .addComponent(btnConsultar)
                        .addGap(38, 38, 38)
                        .addComponent(btnActualizar4)
                        .addGap(51, 51, 51)
                        .addComponent(btnBorrar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReporte)
                    .addComponent(txtMatematica))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo)
                            .addComponent(txtMatematica))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(txtDesarrollo)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnConsultar)
                    .addComponent(btnActualizar4)
                    .addComponent(btnBorrar)
                    .addComponent(btnReporte))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnActualizar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizar4ActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar4;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JLabel txtDesarrollo;
    private javax.swing.JLabel txtMatematica;
    // End of variables declaration//GEN-END:variables
}
