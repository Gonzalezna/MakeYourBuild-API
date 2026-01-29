package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.PcieVersion;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de GPU.
 */
public class GpuDTO {
    
    private Long id;
    private String name;
    private String brand;
    private Integer tdp;
    private BigDecimal price;
    private PcieVersion pcieVersion;
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer vram;
    private String tier;
    
    // Constructors
    public GpuDTO() {}
    
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
