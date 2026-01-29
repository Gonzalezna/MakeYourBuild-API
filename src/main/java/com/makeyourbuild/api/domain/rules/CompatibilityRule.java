package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.model.BuildContext;

/**
 * Interfaz para reglas de compatibilidad.
 * Las reglas deben estar desacopladas del framework y vivir en el dominio.
 */
public interface CompatibilityRule {
    
    /**
     * Evalúa la regla de compatibilidad sobre el contexto de build.
     * 
     * @param context El contexto de la configuración de PC
     * @return El resultado de la evaluación
     */
    RuleResult evaluate(BuildContext context);
    
    /**
     * Nombre descriptivo de la regla.
     */
    String getName();
}
