package com.makeyourbuild.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para respuesta de validación de build.
 * Contiene el resultado de la validación, precio total, errores y advertencias.
 */
public class BuildResponseDTO {
    
    private boolean valid;
    private BigDecimal totalPrice;
    private CpuDTO cpu;
    private MotherboardDTO motherboard;
    private List<RamDTO> rams; // Múltiples módulos de RAM
    private List<StorageDTO> storages;
    private GpuDTO gpu;
    private PsuDTO psu;
    private CaseDTO caseEntity;
    private List<ErrorDTO> errors;
    private List<WarningDTO> warnings;
    
    public BuildResponseDTO() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    // Getters and Setters
    public boolean isValid() {
        return valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public CpuDTO getCpu() {
        return cpu;
    }
    
    public void setCpu(CpuDTO cpu) {
        this.cpu = cpu;
    }
    
    public MotherboardDTO getMotherboard() {
        return motherboard;
    }
    
    public void setMotherboard(MotherboardDTO motherboard) {
        this.motherboard = motherboard;
    }
    
    public List<RamDTO> getRams() {
        return rams;
    }
    
    public void setRams(List<RamDTO> rams) {
        this.rams = rams;
    }
    
    public List<ErrorDTO> getErrors() {
        return errors;
    }
    
    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
    
    public void addError(ErrorDTO error) {
        this.errors.add(error);
    }
    
    public List<WarningDTO> getWarnings() {
        return warnings;
    }
    
    public void setWarnings(List<WarningDTO> warnings) {
        this.warnings = warnings;
    }
    
    public void addWarning(WarningDTO warning) {
        this.warnings.add(warning);
    }
    
    public List<StorageDTO> getStorages() {
        return storages;
    }
    
    public void setStorages(List<StorageDTO> storages) {
        this.storages = storages;
    }
    
    public GpuDTO getGpu() {
        return gpu;
    }
    
    public void setGpu(GpuDTO gpu) {
        this.gpu = gpu;
    }
    
    public PsuDTO getPsu() {
        return psu;
    }
    
    public void setPsu(PsuDTO psu) {
        this.psu = psu;
    }
    
    public CaseDTO getCase() {
        return caseEntity;
    }
    
    public void setCase(CaseDTO caseEntity) {
        this.caseEntity = caseEntity;
    }
}
