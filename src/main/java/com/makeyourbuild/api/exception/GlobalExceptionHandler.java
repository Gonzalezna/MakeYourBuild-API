package com.makeyourbuild.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Manejador global de excepciones para toda la API.
 * Convierte excepciones en respuestas HTTP apropiadas.
 * 
 * IMPORTANTE: No expone detalles técnicos en las respuestas al cliente.
 * Los detalles se loguean internamente para debugging.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /**
     * Maneja excepciones de negocio.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, String>> handleBusinessException(BusinessException ex) {
        logger.warn("Business exception: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", ex.getMessage()));
    }
    
    /**
     * Maneja excepciones de entidades no encontradas.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.debug("Entity not found: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", ex.getMessage()));
    }
    
    /**
     * Maneja otros errores de runtime no manejados específicamente.
     * 
     * IMPORTANTE: No expone el mensaje de la excepción al cliente por seguridad.
     * Los detalles se loguean internamente.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        // Loguear el error completo internamente (con stack trace)
        logger.error("Unexpected runtime exception", ex);
        
        // Retornar mensaje genérico al cliente (sin detalles técnicos)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("error", "Error interno del servidor"));
    }
    
    /**
     * Maneja excepciones de validación.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.warn("Validation error: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", ex.getMessage()));
    }
}
