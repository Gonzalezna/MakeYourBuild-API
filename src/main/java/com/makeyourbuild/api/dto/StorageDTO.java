package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.StorageType;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de Storage.
 */
public class StorageDTO {
    
    private Long id;
    private String name;
    private String brand;
    private StorageType type;
    private Integer capacity;
    private BigDecimal price;
    private Integer readSpeed;
    private Integer writeSpeed;
    private String formFactor;
    
    // Constructors
    public StorageDTO() {}
    
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
    
    public StorageType getType() {
        return type;
    }
    
    public void setType(StorageType type) {
        this.type = type;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getReadSpeed() {
        return readSpeed;
    }
    
    public void setReadSpeed(Integer readSpeed) {
        this.readSpeed = readSpeed;
    }
    
    public Integer getWriteSpeed() {
        return writeSpeed;
    }
    
    public void setWriteSpeed(Integer writeSpeed) {
        this.writeSpeed = writeSpeed;
    }
    
    public String getFormFactor() {
        return formFactor;
    }
    
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
