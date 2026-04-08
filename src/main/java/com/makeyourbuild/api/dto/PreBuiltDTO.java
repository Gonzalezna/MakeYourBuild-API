package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.PreBuiltCategory;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para transferencia de datos de Pre-Built builds.
 * Incluye los componentes completos para facilitar el uso en el frontend.
 */
public class PreBuiltDTO {
    
    private Long id;
    private String name;
    private String description;
    private PreBuiltCategory category;
    private BigDecimal totalPrice;
    private String imageUrl;
    private CpuDTO cpu;
    private MotherboardDTO motherboard;
    private List<RamDTO> rams;
    private List<StorageDTO> storages;
    private GpuDTO gpu;
    private PsuDTO psu;
    private CaseDTO caseEntity;
    
    // Constructors
    public PreBuiltDTO() {}
    
    public PreBuiltDTO(Long id, String name, String description, PreBuiltCategory category, 
                      BigDecimal totalPrice, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public PreBuiltCategory getCategory() {
        return category;
    }
    
    public void setCategory(PreBuiltCategory category) {
        this.category = category;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    // Getters and Setters para componentes completos
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
