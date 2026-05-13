/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import Controlador.Controlador;
import Modelo.GestorArchivo;
import Vista.Vista;


/**
 *
 * @author FelipeNuevo
 */

public class SistemaAcademico {
    public static void main(String[] args) {
        // Establecer Look and Feel de Windows/Sistema para que se vea bien
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) { }

        Vista v = new Vista();
        GestorArchivo g = new GestorArchivo();
        Controlador c = new Controlador(v, g);
        
        c.inicio();
    }
}