package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.dto.CpuDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.CpuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con CPUs.
 */
@Service
@Transactional
public class CpuService {
    
    private final CpuRepository cpuRepository;
    
    public CpuService(CpuRepository cpuRepository) {
        this.cpuRepository = cpuRepository;
    }
    
    /**
     * Obtiene todas las CPUs.
     */
    public List<CpuDTO> getAllCpus() {
        return cpuRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una CPU por ID.
     */
    public CpuDTO getCpuById(Long id) {
        Cpu cpu = cpuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("CPU", id));
        return toDTO(cpu);
    }
    
    /**
     * Obtiene la entidad CPU por ID (para uso interno).
     */
    public Cpu getCpuEntityById(Long id) {
        return cpuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("CPU", id));
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private CpuDTO toDTO(Cpu cpu) {
        CpuDTO dto = new CpuDTO();
        dto.setId(cpu.getId());
        dto.setName(cpu.getName());
        dto.setBrand(cpu.getBrand());
        dto.setSocket(cpu.getSocket());
        dto.setPrice(cpu.getPrice());
        dto.setCores(cpu.getCores());
        dto.setThreads(cpu.getThreads());
        dto.setBaseClock(cpu.getBaseClock());
        dto.setBoostClock(cpu.getBoostClock());
        dto.setTdp(cpu.getTdp());
        dto.setTier(cpu.getTier());
        dto.setMinRamFrequency(cpu.getMinRamFrequency());
        dto.setGeneration(cpu.getGeneration());
        return dto;
    }
}
