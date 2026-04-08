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
        List<RamDTO> ramDTOs = request.getRamIds().stream()
            .map(ramService::getRamById)
            .collect(Collectors.toList());
        List<com.makeyourbuild.api.domain.model.Ram> ramEntities = request.getRamIds().stream()
            .map(ramService::getRamEntityById)
            .collect(Collectors.toList());

        // Storage es obligatorio para una build completa
        List<Storage> storages = request.getStorageIds().stream()
            .map(storageService::getStorageEntityById)
            .collect(Collectors.toList());
        
        // GPU es el único componente opcional
        com.makeyourbuild.api.domain.model.Gpu gpu = request.getGpuId() != null
            ? gpuService.getGpuEntityById(request.getGpuId())
            : null;
        
        // Convertir a entidades para el contexto de validación
        BuildContext context = new BuildContext(
            cpuService.getCpuEntityById(request.getCpuId()),
            motherboardService.getMotherboardEntityById(request.getMotherboardId()),
            ramEntities,
            storages,
            gpu,
            psuService.getPsuEntityById(request.getPsuId()),
            caseService.getCaseEntityById(request.getCaseId())
        );
        
        // Respuesta con la build completa (GPU null si solo iGPU)
        BuildResponseDTO response = new BuildResponseDTO();
        response.setCpu(cpu);
        response.setMotherboard(motherboard);
        response.setRams(ramDTOs);
        
        List<StorageDTO> storageDTOs = context.getStorages().stream()
            .map(s -> storageService.getStorageById(s.getId()))
            .collect(Collectors.toList());
        response.setStorages(storageDTOs);
        
        if (request.getGpuId() != null) {
            response.setGpu(gpuService.getGpuById(request.getGpuId()));
        } else {
            response.setGpu(null);
        }
        
        response.setPsu(psuService.getPsuById(request.getPsuId()));
        response.setCase(caseService.getCaseById(request.getCaseId()));
        
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
