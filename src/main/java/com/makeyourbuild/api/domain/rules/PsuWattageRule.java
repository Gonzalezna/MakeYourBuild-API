package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Psu;
import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.domain.model.Storage;

import java.util.List;

/**
 * Regla de compatibilidad: PSU debe tener suficiente potencia para todos los componentes.
 * Calcula: CPU TDP + GPU TDP + Motherboard power + RAM (5W por módulo) + Storage (10W por disco) + 20% overhead
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class PsuWattageRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Psu psu = context.getPsu();
        
        if (psu == null) {
            return RuleResult.valid(); // PSU es opcional en el MVP
        }
        
        int totalConsumption = calculateTotalConsumption(context);
        int recommendedWattage = (int)(totalConsumption * 1.2); // 20% overhead
        
        if (psu.getWattage() < recommendedWattage) {
            return RuleResult.error(
                ErrorCode.PSU_INSUFFICIENT,
                String.format(
                    "La PSU (%s) tiene %dW pero se recomiendan al menos %dW para esta configuración (consumo estimado: %dW + 20%% overhead)",
                    psu.getName(),
                    psu.getWattage(),
                    recommendedWattage,
                    totalConsumption
                ),
                "psu"
            );
        }
        
        return RuleResult.valid();
    }
    
    /**
     * Calcula el consumo total estimado de todos los componentes.
     */
    private int calculateTotalConsumption(BuildContext context) {
        int total = 0;
        
        // CPU TDP
        Cpu cpu = context.getCpu();
        if (cpu != null && cpu.getTdp() != null) {
            total += cpu.getTdp();
        }
        
        // GPU TDP
        Gpu gpu = context.getGpu();
        if (gpu != null && gpu.getTdp() != null) {
            total += gpu.getTdp();
        }
        
        // Motherboard power consumption
        Motherboard motherboard = context.getMotherboard();
        if (motherboard != null && motherboard.getPowerConsumption() != null) {
            total += motherboard.getPowerConsumption();
        }
        
        // RAM: 5W por módulo (sumar todos los módulos de todas las RAMs)
        List<Ram> rams = context.getRams();
        if (rams != null && !rams.isEmpty()) {
            int totalRamModules = rams.stream()
                .filter(ram -> ram.getModules() != null)
                .mapToInt(Ram::getModules)
                .sum();
            total += totalRamModules * 5;
        }
        
        // Storage: 10W por disco
        List<Storage> storages = context.getStorages();
        if (storages != null) {
            total += storages.size() * 10;
        }
        
        return total;
    }
    
    @Override
    public String getName() {
        return "PSU Wattage Sufficiency";
    }
}
