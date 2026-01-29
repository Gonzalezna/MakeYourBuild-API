package com.makeyourbuild.api.domain.enums;

/**
 * Códigos de advertencia para identificación programática en el frontend.
 * Cada código corresponde a un tipo específico de advertencia de compatibilidad.
 */
public enum WarningCode {
    RAM_CAPACITY_LOW,
    RAM_FREQUENCY_BELOW_RECOMMENDED,
    CPU_RAM_BALANCE,
    PCIE_VERSION_MISMATCH,
    RAM_BRAND_MISMATCH,        // Múltiples marcas de RAM diferentes
    RAM_FREQUENCY_MISMATCH     // Múltiples frecuencias de RAM diferentes
}
