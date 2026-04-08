package com.makeyourbuild.api.domain.enums;

/**
 * Gama de un componente (CPU o GPU).
 * Determina el rango de rendimiento del componente.
 */
public enum ComponentTier {
    LOW,        // Gama de entrada
    MID,        // Gama media
    HIGH,       // Gama alta
    ENTHUSIAST  // Gama entusiasta / tope de gama
}
