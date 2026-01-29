package com.makeyourbuild.api.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Clase temporal para diagnosticar variables de entorno en Railway.
 * Muestra qué valores está leyendo Spring Boot desde las variables de entorno.
 * 
 * TODO: Eliminar esta clase después de resolver el problema de deploy.
 */
@Configuration
public class EnvironmentLogger {
    
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentLogger.class);
    
    @Value("${spring.datasource.url:NOT_SET}")
    private String datasourceUrl;
    
    @Value("${spring.datasource.username:NOT_SET}")
    private String datasourceUsername;
    
    @Value("${spring.datasource.password:NOT_SET}")
    private String datasourcePassword;
    
    @Value("${cors.allowed-origins:NOT_SET}")
    private String corsOrigins;
    
    @PostConstruct
    public void logEnvironmentVariables() {
        logger.info("==========================================");
        logger.info("DIAGNOSTIC: Environment Variables");
        logger.info("==========================================");
        logger.info("DATABASE_URL (env): {}", System.getenv("DATABASE_URL"));
        logger.info("DB_USERNAME (env): {}", System.getenv("DB_USERNAME"));
        logger.info("DB_PASSWORD (env): {}", System.getenv("DB_PASSWORD") != null ? "***SET***" : "NOT_SET");
        logger.info("CORS_ORIGINS (env): {}", System.getenv("CORS_ORIGINS"));
        logger.info("DDL_AUTO (env): {}", System.getenv("DDL_AUTO"));
        logger.info("==========================================");
        logger.info("Spring Properties (after resolution):");
        logger.info("==========================================");
        logger.info("spring.datasource.url: {}", 
            datasourceUrl != null && datasourceUrl.length() > 60 
                ? datasourceUrl.substring(0, 60) + "..." 
                : datasourceUrl);
        logger.info("spring.datasource.username: {}", datasourceUsername);
        logger.info("spring.datasource.password: {}", 
            datasourcePassword != null && !datasourcePassword.equals("NOT_SET") 
                ? "***SET***" 
                : datasourcePassword);
        logger.info("cors.allowed-origins: {}", corsOrigins);
        logger.info("==========================================");
    }
}
