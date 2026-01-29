package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Storage;
import com.makeyourbuild.api.domain.enums.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con Storage.
 */
@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    
    /**
     * Busca Storage por tipo.
     */
    List<Storage> findByType(StorageType type);
    
    /**
     * Busca Storage por marca.
     */
    List<Storage> findByBrand(String brand);
}
