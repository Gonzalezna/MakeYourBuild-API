package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.RamType;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de RAM.
 */
public class RamDTO {
    
    private Long id;
    private String name;
    private String brand;
    private RamType type;
    private Integer frequency;
    private Integer capacity;
    private Integer modules;
    private BigDecimal price;
    private Integer casLatency;
    
    // Constructors
    public RamDTO() {}
    
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
    
    public RamType getType() {
        return type;
    }
    
    public void setType(RamType type) {
        this.type = type;
    }
    
    public Integer getFrequency() {
        return frequency;
    }
    
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public Integer getModules() {
        return modules;
    }
    
    public void setModules(Integer modules) {
        this.modules = modules;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getCasLatency() {
        return casLatency;
    }
    
    public void setCasLatency(Integer casLatency) {
        this.casLatency = casLatency;
    }
}
