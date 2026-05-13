/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author FelipeNuevo
 */
public abstract class Persona {
    protected int codigo;
    protected String nombre;

    public Persona(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public abstract String getRol();
    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}