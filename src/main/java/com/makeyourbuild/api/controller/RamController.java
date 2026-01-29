package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.RamDTO;
import com.makeyourbuild.api.service.RamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con RAMs.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/rams")
public class RamController {
    
    private final RamService ramService;
    
    public RamController(RamService ramService) {
        this.ramService = ramService;
    }
    
    /**
     * Lista todas las RAMs.
     */
    @GetMapping
    public ResponseEntity<List<RamDTO>> getAllRams() {
        List<RamDTO> rams = ramService.getAllRams();
        return ResponseEntity.ok(rams);
    }
    
    /**
     * Lista RAMs compatibles con CPU + Motherboard.
     * Query params: ?cpuId=1&motherboardId=2&existingRamIds=3,4,5
     * 
     * Validaciones:
     * - Tipo de RAM compatible con motherboard
     * - Frecuencia <= frecuencia máxima de motherboard
     * - No exceder slots disponibles (considerando RAMs ya seleccionadas)
     * - No exceder capacidad máxima de RAM total
     */
    @GetMapping("/compatible")
    public ResponseEntity<List<RamDTO>> getCompatibleRams(
            @RequestParam(required = false) Long cpuId,
            @RequestParam Long motherboardId,
            @RequestParam(required = false) List<Long> existingRamIds) {
        List<RamDTO> rams = ramService.getCompatibleRams(cpuId, motherboardId, existingRamIds);
        return ResponseEntity.ok(rams);
    }
    
    /**
     * Obtiene una RAM por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RamDTO> getRamById(@PathVariable Long id) {
        RamDTO ram = ramService.getRamById(id);
        return ResponseEntity.ok(ram);
    }
}
