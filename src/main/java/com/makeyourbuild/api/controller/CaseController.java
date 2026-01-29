package com.makeyourbuild.api.controller;

import com.makeyourbuild.api.dto.CaseDTO;
import com.makeyourbuild.api.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones con Cases.
 * Sin lógica de negocio - solo delega a los servicios.
 */
@RestController
@RequestMapping("/api/cases")
public class CaseController {
    
    private final CaseService caseService;
    
    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }
    
    /**
     * Lista todos los Cases.
     */
    @GetMapping
    public ResponseEntity<List<CaseDTO>> getAllCases() {
        List<CaseDTO> cases = caseService.getAllCases();
        return ResponseEntity.ok(cases);
    }
    
    /**
     * Lista Cases compatibles con GPU y/o Motherboard.
     * Query params: ?gpuId=1&motherboardId=2 (ambos opcionales)
     */
    @GetMapping("/compatible")
    public ResponseEntity<List<CaseDTO>> getCompatibleCases(
            @RequestParam(required = false) Long gpuId,
            @RequestParam(required = false) Long motherboardId) {
        List<CaseDTO> cases = caseService.getCompatibleCases(gpuId, motherboardId);
        return ResponseEntity.ok(cases);
    }
    
    /**
     * Obtiene un Case por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CaseDTO> getCaseById(@PathVariable Long id) {
        CaseDTO caseDTO = caseService.getCaseById(id);
        return ResponseEntity.ok(caseDTO);
    }
}
