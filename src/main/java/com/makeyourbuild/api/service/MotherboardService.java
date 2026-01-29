package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.dto.MotherboardDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.MotherboardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con Motherboards.
 */
@Service
@Transactional
public class MotherboardService {
    
    private final MotherboardRepository motherboardRepository;
    private final CpuService cpuService;
    
    public MotherboardService(MotherboardRepository motherboardRepository, CpuService cpuService) {
        this.motherboardRepository = motherboardRepository;
        this.cpuService = cpuService;
    }
    
    /**
     * Obtiene todas las motherboards.
     */
    public List<MotherboardDTO> getAllMotherboards() {
        return motherboardRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene motherboards compatibles con una CPU.
     * Regla: CPU.socket == Motherboard.socket
     */
    public List<MotherboardDTO> getCompatibleMotherboards(Long cpuId) {
        Cpu cpu = cpuService.getCpuEntityById(cpuId);
        List<Motherboard> motherboards = motherboardRepository.findBySocket(cpu.getSocket());
        return motherboards.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una motherboard por ID.
     */
    public MotherboardDTO getMotherboardById(Long id) {
        Motherboard motherboard = motherboardRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Motherboard", id));
        return toDTO(motherboard);
    }
    
    /**
     * Obtiene la entidad Motherboard por ID (para uso interno).
     */
    public Motherboard getMotherboardEntityById(Long id) {
        return motherboardRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Motherboard", id));
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private MotherboardDTO toDTO(Motherboard motherboard) {
        MotherboardDTO dto = new MotherboardDTO();
        dto.setId(motherboard.getId());
        dto.setName(motherboard.getName());
        dto.setBrand(motherboard.getBrand());
        dto.setSocket(motherboard.getSocket());
        dto.setRamType(motherboard.getRamType());
        dto.setMaxFrequency(motherboard.getMaxFrequency());
        dto.setPrice(motherboard.getPrice());
        dto.setChipset(motherboard.getChipset());
        dto.setFormFactor(motherboard.getFormFactor());
        dto.setRamSlots(motherboard.getRamSlots());
        dto.setSupportedCpuGenerations(motherboard.getSupportedCpuGenerations());
        dto.setPowerConsumption(motherboard.getPowerConsumption());
        dto.setM2Slots(motherboard.getM2Slots());
        dto.setSataPorts(motherboard.getSataPorts());
        return dto;
    }
}
