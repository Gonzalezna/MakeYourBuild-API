package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.enums.FormFactor;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.util.FormFactorUtils;
import com.makeyourbuild.api.dto.CaseDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.CaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con Cases.
 */
@Service
@Transactional
public class CaseService {
    
    private final CaseRepository caseRepository;
    private final GpuService gpuService;
    private final MotherboardService motherboardService;
    
    public CaseService(CaseRepository caseRepository, GpuService gpuService, MotherboardService motherboardService) {
        this.caseRepository = caseRepository;
        this.gpuService = gpuService;
        this.motherboardService = motherboardService;
    }
    
    /**
     * Obtiene todos los Cases.
     */
    public List<CaseDTO> getAllCases() {
        return caseRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene un Case por ID.
     */
    public CaseDTO getCaseById(Long id) {
        Case caseEntity = caseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Case", id));
        return toDTO(caseEntity);
    }
    
    /**
     * Obtiene la entidad Case por ID (para uso interno).
     */
    public Case getCaseEntityById(Long id) {
        return caseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Case", id));
    }
    
    /**
     * Obtiene Cases compatibles con GPU y Motherboard.
     * Reglas:
     * - Case debe soportar el form factor de la motherboard
     * - Case debe tener suficiente espacio para la GPU (maxGpuLength >= gpu.length)
     */
    public List<CaseDTO> getCompatibleCases(Long gpuId, Long motherboardId) {
        List<Case> allCases = caseRepository.findAll();
        
        // Si no hay GPU ni motherboard, retornar todos
        if (gpuId == null && motherboardId == null) {
            return allCases.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        }
        
        // Obtener GPU si está presente
        final com.makeyourbuild.api.domain.model.Gpu gpu = gpuId != null 
            ? gpuService.getGpuEntityById(gpuId) 
            : null;
        
        // Obtener Motherboard si está presente
        final com.makeyourbuild.api.domain.model.Motherboard motherboard = motherboardId != null 
            ? motherboardService.getMotherboardEntityById(motherboardId) 
            : null;
        
        // Filtrar cases compatibles
        return allCases.stream()
            .filter(caseEntity -> {
                // Validar form factor con motherboard
                if (motherboard != null) {
                    if (!isFormFactorCompatible(caseEntity, motherboard)) {
                        return false;
                    }
                }
                
                // Validar tamaño GPU con case
                if (gpu != null && gpu.getLength() != null && caseEntity.getMaxGpuLength() != null) {
                    if (gpu.getLength() > caseEntity.getMaxGpuLength()) {
                        return false;
                    }
                }
                
                return true;
            })
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Verifica si el form factor del case es compatible con el de la motherboard.
     * Delega la lógica a FormFactorUtils para evitar duplicación de código.
     */
    private boolean isFormFactorCompatible(Case caseEntity, com.makeyourbuild.api.domain.model.Motherboard motherboard) {
        FormFactor caseFormFactor = caseEntity.getSupportedFormFactor();
        FormFactor mbFormFactor = FormFactorUtils.fromString(motherboard.getFormFactor());
        
        return FormFactorUtils.isCompatible(caseFormFactor, mbFormFactor);
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private CaseDTO toDTO(Case caseEntity) {
        CaseDTO dto = new CaseDTO();
        dto.setId(caseEntity.getId());
        dto.setName(caseEntity.getName());
        dto.setBrand(caseEntity.getBrand());
        dto.setSupportedFormFactor(caseEntity.getSupportedFormFactor());
        dto.setPrice(caseEntity.getPrice());
        dto.setMaxGpuLength(caseEntity.getMaxGpuLength());
        dto.setMaxCpuCoolerHeight(caseEntity.getMaxCpuCoolerHeight());
        dto.setStorage25Slots(caseEntity.getStorage25Slots());
        dto.setStorage35Slots(caseEntity.getStorage35Slots());
        dto.setIncludesFans(caseEntity.getIncludesFans());
        dto.setFanSlots(caseEntity.getFanSlots());
        return dto;
    }
}
