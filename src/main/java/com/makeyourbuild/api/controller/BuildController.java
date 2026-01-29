package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.BuildRequestDTO;
import com.makeyourbuild.api.dto.BuildResponseDTO;
import com.makeyourbuild.api.service.BuildService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para validación de builds de PC.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/builds")
public class BuildController {
    
    private final BuildService buildService;
    
    public BuildController(BuildService buildService) {
        this.buildService = buildService;
    }
    
    /**
     * Valida una configuración de PC.
     * Retorna errores (bloqueantes) y advertencias (no bloqueantes).
     */
    @PostMapping("/validate")
    public ResponseEntity<BuildResponseDTO> validateBuild(
            @Valid @RequestBody BuildRequestDTO request) {
        BuildResponseDTO response = buildService.validateBuild(request);
        return ResponseEntity.ok(response);
    }
}
