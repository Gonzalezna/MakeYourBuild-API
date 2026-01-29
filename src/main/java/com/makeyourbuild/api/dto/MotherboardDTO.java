package com.makeyourbuild.api.dto;

import com.makeyourbuild.api.domain.enums.RamType;
import com.makeyourbuild.api.domain.enums.SocketType;
import java.math.BigDecimal;

/**
 * DTO para transferencia de datos de Motherboard.
 */
public class MotherboardDTO {
    
    private Long id;
    private String name;
    private String brand;
    private SocketType socket;
    private RamType ramType;
    private Integer maxFrequency;
    private BigDecimal price;
    private String chipset;
    private String formFactor;
    private Integer ramSlots;
    private String supportedCpuGenerations;
    private Integer powerConsumption;
    private Integer m2Slots;
    private Integer sataPorts;
    
    // Constructors
    public MotherboardDTO() {}
    
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
    
    public RamType getRamType() {
        return ramType;
    }
    
    public void setRamType(RamType ramType) {
        this.ramType = ramType;
    }
    
    public Integer getMaxFrequency() {
        return maxFrequency;
    }
    
    public void setMaxFrequency(Integer maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getChipset() {
        return chipset;
    }
    
    public void setChipset(String chipset) {
        this.chipset = chipset;
    }
    
    public String getFormFactor() {
        return formFactor;
    }
    
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
    
    public Integer getRamSlots() {
        return ramSlots;
    }
    
    public void setRamSlots(Integer ramSlots) {
        this.ramSlots = ramSlots;
    }
    
    public String getSupportedCpuGenerations() {
        return supportedCpuGenerations;
    }
    
    public void setSupportedCpuGenerations(String supportedCpuGenerations) {
        this.supportedCpuGenerations = supportedCpuGenerations;
    }
    
    public Integer getPowerConsumption() {
        return powerConsumption;
    }
    
    public void setPowerConsumption(Integer powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
    
    public Integer getM2Slots() {
        return m2Slots;
    }
    
    public void setM2Slots(Integer m2Slots) {
        this.m2Slots = m2Slots;
    }
    
    public Integer getSataPorts() {
        return sataPorts;
    }
    
    public void setSataPorts(Integer sataPorts) {
        this.sataPorts = sataPorts;
    }
}
