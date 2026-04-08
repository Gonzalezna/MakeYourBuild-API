package com.makeyourbuild.api.domain.enums;

/**
 * Form factors de gabinetes y motherboards.
 *
 * Cada valor conoce, a través de un "tamaño" relativo, qué otros form factors
 * puede alojar físicamente.
 *
 * <p>Reglas de compatibilidad (gabinete → motherboard): un gabinete puede alojar
 * motherboards de su mismo form factor o más pequeñas. Por ejemplo, EATX aloja
 * EATX/ATX/mATX/ITX; ATX aloja ATX/mATX/ITX; mATX aloja mATX/ITX; ITX solo ITX.
 */
public enum FormFactor {
    ITX(0),   // Mini ITX  (más pequeño)
    MATX(1),  // Micro ATX
    ATX(2),   // Standard ATX
    EATX(3);  // Extended ATX (más grande)

    /**
     * Rank de tamaño: a mayor valor, más grande es el form factor.
     * Un gabinete puede alojar motherboards de igual o menor rank.
     */
    private final int sizeRank;

    FormFactor(int sizeRank) {
        this.sizeRank = sizeRank;
    }

    /**
     * Verifica si el form factor del gabinete es compatible con el de la motherboard.
     * Si falta cualquiera de los dos, no se invalida (se asume compatible).
     *
     * @param caseFormFactor form factor soportado por el gabinete
     * @param motherboardFormFactor form factor de la motherboard
     * @return true si el gabinete puede alojar esa motherboard
     */
    public static boolean isCompatible(FormFactor caseFormFactor, FormFactor motherboardFormFactor) {
        if (caseFormFactor == null || motherboardFormFactor == null) {
            return true;
        }
        return caseFormFactor.canHost(motherboardFormFactor);
    }

    /**
     * Indica si este form factor (de gabinete) puede alojar una motherboard
     * del form factor dado, basándose en el tamaño relativo.
     *
     * @param motherboardFormFactor form factor de la motherboard
     * @return true si este form factor puede alojar al dado, false en caso contrario
     */
    public boolean canHost(FormFactor motherboardFormFactor) {
        if (motherboardFormFactor == null) {
            // Sin información de la motherboard, no invalidamos
            return true;
        }
        return this.sizeRank >= motherboardFormFactor.sizeRank;
    }
}
