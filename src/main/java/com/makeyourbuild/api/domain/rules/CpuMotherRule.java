package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;

/**
 * Regla de compatibilidad: CPU.socket == Motherboard.socket
 * Esta es una regla BLOQUEANTE (ERROR).
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
            // Obtener marcas para mensaje más claro
            String cpuBrand = cpu.getBrand() != null ? cpu.getBrand() : "";
            String mbBrand = motherboard.getBrand() != null ? motherboard.getBrand() : "";
            
            // Determinar fabricante del socket (Intel usa LGA, AMD usa AM)
            String cpuSocketBrand = cpu.getSocket().toString().startsWith("LGA") ? "Intel" : "AMD";
            String mbSocketBrand = motherboard.getSocket().toString().startsWith("LGA") ? "Intel" : "AMD";
            
            return RuleResult.error(
                ErrorCode.CPU_SOCKET_MISMATCH,
                String.format(
                    "El socket de la CPU %s (%s) no es compatible con el socket de la motherboard %s (%s). No se pueden combinar componentes %s con %s",
                    cpuBrand,
                    cpu.getSocket(),
                    mbBrand,
                    motherboard.getSocket(),
                    cpuSocketBrand,
                    mbSocketBrand
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
