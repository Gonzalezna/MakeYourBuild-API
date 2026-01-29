package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.StorageDTO;
import com.makeyourbuild.api.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con Storage.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/storages")
public class StorageController {
    
    private final StorageService storageService;
    
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }
    
    /**
     * Lista todos los Storage.
     */
    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAllStorages() {
        List<StorageDTO> storages = storageService.getAllStorages();
        return ResponseEntity.ok(storages);
    }
    
    /**
     * Lista Storages compatibles con Motherboard y Case.
     * Query params: ?motherboardId=1&caseId=2&existingStorageIds=3,4,5 (todos opcionales)
     */
    @GetMapping("/compatible")
    public ResponseEntity<List<StorageDTO>> getCompatibleStorages(
            @RequestParam(required = false) Long motherboardId,
            @RequestParam(required = false) Long caseId,
            @RequestParam(required = false) List<Long> existingStorageIds) {
        List<StorageDTO> storages = storageService.getCompatibleStorages(motherboardId, caseId, existingStorageIds);
        return ResponseEntity.ok(storages);
    }
    
    /**
     * Obtiene un Storage por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StorageDTO> getStorageById(@PathVariable Long id) {
        StorageDTO storage = storageService.getStorageById(id);
        return ResponseEntity.ok(storage);
    }
}
