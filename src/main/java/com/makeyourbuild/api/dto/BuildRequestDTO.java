package com.makeyourbuild.api.dto;

import java.util.List;

/**
 * DTO para solicitud de validación de build.
 * Contiene los IDs de los componentes seleccionados.
 */
public class BuildRequestDTO {
    
    private Long cpuId;
    private Long motherboardId;
    private List<Long> ramIds; // Puede haber múltiples módulos de RAM
    private List<Long> storageIds; // Puede haber múltiples unidades de almacenamiento
    private Long gpuId;
    private Long psuId;
    private Long caseId;
    
    // Constructors
    public BuildRequestDTO() {}
    
    public BuildRequestDTO(Long cpuId, Long motherboardId, List<Long> ramIds) {
        this.cpuId = cpuId;
        this.motherboardId = motherboardId;
        this.ramIds = ramIds;
    }
    
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
