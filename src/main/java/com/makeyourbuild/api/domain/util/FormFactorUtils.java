package com.makeyourbuild.api.domain.util;

import com.makeyourbuild.api.domain.enums.FormFactor;

/**
 * Utilidades auxiliares relacionadas con {@link FormFactor} (parsing desde texto).
 * La compatibilidad gabinete/motherboard vive en {@link FormFactor#isCompatible}.
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
