package com.makeyourbuild.api.domain.enums;

/**
 * Códigos de error para identificación programática en el frontend.
 * Cada código corresponde a un tipo específico de error de compatibilidad.
 */
public enum ErrorCode {
    CPU_SOCKET_MISMATCH,
    CHIPSET_INCOMPATIBLE,
    RAM_TYPE_MISMATCH,
    RAM_FREQUENCY_EXCEEDED,
    RAM_SLOTS_EXCEEDED,
    GPU_CASE_SIZE_EXCEEDED,
    PSU_INSUFFICIENT,
    CASE_FORM_FACTOR_INCOMPATIBLE,
    STORAGE_CASE_SLOTS_25_EXCEEDED,
    STORAGE_CASE_SLOTS_35_EXCEEDED,
    STORAGE_M2_SLOTS_EXCEEDED,
    STORAGE_SATA_PORTS_EXCEEDED
}
