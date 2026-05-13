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

    @Override
    public String getRol() { return "Estudiante"; }

    @Override
    public double calcularDefinitiva() {
        // Redondeo a 2 decimales para evitar errores de precisión
        double def = (this.notaDesarrollo * 0.6) + (this.notaMatematica * 0.4);
        return Math.round(def * 100.0) / 100.0;
    }

    @Override
    public String obtenerEstadoAprobacion() {
        // Restricción: >= 3.5 APRUEBA
        return (this.calcularDefinitiva() >= 3.5) ? "SI APRUEBA" : "NO APRUEBA";
    }

    // Getters y Setters necesarios para el Controlador
    public double getNotaDesarrollo() { return notaDesarrollo; }
    public void setNotaDesarrollo(double notaDesarrollo) { this.notaDesarrollo = notaDesarrollo; }
    public double getNotaMatematica() { return notaMatematica; }
    public void setNotaMatematica(double notaMatematica) { this.notaMatematica = notaMatematica; }

    @Override
    public String toString() {
        return codigo + "," + nombre + "," + notaDesarrollo + "," + notaMatematica;
    }
}