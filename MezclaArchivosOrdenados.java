/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mezclaarchivosordenados;

/**
 *
 * @author Margarita G
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class MezclaArchivosOrdenados {
    
    //Se eliminó el método Escribir_Archivo ya que el archivo quedaba mal escrito
    //ya que se hacía un close cada vez que se grababa una línea (registro_clientes.println)
    //esto generaba que el archivo quedara solamente con el último registro guardado.
    

    public static void main(String[] args) {
        
        try {
        FileReader outFile1 = new FileReader("Sucursal1"+".txt");
        BufferedReader BufferLectura1 = new BufferedReader(outFile1);

        FileReader outFile2 = new FileReader("Sucursal2"+".txt");
        BufferedReader BufferLectura2 = new BufferedReader(outFile2);
        
        FileWriter outFile = new FileWriter("Sucursal3" + ".txt", false); 
        PrintWriter registro_clientes = new PrintWriter(outFile);

        //Leer la primera línea de cada archivo
        String line1 = BufferLectura1.readLine();
        String line2 = BufferLectura2.readLine();


        while((line1 != null && line2 != null )){
            
            //Las siguientes 4 líneas de código se colocaron al inicio del While
            //ya que necesitan que line1 y line2 sean diferentes de null.
            //Si estas líneas se dejan inmediatamente después de leer line1 y line2
            //se genera un error ya que line1 o line2 son nulos al finalizar el archivo.
            String temp1[] = line1.split("\t");
            int Cedula1 = Integer.parseInt(temp1[0]);
            
            String temp2[] = line2.split("\t");
            int Cedula2 = Integer.parseInt(temp2[0]);
             
            if (Cedula1<Cedula2){
                
                //Escribir_Archivo(Cedula1, temp1[1], temp1[2], "Sucursal3");
                registro_clientes.println(Cedula1 +"\t"+ temp1[1] +"\t"+ temp1[2]);
                System.out.println("Se escribió"+Cedula1);
                line1 = BufferLectura1.readLine();
                
            }

            else {
                
                //Escribir_Archivo(Cedula2, temp2[1], temp2[2], "Sucursal3");
                registro_clientes.println(Cedula2 +"\t"+ temp2[1] +"\t"+ temp2[2]);
                System.out.println("Se escribió"+Cedula2);
                line2 = BufferLectura2.readLine();  
                
            }

            


        }
        
        if (line1 == null){
                while((line2 != null )){
                    
                    String temp2[] = line2.split("\t");
                    int Cedula2 = Integer.parseInt(temp2[0]);
                    //Escribir_Archivo(Cedula2, temp2[1], temp2[2], "Sucursal3");
                    registro_clientes.println(Cedula2 +"\t"+ temp2[1] +"\t"+ temp2[2]);
                    line2 = BufferLectura2.readLine();
                    System.out.println("Se escribió"+Cedula2);
                    
                }

            }

            if (line2 == null){
                while((line1 != null )){
                    
                    String temp1[] = line1.split("\t");
                    int Cedula1 = Integer.parseInt(temp1[0]);
                    //Escribir_Archivo(Cedula1, temp1[1], temp1[2], "Sucursal3");
                    registro_clientes.println(Cedula1 +"\t"+ temp1[1] +"\t"+ temp1[2]);
                    line1 = BufferLectura1.readLine();
                    System.out.println("Se escribió"+Cedula1);
                    
                }

            }
        
        BufferLectura1.close();
        BufferLectura2.close();
        registro_clientes.close(); 
        System.out.println("Archivos mezclados");
        }
        
        catch (IOException ex) {
            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }


    }
        
        
}
