package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;

/**
 * Regla de compatibilidad: Validar frecuencia mínima recomendada de RAM según CPU.
 * Esta es una regla de ADVERTENCIA (no bloqueante, pero recomendada).
 */
public class RamFrequencyMinimumRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Cpu cpu = context.getCpu();
        List<Ram> rams = context.getRams();
        
        if (cpu == null || rams == null || rams.isEmpty()) {
            return RuleResult.valid();
        }
        
        // Si la CPU no tiene frecuencia mínima definida, no validar
        if (cpu.getMinRamFrequency() == null) {
            return RuleResult.valid();
        }
        
        // Validar cada RAM - si alguna está por debajo, generar advertencia
        for (Ram ram : rams) {
            if (ram.getFrequency() < cpu.getMinRamFrequency()) {
                return RuleResult.warning(
                    WarningCode.RAM_FREQUENCY_BELOW_RECOMMENDED,
                    String.format(
                        "Se recomienda al menos %d MHz de frecuencia de RAM para esta CPU. La RAM seleccionada tiene %d MHz",
                        cpu.getMinRamFrequency(),
                        ram.getFrequency()
                    ),
                    "ram,cpu"
                );
            }
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "RAM Frequency Minimum Recommendation";
    }
}
