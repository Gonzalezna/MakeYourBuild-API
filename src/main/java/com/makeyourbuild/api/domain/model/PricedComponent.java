package com.makeyourbuild.api.domain.model;

import java.math.BigDecimal;

/**
 * Interfaz que representa un componente con precio.
 * Permite tratar de manera uniforme todos los componentes de hardware que tienen un precio.
 */
public interface PricedComponent {
    /**
     * Obtiene el precio del componente.
     *
     * @return El precio del componente, o null si no tiene precio asignado
     */
    BigDecimal getPrice();
}
