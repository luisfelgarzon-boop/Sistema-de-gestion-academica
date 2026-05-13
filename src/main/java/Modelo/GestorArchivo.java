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
    private static final String RUTA_ARCHIVO = "estudiantes.txt";
    private ArrayList<Estudiante> listaEstudiantes;

    public GestorArchivo() {
        listaEstudiantes = new ArrayList<>();
        cargarDatos();
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void agregarEstudiante(Estudiante e) {
        listaEstudiantes.add(e);
        guardarDatos();
    }

    public Estudiante buscarEstudiante(int codigo) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEstudiante(int codigo) {
        Estudiante encontrado = buscarEstudiante(codigo);
        if (encontrado != null) {
            listaEstudiantes.remove(encontrado);
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
            System.err.println("Error al guardar datos: " + ex.getMessage());
        }
    }

    private void cargarDatos() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int cod        = Integer.parseInt(partes[0].trim());
                    String nom     = partes[1].trim();
                    double nDes    = Double.parseDouble(partes[2].trim());
                    double nMat    = Double.parseDouble(partes[3].trim());
                    listaEstudiantes.add(new Estudiante(cod, nom, nDes, nMat));
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al cargar datos: " + ex.getMessage());
        }
    }
}
