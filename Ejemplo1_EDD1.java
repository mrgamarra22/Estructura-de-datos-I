/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo1_edd1;

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
import java.util.Scanner;

public class Ejemplo1_EDD1 {

    /**
     * @param args the command line arguments
     */
    
    public static void Escribir_Archivo(Scanner sc, String file_name){
        String Nombre, Cedula, Saldo;
        
        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", false); 
            PrintWriter registro_clientes = new PrintWriter(outFile);
            
            String hay_cliente;
            System.out.println("Tiene clientes para ingresar? si - no");
            hay_cliente = sc.nextLine();
            while(hay_cliente.equalsIgnoreCase("si")){
                System.out.println("Por favor ingrese el nombre del cliente");
                Nombre = sc.nextLine();
                System.out.println("Por favor ingrese la cédula del cliente");
                Cedula = sc.nextLine();
                System.out.println("Por favor ingrese el saldo del cliente");
                Saldo = sc.nextLine();
                
                while(Double.parseDouble(Saldo) < 0){
                    System.out.println("Ingrese nuevo saldo. El Saldo debe ser positivo");
                    Saldo = sc.nextLine();
                }
                if (!Nombre.isEmpty() && !Cedula.isEmpty() && !Saldo.isEmpty()){
                    registro_clientes.println(Nombre +"\t"+ Cedula +"\t"+ Saldo);
                }
                
                System.out.println("Hay registos? si - no");
                hay_cliente = sc.nextLine();  
                
            }
            registro_clientes.close();
        }
        catch (IOException ex) {
            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }
        
    }
    
    public static void Leer_Archivo(Scanner sc, String file_name){
                
            try {
                FileReader outFile = new FileReader(file_name+".txt");
                BufferedReader BufferLectura = new BufferedReader(outFile);
                
                String line = null; //definición de line
                while((line = BufferLectura.readLine()) != null){
                    String temp[] = line.split("\t");
                    // String temp[] ={1,2,3,4}
                    System.out.println("El nombre es" + temp[0]);
                    System.out.println("La cédula es" + temp[1]);
                    System.out.println("El saldo es" + temp[2]);
                    //System.out.println(temp[0] + ","+ temp[1] + ","+temp[2]);
                }
                BufferLectura.close();
                

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                file_name = sc.nextLine(); // Archivo
            }
        
    }
    
    public static void Buscar_Registro(Scanner sc, String file_name){
            try{
                FileReader outFile = new FileReader(file_name+".txt");
                BufferedReader BufferLectura = new BufferedReader(outFile);
                
                System.out.println("Digite la cédula del registro a buscar");
                String cedula_buscar = sc.nextLine();
                
                String line = null;
                while((line = BufferLectura.readLine()) != null){
                    String temp[] = line.split("\t");
                    if (temp[1].equals(cedula_buscar)){
                        System.out.println("El nombre es " + temp[0]);
                        System.out.println("La cédula es " + temp[1]);
                        System.out.println("El saldo es " + temp[2]);
                    }
           
                }
                BufferLectura.close();
                
                   
    } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                file_name = sc.nextLine(); // Archivo
            }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        
        //Crear el archivo
        /*
        System.out.println("Digite nombre del archivo");
        String file_name = sc.nextLine(); // Archivo
        Escribir_Archivo(sc, file_name);
        System.out.println("Archivo Creado!!!");
        */
        
        //Leer el archivo
        /*
        System.out.println("Digite nombre del archivo a leer");
        String file_name = sc.nextLine(); // Archivo
        Leer_Archivo(sc, file_name);
        System.out.println("Archivo Leido completamente!!!");
        */
        
        //Buscar registro
        System.out.println("Digite nombre del archivo a leer");
        String file_name = sc.nextLine(); // Archivo
        Buscar_Registro(sc, file_name);
        
    }
    
}
