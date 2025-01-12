package com.literalura.principal;

import com.literalura.exceptions.LibroDuplicado;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.model.LibroDTO;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import com.literalura.service.ConsumoAPI;
import com.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos convertir = new ConvierteDatos();
    private int opcion = 0;
    private boolean ok = false;



    LibroRepository repositorioLibro;
    AutorRepository repositorioAutor;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository){
        this.repositorioLibro = libroRepository;
        this.repositorioAutor = autorRepository;
    }

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
                    buscarLibro();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Fin de la aplicación...");
                    break;
                default:
                    System.out.println("Elija una opción del menú..");
            }
        }while (opcion != 6);
    }

    private void buscarLibro(){
        System.out.println("Introduce el nombre del libro que deseas buscar: ");
        var libroBuscado = teclado.nextLine();

        try{
            //Comprueba si el libro ya existe en la base de datos
            Optional<Libro> libroEnDB = repositorioLibro.findByTituloContainsIgnoreCase(libroBuscado);
            if(libroEnDB.isPresent()){
                throw  new LibroDuplicado("El libro que buscas ya existe en la base de datos");
            }

            //Obtiene datos de API
            var datosApi = consumoAPI.obtenerDatos(URL_BASE + libroBuscado.replace(" ", "%20"));
            //Datos obtenidos
//            System.out.println(datosApi);


            //Convierte datos
            LibroDTO datos = convertir.obtenerDatos(datosApi, LibroDTO.class);

            //Comprueva si el autor ya existe en la base de datos
            Optional<Autor> autorEnDB = repositorioAutor.findByNombreIgnoreCase(datos.autores().get(0).nombre());
            Autor autor;
            if(autorEnDB.isPresent()){
                autor = autorEnDB.get();
            }else{
                autor = new Autor(datos.autores().get(0));
                autor = repositorioAutor.save(autor);
            }
            //Crea entidad
            Libro libro = new Libro(datos);
            //Agrega autor al libro
            libro.setAutor(autor);

            System.out.println(libro.toString());
            //Guarda libro en DB
            repositorioLibro.save(libro);
            System.out.println("Libro guardado en la base de datos con éxito");


        } catch (LibroDuplicado e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void listarLibrosRegistrados(){
        List<Libro> libros = repositorioLibro.listarLibros();
        System.out.println("Libros registrados: ");
        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        List<Autor> autores = repositorioAutor.listarAutores();
        System.out.println("Autores encontrados: ");
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosAnio(){
        System.out.println("Introduce el año: ");
        var busquedaAnio = teclado.nextInt();
        List<Autor> autores = repositorioAutor.listarAutoresVivosAnio(busquedaAnio);

        if(autores.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año " + busquedaAnio);
        }else{
            System.out.println("Autores encontrados: ");
            autores.forEach(System.out::println);
        }
        teclado.nextLine();
    }

    private void listarLibrosPorIdioma(){
        String idioma = """
            Elige un idioma:\s
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
           \s""";
        String opcionIdioma;
        boolean ok = false;
        List<Libro> libros = null;

        while (!ok){
            System.out.println(idioma);
            opcionIdioma = teclado.nextLine();

            switch (opcionIdioma){
                case "es":
                case "en":
                case "fr":
                case "pt":
                    libros = repositorioLibro.listarPorIdioma(opcionIdioma);
                    ok = true;
                    break;
                default:
                    System.out.println("Idioma no válido. Selecciona una opción correcta");
                    break;
            }
        }

        System.out.println("Libros encontrados: ");
        libros.forEach(System.out::println);

    }

}
