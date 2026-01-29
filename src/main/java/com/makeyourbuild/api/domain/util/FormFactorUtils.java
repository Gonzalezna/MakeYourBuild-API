package com.makeyourbuild.api.domain.util;

import com.makeyourbuild.api.domain.enums.FormFactor;

/**
 * Utilidades para validación de compatibilidad de form factors entre gabinetes y motherboards.
 * 
 * Reglas de compatibilidad:
 * - Un gabinete puede albergar motherboards de su mismo form factor o más pequeñas
 * - EATX soporta: EATX, ATX, mATX, ITX
 * - ATX soporta: ATX, mATX, ITX
 * - mATX soporta: mATX, ITX
 * - ITX solo soporta: ITX
 */
public final class FormFactorUtils {
    
    /**
     * Constructor privado para prevenir instanciación.
     * Esta es una clase de utilidad con métodos estáticos únicamente.
     */
    private FormFactorUtils() {
        throw new UnsupportedOperationException("Clase de utilidad - no instanciar");
    }
    
    /**
     * Verifica si un form factor de gabinete es compatible con un form factor de motherboard.
     * 
     * @param caseFormFactor El form factor del gabinete (el tamaño máximo que soporta)
     * @param motherboardFormFactor El form factor de la motherboard
     * @return true si el gabinete puede albergar la motherboard, false en caso contrario
     */
    public static boolean isCompatible(FormFactor caseFormFactor, FormFactor motherboardFormFactor) {
        if (caseFormFactor == null || motherboardFormFactor == null) {
            return true; // No se puede validar sin form factors, asumir compatible
        }

        // Delegar la lógica de compatibilidad al propio enum de dominio
        return caseFormFactor.canHost(motherboardFormFactor);
    }
    
    /**
     * Convierte un string de form factor a su enum correspondiente.
     * 
     * @param formFactorString El string representando el form factor (ej: "ATX", "mATX", "ITX")
     * @return El FormFactor correspondiente, o null si no se reconoce
     */
    public static FormFactor fromString(String formFactorString) {
        if (formFactorString == null || formFactorString.trim().isEmpty()) {
            return null;
        }
        
        try {
            return FormFactor.valueOf(formFactorString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
