package com.makeyourbuild.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO para solicitud de validación de build.
 * Contiene los IDs de los componentes seleccionados.
 * Una build completa requiere todos los componentes excepto GPU (opcional).
 */
public class BuildRequestDTO {
    
    @NotNull
    private Long cpuId;
    @NotNull
    private Long motherboardId;
    @NotEmpty
    private List<Long> ramIds; // Puede haber múltiples módulos de RAM
    @NotEmpty
    private List<Long> storageIds; // Puede haber múltiples unidades de almacenamiento
    // Opcional: una PC puede funcionar con iGPU sin GPU discreta
    private Long gpuId;
    @NotNull
    private Long psuId;
    @NotNull
    private Long caseId;
    
    // Constructors
    public BuildRequestDTO() {}
    
    // Getters and Setters
    public Long getCpuId() {
        return cpuId;
    }
    
    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }
    
    public Long getMotherboardId() {
        return motherboardId;
    }
    
    public void setMotherboardId(Long motherboardId) {
        this.motherboardId = motherboardId;
    }
    
    public List<Long> getRamIds() {
        return ramIds;
    }
    
    public void setRamIds(List<Long> ramIds) {
        this.ramIds = ramIds;
    }
    
    public List<Long> getStorageIds() {
        return storageIds;
    }
    
    public void setStorageIds(List<Long> storageIds) {
        this.storageIds = storageIds;
    }
    
    public Long getGpuId() {
        return gpuId;
    }
    
    public void setGpuId(Long gpuId) {
        this.gpuId = gpuId;
    }
    
    public Long getPsuId() {
        return psuId;
    }
    
    public void setPsuId(Long psuId) {
        this.psuId = psuId;
    }
    
    public Long getCaseId() {
        return caseId;
    }
    
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
