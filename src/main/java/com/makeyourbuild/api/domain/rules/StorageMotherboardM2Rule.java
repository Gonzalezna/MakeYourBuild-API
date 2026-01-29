package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.StorageType;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Storage;

import java.util.List;

/**
 * Regla de compatibilidad: Storage debe ser compatible con los slots/puertos de la motherboard.
 * - NVMe M.2: valida contra m2Slots de la motherboard
 * - SATA (SSD 2.5" y HDD 3.5"): valida contra sataPorts de la motherboard
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class StorageMotherboardM2Rule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        List<Storage> storages = context.getStorages();
        Motherboard motherboard = context.getMotherboard();
        
        if (storages == null || storages.isEmpty() || motherboard == null) {
            return RuleResult.valid();
        }
        
        int m2Count = 0; // Contador de NVMe M.2
        int sataCount = 0; // Contador de SATA (SSD 2.5" y HDD 3.5")
        
        for (Storage storage : storages) {
            if (storage.getType() == StorageType.NVME_SSD) {
                m2Count++;
            } else if (storage.getType() == StorageType.SATA_SSD || 
                      storage.getType() == StorageType.HDD || 
                      storage.getType() == StorageType.SATA_HDD) {
                sataCount++;
            }
        }
        
        // Validar slots M.2
        if (motherboard.getM2Slots() != null && m2Count > motherboard.getM2Slots()) {
            return RuleResult.error(
                ErrorCode.STORAGE_M2_SLOTS_EXCEEDED,
                String.format(
                    "Se requieren %d slots M.2 pero la motherboard (%s) solo tiene %d disponibles",
                    m2Count,
                    motherboard.getName(),
                    motherboard.getM2Slots()
                ),
                "storage,motherboard"
            );
        }
        
        // Validar puertos SATA
        if (motherboard.getSataPorts() != null && sataCount > motherboard.getSataPorts()) {
            return RuleResult.error(
                ErrorCode.STORAGE_SATA_PORTS_EXCEEDED,
                String.format(
                    "Se requieren %d puertos SATA pero la motherboard (%s) solo tiene %d disponibles",
                    sataCount,
                    motherboard.getName(),
                    motherboard.getSataPorts()
                ),
                "storage,motherboard"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "Storage-Motherboard M.2 and SATA Compatibility";
    }
}
