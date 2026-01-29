package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;

/**
 * Regla de compatibilidad: Validar que los módulos de RAM no excedan los slots disponibles.
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class RamSlotsRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Motherboard motherboard = context.getMotherboard();
        List<Ram> rams = context.getRams();
        
        if (motherboard == null || rams == null || rams.isEmpty()) {
            return RuleResult.valid(); // No se puede validar sin ambos componentes
        }
        
        // Si la motherboard no tiene información de slots, no validar
        if (motherboard.getRamSlots() == null) {
            return RuleResult.valid();
        }
        
        // Sumar todos los módulos de todas las RAMs
        int totalModules = rams.stream()
            .filter(ram -> ram.getModules() != null)
            .mapToInt(Ram::getModules)
            .sum();
        
        // Validar que los módulos de RAM no excedan los slots disponibles
        if (totalModules > motherboard.getRamSlots()) {
            return RuleResult.error(
                ErrorCode.RAM_SLOTS_EXCEEDED,
                String.format(
                    "La cantidad total de módulos de RAM (%d) excede los slots disponibles en la motherboard (%d)",
                    totalModules,
                    motherboard.getRamSlots()
                ),
                "ram,motherboard"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "RAM Slots Compatibility";
    }
}
