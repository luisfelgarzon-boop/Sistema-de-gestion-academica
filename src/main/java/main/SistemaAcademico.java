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
        Vista vista          = new Vista();
        GestorArchivo gestor = new GestorArchivo();
        Controlador controlador = new Controlador(vista, gestor);
        controlador.iniciar();
    }
}
