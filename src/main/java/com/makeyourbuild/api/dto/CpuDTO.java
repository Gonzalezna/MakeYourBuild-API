package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.SocketType;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de CPU.
 */
public class CpuDTO {
    
    private Long id;
    private String name;
    private String brand;
    private SocketType socket;
    private BigDecimal price;
    private Integer cores;
    private Integer threads;
    private Double baseClock;
    private Double boostClock;
    private Integer tdp;
    private String tier;
    private Integer minRamFrequency;
    private String generation;
    
    // Constructors
    public CpuDTO() {}
    
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
    
    public SocketType getSocket() {
        return socket;
    }
    
    public void setSocket(SocketType socket) {
        this.socket = socket;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getCores() {
        return cores;
    }
    
    public void setCores(Integer cores) {
        this.cores = cores;
    }
    
    public Integer getThreads() {
        return threads;
    }
    
    public void setThreads(Integer threads) {
        this.threads = threads;
    }
    
    public Double getBaseClock() {
        return baseClock;
    }
    
    public void setBaseClock(Double baseClock) {
        this.baseClock = baseClock;
    }
    
    public Double getBoostClock() {
        return boostClock;
    }
    
    public void setBoostClock(Double boostClock) {
        this.boostClock = boostClock;
    }
    
    public Integer getTdp() {
        return tdp;
    }
    
    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
    
    public String getTier() {
        return tier;
    }
    
    public void setTier(String tier) {
        this.tier = tier;
    }
    
    public Integer getMinRamFrequency() {
        return minRamFrequency;
    }
    
    public void setMinRamFrequency(Integer minRamFrequency) {
        this.minRamFrequency = minRamFrequency;
    }
    
    public String getGeneration() {
        return generation;
    }
    
    public void setGeneration(String generation) {
        this.generation = generation;
    }
}
