package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.domain.enums.RamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con RAMs.
 */
@Repository
public interface RamRepository extends JpaRepository<Ram, Long> {
    
    /**
     * Busca RAMs por tipo.
     */
    List<Ram> findByType(RamType type);
    
    /**
     * Busca RAMs por tipo y frecuencia máxima (menor o igual).
     */
    List<Ram> findByTypeAndFrequencyLessThanEqual(RamType type, Integer maxFrequency);
}
