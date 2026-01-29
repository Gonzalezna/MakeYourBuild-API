package com.makeyourbuild.api.exception;

/**
 * Excepción de negocio para errores relacionados con reglas de dominio.
 */
public class BusinessException extends RuntimeException {
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
