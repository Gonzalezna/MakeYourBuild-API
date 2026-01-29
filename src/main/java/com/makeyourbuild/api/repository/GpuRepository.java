package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.domain.enums.PcieVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con GPUs.
 */
@Repository
public interface GpuRepository extends JpaRepository<Gpu, Long> {
    
    /**
     * Busca GPUs por versión PCIe.
     */
    List<Gpu> findByPcieVersion(PcieVersion pcieVersion);
    
    /**
     * Busca GPUs por marca.
     */
    List<Gpu> findByBrand(String brand);
    
    /**
     * Busca GPUs por gama (tier).
     */
    List<Gpu> findByTier(String tier);
}
