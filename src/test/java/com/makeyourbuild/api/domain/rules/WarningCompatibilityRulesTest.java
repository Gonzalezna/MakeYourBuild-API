package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.testsupport.CompatibilityTestFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarningCompatibilityRulesTest {

    @Test
    void ramBrandMismatch_advierteConDosMarcas() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(
                CompatibilityTestFixtures.ramDdr5(5600, "Corsair", 1, 16),
                CompatibilityTestFixtures.ramDdr5(5600, "G.Skill", 1, 16)
            ),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new RamBrandMismatchRule().evaluate(ctx);

        assertTrue(r.isWarning());
        assertEquals(WarningCode.RAM_BRAND_MISMATCH, r.getWarningCode());
    }

    @Test
    void ramFrequencyMismatch_advierteConDosFrecuencias() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(
                CompatibilityTestFixtures.ramDdr5(5600, "X", 1, 16),
                CompatibilityTestFixtures.ramDdr5(6000, "X", 1, 16)
            ),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new RamFrequencyMismatchRule().evaluate(ctx);

        assertTrue(r.isWarning());
        assertEquals(WarningCode.RAM_FREQUENCY_MISMATCH, r.getWarningCode());
    }

    @Test
    void ramRecommendation_advierteCpuHighConPocaRam() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 8)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new RamRecommendationRule().evaluate(ctx);

        assertTrue(r.isWarning());
        assertEquals(WarningCode.RAM_CAPACITY_LOW, r.getWarningCode());
    }

    @Test
    void ramFrequencyMinimum_adviertePorDebajoDelMinimoCpu() {
        Cpu cpu = CompatibilityTestFixtures.cpuLga1700Mid();
        Motherboard mb = CompatibilityTestFixtures.motherboardLga1700Ddr4();
        BuildContext ctx = CompatibilityTestFixtures.context(
            cpu,
            mb,
            List.of(CompatibilityTestFixtures.ramDdr4(3000, "X", 2, 16)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new RamFrequencyMinimumRule().evaluate(ctx);

        assertTrue(r.isWarning());
        assertEquals(WarningCode.RAM_FREQUENCY_BELOW_RECOMMENDED, r.getWarningCode());
    }

    @Test
    void cpuRamBalance_advierteFrecuenciaBajaDdr5ConCpuHigh() {
        Cpu cpu = CompatibilityTestFixtures.cpuAm5High();
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        Ram ram = CompatibilityTestFixtures.ramDdr5(4800, "X", 2, 32);
        ram.setFrequency(2400);

        BuildContext ctx = CompatibilityTestFixtures.context(
            cpu,
            mb,
            List.of(ram),
            List.of(CompatibilityTestFixtures.sataSsd()),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new CpuRamBalanceRule().evaluate(ctx);

        assertTrue(r.isWarning());
        assertEquals(WarningCode.CPU_RAM_BALANCE, r.getWarningCode());
    }

    @Test
    void gpuPcieRule_placeholderSiempreValido() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            CompatibilityTestFixtures.gpuLong(),
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new GpuPcieRule().evaluate(ctx);

        assertFalse(r.isError());
        assertFalse(r.isWarning());
    }
}
