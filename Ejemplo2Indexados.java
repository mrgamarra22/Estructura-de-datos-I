/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemploindexados;

/**
 *
 * @author Margarita G
 */
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Ejemplo2Indexados {
    

    private static final String DATA_FILE = "productos.dat";
    private static final String INDEX_FILE = "productos.idx";
    
    // El nombre ocupará exactamente 20 caracteres (40 bytes en UTF-16)
    private static final int RECORD_SIZE = 4 + 40 + 8; // ID(4) + Nombre(40) + Precio(8) = 52 bytes

    public static void main(String[] args) {
        try {
            // 1. Escribir datos de prueba
            escribirProducto(101, "Laptop Asus         ", 850.50);
            escribirProducto(105, "Raton Optico        ", 25.00);
            escribirProducto(102, "Monitor Gamer       ", 300.99);

            // 2. Cargar índices a Memoria (Simulación de índice denso)
            Map<Integer, Long> indices = cargarIndices();

            // 3. Buscar un producto usando el índice (Búsqueda Directa)
            int idABuscar = 105;
            if (indices.containsKey(idABuscar)) {
                long posicionByte = indices.get(idABuscar);
                buscarProducto(posicionByte);
            } else {
                System.out.println("Producto no encontrado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guarda el registro en el archivo de datos y su posición en el archivo de índices
    public static void escribirProducto(int id, String nombre, double precio) throws IOException {
        try (RandomAccessFile df = new RandomAccessFile(DATA_FILE, "rw");
             RandomAccessFile ifile = new RandomAccessFile(INDEX_FILE, "rw")) {
            
            // Ir al final del archivo de datos para añadir el nuevo registro
            long posicionByte = df.length();
            df.seek(posicionByte);
            
            // Escribir datos
            df.writeInt(id);
            df.writeChars(nombre.substring(0, 20)); // Forzar tamaño fijo de cadena
            df.writeDouble(precio);

            // Guardar en el índice: ID y la posición de byte correspondiente
            ifile.seek(ifile.length());
            ifile.writeInt(id);
            ifile.writeLong(posicionByte);
        }
    }

    // Carga las parejas (ID -> Posición Byte) en un Map para búsquedas instantáneas
    public static Map<Integer, Long> cargarIndices() throws IOException {
        Map<Integer, Long> mapaIndices = new HashMap<>();
        try (RandomAccessFile ifile = new RandomAccessFile(INDEX_FILE, "r")) {
            while (ifile.getFilePointer() < ifile.length()) {
                int id = ifile.readInt();
                long pos = ifile.readLong();
                mapaIndices.put(id, pos);
            }
        }
        return mapaIndices;
    }

    // Accede directamente al registro sin recorrer el archivo secuencialmente
    public static void buscarProducto(long posicionByte) throws IOException {
        try (RandomAccessFile df = new RandomAccessFile(DATA_FILE, "r")) {
            df.seek(posicionByte); // Saltamos directo a la posición obtenida por el índice
            
            int id = df.readInt();
            
            // Leer los 20 caracteres del nombre
            StringBuilder nombreBuilder = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                nombreBuilder.append(df.readChar());
            }
            double precio = df.readDouble();

            System.out.println("--- PRODUCTO ENCONTRADO ---");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombreBuilder.toString().trim());
            System.out.println("Precio: $" + precio);
        }
    }
}

    
    
