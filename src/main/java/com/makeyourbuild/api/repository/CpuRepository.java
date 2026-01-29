package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.enums.SocketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con CPUs.
 */
@Repository
public interface CpuRepository extends JpaRepository<Cpu, Long> {
    
    /**
     * Busca CPUs por socket.
     */
    List<Cpu> findBySocket(SocketType socket);
    
    /**
     * Busca CPUs por marca.
     */
    List<Cpu> findByBrand(String brand);
    
    /**
     * Busca CPUs por gama (tier).
     */
    List<Cpu> findByTier(String tier);
}
