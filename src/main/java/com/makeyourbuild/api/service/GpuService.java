package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.dto.GpuDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.GpuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con GPUs.
 */
@Service
@Transactional
public class GpuService {
    
    private final GpuRepository gpuRepository;
    
    public GpuService(GpuRepository gpuRepository) {
        this.gpuRepository = gpuRepository;
    }
    
    /**
     * Obtiene todas las GPUs.
     */
    public List<GpuDTO> getAllGpus() {
        return gpuRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una GPU por ID.
     */
    public GpuDTO getGpuById(Long id) {
        Gpu gpu = gpuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GPU", id));
        return toDTO(gpu);
    }
    
    /**
     * Obtiene la entidad GPU por ID (para uso interno).
     */
    public Gpu getGpuEntityById(Long id) {
        return gpuRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("GPU", id));
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private GpuDTO toDTO(Gpu gpu) {
        GpuDTO dto = new GpuDTO();
        dto.setId(gpu.getId());
        dto.setName(gpu.getName());
        dto.setBrand(gpu.getBrand());
        dto.setTdp(gpu.getTdp());
        dto.setPrice(gpu.getPrice());
        dto.setPcieVersion(gpu.getPcieVersion());
        dto.setLength(gpu.getLength());
        dto.setWidth(gpu.getWidth());
        dto.setHeight(gpu.getHeight());
        dto.setVram(gpu.getVram());
        dto.setTier(gpu.getTier());
        return dto;
    }
}
