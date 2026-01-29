package com.makeyourbuild.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakeYourBuildApiApplication {

	public static void main(String[] args) {
		// Diagnostic logging ANTES de que Spring Boot inicie
		System.out.println("==========================================");
		System.out.println("DIAGNOSTIC: Environment Variables (BEFORE Spring Boot)");
		System.out.println("==========================================");
		
		// Leer variables de entorno
		String dbUrl = System.getenv("DATABASE_URL");
		String dbUsername = System.getenv("DB_USERNAME");
		String dbPassword = System.getenv("DB_PASSWORD");
		String corsOrigins = System.getenv("CORS_ORIGINS");
		String ddlAuto = System.getenv("DDL_AUTO");
		String showSql = System.getenv("SHOW_SQL");
		String logLevel = System.getenv("LOG_LEVEL");
		String securityLogLevel = System.getenv("SECURITY_LOG_LEVEL");
		String hibernateSqlLog = System.getenv("HIBERNATE_SQL_LOG");
		
		// Logging de diagnóstico
		System.out.println("DATABASE_URL: " + dbUrl);
		System.out.println("DB_USERNAME: " + (dbUsername != null ? dbUsername : "NULL/EMPTY"));
		System.out.println("DB_PASSWORD: " + (dbPassword != null ? "***SET***" : "NOT_SET"));
		
		// SOLUCIÓN: Establecer propiedades del sistema desde variables de entorno
		// Esto asegura que Spring Boot las lea correctamente, incluso si hay problemas
		// con la resolución de variables en application.properties
		if (dbUrl != null && !dbUrl.isEmpty()) {
			System.setProperty("spring.datasource.url", dbUrl);
			System.out.println("✓ Set spring.datasource.url from DATABASE_URL");
		}
		if (dbUsername != null && !dbUsername.isEmpty()) {
			System.setProperty("spring.datasource.username", dbUsername);
			System.out.println("✓ Set spring.datasource.username from DB_USERNAME");
		}
		if (dbPassword != null && !dbPassword.isEmpty()) {
			System.setProperty("spring.datasource.password", dbPassword);
			System.out.println("✓ Set spring.datasource.password from DB_PASSWORD");
		}
		
		// Establecer otras propiedades importantes
		if (corsOrigins != null && !corsOrigins.isEmpty()) {
			System.setProperty("cors.allowed-origins", corsOrigins);
			System.out.println("✓ Set cors.allowed-origins from CORS_ORIGINS");
		}
		
		if (ddlAuto != null && !ddlAuto.isEmpty()) {
			System.setProperty("spring.jpa.hibernate.ddl-auto", ddlAuto);
			System.out.println("✓ Set spring.jpa.hibernate.ddl-auto from DDL_AUTO");
		}
		
		if (showSql != null && !showSql.isEmpty()) {
			System.setProperty("spring.jpa.show-sql", showSql);
		}
		
		if (logLevel != null && !logLevel.isEmpty()) {
			System.setProperty("logging.level.com.makeyourbuild.api", logLevel);
		}
		
		if (securityLogLevel != null && !securityLogLevel.isEmpty()) {
			System.setProperty("logging.level.org.springframework.security", securityLogLevel);
		}
		
		if (hibernateSqlLog != null && !hibernateSqlLog.isEmpty()) {
			System.setProperty("logging.level.org.hibernate.SQL", hibernateSqlLog);
		}
		
		System.out.println("==========================================");
		
		SpringApplication.run(MakeYourBuildApiApplication.class, args);
	}

}
