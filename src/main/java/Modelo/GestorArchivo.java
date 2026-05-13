/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author FelipeNuevo
 */
public class GestorArchivo {
    private final String RUTA_ARCHIVO = "estudiantes.txt";
    private ArrayList<Estudiante> listaEstudiantes;

    public GestorArchivo() {
        this.listaEstudiantes = new ArrayList<>();
        cargarDatos();
    }

    public ArrayList<Estudiante> getListaEstudiantes() { return listaEstudiantes; }

    public void agregarEstudiante(Estudiante e) {
        listaEstudiantes.add(e);
        guardarDatos();
    }

    public Estudiante buscarEstudiante(int codigo) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCodigo() == codigo) return e;
        }
        return null;
    }

    public boolean eliminarEstudiante(int codigo) {
        Estudiante e = buscarEstudiante(codigo);
        if (e != null) {
            listaEstudiantes.remove(e);
            guardarDatos();
            return true;
        }
        return false;
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Estudiante e : listaEstudiantes) {
                pw.println(e.toString());
            }
        } catch (IOException ex) {
            System.err.println("Error al guardar: " + ex.getMessage());
        }
    }

    public void cargarDatos() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            listaEstudiantes.clear();
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 4) {
                    listaEstudiantes.add(new Estudiante(
                        Integer.parseInt(d[0]), d[1], 
                        Double.parseDouble(d[2]), Double.parseDouble(d[3])));
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar datos.");
        }
    }
}
