package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.RuleSeverity;
import com.makeyourbuild.api.domain.enums.WarningCode;

/**
 * Resultado de la evaluación de una regla de compatibilidad.
 */
public class RuleResult {
    
    private boolean valid;
    private RuleSeverity severity;
    private String message;
    private ErrorCode errorCode;
    private WarningCode warningCode;
    private String component; // Componente afectado: "cpu", "motherboard", "ram", "gpu", "psu", "case", "storage"
    
    public RuleResult(boolean valid, RuleSeverity severity, String message, ErrorCode errorCode, WarningCode warningCode, String component) {
        this.valid = valid;
        this.severity = severity;
        this.message = message;
        this.errorCode = errorCode;
        this.warningCode = warningCode;
        this.component = component;
    }
    
    /**
     * Crea un resultado de error (bloqueante).
     */
    public static RuleResult error(ErrorCode code, String message, String component) {
        return new RuleResult(false, RuleSeverity.ERROR, message, code, null, component);
    }
    
    /**
     * Crea un resultado de advertencia (no bloqueante).
     */
    public static RuleResult warning(WarningCode code, String message, String component) {
        return new RuleResult(true, RuleSeverity.WARNING, message, null, code, component);
    }
    
    /**
     * Crea un resultado válido (sin problemas).
     * Un resultado válido no tiene severidad, mensaje, ni códigos de error/advertencia.
     */
    public static RuleResult valid() {
        return new RuleResult(true, null, null, null, null, null);
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public RuleSeverity getSeverity() {
        return severity;
    }
    
    public String getMessage() {
        return message;
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public WarningCode getWarningCode() {
        return warningCode;
    }
    
    public String getComponent() {
        return component;
    }
    
    public boolean isError() {
        return severity == RuleSeverity.ERROR;
    }
    
    public boolean isWarning() {
        return severity == RuleSeverity.WARNING && message != null;
    }
}
