/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemploindexados;

/**
 *
 * @author Margarita G
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EjemploIndexados {
    
    private static final String DATA_FILE = "datos.txt";
    private static final String INDEX_FILE = "indice.txt";
    
    /*
    private: Restricts visibility. The string is only accessible inside this specific class.
    static: Attaches the variable to the class rather than instances. Only one copy exists in memory, shared by all instances.
    final: Makes the variable immutable. Once assigned, its reference or value cannot be changed.
    String: Specifies the data type as a sequence of characters.
    */
    
    // Índice en memoria: Clave (ID del registro) -> Posición en bytes (Offset)
    private static Map<Integer, Long> indice = new HashMap<>();
    

    
      
     //Inserta un registro en el archivo de datos y actualiza el índice.
     
    public static void insertarRegistro(int id, String nombre, int edad) {
        if (indice.containsKey(id)) {
            System.out.println("Error: El ID " + id + " ya existe.");
            return;
        }

        try (RandomAccessFile ArchivoInx = new RandomAccessFile(DATA_FILE, "rw")) {
            long posicion = ArchivoInx.length(); // Ir al final del archivo
            ArchivoInx.seek(posicion);

            // Guardar posición en el índice antes de escribir
            indice.put(id, posicion);

            // Escribir datos
            ArchivoInx.writeInt(id);         // 4 bytes
            ArchivoInx.writeUTF(nombre);     // Variable, añade 2 bytes de longitud al inicio
            ArchivoInx.writeInt(edad);       // 4 bytes

            System.out.println("Registro guardado con éxito en la posición de bytes: " + posicion);
        } catch (IOException e) {
            System.err.println("Error al escribir el registro: " + e.getMessage());
        }
    }
    
     
     // Busca un registro directamente saltando a la posición indicada por el índice.
     
    public static void buscarRegistro(int id) {
        if (!indice.containsKey(id)) {
            System.out.println("Registro con ID " + id + " no encontrado.");
            return;
        }

        long posicion = indice.get(id);

        try (RandomAccessFile file = new RandomAccessFile(DATA_FILE, "r")) {
            file.seek(posicion); // Acceso directo inmediato

            // Leer datos en el mismo orden de escritura
            int idLeido = file.readInt();
            String nombreLeido = file.readUTF();
            int edadLeida = file.readInt();

            System.out.println("Registro encontrado [ID: " + idLeido + ", Nombre: " + nombreLeido + ", Edad: " + edadLeida + "]");
        } catch (IOException e) {
            System.err.println("Error al leer el registro: " + e.getMessage());
        }
    }
    
    
    //Carga el archivo de índices a memoria al iniciar el programa.
    
    private static void cargarIndice() {
        File file = new File(INDEX_FILE);
        if (!file.exists()) return;

        try (DataInputStream inx = new DataInputStream(new FileInputStream(file))) {
            while (inx.available() > 0) {
                int id = inx.readInt();
                long pos = inx.readLong();
                indice.put(id, pos);
            }
            System.out.println("Índice cargado en memoria desde el disco.");
        } catch (IOException e) {
            System.err.println("Error al cargar el índice: " + e.getMessage());
        }
    }
    
    
    //Persiste el índice de memoria a disco al finalizar el programa.
   
    private static void guardarIndice() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(INDEX_FILE))) {
            for (Map.Entry<Integer, Long> entry : indice.entrySet()) {
                out.writeInt(entry.getKey());
                out.writeLong(entry.getValue());
            }
            System.out.println("Índice guardado en disco exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el índice: " + e.getMessage());
        }
    }

    

    public static void main(String[] args) {
               
        cargarIndice();

        // 1. Inserción de registros de ejemplo
        
        insertarRegistro(101, "Carlos Perez", 25);
        insertarRegistro(102, "Ana Martinez", 30);
        insertarRegistro(103, "Luis Gomez", 22); // Espacios fijos opcionales

        // 2. Búsqueda directa por índice
        buscarRegistro(102);
        buscarRegistro(105); // No existe

        guardarIndice();
        
    }
}
