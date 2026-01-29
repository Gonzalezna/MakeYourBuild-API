package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.FormFactor;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de PSU.
 */
public class PsuDTO {
    
    private Long id;
    private String name;
    private String brand;
    private Integer wattage;
    private BigDecimal price;
    private String efficiency;
    private Boolean modular;
    private FormFactor formFactor;
    
    // Constructors
    public PsuDTO() {}
    
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
    
    public Integer getWattage() {
        return wattage;
    }
    
    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getEfficiency() {
        return efficiency;
    }
    
    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
    
    public Boolean getModular() {
        return modular;
    }
    
    public void setModular(Boolean modular) {
        this.modular = modular;
    }
    
    public FormFactor getFormFactor() {
        return formFactor;
    }
    
    public void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }
}
