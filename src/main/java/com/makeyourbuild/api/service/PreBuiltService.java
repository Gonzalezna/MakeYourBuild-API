package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.enums.PreBuiltCategory;
import com.makeyourbuild.api.domain.model.PreBuilt;
import com.makeyourbuild.api.dto.*;
import com.makeyourbuild.api.exception.EntityNotFoundException;
import com.makeyourbuild.api.repository.PreBuiltRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para operaciones de negocio con Pre-Built builds.
 */
@Service
@Transactional
public class PreBuiltService {
    
    private static final Logger logger = LoggerFactory.getLogger(PreBuiltService.class);
    
    private final PreBuiltRepository preBuiltRepository;
    private final BuildService buildService;
    private final CpuService cpuService;
    private final MotherboardService motherboardService;
    private final RamService ramService;
    private final StorageService storageService;
    private final GpuService gpuService;
    private final PsuService psuService;
    private final CaseService caseService;
    
    // Porcentajes iniciales y de expansión
    private static final BigDecimal INITIAL_BELOW_PERCENT = new BigDecimal("0.20"); // -20%
    private static final BigDecimal INITIAL_ABOVE_PERCENT = new BigDecimal("0.20"); // +20%
    private static final BigDecimal TOLERANCE_PERCENT = new BigDecimal("0.05"); // ±5% para "justo"
    private static final BigDecimal EXPANSION_STEP = new BigDecimal("0.10"); // +10% por cada expansión
    
    public PreBuiltService(PreBuiltRepository preBuiltRepository, 
                          BuildService buildService,
                          CpuService cpuService,
                          MotherboardService motherboardService,
                          RamService ramService,
                          StorageService storageService,
                          GpuService gpuService,
                          PsuService psuService,
                          CaseService caseService) {
        this.preBuiltRepository = preBuiltRepository;
        this.buildService = buildService;
        this.cpuService = cpuService;
        this.motherboardService = motherboardService;
        this.ramService = ramService;
        this.storageService = storageService;
        this.gpuService = gpuService;
        this.psuService = psuService;
        this.caseService = caseService;
    }
    
    /**
     * Obtiene todos los builds predefinidos.
     * Si alguna build tiene componentes inválidos, se omite pero se registra el error.
     */
    public List<PreBuiltDTO> getAllPreBuilt() {
        try {
            List<PreBuilt> allBuilds = preBuiltRepository.findAllByOrderByNameAsc();
            if (allBuilds == null || allBuilds.isEmpty()) {
                return new ArrayList<>();
            }
            
            return allBuilds.stream()
                .filter(build -> build != null) // Filtrar builds null
                .map(build -> {
                    try {
                        return toDTO(build);
                    } catch (EntityNotFoundException e) {
                        logger.warn("Build predefinido ID {} omitido: componente requerido no encontrado - {}", 
                            build.getId(), e.getMessage());
                        return null;
                    } catch (Exception e) {
                        logger.error("Error inesperado al cargar build predefinido ID {}: {}", 
                            build.getId(), e.getMessage(), e);
                        return null; // Retornar null para filtrarlo después
                    }
                })
                .filter(dto -> dto != null) // Filtrar builds con errores
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error crítico al obtener builds predefinidos: {}", e.getMessage(), e);
            return new ArrayList<>(); // Retornar lista vacía en lugar de fallar
        }
    }
    
    /**
     * Obtiene builds predefinidos según los parámetros proporcionados.
     * Maneja la lógica de decisión para determinar qué método usar:
     * - Si no hay categoría: retorna todos los builds
     * - Si hay categoría y presupuesto: retorna 3 builds según presupuesto
     * - Si solo hay categoría: retorna todos los builds de la categoría
     * 
     * @param category Categoría opcional para filtrar
     * @param budget Presupuesto opcional para búsqueda por presupuesto
     * @return Lista de PreBuiltDTO según los criterios
     */
    public List<PreBuiltDTO> getPreBuilt(String category, BigDecimal budget) {
        // Si no hay categoría, retornar todos
        if (category == null || category.trim().isEmpty()) {
            return getAllPreBuilt();
        }
        
        // Si hay presupuesto, usar el método de búsqueda por presupuesto
        if (budget != null) {
            return findPreBuiltByBudget(category, budget);
        }
        
        // Si solo hay categoría, retornar todos los de la categoría
        return getPreBuiltByCategory(category);
    }
    
