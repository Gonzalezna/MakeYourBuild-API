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
		System.out.println("DATABASE_URL: " + System.getenv("DATABASE_URL"));
		System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD: " + (System.getenv("DB_PASSWORD") != null ? "***SET***" : "NOT_SET"));
		System.out.println("CORS_ORIGINS: " + System.getenv("CORS_ORIGINS"));
		System.out.println("DDL_AUTO: " + System.getenv("DDL_AUTO"));
		System.out.println("PORT: " + System.getenv("PORT"));
		System.out.println("==========================================");
		
		SpringApplication.run(MakeYourBuildApiApplication.class, args);
	}

}
