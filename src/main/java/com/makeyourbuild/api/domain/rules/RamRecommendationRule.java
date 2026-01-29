package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;

/**
 * Regla de recomendación: CPU de gama alta o enthusiast con menos de 16GB de RAM.
 * Se aplica a CPUs con tier "high" o "enthusiast".
 * Esta es una regla de ADVERTENCIA (no bloqueante).
 */
public class RamRecommendationRule implements CompatibilityRule {
    
    private static final int MINIMUM_RAM_FOR_HIGH_END_CPU = 16; // GB
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Cpu cpu = context.getCpu();
        List<Ram> rams = context.getRams();
        
        if (cpu == null || rams == null || rams.isEmpty()) {
            return RuleResult.valid();
        }
        
        // Verificar si la CPU es de gama alta o enthusiast
        String tier = cpu.getTier();
        boolean isHighEndCpu = "high".equalsIgnoreCase(tier) || "enthusiast".equalsIgnoreCase(tier);
        
        if (!isHighEndCpu) {
            return RuleResult.valid(); // Solo aplicar a CPUs de gama alta o enthusiast
        }
        
        // Sumar la capacidad total de todas las RAMs
        int totalCapacity = rams.stream()
            .filter(ram -> ram.getCapacity() != null)
            .mapToInt(Ram::getCapacity)
            .sum();
        
        if (totalCapacity < MINIMUM_RAM_FOR_HIGH_END_CPU) {
            return RuleResult.warning(
                WarningCode.RAM_CAPACITY_LOW,
                String.format(
                    "Se recomienda al menos %dGB de RAM para CPUs de gama alta. Actualmente tienes %dGB",
                    MINIMUM_RAM_FOR_HIGH_END_CPU,
                    totalCapacity
                ),
                "ram,cpu"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "RAM Recommendation for High-End CPU";
    }
}
