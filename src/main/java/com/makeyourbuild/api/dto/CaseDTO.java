package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.FormFactor;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de Case.
 */
public class CaseDTO {
    
    private Long id;
    private String name;
    private String brand;
    private FormFactor supportedFormFactor;
    private BigDecimal price;
    private Integer maxGpuLength;
    private Integer maxCpuCoolerHeight;
    private Integer storage25Slots;
    private Integer storage35Slots;
    private Boolean includesFans;
    private Integer fanSlots;
    
    // Constructors
    public CaseDTO() {}
    
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
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public FormFactor getSupportedFormFactor() {
        return supportedFormFactor;
    }
    
    public void setSupportedFormFactor(FormFactor supportedFormFactor) {
        this.supportedFormFactor = supportedFormFactor;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getMaxGpuLength() {
        return maxGpuLength;
    }
    
    public void setMaxGpuLength(Integer maxGpuLength) {
        this.maxGpuLength = maxGpuLength;
    }
    
    public Integer getMaxCpuCoolerHeight() {
        return maxCpuCoolerHeight;
    }
    
    public void setMaxCpuCoolerHeight(Integer maxCpuCoolerHeight) {
        this.maxCpuCoolerHeight = maxCpuCoolerHeight;
    }
    
    public Integer getStorage25Slots() {
        return storage25Slots;
    }
    
    public void setStorage25Slots(Integer storage25Slots) {
        this.storage25Slots = storage25Slots;
    }
    
    public Integer getStorage35Slots() {
        return storage35Slots;
    }
    
    public void setStorage35Slots(Integer storage35Slots) {
        this.storage35Slots = storage35Slots;
    }
    
    public Boolean getIncludesFans() {
        return includesFans;
    }
    
    public void setIncludesFans(Boolean includesFans) {
        this.includesFans = includesFans;
    }
    
    public Integer getFanSlots() {
        return fanSlots;
    }
    
    public void setFanSlots(Integer fanSlots) {
        this.fanSlots = fanSlots;
    }
}
