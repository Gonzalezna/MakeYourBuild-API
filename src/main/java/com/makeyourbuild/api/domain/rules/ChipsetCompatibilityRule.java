package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Regla bloqueante: la generación de la CPU debe estar entre las soportadas por el chipset.
 * Algunos chipsets no soportan todas las generaciones de CPUs del mismo socket.
 * Si falla, la configuración no es válida (ERROR).
 * <p>
 * Usa los campos configurables {@code generation} y {@code supportedCpuGenerations}.
 */
public class ChipsetCompatibilityRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        Cpu cpu = context.getCpu();
        Motherboard motherboard = context.getMotherboard();
        
        if (cpu == null || motherboard == null) {
            return RuleResult.valid(); // No se puede validar sin ambos componentes
        }
        
        // Si la CPU no tiene generación definida, no validar (asumimos compatibilidad)
        if (cpu.getGeneration() == null || cpu.getGeneration().isEmpty()) {
            return RuleResult.valid();
        }
        
        // Si la motherboard no tiene generaciones soportadas definidas, no validar (asumimos compatibilidad)
        if (motherboard.getSupportedCpuGenerations() == null || 
            motherboard.getSupportedCpuGenerations().isEmpty()) {
            return RuleResult.valid();
        }
        
        // Parsear las generaciones soportadas (separadas por comas)
        String supportedGenerations = motherboard.getSupportedCpuGenerations();
        List<String> supportedList = Arrays.asList(
            supportedGenerations.split(",")
        ).stream()
            .map(String::trim)
            .map(String::toUpperCase)
            .toList();
        
        String cpuGeneration = cpu.getGeneration().trim().toUpperCase();
        
        // Validar si la generación de la CPU está en la lista de generaciones soportadas
        if (!supportedList.contains(cpuGeneration)) {
            String cpuBrand = cpu.getBrand() != null ? cpu.getBrand() : "";
            String chipset = motherboard.getChipset() != null ? motherboard.getChipset() : "desconocido";
            String chipsetBrand = motherboard.getBrand() != null ? motherboard.getBrand() : "";
            String formattedGenerations = formatSupportedGenerations(
                motherboard.getSupportedCpuGenerations(),
                chipsetBrand
            );
            
            return RuleResult.error(
                ErrorCode.CHIPSET_INCOMPATIBLE,
                String.format(
                    "El chipset %s (%s) no es compatible con CPUs %s de generación %s. Generaciones soportadas: %s",
                    chipset,
                    chipsetBrand,
                    cpuBrand,
                    cpu.getGeneration(),
                    formattedGenerations
                ),
                "cpu,motherboard"
            );
        }
        
        return RuleResult.valid();
    }
    
    /**
     * Formatea las generaciones soportadas para incluir la marca de la motherboard.
     * Entrada: "9th,10th,11th", brand: "Intel"
     * Salida: "Intel 9th, Intel 10th, Intel 11th"
     * Si la generación ya contiene la marca, no la duplica.
     */
    private String formatSupportedGenerations(String generations, String brand) {
        if (generations == null || generations.isEmpty()) {
            return generations;
        }

        String brandLower = brand != null ? brand.toLowerCase() : "";
        return Arrays.stream(generations.split(","))
            .map(String::trim)
            .filter(g -> !g.isEmpty())
            .map(gen -> {
                if (!brandLower.isEmpty() && !gen.toLowerCase().contains(brandLower)) {
                    return brand + " " + gen;
                }
                return gen;
            })
            .collect(Collectors.joining(", "));
    }
    
    @Override
    public String getName() {
        return "Chipset-CPU Generation Compatibility";
    }
}
