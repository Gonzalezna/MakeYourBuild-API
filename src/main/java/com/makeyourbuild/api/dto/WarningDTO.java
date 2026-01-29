package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.WarningCode;

/**
 * DTO para advertencias de compatibilidad.
 * Permite al frontend identificar programáticamente el tipo de advertencia.
 */
public class WarningDTO {
    
    private WarningCode code;
    private String message;
    private String component;
    
    public WarningDTO() {}
    
    public WarningDTO(WarningCode code, String message, String component) {
        this.code = code;
        this.message = message;
        this.component = component;
    }
    
    public WarningCode getCode() {
        return code;
    }
    
    public void setCode(WarningCode code) {
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
