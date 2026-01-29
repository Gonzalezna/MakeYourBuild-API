package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.PcieVersion;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa una tarjeta gráfica (GPU).
 */
@Entity
@Table(name = "gpus")
public class Gpu implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand; // NVIDIA, AMD
    
    @Column(nullable = false)
    private Integer tdp; // Consumo en watts (ej: 200, 350, 450)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PcieVersion pcieVersion; // PCIe 3.0, 4.0, 5.0
    
    @Column
    private Integer length; // Longitud en mm (ej: 300, 320, 350)
    
    @Column
    private Integer width; // Ancho en mm (slots ocupados, ej: 2, 3)
    
    @Column
    private Integer height; // Altura en mm
    
    @Column
    private Integer vram; // VRAM en GB (ej: 8, 12, 16, 24)
    
    @Column
    private String tier; // low, mid, high, enthusiast
    
    // Constructors
    public Gpu() {}
    
    public Gpu(String name, String brand, Integer tdp, BigDecimal price, PcieVersion pcieVersion) {
        this.name = name;
        this.brand = brand;
        this.tdp = tdp;
        this.price = price;
        this.pcieVersion = pcieVersion;
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
    
    public Integer getTdp() {
        return tdp;
    }
    
    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public PcieVersion getPcieVersion() {
        return pcieVersion;
    }
    
    public void setPcieVersion(PcieVersion pcieVersion) {
        this.pcieVersion = pcieVersion;
    }
    
    public Integer getLength() {
        return length;
    }
    
    public void setLength(Integer length) {
        this.length = length;
    }
    
    public Integer getWidth() {
        return width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public Integer getHeight() {
        return height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public Integer getVram() {
        return vram;
    }
    
    public void setVram(Integer vram) {
        this.vram = vram;
    }
    
    public String getTier() {
        return tier;
    }
    
    public void setTier(String tier) {
        this.tier = tier;
    }
}
