package com.makeyourbuild.api.domain.enums;

/**
 * Tipos de almacenamiento soportados.
 */
public enum StorageType {
    SATA_SSD,   // SSD SATA 2.5"
    NVME_SSD,   // SSD NVMe M.2
    HDD,        // Disco duro mecánico
    SATA_HDD    // HDD SATA 3.5"
}
