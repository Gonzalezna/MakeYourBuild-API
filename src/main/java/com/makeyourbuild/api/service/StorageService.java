package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.enums.StorageType;
import com.makeyourbuild.api.domain.model.Storage;
import com.makeyourbuild.api.dto.StorageDTO;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con Storage.
 */
@Service
@Transactional
public class StorageService {
    
    private final StorageRepository storageRepository;
    private final MotherboardService motherboardService;
    private final CaseService caseService;
    
    public StorageService(StorageRepository storageRepository, MotherboardService motherboardService, CaseService caseService) {
        this.storageRepository = storageRepository;
        this.motherboardService = motherboardService;
        this.caseService = caseService;
    }
    
    /**
     * Obtiene todos los Storage.
     */
    public List<StorageDTO> getAllStorages() {
        return storageRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene un Storage por ID.
     */
    public StorageDTO getStorageById(Long id) {
        Storage storage = storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage", id));
        return toDTO(storage);
    }
    
    /**
     * Obtiene la entidad Storage por ID (para uso interno).
     */
    public Storage getStorageEntityById(Long id) {
        return storageRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Storage", id));
    }
    
    /**
     * Obtiene Storages compatibles con Motherboard y Case.
     * Reglas:
     * - NVMe M.2: motherboard debe tener slots M.2 disponibles
     * - SATA (SSD 2.5" y HDD 3.5"): motherboard debe tener puertos SATA y case debe tener slots disponibles
     * 
     * @param motherboardId ID de la motherboard (opcional)
     * @param caseId ID del case (opcional)
     * @param existingStorageIds Lista de IDs de storages ya seleccionados (para contar slots usados)
     */
    public List<StorageDTO> getCompatibleStorages(Long motherboardId, Long caseId, List<Long> existingStorageIds) {
        List<Storage> allStorages = storageRepository.findAll();
        
        // Si no hay motherboard ni case, retornar todos
        if (motherboardId == null && caseId == null) {
            return allStorages.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        }
        
        // Obtener motherboard si está presente
        final com.makeyourbuild.api.domain.model.Motherboard motherboard = motherboardId != null 
            ? motherboardService.getMotherboardEntityById(motherboardId) 
            : null;
        
        // Obtener case si está presente
        final com.makeyourbuild.api.domain.model.Case caseEntity = caseId != null 
            ? caseService.getCaseEntityById(caseId) 
            : null;
        
        // Contar storages existentes por tipo
        final int[] existingM2Count = {0};
        final int[] existingSataCount = {0};
        final int[] existing25Count = {0};
        final int[] existing35Count = {0};
        
        if (existingStorageIds != null && !existingStorageIds.isEmpty()) {
            List<Storage> existingStorages = 
                existingStorageIds.stream()
                    .map(id -> storageRepository.findById(id).orElse(null))
                    .filter(s -> s != null)
                    .collect(Collectors.toList());
            
            for (Storage storage : existingStorages) {
                if (storage.getType() == StorageType.NVME_SSD) {
                    existingM2Count[0]++;
                } else if (storage.getType() == StorageType.SATA_SSD || 
                          storage.getType() == StorageType.HDD ||
                          storage.getType() == StorageType.SATA_HDD) {
                    existingSataCount[0]++;
                    
                    // Contar por form factor para slots de case
                    if (storage.getFormFactor() != null && storage.getFormFactor().equals("2.5\"")) {
                        existing25Count[0]++;
                    } else if (storage.getFormFactor() != null && storage.getFormFactor().equals("3.5\"")) {
                        existing35Count[0]++;
                    }
                }
            }
        }
        
        // Filtrar storages compatibles
        return allStorages.stream()
            .filter(storage -> {
                // Validar NVMe M.2 contra motherboard
                if (storage.getType() == StorageType.NVME_SSD) {
                    if (motherboard != null && motherboard.getM2Slots() != null) {
                        // Verificar si hay slots M.2 disponibles (contando el storage actual si no está en existingStorageIds)
                        boolean isNewStorage = existingStorageIds == null || !existingStorageIds.contains(storage.getId());
                        int m2Count = existingM2Count[0] + (isNewStorage ? 1 : 0);
                        if (m2Count > motherboard.getM2Slots()) {
                            return false;
                        }
                    }
                }
                
                // Validar SATA contra motherboard y case
                if (storage.getType() == StorageType.SATA_SSD || 
                    storage.getType() == StorageType.HDD ||
                    storage.getType() == StorageType.SATA_HDD) {
                    
                    // Validar puertos SATA en motherboard
                    if (motherboard != null && motherboard.getSataPorts() != null) {
                        boolean isNewStorage = existingStorageIds == null || !existingStorageIds.contains(storage.getId());
                        int sataCount = existingSataCount[0] + (isNewStorage ? 1 : 0);
                        if (sataCount > motherboard.getSataPorts()) {
                            return false;
                        }
                    }
                    
                    // Validar slots en case
                    if (caseEntity != null) {
                        boolean isNewStorage = existingStorageIds == null || !existingStorageIds.contains(storage.getId());
                        
                        // Validar slots 2.5"
                        if (storage.getFormFactor() != null && storage.getFormFactor().equals("2.5\"")) {
                            if (caseEntity.getStorage25Slots() != null) {
                                int count25 = existing25Count[0] + (isNewStorage ? 1 : 0);
                                if (count25 > caseEntity.getStorage25Slots()) {
                                    return false;
                                }
                            }
                        }
                        
                        // Validar slots 3.5"
                        if (storage.getFormFactor() != null && storage.getFormFactor().equals("3.5\"")) {
                            if (caseEntity.getStorage35Slots() != null) {
                                int count35 = existing35Count[0] + (isNewStorage ? 1 : 0);
                                if (count35 > caseEntity.getStorage35Slots()) {
                                    return false;
                                }
                            }
                        }
                    }
                }
                
                return true;
            })
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Convierte entidad a DTO.
     */
    private StorageDTO toDTO(Storage storage) {
        StorageDTO dto = new StorageDTO();
        dto.setId(storage.getId());
        dto.setName(storage.getName());
        dto.setBrand(storage.getBrand());
        dto.setType(storage.getType());
        dto.setCapacity(storage.getCapacity());
        dto.setPrice(storage.getPrice());
        dto.setReadSpeed(storage.getReadSpeed());
        dto.setWriteSpeed(storage.getWriteSpeed());
        dto.setFormFactor(storage.getFormFactor());
        return dto;
    }
}
