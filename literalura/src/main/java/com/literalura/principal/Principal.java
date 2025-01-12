package com.literalura.principal;

import com.literalura.model.DatosApi;
import com.literalura.model.Libro;
import com.literalura.model.LibroDTO;
import com.literalura.service.ConsumoAPI;
import com.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convertir = new ConvierteDatos();

    public Libro obtenerDatosLibro(){
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine().toLowerCase();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        LibroDTO datos = convertir.obtenerDatos(json, LibroDTO.class);

        return new Libro(datos);


    }

}
