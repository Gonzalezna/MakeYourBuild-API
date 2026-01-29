package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.SocketType;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa una CPU.
 */
@Entity
@Table(name = "cpus")
public class Cpu implements PricedComponent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String brand; // AMD, Intel
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocketType socket;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(nullable = false)
    private Integer cores;
    
    @Column(nullable = false)
    private Integer threads;
    
    @Column(nullable = false)
    private Double baseClock; // GHz
    
    @Column
    private Double boostClock; // GHz
    
    @Column
    private Integer tdp; // Thermal Design Power en watts
    
    @Column
    private String tier; // "low", "mid", "high" para determinar gama
    
    @Column
    private Integer minRamFrequency; // Frecuencia mínima recomendada de RAM en MHz
    
    @Column
    private String generation; // Generación de CPU (ej: "12th", "13th", "14th" para Intel, "Ryzen 5000", "Ryzen 7000" para AMD)
    
    // Constructors
    public Cpu() {}
    
    public Cpu(String name, String brand, SocketType socket, BigDecimal price, 
               Integer cores, Integer threads, Double baseClock) {
        this.name = name;
        this.brand = brand;
        this.socket = socket;
        this.price = price;
        this.cores = cores;
        this.threads = threads;
        this.baseClock = baseClock;
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
