package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.BuildRequestDTO;
import com.makeyourbuild.api.dto.BuildResponseDTO;
import com.makeyourbuild.api.dto.PreBuiltDTO;
import com.makeyourbuild.api.service.BuildService;
import com.makeyourbuild.api.service.PreBuiltService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador REST para operaciones con Pre-Built builds.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/pre-built")
public class PreBuiltController {
    
    private final PreBuiltService preBuiltService;
    private final BuildService buildService;
    
    public PreBuiltController(PreBuiltService preBuiltService, BuildService buildService) {
        this.preBuiltService = preBuiltService;
        this.buildService = buildService;
    }
    
    /**
     * Lista builds predefinidos.
     * 
     * Parámetros opcionales:
     * - category: Filtra por categoría (Gaming, Workstation, Graphic design, Streaming, Personal/Home)
     * - budget: Si se proporciona junto con category, retorna exactamente 3 builds según presupuesto
     * 
     * Ejemplos:
     * - GET /api/pre-built (retorna todos los builds)
     * - GET /api/pre-built?category=Gaming (retorna todos los builds de Gaming)
     * - GET /api/pre-built?category=Gaming&budget=1000 (retorna 3 builds según presupuesto)
     * - GET /api/pre-built?category=Personal/Home (funciona correctamente con caracteres especiales)
     */
    @GetMapping
    public ResponseEntity<List<PreBuiltDTO>> getAllPreBuilt(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal budget) {
        
        List<PreBuiltDTO> preBuiltList = preBuiltService.getPreBuilt(category, budget);
        return ResponseEntity.ok(preBuiltList);
    }
    
    /**
     * Obtiene un build predefinido por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PreBuiltDTO> getPreBuiltById(@PathVariable Long id) {
        PreBuiltDTO preBuilt = preBuiltService.getPreBuiltById(id);
        return ResponseEntity.ok(preBuilt);
    }
    
    /**
     * Valida un build predefinido.
     * Convierte el Pre-Built a BuildRequestDTO y lo valida usando BuildService.
     */
    @PostMapping("/{id}/validate")
    public ResponseEntity<BuildResponseDTO> validatePreBuilt(@PathVariable Long id) {
        BuildRequestDTO buildRequest = preBuiltService.convertToBuildRequestDTO(id);
        BuildResponseDTO response = buildService.validateBuild(buildRequest);
        return ResponseEntity.ok(response);
    }
}
