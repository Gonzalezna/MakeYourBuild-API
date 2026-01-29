package com.makeyourbuild.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakeYourBuildApiApplication {

	public static void main(String[] args) {
		// Diagnostic logging ANTES de que Spring Boot inicie
		// Esto nos permite ver las variables de entorno antes de que Spring intente usarlas
		System.out.println("==========================================");
		System.out.println("DIAGNOSTIC: Environment Variables (BEFORE Spring Boot)");
		System.out.println("==========================================");
		String dbUrl = System.getenv("DATABASE_URL");
		String dbUsername = System.getenv("DB_USERNAME");
		String dbPassword = System.getenv("DB_PASSWORD");
		
		System.out.println("DATABASE_URL: " + dbUrl);
		System.out.println("DB_USERNAME: " + (dbUsername != null ? dbUsername : "NULL/EMPTY"));
		System.out.println("DB_PASSWORD: " + (dbPassword != null ? "***SET***" : "NOT_SET"));
		
		// Verificar si las variables están vacías o tienen espacios
		if (dbUrl != null) {
			System.out.println("DATABASE_URL length: " + dbUrl.length());
			System.out.println("DATABASE_URL starts with jdbc: " + dbUrl.startsWith("jdbc:"));
		}
		if (dbUsername != null) {
			System.out.println("DB_USERNAME length: " + dbUsername.length());
			System.out.println("DB_USERNAME value: " + dbUsername);
		} else {
			System.out.println("DB_USERNAME is NULL - Esta es la causa del problema!");
		}
		System.out.println("CORS_ORIGINS: " + System.getenv("CORS_ORIGINS"));
		System.out.println("DDL_AUTO: " + System.getenv("DDL_AUTO"));
		System.out.println("PORT: " + System.getenv("PORT"));
		System.out.println("==========================================");
		
		SpringApplication.run(MakeYourBuildApiApplication.class, args);
	}

}
