package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;

/**
 * Regla bloqueante: tipo y frecuencia de RAM compatibles con la motherboard.
 * <ul>
 *   <li>{@code Motherboard.ramType == RAM.type}</li>
 *   <li>{@code RAM.frequency <= Motherboard.maxFrequency}</li>
 * </ul>
 * Si falla, la configuración no es válida (ERROR).
 */
public class MotherRamRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Motherboard motherboard = context.getMotherboard();
        List<Ram> rams = context.getRams();
        
        if (motherboard == null || rams == null || rams.isEmpty()) {
            return RuleResult.valid(); // No se puede validar sin ambos componentes
        }
        
        // Validar cada RAM individualmente
        for (Ram ram : rams) {
            // Validar tipo de RAM
            if (!motherboard.getRamType().equals(ram.getType())) {
                return RuleResult.error(
                    ErrorCode.RAM_TYPE_MISMATCH,
                    String.format(
                        "El tipo de RAM (%s) no es compatible con el tipo soportado por la motherboard (%s)",
                        ram.getType(),
                        motherboard.getRamType()
                    ),
                    "ram,motherboard"
                );
            }
            
            // Validar frecuencia máxima
            if (ram.getFrequency() > motherboard.getMaxFrequency()) {
                return RuleResult.error(
                    ErrorCode.RAM_FREQUENCY_EXCEEDED,
                    String.format(
                        "La frecuencia de la RAM (%d MHz) excede la frecuencia máxima soportada por la motherboard (%d MHz)",
                        ram.getFrequency(),
                        motherboard.getMaxFrequency()
                    ),
                    "ram,motherboard"
                );
            }
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "Motherboard-RAM Compatibility";
    }
}
