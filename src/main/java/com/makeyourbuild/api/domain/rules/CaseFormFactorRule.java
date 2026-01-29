package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.FormFactor;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.util.FormFactorUtils;

/**
 * Regla de compatibilidad: Case debe soportar el form factor de la motherboard.
 * Un Case ATX puede soportar mATX e ITX, pero un Case mATX no puede soportar ATX.
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class CaseFormFactorRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Case caseEntity = context.getCase();
        Motherboard motherboard = context.getMotherboard();
        
        if (caseEntity == null || motherboard == null) {
            return RuleResult.valid();
        }
        
        FormFactor caseFormFactor = caseEntity.getSupportedFormFactor();
        FormFactor mbFormFactor = FormFactorUtils.fromString(motherboard.getFormFactor());
        
        if (caseFormFactor == null || mbFormFactor == null) {
            return RuleResult.valid(); // No se puede validar sin form factors
        }
        
        // Verificar compatibilidad usando la utilidad centralizada
        if (!FormFactorUtils.isCompatible(caseFormFactor, mbFormFactor)) {
            return RuleResult.error(
                ErrorCode.CASE_FORM_FACTOR_INCOMPATIBLE,
                String.format(
                    "El gabinete (%s) soporta %s pero la motherboard requiere %s",
                    caseEntity.getName(),
                    caseFormFactor,
                    mbFormFactor
                ),
                "case,motherboard"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "Case-Motherboard Form Factor Compatibility";
    }
}