    /**
     * Obtiene builds predefinidos por categoría.
     * Convierte el String recibido a PreBuiltCategory enum.
     * 
     * @param category String con la categoría (puede ser displayName o nombre del enum)
     * @return Lista de PreBuiltDTO filtrados por categoría
     * @throws IllegalArgumentException si la categoría no es válida
     */
    public List<PreBuiltDTO> getPreBuiltByCategory(String category) {
        PreBuiltCategory categoryEnum = PreBuiltCategory.fromString(category);
        if (categoryEnum == null) {
            throw new IllegalArgumentException("Categoría inválida: " + category + 
                ". Categorías válidas: Gaming, Workstation, Graphic design, Streaming, Personal/Home");
        }
        
        try {
            List<PreBuilt> builds = preBuiltRepository.findByCategoryOrderByNameAsc(categoryEnum);
            if (builds == null || builds.isEmpty()) {
                return new ArrayList<>();
            }
            
            return builds.stream()
                .filter(build -> build != null) // Filtrar builds null
                .map(build -> {
                    try {
                        return toDTO(build);
                    } catch (EntityNotFoundException e) {
                        logger.warn("Build predefinido ID {} omitido: componente requerido no encontrado - {}", 
                            build.getId(), e.getMessage());
                        return null;
                    } catch (Exception e) {
                        logger.error("Error inesperado al cargar build predefinido ID {}: {}", 
                            build.getId(), e.getMessage(), e);
                        return null;
                    }
                })
                .filter(dto -> dto != null) // Filtrar builds con errores
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error crítico al obtener builds por categoría {}: {}", category, e.getMessage(), e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Busca 3 builds predefinidos según categoría y presupuesto:
     * - Uno por debajo del presupuesto (hasta -20%, expandible)
     * - Uno justo en el presupuesto (±5%, expandible)
     * - Uno por encima del presupuesto (hasta +20%, expandible)
     * 
     * Si no hay suficientes builds en el rango inicial, expande el rango hasta encontrar 3.
     * 
     * @param category Categoría del build
     * @param budget Presupuesto del usuario
     * @return Lista de exactamente 3 PreBuiltDTO (o menos si no hay suficientes en la categoría)
     * @throws IllegalArgumentException si la categoría no es válida o el presupuesto es inválido
     */
    public List<PreBuiltDTO> findPreBuiltByBudget(String category, BigDecimal budget) {
        // Validar categoría
        PreBuiltCategory categoryEnum = PreBuiltCategory.fromString(category);
        if (categoryEnum == null) {
            throw new IllegalArgumentException("Categoría inválida: " + category);
        }
        
        // Validar presupuesto
        if (budget == null || budget.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El presupuesto debe ser mayor a 0");
        }
        
        // Obtener todos los builds de la categoría con precio calculado
        List<PreBuilt> allBuilds = preBuiltRepository.findByCategoryOrderByNameAsc(categoryEnum);
        
        if (allBuilds.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Asegurar que todos tengan precio calculado
        List<PreBuilt> buildsWithPrice = allBuilds.stream()
            .map(build -> {
                if (build.getTotalPrice() == null) {
                    // Calcular precio si no está guardado
                    try {
                        BuildRequestDTO components = convertToBuildRequestDTO(build);
                        var buildResponse = buildService.validateBuild(components);
                        build.setTotalPrice(buildResponse.getTotalPrice());
                    } catch (Exception e) {
                        // Si no se puede calcular, usar 0 (se filtrará después)
                        build.setTotalPrice(BigDecimal.ZERO);
                    }
                }
                return build;
            })
            .filter(build -> build.getTotalPrice().compareTo(BigDecimal.ZERO) > 0)
            .sorted(Comparator.comparing(PreBuilt::getTotalPrice))
            .collect(Collectors.toList());
        
        if (buildsWithPrice.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Buscar los 3 builds
        List<PreBuilt> result = new ArrayList<>();
        BigDecimal expansionPercent = BigDecimal.ZERO;
        int maxExpansions = 10; // Límite de seguridad para evitar loops infinitos
        
        while (result.size() < 3 && expansionPercent.compareTo(new BigDecimal("2.0")) <= 0 && maxExpansions > 0) {
            BigDecimal currentBelowPercent = INITIAL_BELOW_PERCENT.add(expansionPercent);
            BigDecimal currentAbovePercent = INITIAL_ABOVE_PERCENT.add(expansionPercent);
            
            // Calcular rangos
            BigDecimal minPrice = budget.multiply(BigDecimal.ONE.subtract(currentBelowPercent));
            BigDecimal maxPrice = budget.multiply(BigDecimal.ONE.add(currentAbovePercent));
            
            // Rango para "justo" (±5% + expansión)
            BigDecimal tolerance = TOLERANCE_PERCENT.add(expansionPercent.multiply(new BigDecimal("0.5")));
            BigDecimal justMinPrice = budget.multiply(BigDecimal.ONE.subtract(tolerance));
            BigDecimal justMaxPrice = budget.multiply(BigDecimal.ONE.add(tolerance));
            
            // Buscar builds en cada rango
            PreBuilt belowBuild = findClosestBelow(buildsWithPrice, budget, minPrice);
            PreBuilt justBuild = findClosestInRange(buildsWithPrice, justMinPrice, justMaxPrice, budget);
            PreBuilt aboveBuild = findClosestAbove(buildsWithPrice, budget, maxPrice);
            
            // Agregar builds únicos a la lista
            if (belowBuild != null && !result.contains(belowBuild)) {
                result.add(belowBuild);
            }
            if (justBuild != null && !result.contains(justBuild)) {
                result.add(justBuild);
            }
            if (aboveBuild != null && !result.contains(aboveBuild)) {
                result.add(aboveBuild);
            }
            
            // Si tenemos 3, salir
            if (result.size() >= 3) {
                break;
            }
            
            // Expandir rango para la siguiente iteración
            expansionPercent = expansionPercent.add(EXPANSION_STEP);
            maxExpansions--;
        }
        
        // Si aún no tenemos 3, tomar los más cercanos disponibles
        if (result.size() < 3) {
            List<PreBuilt> remaining = buildsWithPrice.stream()
                .filter(build -> !result.contains(build))
                .sorted(Comparator.comparing(build -> 
                    build.getTotalPrice().subtract(budget).abs()))
                .limit(3 - result.size())
                .collect(Collectors.toList());
            
            result.addAll(remaining);
        }
        
        // Limitar a 3 y ordenar por precio
        try {
            return result.stream()
                .filter(build -> build != null) // Filtrar builds null
                .limit(3)
                .sorted(Comparator.comparing(PreBuilt::getTotalPrice))
                .map(build -> {
                    try {
                        return toDTO(build);
                    } catch (EntityNotFoundException e) {
                        logger.warn("Build predefinido ID {} omitido: componente requerido no encontrado - {}", 
                            build.getId(), e.getMessage());
                        return null;
                    } catch (Exception e) {
                        logger.error("Error inesperado al cargar build predefinido ID {}: {}", 
                            build.getId(), e.getMessage(), e);
                        return null;
                    }
                })
                .filter(dto -> dto != null) // Filtrar builds con errores
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error crítico al procesar builds por presupuesto: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }
    
    /**
     * Encuentra el build más cercano por debajo del presupuesto.
     */
    private PreBuilt findClosestBelow(List<PreBuilt> builds, BigDecimal budget, BigDecimal minPrice) {
        return builds.stream()
            .filter(build -> {
                BigDecimal price = build.getTotalPrice();
                return price.compareTo(minPrice) >= 0 && price.compareTo(budget) < 0;
            })
            .max(Comparator.comparing(PreBuilt::getTotalPrice))
            .orElse(null);
    }
    
    /**
     * Encuentra el build más cercano dentro del rango "justo".
     */
    private PreBuilt findClosestInRange(List<PreBuilt> builds, BigDecimal minPrice, BigDecimal maxPrice, BigDecimal budget) {
        return builds.stream()
            .filter(build -> {
                BigDecimal price = build.getTotalPrice();
                return price.compareTo(minPrice) >= 0 && price.compareTo(maxPrice) <= 0;
            })
            .min(Comparator.comparing(build -> 
                build.getTotalPrice().subtract(budget).abs()))
            .orElse(null);
    }
    
    /**
     * Encuentra el build más cercano por encima del presupuesto.
     */
    private PreBuilt findClosestAbove(List<PreBuilt> builds, BigDecimal budget, BigDecimal maxPrice) {
        return builds.stream()
            .filter(build -> {
                BigDecimal price = build.getTotalPrice();
                return price.compareTo(budget) > 0 && price.compareTo(maxPrice) <= 0;
            })
            .min(Comparator.comparing(PreBuilt::getTotalPrice))
            .orElse(null);
    }
    
    /**
     * Obtiene un build predefinido por ID.
     */
    public PreBuiltDTO getPreBuiltById(Long id) {
        PreBuilt preBuilt = preBuiltRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pre-Built", id));
        return toDTO(preBuilt);
    }
    
    /**
     * Convierte un PreBuilt a BuildRequestDTO para validación.
     * Permite validar un build predefinido usando el BuildService existente.
     */
    public BuildRequestDTO convertToBuildRequestDTO(Long preBuiltId) {
        PreBuilt preBuilt = preBuiltRepository.findById(preBuiltId)
            .orElseThrow(() -> new EntityNotFoundException("Pre-Built", preBuiltId));
        
        return convertToBuildRequestDTO(preBuilt);
    }
    
    /**
     * Convierte un PreBuilt a BuildRequestDTO.
     * Todos los campos son obligatorios salvo gpuId (opcional si usa iGPU).
     */
    private BuildRequestDTO convertToBuildRequestDTO(PreBuilt preBuilt) {
        BuildRequestDTO request = new BuildRequestDTO();
        request.setCpuId(preBuilt.getCpuId());
        request.setMotherboardId(preBuilt.getMotherboardId());
        request.setRamIds(parseIds(preBuilt.getRamIds()));
        request.setStorageIds(parseIds(preBuilt.getStorageIds()));
        request.setGpuId(preBuilt.getGpuId()); // null si iGPU
        request.setPsuId(preBuilt.getPsuId());
        request.setCaseId(preBuilt.getCaseId());
        return request;
    }
    
    /**
     * Parsea una cadena de IDs a una lista de Long.
     * Soporta formatos:
     * - JSON array: "[1,2,3]"
     * - Comma-separated: "1,2,3"
     * - Single value: "1"
     * 
     * Si hay IDs inválidos, se omiten y se registra un warning.
     */
    private List<Long> parseIds(String idsString) {
        if (idsString == null || idsString.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            // Remover corchetes si es JSON array
            String cleaned = idsString.trim();
            if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
                cleaned = cleaned.substring(1, cleaned.length() - 1);
            }
            
            // Dividir por comas y convertir a Long, ignorando valores inválidos
            return Arrays.stream(cleaned.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(idStr -> {
                    try {
                        return Long.parseLong(idStr);
                    } catch (NumberFormatException e) {
                        logger.warn("ID inválido en parseIds: '{}' - se omite", idStr);
                        return null;
                    }
                })
                .filter(id -> id != null) // Filtrar IDs inválidos
                .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al parsear IDs: '{}' - {}", idsString, e.getMessage(), e);
            return new ArrayList<>(); // Retornar lista vacía en caso de error
        }
    }
    
    /**
     * Convierte entidad a DTO.
     * Carga los componentes completos para facilitar el uso en el frontend.
     */
    private PreBuiltDTO toDTO(PreBuilt preBuilt) {
        PreBuiltDTO dto = new PreBuiltDTO();
        dto.setId(preBuilt.getId());
        dto.setName(preBuilt.getName());
        dto.setDescription(preBuilt.getDescription());
        dto.setCategory(preBuilt.getCategory());
        dto.setImageUrl(preBuilt.getImageUrl());
        
        // Cargar componentes completos directamente desde la entidad
        try {
            // CPU (requerido)
            if (preBuilt.getCpuId() != null) {
                try {
                    dto.setCpu(cpuService.getCpuById(preBuilt.getCpuId()));
                } catch (EntityNotFoundException e) {
                    logger.warn("CPU con ID {} no encontrada para build predefinido ID {}", preBuilt.getCpuId(), preBuilt.getId());
                    throw e; // Re-lanzar porque CPU es requerido
                }
            }
            
            // Motherboard (requerido)
            if (preBuilt.getMotherboardId() != null) {
                try {
                    dto.setMotherboard(motherboardService.getMotherboardById(preBuilt.getMotherboardId()));
                } catch (EntityNotFoundException e) {
                    logger.warn("Motherboard con ID {} no encontrada para build predefinido ID {}", preBuilt.getMotherboardId(), preBuilt.getId());
                    throw e; // Re-lanzar porque Motherboard es requerido
                }
            }
            
            // RAMs (requerido, puede ser múltiple) - parsear directamente
            List<Long> ramIds = parseIds(preBuilt.getRamIds());
            if (ramIds != null && !ramIds.isEmpty()) {
                List<RamDTO> ramDTOs = ramIds.stream()
                    .map(ramId -> {
                        try {
                            return ramService.getRamById(ramId);
                        } catch (EntityNotFoundException e) {
                            logger.warn("RAM con ID {} no encontrada para build predefinido ID {}", ramId, preBuilt.getId());
                            return null;
                        }
                    })
                    .filter(ram -> ram != null) // Filtrar RAMs no encontradas
                    .collect(Collectors.toList());
                if (!ramDTOs.isEmpty()) {
                    dto.setRams(ramDTOs);
                }
            }
            
            // Storages (requerido)
            List<Long> storageIds = parseIds(preBuilt.getStorageIds());
            List<StorageDTO> storageDTOs = new ArrayList<>();
            for (Long storageId : storageIds) {
                try {
                    storageDTOs.add(storageService.getStorageById(storageId));
                } catch (EntityNotFoundException e) {
                    logger.warn("Storage con ID {} no encontrado para build predefinido ID {}", storageId, preBuilt.getId());
                    throw e;
                }
            }
            dto.setStorages(storageDTOs);

            // GPU (opcional: null si el build usa iGPU)
            if (preBuilt.getGpuId() != null) {
                try {
                    dto.setGpu(gpuService.getGpuById(preBuilt.getGpuId()));
                } catch (EntityNotFoundException e) {
                    logger.warn("GPU con ID {} no encontrada para build predefinido ID {}", preBuilt.getGpuId(), preBuilt.getId());
                    throw e;
                }
            }

            // PSU (requerido)
            try {
                dto.setPsu(psuService.getPsuById(preBuilt.getPsuId()));
            } catch (EntityNotFoundException e) {
                logger.warn("PSU con ID {} no encontrada para build predefinido ID {}", preBuilt.getPsuId(), preBuilt.getId());
                throw e;
            }

            // Case (requerido)
            try {
                dto.setCase(caseService.getCaseById(preBuilt.getCaseId()));
            } catch (EntityNotFoundException e) {
                logger.warn("Gabinete con ID {} no encontrado para build predefinido ID {}", preBuilt.getCaseId(), preBuilt.getId());
                throw e;
            }
        } catch (EntityNotFoundException e) {
            // Si un componente requerido no existe, re-lanzar la excepción
            throw e;
        } catch (Exception e) {
            // Si hay otro tipo de error, loguearlo y re-lanzar
            logger.error("Error inesperado al cargar componentes para build predefinido ID {}: {}", preBuilt.getId(), e.getMessage(), e);
            throw e;
        }
        
        // Calcular precio total si no está guardado
        if (preBuilt.getTotalPrice() != null && preBuilt.getTotalPrice().compareTo(BigDecimal.ZERO) > 0) {
            dto.setTotalPrice(preBuilt.getTotalPrice());
        } else {
            // Solo crear BuildRequestDTO cuando se necesite calcular el precio
            try {
                // Verificar que los componentes requeridos existan antes de calcular precio
                if (preBuilt.getCpuId() == null || preBuilt.getMotherboardId() == null) {
                    logger.warn("Build predefinido ID {} no puede calcular precio: CPU o Motherboard faltantes", preBuilt.getId());
                    dto.setTotalPrice(BigDecimal.ZERO);
                } else {
                    BuildRequestDTO components = convertToBuildRequestDTO(preBuilt);
                    var buildResponse = buildService.validateBuild(components);
                    if (buildResponse != null && buildResponse.getTotalPrice() != null) {
                        dto.setTotalPrice(buildResponse.getTotalPrice());
                    } else {
                        dto.setTotalPrice(BigDecimal.ZERO);
                    }
                }
            } catch (EntityNotFoundException e) {
                logger.warn("No se puede calcular precio para build predefinido ID {}: componente no encontrado - {}", 
                    preBuilt.getId(), e.getMessage());
                dto.setTotalPrice(BigDecimal.ZERO);
            } catch (Exception e) {
                logger.error("Error al calcular precio para build predefinido ID {}: {}", 
                    preBuilt.getId(), e.getMessage(), e);
                dto.setTotalPrice(BigDecimal.ZERO);
            }
        }
        
        return dto;
    }
}
