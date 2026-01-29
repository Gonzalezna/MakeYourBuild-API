package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.FormFactor;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un gabinete (Case).
 */
@Entity
@Table(name = "cases")
public class Case implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormFactor supportedFormFactor; // ATX, mATX, ITX (el más grande que soporta)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column
    private Integer maxGpuLength; // Longitud máxima de GPU en mm (ej: 330, 350, 400)
    
    @Column
    private Integer maxCpuCoolerHeight; // Altura máxima de CPU cooler en mm (ej: 160, 170)
    
    @Column(name = "storage25_slots")
    private Integer storage25Slots; // Cantidad de slots para SSD 2.5"
    
    @Column(name = "storage35_slots")
    private Integer storage35Slots; // Cantidad de slots para HDD 3.5"
    
    @Column
    private Boolean includesFans; // Si incluye ventiladores preinstalados
    
    @Column
    private Integer fanSlots; // Cantidad de slots para ventiladores adicionales
    
    // Constructors
    public Case() {}
    
    public Case(String name, String brand, FormFactor supportedFormFactor, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.supportedFormFactor = supportedFormFactor;
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
