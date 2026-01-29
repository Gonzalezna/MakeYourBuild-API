package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.Psu;
import com.makeyourbuild.api.dto.BuildRequestDTO;
import com.makeyourbuild.api.dto.PsuDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.PsuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con PSUs.
 */
@Service
@Transactional
public class PsuService {
    
    private final PsuRepository psuRepository;
    private final CpuService cpuService;
    private final GpuService gpuService;
    private final MotherboardService motherboardService;
    private final RamService ramService;
    
    public PsuService(PsuRepository psuRepository, CpuService cpuService, GpuService gpuService, 
                      MotherboardService motherboardService, RamService ramService) {
        this.psuRepository = psuRepository;
        this.cpuService = cpuService;
        this.gpuService = gpuService;
        this.motherboardService = motherboardService;
        this.ramService = ramService;
    }
    
    /**
     * Obtiene todas las PSUs.
     */
    public List<PsuDTO> getAllPsus() {
        return psuRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una PSU por ID.
     */
    public PsuDTO getPsuById(Long id) {
        Psu psu = psuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("PSU", id));
        return toDTO(psu);
    }
    
    /**
     * Obtiene la entidad PSU por ID (para uso interno).
     */
    public Psu getPsuEntityById(Long id) {
        return psuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("PSU", id));
    }
    
    /**
     * Obtiene PSUs compatibles con una configuración parcial de build.
     * Regla: PSU.wattage >= consumo total estimado + 20% overhead
     */
    public List<PsuDTO> getCompatiblePsus(BuildRequestDTO buildRequest) {
        // Calcular consumo total estimado
        int totalConsumption = calculateTotalConsumption(buildRequest);
        int recommendedWattage = (int)(totalConsumption * 1.2); // 20% overhead
        
        // Buscar PSUs con suficiente potencia
        List<Psu> compatiblePsus = psuRepository.findByWattageGreaterThanEqual(recommendedWattage);
        
        return compatiblePsus.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Calcula el consumo total estimado de los componentes seleccionados.
     */
    private int calculateTotalConsumption(BuildRequestDTO buildRequest) {
        int total = 0;
        
        // CPU TDP
        if (buildRequest.getCpuId() != null) {
            com.makeyourbuild.api.domain.model.Cpu cpu = cpuService.getCpuEntityById(buildRequest.getCpuId());
            if (cpu.getTdp() != null) {
                total += cpu.getTdp();
            }
        }
        
        // GPU TDP
        if (buildRequest.getGpuId() != null) {
            com.makeyourbuild.api.domain.model.Gpu gpu = gpuService.getGpuEntityById(buildRequest.getGpuId());
            if (gpu.getTdp() != null) {
                total += gpu.getTdp();
            }
        }
        
        // Motherboard power consumption
        if (buildRequest.getMotherboardId() != null) {
            com.makeyourbuild.api.domain.model.Motherboard motherboard = motherboardService.getMotherboardEntityById(buildRequest.getMotherboardId());
            if (motherboard.getPowerConsumption() != null) {
                total += motherboard.getPowerConsumption();
            }
        }
        
        // RAM: 5W por módulo (sumar todos los módulos de todas las RAMs)
        if (buildRequest.getRamIds() != null && !buildRequest.getRamIds().isEmpty()) {
            for (Long ramId : buildRequest.getRamIds()) {
                com.makeyourbuild.api.domain.model.Ram ram = ramService.getRamEntityById(ramId);
                if (ram.getModules() != null) {
                    total += ram.getModules() * 5;
                }
            }
        }
        
        // Storage: 10W por disco
        if (buildRequest.getStorageIds() != null && !buildRequest.getStorageIds().isEmpty()) {
            total += buildRequest.getStorageIds().size() * 10;
        }
        
        return total;
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private PsuDTO toDTO(Psu psu) {
        PsuDTO dto = new PsuDTO();
        dto.setId(psu.getId());
        dto.setName(psu.getName());
        dto.setBrand(psu.getBrand());
        dto.setWattage(psu.getWattage());
        dto.setPrice(psu.getPrice());
        dto.setEfficiency(psu.getEfficiency());
        dto.setModular(psu.getModular());
        dto.setFormFactor(psu.getFormFactor());
        return dto;
    }
}
