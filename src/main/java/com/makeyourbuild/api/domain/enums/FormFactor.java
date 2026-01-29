package com.makeyourbuild.api.domain.enums;

/**
 * Form factors de gabinetes y motherboards.
 *
 * Cada valor conoce, a través de un "tamaño" relativo, qué otros form factors
 * puede alojar físicamente.
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
