package com.literalura;

import com.literalura.principal.Principal;
import com.literalura.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("****************Prueba**************");
//		//inicio prueba ConsumoAPI
//		ConsumoAPI consumoAPI = new ConsumoAPI();
//		Scanner teclado = new Scanner(System.in);
//
//		System.out.println("Ingresar nombre del libro: ");
//
//		var libro = teclado.nextLine();
//		var json = consumoAPI
//				.obtenerDatos("http://gutendex.com/books/?search="+libro.replace(" ","%20"));
//
//		System.out.println("Datos obtenidos: ");
//		System.out.println(json);
//		//fin prueba consumoAPI

		Principal principal = new Principal();
		principal.mostrarMenu();

	}
}
