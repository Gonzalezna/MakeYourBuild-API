package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Storage;
import com.makeyourbuild.api.domain.rules.*;
import com.makeyourbuild.api.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para validar configuraciones de PC y calcular presupuestos.
 */
@Service
@Transactional
public class BuildService {
    
    private final CpuService cpuService;
    private final MotherboardService motherboardService;
    private final RamService ramService;
    private final StorageService storageService;
    private final GpuService gpuService;
    private final PsuService psuService;
    private final CaseService caseService;
    
    // Lista de reglas de compatibilidad
    private final List<CompatibilityRule> rules;
    
    public BuildService(CpuService cpuService, 
                       MotherboardService motherboardService, 
                       RamService ramService,
                       StorageService storageService,
                       GpuService gpuService,
                       PsuService psuService,
                       CaseService caseService) {
        this.cpuService = cpuService;
        this.motherboardService = motherboardService;
        this.ramService = ramService;
        this.storageService = storageService;
        this.gpuService = gpuService;
        this.psuService = psuService;
        this.caseService = caseService;
        
        // Inicializar reglas de compatibilidad
        this.rules = new ArrayList<>();
        // Reglas bloqueantes (ERROR)
        this.rules.add(new CpuMotherRule());
        this.rules.add(new MotherRamRule());
        this.rules.add(new RamSlotsRule());
        this.rules.add(new ChipsetCompatibilityRule());
        this.rules.add(new GpuCaseSizeRule());
        this.rules.add(new PsuWattageRule());
        this.rules.add(new CaseFormFactorRule());
        this.rules.add(new StorageCaseSlotsRule());
        this.rules.add(new StorageMotherboardM2Rule());
        // Reglas de advertencia (WARNING)
        this.rules.add(new RamRecommendationRule());
        this.rules.add(new RamFrequencyMinimumRule());
        this.rules.add(new CpuRamBalanceRule());
        this.rules.add(new GpuPcieRule());
        this.rules.add(new RamBrandMismatchRule());
        this.rules.add(new RamFrequencyMismatchRule());
    }
    
    /**
     * Valida una configuración de PC y retorna el resultado.
     */
    public BuildResponseDTO validateBuild(BuildRequestDTO request) {
        // Obtener componentes como DTOs
        CpuDTO cpu = cpuService.getCpuById(request.getCpuId());
        MotherboardDTO motherboard = motherboardService.getMotherboardById(request.getMotherboardId());
        
        // Cargar múltiples RAMs
        List<RamDTO> ramDTOs = null;
        List<com.makeyourbuild.api.domain.model.Ram> ramEntities = null;
        if (request.getRamIds() != null && !request.getRamIds().isEmpty()) {
            ramDTOs = request.getRamIds().stream()
                .map(ramService::getRamById)
                .collect(Collectors.toList());
            ramEntities = request.getRamIds().stream()
                .map(ramService::getRamEntityById)
                .collect(Collectors.toList());
        }
        
        // Convertir a entidades para el contexto de validación
        BuildContext context = new BuildContext(
            cpuService.getCpuEntityById(request.getCpuId()),
            motherboardService.getMotherboardEntityById(request.getMotherboardId()),
            ramEntities != null ? ramEntities : new ArrayList<>()
        );
        
        // Cargar componentes opcionales
        if (request.getStorageIds() != null && !request.getStorageIds().isEmpty()) {
            List<Storage> storages = request.getStorageIds().stream()
                .map(storageService::getStorageEntityById)
                .collect(Collectors.toList());
            context.setStorages(storages);
        }
        
        if (request.getGpuId() != null) {
            context.setGpu(gpuService.getGpuEntityById(request.getGpuId()));
        }
        
        if (request.getPsuId() != null) {
            context.setPsu(psuService.getPsuEntityById(request.getPsuId()));
        }
        
        if (request.getCaseId() != null) {
            context.setCase(caseService.getCaseEntityById(request.getCaseId()));
        }
        
        // Crear respuesta
        BuildResponseDTO response = new BuildResponseDTO();
        response.setCpu(cpu);
        response.setMotherboard(motherboard);
        response.setRams(ramDTOs);
        
        // Agregar componentes opcionales a la respuesta
        if (context.getStorages() != null && !context.getStorages().isEmpty()) {
            List<StorageDTO> storageDTOs = context.getStorages().stream()
                .map(s -> storageService.getStorageById(s.getId()))
                .collect(Collectors.toList());
            response.setStorages(storageDTOs);
        }
        
        if (context.getGpu() != null) {
            response.setGpu(gpuService.getGpuById(context.getGpu().getId()));
        }
        
        if (context.getPsu() != null) {
            response.setPsu(psuService.getPsuById(context.getPsu().getId()));
        }
        
        if (context.getCase() != null) {
            response.setCase(caseService.getCaseById(context.getCase().getId()));
        }
        
        // Calcular precio total
        response.setTotalPrice(context.getTotalPrice());
        
        // Evaluar reglas
        boolean isValid = true;
        for (CompatibilityRule rule : rules) {
            RuleResult result = rule.evaluate(context);
            
            if (result.isError()) {
                isValid = false;
                ErrorDTO errorDTO = new ErrorDTO(
                    result.getErrorCode(),
                    result.getMessage(),
                    result.getComponent()
                );
                response.addError(errorDTO);
            } else if (result.isWarning()) {
                WarningDTO warningDTO = new WarningDTO(
                    result.getWarningCode(),
                    result.getMessage(),
                    result.getComponent()
                );
                response.addWarning(warningDTO);
            }
        }
        
        response.setValid(isValid);
        
        return response;
    }
}
