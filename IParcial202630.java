/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.iparcial202630;

/**
 *
 * @author mrgamarra
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IParcial202630 {
    
    
    public static void ActualizarProductos(String Archivo1, String Archivo2) {
        
        try {
        FileReader outFile1 = new FileReader(Archivo1+".txt");
        BufferedReader BufferLectura1 = new BufferedReader(outFile1);

        FileReader outFile2 = new FileReader(Archivo2+".txt");
        BufferedReader BufferLectura2 = new BufferedReader(outFile2);
        
        FileWriter outFile = new FileWriter("ProductosActualizados" + ".txt", false); 
        PrintWriter registro_productos = new PrintWriter(outFile);

        //Leer la primera línea de cada archivo
        String line1 = BufferLectura1.readLine();
        String line2 = BufferLectura2.readLine();


        while((line1 != null && line2 != null )){
            
            //Las siguientes 4 líneas de código se colocaron al inicio del While
            //ya que necesitan que line1 y line2 sean diferentes de null.
            //Si estas líneas se dejan inmediatamente después de leer line1 y line2
            //se genera un error ya que line1 o line2 son nulos al finalizar el archivo.
            String temp1[] = line1.split(";");
            int Codigo1 = Integer.parseInt(temp1[0]);
            
            String temp2[] = line2.split(";");
            int Codigo2 = Integer.parseInt(temp2[0]);
            String Estado = temp2[4];
            
             
            if (Codigo1==Codigo2){
                if (Estado.equalsIgnoreCase("A")){
                    registro_productos.println(Codigo2 +";"+ temp2[1] +";"+ temp2[2]+";"+temp2[3]); 
                    System.out.println("Se escribió"+Codigo1);
                    
                }
                else {
                        System.out.println("Se eliminó"+Codigo2);
                    }
                line1 = BufferLectura1.readLine();
                line2 = BufferLectura2.readLine();

            }
            
            else {
                if (Codigo1<Codigo2){
                
                //Se escribe igual el registro de Productos_Actuales
                registro_productos.println(Codigo1 +";"+ temp1[1] +";"+ temp1[2]+";"+temp1[3]);
                System.out.println("Se escribió"+Codigo1);
                line1 = BufferLectura1.readLine();
                }
                
                else {
                
                //Se verifica que tenga estado A, porque se va a copiar la novedad
                if (Estado.equalsIgnoreCase("A")){
                    registro_productos.println(Codigo2 +";"+ temp2[1] +";"+ temp2[2]+";"+temp2[3]);  
                    System.out.println("Se escribió"+Codigo2);
                }  
                else {
                        System.out.println("Se eliminó"+Codigo2);
                    }
                //Se lee la siguiente línea, así se haya copiado o eliminado
                line2 = BufferLectura2.readLine();  
                
                }
                
            }


        }
        
        if (line1 == null){
                while((line2 != null )){
                    
                    String temp2[] = line2.split(";");
                    int Codigo2 = Integer.parseInt(temp2[0]);
                    String Estado = temp2[4];
                    
                    //Escribir_Archivo(Cedula2, temp2[1], temp2[2], "Sucursal3");
                    if (Estado.equalsIgnoreCase("A")){
                    registro_productos.println(Codigo2 +";"+ temp2[1] +";"+ temp2[2]+";"+temp2[3]);  
                    System.out.println("Se escribió"+Codigo2);
                    } 
                    else {
                        System.out.println("Se eliminó"+Codigo2);
                    }
                    
                    line2 = BufferLectura2.readLine();

                    
                }

            }

            if (line2 == null){
                while((line1 != null )){
                    
                    String temp1[] = line1.split(";");
                    int Codigo1 = Integer.parseInt(temp1[0]);
                    //Escribir_Archivo(Cedula1, temp1[1], temp1[2], "Sucursal3");
                    registro_productos.println(Codigo1 +";"+ temp1[1] +";"+ temp1[2]+";"+temp1[3]);
                    line1 = BufferLectura1.readLine();
                    System.out.println("Se escribió"+Codigo1);
                    
                }

            }
        
        BufferLectura1.close();
        BufferLectura2.close();
        registro_productos.close(); 
        System.out.println("Archivos mezclados");
        }
        
        catch (IOException ex) {
            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }    
    }
    

    public static void main(String[] args) {
        
        ActualizarProductos("Productos_Actuales", "Novedades_Productos");
    }
}
