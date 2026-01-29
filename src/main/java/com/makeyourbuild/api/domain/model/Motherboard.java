package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.RamType;
import com.makeyourbuild.api.domain.enums.SocketType;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa una Motherboard.
 */
@Entity
@Table(name = "motherboards")
public class Motherboard implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocketType socket;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RamType ramType;
    
    @Column(nullable = false)
    private Integer maxFrequency; // MHz (ej: 3200, 3600, 6000)
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column
    private String chipset; // B550, X570, Z690, etc.
    
    @Column
    private String formFactor; // ATX, mATX, ITX
    
    @Column
    private Integer ramSlots; // Cantidad de slots de RAM disponibles (ej: 2, 4)
    
    @Column(length = 500)
    private String supportedCpuGenerations; // Generaciones de CPU soportadas, separadas por comas (ej: "12th,13th,14th" o "Ryzen 5000,Ryzen 7000")
    
    @Column
    private Integer powerConsumption; // Consumo de energía en watts (ej: 30, 50)
    
    @Column
    private Integer m2Slots; // Cantidad de slots M.2 disponibles (ej: 1, 2, 3)
    
    @Column
    private Integer sataPorts; // Cantidad de puertos SATA disponibles (ej: 4, 6, 8)
    
    // Constructors
    public Motherboard() {}
    
    public Motherboard(String name, String brand, SocketType socket, 
                      RamType ramType, Integer maxFrequency, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.socket = socket;
        this.ramType = ramType;
        this.maxFrequency = maxFrequency;
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
