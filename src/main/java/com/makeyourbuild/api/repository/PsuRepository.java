package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Psu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con PSUs.
 */
@Repository
public interface PsuRepository extends JpaRepository<Psu, Long> {
    
    /**
     * Busca PSUs por potencia mínima.
     */
    List<Psu> findByWattageGreaterThanEqual(Integer wattage);
    
    /**
     * Busca PSUs por marca.
     */
    List<Psu> findByBrand(String brand);
    
    /**
     * Busca PSUs por eficiencia.
     */
    List<Psu> findByEfficiency(String efficiency);
}
