package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.enums.FormFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con Cases.
 */
@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    
    /**
     * Busca Cases por form factor soportado.
     */
    List<Case> findBySupportedFormFactor(FormFactor formFactor);
    
    /**
     * Busca Cases por marca.
     */
    List<Case> findByBrand(String brand);
    
    /**
     * Busca Cases que soporten una longitud mínima de GPU.
     */
    List<Case> findByMaxGpuLengthGreaterThanEqual(Integer length);
}
