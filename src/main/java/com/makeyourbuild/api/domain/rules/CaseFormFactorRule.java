package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.FormFactor;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.util.FormFactorUtils;

/**
 * Regla bloqueante: el gabinete debe poder alojar el form factor de la motherboard.
 * Un case más grande puede alojar placas más pequeñas; al revés, no.
 * Si falla, la configuración no es válida (ERROR).
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
        
        if (!FormFactor.isCompatible(caseFormFactor, mbFormFactor)) {
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
