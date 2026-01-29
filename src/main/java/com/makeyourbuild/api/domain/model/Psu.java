package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.FormFactor;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa una fuente de alimentación (PSU).
 */
@Entity
@Table(name = "psus")
public class Psu implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private Integer wattage; // Potencia en watts (ej: 550, 650, 750, 850, 1000)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column
    private String efficiency; // 80 Plus rating: Bronze, Gold, Platinum, Titanium
    
    @Column
    private Boolean modular; // true = modular, false = no modular
    
    @Enumerated(EnumType.STRING)
    @Column
    private FormFactor formFactor; // ATX, SFX (para gabinetes pequeños)
    
    // Constructors
    public Psu() {}
    
    public Psu(String name, String brand, Integer wattage, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.wattage = wattage;
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
