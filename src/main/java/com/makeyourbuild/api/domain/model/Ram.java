package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.RamType;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa una memoria RAM.
 */
@Entity
@Table(name = "rams")
public class Ram implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RamType type;
    
    @Column(nullable = false)
    private Integer frequency; // MHz (ej: 3200, 3600, 6000)
    
    @Column(nullable = false)
    private Integer capacity; // GB total del kit (ej: 16, 32, 64)
    
    @Column(nullable = false)
    private Integer modules; // Cantidad de módulos (ej: 2 para 2x8GB, 2x16GB)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column
    private Integer casLatency; // CL (ej: 16, 18, 32)
    
    // Constructors
    public Ram() {}
    
    public Ram(String name, String brand, RamType type, Integer frequency, 
              Integer capacity, Integer modules, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.frequency = frequency;
        this.capacity = capacity;
        this.modules = modules;
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
