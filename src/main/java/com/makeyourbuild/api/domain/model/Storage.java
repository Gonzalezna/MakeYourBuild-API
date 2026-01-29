package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.StorageType;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un dispositivo de almacenamiento.
 */
@Entity
@Table(name = "storages")
public class Storage implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StorageType type;
    
    @Column(nullable = false)
    private Integer capacity; // GB (ej: 256, 512, 1000, 2000)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column
    private Integer readSpeed; // MB/s (ej: 3500 para NVMe, 550 para SATA)
    
    @Column
    private Integer writeSpeed; // MB/s
    
    @Column
    private String formFactor; // M.2, 2.5", 3.5"
    
    // Constructors
    public Storage() {}
    
    public Storage(String name, String brand, StorageType type, Integer capacity, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
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
