package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.enums.PreBuiltCategory;
import com.makeyourbuild.api.domain.model.PreBuilt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repositorio para operaciones de base de datos con Pre-Built builds.
 */
@Repository
public interface PreBuiltRepository extends JpaRepository<PreBuilt, Long> {
    
    /**
     * Busca builds predefinidos por categoría.
     */
    List<PreBuilt> findByCategory(PreBuiltCategory category);
    
    /**
     * Obtiene todos los builds ordenados por nombre.
     */
    List<PreBuilt> findAllByOrderByNameAsc();
    
    /**
     * Busca builds predefinidos por categoría ordenados por nombre.
     */
    List<PreBuilt> findByCategoryOrderByNameAsc(PreBuiltCategory category);
    
    /**
     * Busca builds por categoría y rango de precio.
     * Ordena por precio ascendente.
     */
    @Query("SELECT p FROM PreBuilt p WHERE p.category = :category " +
           "AND p.totalPrice BETWEEN :minPrice AND :maxPrice " +
           "ORDER BY p.totalPrice ASC")
    List<PreBuilt> findByCategoryAndPriceRange(
        @Param("category") PreBuiltCategory category,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice
    );
    
    /**
     * Busca builds por categoría con precio menor o igual al máximo.
     * Ordena por precio descendente (más cercano primero).
     */
    @Query("SELECT p FROM PreBuilt p WHERE p.category = :category " +
           "AND p.totalPrice <= :maxPrice " +
           "ORDER BY p.totalPrice DESC")
    List<PreBuilt> findByCategoryAndPriceLessThanOrEqual(
        @Param("category") PreBuiltCategory category,
        @Param("maxPrice") BigDecimal maxPrice
    );
    
    /**
     * Busca builds por categoría con precio mayor o igual al mínimo.
     * Ordena por precio ascendente (más cercano primero).
     */
    @Query("SELECT p FROM PreBuilt p WHERE p.category = :category " +
           "AND p.totalPrice >= :minPrice " +
           "ORDER BY p.totalPrice ASC")
    List<PreBuilt> findByCategoryAndPriceGreaterThanOrEqual(
        @Param("category") PreBuiltCategory category,
        @Param("minPrice") BigDecimal minPrice
    );
}
