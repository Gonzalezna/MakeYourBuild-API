package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Regla de advertencia: Validar que todas las RAMs tengan la misma frecuencia.
 * Mezclar frecuencias diferentes puede causar que todas funcionen a la frecuencia más baja,
 * limitando el rendimiento de los módulos más rápidos.
 * Esta es una regla de ADVERTENCIA (no bloqueante).
 */
public class RamFrequencyMismatchRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        List<Ram> rams = context.getRams();
        
        if (rams == null || rams.size() <= 1) {
            return RuleResult.valid(); // Necesita al menos 2 RAMs para comparar
        }
        
        // Obtener todas las frecuencias únicas
        Set<Integer> frequencies = rams.stream()
            .filter(ram -> ram.getFrequency() != null)
            .map(Ram::getFrequency)
            .collect(Collectors.toSet());
        
        // Si hay más de una frecuencia, generar advertencia
        if (frequencies.size() > 1) {
            String frequenciesList = frequencies.stream()
                .map(f -> f + " MHz")
                .collect(Collectors.joining(", "));
            return RuleResult.warning(
                WarningCode.RAM_FREQUENCY_MISMATCH,
                String.format(
                    "Se están usando RAMs con diferentes frecuencias (%s). Todas funcionarán a la frecuencia más baja, limitando el rendimiento de los módulos más rápidos. Se recomienda usar módulos con la misma frecuencia",
                    frequenciesList
                ),
                "ram"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "RAM Frequency Mismatch Warning";
    }
}
