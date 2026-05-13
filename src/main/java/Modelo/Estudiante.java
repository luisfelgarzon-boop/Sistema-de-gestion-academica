/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author FelipeNuevo
 */
public class Estudiante extends Persona implements IEvaluable {
    private double notaDesarrollo;
    private double notaMatematica;

    public Estudiante(int codigo, String nombre, double notaDesarrollo, double notaMatematica) {
        super(codigo, nombre);
        this.notaDesarrollo = notaDesarrollo;
        this.notaMatematica = notaMatematica;
    }

    @Override public String getRol() { return "Estudiante"; }
    
    @Override 
    public double calcularDefinitiva() { 
        double def = (notaDesarrollo * 0.6) + (notaMatematica * 0.4);
        return Math.round(def * 100.0) / 100.0; // Redondeo a 2 decimales
    }

    @Override 
    public String obtenerEstadoAprobacion() { 
        return (calcularDefinitiva() >= 3.5) ? "SI APRUEBA" : "NO APRUEBA"; 
    }

    public double getNotaDesarrollo() { return notaDesarrollo; }
    public void setNotaDesarrollo(double nd) { this.notaDesarrollo = nd; }
    public double getNotaMatematica() { return notaMatematica; }
    public void setNotaMatematica(double nm) { this.notaMatematica = nm; }

    @Override 
    public String toString() { return codigo + "," + nombre + "," + notaDesarrollo + "," + notaMatematica; }
}