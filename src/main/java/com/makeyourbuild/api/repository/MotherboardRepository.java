package com.makeyourbuild.api.repository;

import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.enums.RamType;
import com.makeyourbuild.api.domain.enums.SocketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos con Motherboards.
 */
@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    
    /**
     * Busca motherboards compatibles con un socket de CPU.
     */
    List<Motherboard> findBySocket(SocketType socket);
    
    /**
     * Busca motherboards por tipo de RAM.
     */
    List<Motherboard> findByRamType(RamType ramType);
    
    /**
     * Busca motherboards compatibles con socket Y tipo de RAM.
     */
    List<Motherboard> findBySocketAndRamType(SocketType socket, RamType ramType);
}
