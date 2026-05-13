/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author FelipeNuevo
 */
public class GestorArchivo {
    private final String RUTA = "estudiantes.txt";
    private ArrayList<Estudiante> listaEstudiantes;

    public GestorArchivo() {
        this.listaEstudiantes = new ArrayList<>();
        cargarDatos();
    }

    // El UML exige persistencia: este método guarda todo el ArrayList en el TXT
    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {
            for (Estudiante e : listaEstudiantes) {
                pw.println(e.getCodigo() + "," + e.getNombre() + "," + 
                           e.getNotaDesarrollo() + "," + e.getNotaMatematica());
            }
        } catch (IOException ex) {
            System.err.println("Error de persistencia: " + ex.getMessage());
        }
    }

    private void cargarDatos() {
        File f = new File(RUTA);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                listaEstudiantes.add(new Estudiante(Integer.parseInt(d[0]), d[1], 
                                     Double.parseDouble(d[2]), Double.parseDouble(d[3])));
            }
        } catch (Exception ex) { System.err.println("Error al cargar."); }
    }

    public void agregarEstudiante(Estudiante e) {
        listaEstudiantes.add(e);
        guardarDatos();
    }

    public boolean eliminarEstudiante(int cod) {
        Estudiante e = buscarEstudiante(cod);
        if (e != null) {
            listaEstudiantes.remove(e);
            guardarDatos();
            return true;
        }
        return false;
    }

    public Estudiante buscarEstudiante(int cod) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCodigo() == cod) return e;
        }
        return null;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
}