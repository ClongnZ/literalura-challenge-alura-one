package com.literalura.principal;

import com.literalura.model.DatosApi;
import com.literalura.model.Libro;
import com.literalura.model.LibroDTO;
import com.literalura.service.ConsumoAPI;
import com.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convertir = new ConvierteDatos();
    private int opcion = 0;
    private boolean ok = false;

    private String menu = """
            \nElija la opción a través de su número:
            1 - Buscar libro por titulo
            2 - Listar libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos en un determinado año
            5 - Listar libros por idioma
            6 - Salir
            """;

    public void mostrarMenu(){
        do{
            do{
                try{
                    ok = false;
                    System.out.println(menu);
                    opcion = teclado.nextInt();
                    teclado.nextLine();
                }catch (InputMismatchException ime){
                    System.out.println("Opción no válida..");
                    ok = true;
                    teclado.nextLine();
                }
            }while (ok == true);

            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Fin de la aplicación...");
                    break;
                default:
                    System.out.println("Elija una opción del menú..");
            }
        }while (opcion != 6);
    }

}
