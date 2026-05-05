package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Regla de advertencia: usar varias RAMs con frecuencias distintas (conviven pero la m?s baja limita al resto).
 * No bloquea la build (WARNING).
 */
public class RamFrequencyMismatchRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        List<Ram> rams = context.getRams();
        
        if (rams == null || rams.size() <= 1) {
            return RuleResult.valid(); // Necesita al menos 2 RAMs para comparar
        }
        
        // Obtener todas las frecuencias ?nicas
        Set<Integer> frequencies = rams.stream()
            .filter(ram -> ram.getFrequency() != null)
            .map(Ram::getFrequency)
            .collect(Collectors.toSet());
        
        // Si hay m?s de una frecuencia, generar advertencia
        if (frequencies.size() > 1) {
            String frequenciesList = frequencies.stream()
                .map(f -> f + " MHz")
                .collect(Collectors.joining(", "));
            return RuleResult.warning(
                WarningCode.RAM_FREQUENCY_MISMATCH,
                String.format(
                    "Se est?n usando RAMs con diferentes frecuencias (%s). Todas funcionar?n a la frecuencia m?s baja, limitando el rendimiento de los m?dulos m?s r?pidos. Se recomienda usar m?dulos con la misma frecuencia",
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
