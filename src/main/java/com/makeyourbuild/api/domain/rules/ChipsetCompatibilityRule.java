package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;

import java.util.Arrays;
import java.util.List;

/**
 * Regla de compatibilidad: Validar compatibilidad de generación de CPU con chipset.
 * Algunos chipsets no soportan todas las generaciones de CPUs del mismo socket.
 * Esta es una regla BLOQUEANTE (ERROR).
 * 
 * Usa campos configurables (generation y supportedCpuGenerations) en lugar de hardcodeo.
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
            // Obtener marca de CPU para mensaje más claro (ej: "AMD", "Intel")
            String cpuBrand = cpu.getBrand() != null ? cpu.getBrand() : "";
            
            // Determinar fabricante del chipset a partir del socket de la motherboard
            String chipset = motherboard.getChipset() != null ? motherboard.getChipset() : "desconocido";
            String chipsetBrand = detectBrandBySocket(motherboard.getSocket());
            
            // Formatear generaciones soportadas con la marca del chipset
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
     * Determina la marca (Intel/AMD) a partir del socket de la motherboard.
     * Sockets que comienzan con LGA se consideran Intel, sockets que comienzan con AM se consideran AMD.
     */
    private String detectBrandBySocket(com.makeyourbuild.api.domain.enums.SocketType socket) {
        if (socket == null) {
            return "desconocido";
        }
        String name = socket.toString().toUpperCase();
        if (name.startsWith("LGA")) {
            return "Intel";
        }
        if (name.startsWith("AM")) {
            return "AMD";
        }
        return "desconocido";
    }
    
    /**
     * Formatea las generaciones soportadas para incluir la marca.
     * Entrada: "9th,10th,11th"
     * Salida: "Intel 9th, Intel 10th, Intel 11th"
     */
    private String formatSupportedGenerations(String generations, String brand) {
        if (generations == null || generations.isEmpty()) {
            return generations;
        }
        
        return Arrays.stream(generations.split(","))
            .map(String::trim)
            .filter(g -> !g.isEmpty())
            .map(gen -> {
                // Si ya tiene la marca (como "Ryzen 3000"), no agregarla
                if (gen.toLowerCase().contains("ryzen") || 
                    gen.toLowerCase().contains("intel") ||
                    gen.toLowerCase().contains("amd")) {
                    return gen;
                }
                return brand + " " + gen;
            })
            .reduce((a, b) -> a + ", " + b)
            .orElse(generations);
    }
    
    @Override
    public String getName() {
        return "Chipset-CPU Generation Compatibility";
    }
}
