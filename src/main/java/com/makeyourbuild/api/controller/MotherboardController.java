package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.MotherboardDTO;
import com.makeyourbuild.api.service.MotherboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con Motherboards.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/motherboards")
public class MotherboardController {
    
    private final MotherboardService motherboardService;
    
    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }
    
    /**
     * Lista todas las motherboards.
     */
    @GetMapping
    public ResponseEntity<List<MotherboardDTO>> getAllMotherboards() {
        List<MotherboardDTO> motherboards = motherboardService.getAllMotherboards();
        return ResponseEntity.ok(motherboards);
    }
    
    /**
     * Lista motherboards compatibles con una CPU.
     * Query param: ?cpuId=1
     */
    @GetMapping("/compatible")
    public ResponseEntity<List<MotherboardDTO>> getCompatibleMotherboards(
            @RequestParam Long cpuId) {
        List<MotherboardDTO> motherboards = motherboardService.getCompatibleMotherboards(cpuId);
        return ResponseEntity.ok(motherboards);
    }
    
    /**
     * Obtiene una motherboard por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MotherboardDTO> getMotherboardById(@PathVariable Long id) {
        MotherboardDTO motherboard = motherboardService.getMotherboardById(id);
        return ResponseEntity.ok(motherboard);
    }
}
