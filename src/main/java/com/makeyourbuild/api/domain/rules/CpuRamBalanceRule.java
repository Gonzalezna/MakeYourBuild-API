package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;

/**
 * Regla de balance: Validar que una CPU de gama alta o enthusiast no esté combinada con RAM muy lenta.
 * Se aplica a CPUs con tier "high" o "enthusiast".
 * Esta es una regla de ADVERTENCIA (no bloqueante).
 */
public class CpuRamBalanceRule implements CompatibilityRule {
    
    // Frecuencias consideradas "bajas" para CPUs de gama alta
    private static final int LOW_FREQUENCY_THRESHOLD_DDR4 = 2666; // MHz
    private static final int LOW_FREQUENCY_THRESHOLD_DDR5 = 4800; // MHz
    
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
        
        // Validar cada RAM - si alguna tiene frecuencia muy baja, generar advertencia
        for (Ram ram : rams) {
            // Determinar umbral según tipo de RAM
            int lowFrequencyThreshold = ram.getType().toString().equals("DDR5") 
                ? LOW_FREQUENCY_THRESHOLD_DDR5 
                : LOW_FREQUENCY_THRESHOLD_DDR4;
            
            // Validar si la frecuencia es muy baja para una CPU de gama alta
            if (ram.getFrequency() < lowFrequencyThreshold) {
                return RuleResult.warning(
                    WarningCode.CPU_RAM_BALANCE,
                    String.format(
                        "Una CPU de gama alta puede verse limitada por RAM de baja frecuencia (%d MHz). Se recomienda al menos %d MHz para aprovechar mejor el rendimiento",
                        ram.getFrequency(),
                        lowFrequencyThreshold
                    ),
                    "cpu,ram"
                );
            }
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "CPU-RAM Balance Check";
    }
}
