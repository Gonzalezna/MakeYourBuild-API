package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.CpuDTO;
import com.makeyourbuild.api.service.CpuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con CPUs.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/cpus")
public class CpuController {
    
    private final CpuService cpuService;
    
    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }
    
    /**
     * Lista todas las CPUs.
     */
    @GetMapping
    public ResponseEntity<List<CpuDTO>> getAllCpus() {
        List<CpuDTO> cpus = cpuService.getAllCpus();
        return ResponseEntity.ok(cpus);
    }
    
    /**
     * Obtiene una CPU por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CpuDTO> getCpuById(@PathVariable Long id) {
        CpuDTO cpu = cpuService.getCpuById(id);
        return ResponseEntity.ok(cpu);
    }
}
