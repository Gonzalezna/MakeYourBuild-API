package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.domain.model.Motherboard;

/**
 * Regla de compatibilidad: GPU PCIe version debe ser compatible con motherboard.
 * PCIe es retrocompatible, pero es mejor advertir si hay diferencia significativa.
 * Esta es una regla de ADVERTENCIA (no bloqueante).
 * 
 * NOTA: Esta regla es actualmente un PLACEHOLDER para futuras implementaciones.
 * PCIe es retrocompatible por diseño, por lo que cualquier GPU funcionará en cualquier
 * motherboard moderna. Sin embargo, se pueden agregar advertencias en el futuro para:
 * - GPU PCIe 5.0 en motherboard PCIe 3.0 (pérdida significativa de ancho de banda)
 * - GPU PCIe 4.0 en motherboard PCIe 3.0 (pérdida menor de rendimiento)
 * 
 * El WarningCode.PCIE_VERSION_MISMATCH ya existe en el enum para cuando se implemente.
 */
public class GpuPcieRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Gpu gpu = context.getGpu();
        Motherboard motherboard = context.getMotherboard();
        
        if (gpu == null || motherboard == null) {
            return RuleResult.valid();
        }
        
        // TODO: Implementar advertencias para diferencias significativas de versión PCIe
        // Por ahora, cualquier GPU PCIe funciona en cualquier motherboard PCIe debido
        // a la retrocompatibilidad del estándar PCIe.
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "GPU PCIe Compatibility";
    }
}
