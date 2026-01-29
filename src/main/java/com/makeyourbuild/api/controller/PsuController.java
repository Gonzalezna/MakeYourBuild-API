package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.BuildRequestDTO;
import com.makeyourbuild.api.dto.PsuDTO;
import com.makeyourbuild.api.service.PsuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con PSUs.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/psus")
public class PsuController {
    
    private final PsuService psuService;
    
    public PsuController(PsuService psuService) {
        this.psuService = psuService;
    }
    
    /**
     * Lista todas las PSUs.
     */
    @GetMapping
    public ResponseEntity<List<PsuDTO>> getAllPsus() {
        List<PsuDTO> psus = psuService.getAllPsus();
        return ResponseEntity.ok(psus);
    }
    
    /**
     * Lista PSUs compatibles con una configuración parcial de build.
     * Body: BuildRequestDTO (todos los campos opcionales)
     */
    @PostMapping("/compatible")
    public ResponseEntity<List<PsuDTO>> getCompatiblePsus(
            @RequestBody BuildRequestDTO buildRequest) {
        List<PsuDTO> psus = psuService.getCompatiblePsus(buildRequest);
        return ResponseEntity.ok(psus);
    }
    
    /**
     * Obtiene una PSU por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PsuDTO> getPsuById(@PathVariable Long id) {
        PsuDTO psu = psuService.getPsuById(id);
        return ResponseEntity.ok(psu);
    }
}
