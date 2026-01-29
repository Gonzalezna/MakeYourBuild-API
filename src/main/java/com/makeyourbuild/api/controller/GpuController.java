package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.GpuDTO;
import com.makeyourbuild.api.service.GpuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con GPUs.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/gpus")
public class GpuController {
    
    private final GpuService gpuService;
    
    public GpuController(GpuService gpuService) {
        this.gpuService = gpuService;
    }
    
    /**
     * Lista todas las GPUs.
     */
    @GetMapping
    public ResponseEntity<List<GpuDTO>> getAllGpus() {
        List<GpuDTO> gpus = gpuService.getAllGpus();
        return ResponseEntity.ok(gpus);
    }
    
    /**
     * Obtiene una GPU por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GpuDTO> getGpuById(@PathVariable Long id) {
        GpuDTO gpu = gpuService.getGpuById(id);
        return ResponseEntity.ok(gpu);
    }
}
