package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Ram;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Regla de advertencia: Validar que todas las RAMs sean de la misma marca.
 * Mezclar marcas diferentes puede causar problemas de compatibilidad y estabilidad.
 * Esta es una regla de ADVERTENCIA (no bloqueante).
 */
public class RamBrandMismatchRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        List<Ram> rams = context.getRams();
        
        if (rams == null || rams.size() <= 1) {
            return RuleResult.valid(); // Necesita al menos 2 RAMs para comparar
        }
        
        // Obtener todas las marcas únicas (case-insensitive)
        Set<String> brands = rams.stream()
            .filter(ram -> ram.getBrand() != null)
            .map(ram -> ram.getBrand().trim())
            .filter(brand -> !brand.isEmpty())
            .collect(Collectors.toSet());
        
        // Si hay más de una marca, generar advertencia
        if (brands.size() > 1) {
            String brandsList = String.join(", ", brands);
            return RuleResult.warning(
                WarningCode.RAM_BRAND_MISMATCH,
                String.format(
                    "Se están usando RAMs de diferentes marcas (%s). Se recomienda usar módulos de la misma marca para mejor compatibilidad y estabilidad",
                    brandsList
                ),
                "ram"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "RAM Brand Mismatch Warning";
    }
}
