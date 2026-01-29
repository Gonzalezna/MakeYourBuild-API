package com.makeyourbuild.api.domain.enums;

/**
 * Severidad de una regla de compatibilidad.
 */
public enum RuleSeverity {
    ERROR,        // Bloqueante - la configuración no es válida
    WARNING       // Advertencia - la configuración funciona pero no es recomendada
}
