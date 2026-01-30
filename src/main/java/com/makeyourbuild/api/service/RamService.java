package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.dto.RamDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.RamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con RAMs.
 */
@Service
@Transactional
public class RamService {
    
    private final RamRepository ramRepository;
    private final MotherboardService motherboardService;
    
    public RamService(RamRepository ramRepository, MotherboardService motherboardService) {
        this.ramRepository = ramRepository;
        this.motherboardService = motherboardService;
    }
    
    /**
     * Obtiene todas las RAMs.
     */
    public List<RamDTO> getAllRams() {
        return ramRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene RAMs compatibles con CPU + Motherboard.
     * Reglas:
     * - Motherboard.ramType == RAM.type
     * - RAM.frequency <= Motherboard.maxFrequency
     * - No exceder slots disponibles (considerando RAMs ya seleccionadas)
     * - No exceder capacidad máxima de RAM (calculada según tipo y slots)
     * 
     * @param cpuId ID del CPU (opcional, para futuras validaciones)
     * @param motherboardId ID de la motherboard (requerido)
     * @param existingRamIds Lista de IDs de RAMs ya seleccionadas (opcional)
     */
    public List<RamDTO> getCompatibleRams(Long cpuId, Long motherboardId, List<Long> existingRamIds) {
        final Motherboard motherboard = motherboardService.getMotherboardEntityById(motherboardId);
        
        // Obtener todas las RAMs del tipo correcto y frecuencia válida
        List<Ram> allCompatibleRams = ramRepository.findByTypeAndFrequencyLessThanEqual(
            motherboard.getRamType(),
            motherboard.getMaxFrequency()
        );
        
        // Si no hay RAMs ya seleccionadas, retornar todas las compatibles
        if (existingRamIds == null || existingRamIds.isEmpty()) {
            return allCompatibleRams.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        }
        
        // Contar slots y capacidad ya usados
        final int[] usedSlots = {0};
        final int[] usedCapacity = {0};
        
        List<Ram> existingRams = existingRamIds.stream()
            .map(id -> ramRepository.findById(id).orElse(null))
            .filter(r -> r != null)
            .collect(Collectors.toList());
        
        for (Ram ram : existingRams) {
            if (ram.getModules() != null) {
                usedSlots[0] += ram.getModules();
            }
            if (ram.getCapacity() != null) {
                usedCapacity[0] += ram.getCapacity();
            }
        }
        
        // Calcular capacidad máxima según tipo de RAM y slots
        Integer maxRamCapacity = calculateMaxRamCapacity(motherboard);
        
        // Filtrar RAMs que no excedan slots ni capacidad máxima
        return allCompatibleRams.stream()
            .filter(ram -> {
                // Verificar slots disponibles
                if (motherboard.getRamSlots() != null) {
                    boolean isNewRam = !existingRamIds.contains(ram.getId());
                    int totalSlots = usedSlots[0] + (isNewRam && ram.getModules() != null ? ram.getModules() : 0);
                    if (totalSlots > motherboard.getRamSlots()) {
                        return false;
                    }
                }
                
                // Verificar capacidad máxima
                if (maxRamCapacity != null) {
                    boolean isNewRam = !existingRamIds.contains(ram.getId());
                    int totalCapacity = usedCapacity[0] + (isNewRam && ram.getCapacity() != null ? ram.getCapacity() : 0);
                    if (totalCapacity > maxRamCapacity) {
                        return false;
                    }
                }
                
                return true;
            })
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Calcula la capacidad máxima de RAM soportada por la motherboard.
     * Límites conservadores basados en estándares comunes:
     * - DDR4: hasta 32GB por módulo
     * - DDR5: hasta 64GB por módulo
     */
    private Integer calculateMaxRamCapacity(Motherboard motherboard) {
        if (motherboard.getRamSlots() == null) {
            return null; // No se puede calcular sin slots
        }
        
        int maxCapacityPerModule;
        switch (motherboard.getRamType()) {
            case DDR4:
                maxCapacityPerModule = 32; // GB por módulo (DDR4 común: hasta 32GB)
                break;
            case DDR5:
                maxCapacityPerModule = 64; // GB por módulo (DDR5 puede llegar a 64GB)
                break;
            default:
                maxCapacityPerModule = 32; // Conservador por defecto
        }
        
        return motherboard.getRamSlots() * maxCapacityPerModule;
    }
    
    /**
     * Obtiene una RAM por ID.
     */
    public RamDTO getRamById(Long id) {
        Ram ram = ramRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("RAM", id));
        return toDTO(ram);
    }
    
    /**
     * Obtiene la entidad Ram por ID (para uso interno).
     */
    public Ram getRamEntityById(Long id) {
        return ramRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("RAM", id));
    }
    
    /**
     * Convierte entidad a DTO.
     * Incluye la frecuencia en el nombre para que sea visible en los listados.
     */
    private RamDTO toDTO(Ram ram) {
        RamDTO dto = new RamDTO();
        dto.setId(ram.getId());
        
        // Construir nombre con frecuencia incluida para listados
        String nameWithFrequency = buildRamNameWithFrequency(ram);
        dto.setName(nameWithFrequency);
        
        dto.setBrand(ram.getBrand());
        dto.setType(ram.getType());
        dto.setFrequency(ram.getFrequency());
        dto.setCapacity(ram.getCapacity());
        dto.setModules(ram.getModules());
        dto.setPrice(ram.getPrice());
        dto.setCasLatency(ram.getCasLatency());
        return dto;
    }
    
    /**
     * Construye el nombre de la RAM incluyendo la frecuencia.
     * Formato: "Nombre Original DDR4 3200MHz"
     * 
     * @param ram Entidad RAM
     * @return Nombre con frecuencia incluida
     */
    private String buildRamNameWithFrequency(Ram ram) {
        String originalName = ram.getName();
        Integer frequency = ram.getFrequency();
        
        if (frequency == null) {
            return originalName; // Si no hay frecuencia, retornar nombre original
        }
        
        // Verificar si el nombre ya incluye la frecuencia
        String frequencyStr = frequency + "MHz";
        if (originalName.contains(frequencyStr) || originalName.contains(frequency.toString())) {
            return originalName; // Ya tiene la frecuencia, no duplicar
        }
        
        // Agregar tipo de RAM y frecuencia al final
        String ramType = ram.getType() != null ? ram.getType().toString() : "";
        String suffix = ramType.isEmpty() ? frequencyStr : ramType + " " + frequencyStr;
        
        return originalName + " " + suffix;
    }
}
