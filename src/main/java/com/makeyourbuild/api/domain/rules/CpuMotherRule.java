package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;

/**
 * Regla bloqueante: el socket de la CPU debe coincidir con el de la motherboard.
 * Si falla, la configuración no es válida (ERROR).
 */
public class CpuMotherRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Cpu cpu = context.getCpu();
        Motherboard motherboard = context.getMotherboard();
        
        if (cpu == null || motherboard == null) {
            return RuleResult.valid(); // No se puede validar sin ambos componentes
        }
        
        if (!cpu.getSocket().equals(motherboard.getSocket())) {
            String cpuBrand = cpu.getBrand() != null ? cpu.getBrand() : "";
            String mbBrand = motherboard.getBrand() != null ? motherboard.getBrand() : "";

            return RuleResult.error(
                ErrorCode.CPU_SOCKET_MISMATCH,
                String.format(
                    "El socket de la CPU %s (%s) no es compatible con el socket de la motherboard %s (%s). No se pueden combinar componentes %s con %s",
                    cpuBrand,
                    cpu.getSocket(),
                    mbBrand,
                    motherboard.getSocket(),
                    cpuBrand,
                    mbBrand
                ),
                "cpu,motherboard"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "CPU-Motherboard Socket Compatibility";
    }
}
