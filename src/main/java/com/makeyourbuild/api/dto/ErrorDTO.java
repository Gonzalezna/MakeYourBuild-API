package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.ErrorCode;

/**
 * DTO para errores de compatibilidad.
 * Permite al frontend identificar programáticamente el tipo de error.
 */
public class ErrorDTO {
    
    private ErrorCode code;
    private String message;
    private String component;
    
    public ErrorDTO() {}
    
    public ErrorDTO(ErrorCode code, String message, String component) {
        this.code = code;
        this.message = message;
        this.component = component;
    }
    
    public ErrorCode getCode() {
        return code;
    }
    
    public void setCode(ErrorCode code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getComponent() {
        return component;
    }
    
    public void setComponent(String component) {
        this.component = component;
    }
}
