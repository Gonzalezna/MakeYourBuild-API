package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.StorageType;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Storage;

import java.util.List;

/**
 * Regla de compatibilidad: Storage SATA (SSD 2.5" y HDD 3.5") debe caber en los slots disponibles del Case.
 * Nota: Los NVMe M.2 se validan contra la motherboard, no contra el case.
 * Esta es una regla BLOQUEANTE (ERROR).
 */
public class StorageCaseSlotsRule implements CompatibilityRule {
    
    @Override
    public RuleResult evaluate(BuildContext context) {
        List<Storage> storages = context.getStorages();
        Case caseEntity = context.getCase();
        
        if (storages == null || storages.isEmpty() || caseEntity == null) {
            return RuleResult.valid();
        }
        
        int count25 = 0; // SSD 2.5"
        int count35 = 0; // HDD 3.5"
        
        for (Storage storage : storages) {
            if (storage.getType() == StorageType.SATA_SSD || 
                (storage.getFormFactor() != null && storage.getFormFactor().equals("2.5\""))) {
                count25++;
            } else if (storage.getType() == StorageType.HDD || 
                      storage.getType() == StorageType.SATA_HDD ||
                      (storage.getFormFactor() != null && storage.getFormFactor().equals("3.5\""))) {
                count35++;
            }
            // NVMe M.2 no ocupa slots de case, se instala directamente en la motherboard
        }
        
        // Validar slots 2.5"
        if (caseEntity.getStorage25Slots() != null && count25 > caseEntity.getStorage25Slots()) {
            return RuleResult.error(
                ErrorCode.STORAGE_CASE_SLOTS_25_EXCEEDED,
                String.format(
                    "Se requieren %d slots de 2.5\" pero el gabinete (%s) solo tiene %d disponibles",
                    count25,
                    caseEntity.getName(),
                    caseEntity.getStorage25Slots()
                ),
                "storage,case"
            );
        }
        
        // Validar slots 3.5"
        if (caseEntity.getStorage35Slots() != null && count35 > caseEntity.getStorage35Slots()) {
            return RuleResult.error(
                ErrorCode.STORAGE_CASE_SLOTS_35_EXCEEDED,
                String.format(
                    "Se requieren %d slots de 3.5\" pero el gabinete (%s) solo tiene %d disponibles",
                    count35,
                    caseEntity.getName(),
                    caseEntity.getStorage35Slots()
                ),
                "storage,case"
            );
        }
        
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "Storage-Case Slots Compatibility";
    }
}
