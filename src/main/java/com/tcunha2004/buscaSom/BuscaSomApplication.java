package com.tcunha2004.buscaSom;

import com.tcunha2004.buscaSom.repositorio.ArtistaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscaSomApplication implements CommandLineRunner {

	@Autowired
	ArtistaRepositorio repositorio;

	public static void main(String[] args) {
		SpringApplication.run(BuscaSomApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositorio);
		main.menu();
	}
}
