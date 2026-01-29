package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Gpu;

/**
 * Regla de compatibilidad: GPU length debe caber en el Case.
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class GpuCaseSizeRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Gpu gpu = context.getGpu();
        Case caseEntity = context.getCase();
        
        if (gpu == null || caseEntity == null) {
            return RuleResult.valid();
        }
        
        if (gpu.getLength() == null || caseEntity.getMaxGpuLength() == null) {
            return RuleResult.valid(); // No se puede validar sin dimensiones
        }
        
        if (gpu.getLength() > caseEntity.getMaxGpuLength()) {
            return RuleResult.error(
                ErrorCode.GPU_CASE_SIZE_EXCEEDED,
                String.format(
                    "La GPU (%s) es demasiado larga (%d mm) para el gabinete (%s) que soporta máximo %d mm",
                    gpu.getName(),
                    gpu.getLength(),
                    caseEntity.getName(),
                    caseEntity.getMaxGpuLength()
                ),
                "gpu,case"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "GPU-Case Size Compatibility";
    }
}
