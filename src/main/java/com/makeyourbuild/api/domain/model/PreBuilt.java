package com.makeyourbuild.api.domain.model;

import com.makeyourbuild.api.domain.enums.PreBuiltCategory;
import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un build predefinido (pre-built).
 * Almacena configuraciones de PC completas.
 * Todos los componentes son obligatorios salvo {@code gpuId}, que puede ser
 * {@code null} cuando el build usa solo gráficos integrados (iGPU).
 */
@Entity
@Table(name = "pre_built_builds")
public class PreBuilt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name; // "Gaming Budget", "Workstation Pro", etc.
    
    @Column(length = 1000)
    private String description; // Descripción del build
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreBuiltCategory category; // Categoría del build (Gaming, Workstation, etc.)
    
    // IDs de componentes (obligatorios)
    @Column(nullable = false)
    private Long cpuId;
    
    @Column(nullable = false)
    private Long motherboardId;
    
    @Column(nullable = false, length = 500)
    private String ramIds; // JSON array o comma-separated: "[1,2]" o "1,2"
    
    @Column(nullable = false, length = 500)
    private String storageIds; // JSON array o comma-separated: "[1,2]" o "1,2"

    /** Opcional: null cuando el build usa solo iGPU, sin GPU discreta. */
    @Column
    private Long gpuId;

    @Column(nullable = false)
    private Long psuId;
    
    @Column(nullable = false)
    private Long caseId;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal totalPrice; // Precio total calculado (opcional, puede calcularse dinámicamente)
    
    @Column
    private String imageUrl; // URL de imagen/thumbnail (opcional)
    
    // Constructors
    public PreBuilt() {}

    public PreBuilt(
        String name,
        String description,
        PreBuiltCategory category,
        Long cpuId,
        Long motherboardId,
        String ramIds,
        String storageIds,
        Long gpuId,
        Long psuId,
        Long caseId
    ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.cpuId = cpuId;
        this.motherboardId = motherboardId;
        this.ramIds = ramIds;
        this.storageIds = storageIds;
        this.gpuId = gpuId;
        this.psuId = psuId;
        this.caseId = caseId;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public PreBuiltCategory getCategory() {
        return category;
    }
    
    public void setCategory(PreBuiltCategory category) {
        this.category = category;
    }
    
    public Long getCpuId() {
        return cpuId;
    }
    
    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }
    
    public Long getMotherboardId() {
        return motherboardId;
    }
    
    public void setMotherboardId(Long motherboardId) {
        this.motherboardId = motherboardId;
    }
    
    public String getRamIds() {
        return ramIds;
    }
    
    public void setRamIds(String ramIds) {
        this.ramIds = ramIds;
    }
    
    public String getStorageIds() {
        return storageIds;
    }
    
    public void setStorageIds(String storageIds) {
        this.storageIds = storageIds;
    }
    
    public Long getGpuId() {
        return gpuId;
    }
    
    public void setGpuId(Long gpuId) {
        this.gpuId = gpuId;
    }
    
    public Long getPsuId() {
        return psuId;
    }
    
    public void setPsuId(Long psuId) {
        this.psuId = psuId;
    }
    
    public Long getCaseId() {
        return caseId;
    }
    
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
