package com.makeyourbuild.api.domain.rules;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Psu;
import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.domain.model.Storage;
import com.makeyourbuild.api.testsupport.CompatibilityTestFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockingCompatibilityRulesTest {

    @Test
    void cpuMotherRule_fallaCuandoSocketDistinto() {
        Cpu cpu = CompatibilityTestFixtures.cpuLga1700Mid();
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        BuildContext ctx = CompatibilityTestFixtures.context(
            cpu, mb, List.of(), List.of(), null, CompatibilityTestFixtures.psu650(), CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new CpuMotherRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.CPU_SOCKET_MISMATCH, r.getErrorCode());
    }

    @Test
    void cpuMotherRule_okCuandoSocketCoincide() {
        Cpu cpu = CompatibilityTestFixtures.cpuAm5High();
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        BuildContext ctx = CompatibilityTestFixtures.context(
            cpu, mb, List.of(), List.of(), null, CompatibilityTestFixtures.psu650(), CompatibilityTestFixtures.caseAtx350mm()
        );

        assertFalse(new CpuMotherRule().evaluate(ctx).isError());
    }

    @Test
    void motherRamRule_fallaPorTipoRam() {
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        Ram ram = CompatibilityTestFixtures.ramDdr4(3200, "G.Skill", 2, 16);
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            mb,
            List.of(ram),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new MotherRamRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.RAM_TYPE_MISMATCH, r.getErrorCode());
    }

    @Test
    void motherRamRule_fallaPorFrecuenciaExcedida() {
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        Ram ram = CompatibilityTestFixtures.ramDdr5(6400, "Corsair", 2, 32);
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            mb,
            List.of(ram),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new MotherRamRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.RAM_FREQUENCY_EXCEEDED, r.getErrorCode());
    }

    @Test
    void ramSlotsRule_fallaCuandoModulosExcedenSlots() {
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        Ram a = CompatibilityTestFixtures.ramDdr5(5600, "A", 3, 24);
        Ram b = CompatibilityTestFixtures.ramDdr5(5600, "B", 2, 16);
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            mb,
            List.of(a, b),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new RamSlotsRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.RAM_SLOTS_EXCEEDED, r.getErrorCode());
    }

    @Test
    void chipsetRule_fallaCuandoGeneracionNoSoportada() {
        Cpu cpu = CompatibilityTestFixtures.cpuAm5High();
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        mb.setSupportedCpuGenerations("Ryzen 5000");

        BuildContext ctx = CompatibilityTestFixtures.context(
            cpu,
            mb,
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new ChipsetCompatibilityRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.CHIPSET_INCOMPATIBLE, r.getErrorCode());
    }

    @Test
    void gpuCaseSizeRule_fallaCuandoGpuMuyLarga() {
        Gpu gpu = CompatibilityTestFixtures.gpuLong();
        Case c = CompatibilityTestFixtures.caseItx300mm();
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            gpu,
            CompatibilityTestFixtures.psu650(),
            c
        );

        RuleResult r = new GpuCaseSizeRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.GPU_CASE_SIZE_EXCEEDED, r.getErrorCode());
    }

    @Test
    void psuWattageRule_fallaCuandoPsuNula() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            null,
            null,
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new PsuWattageRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.PSU_INSUFFICIENT, r.getErrorCode());
    }

    @Test
    void psuWattageRule_fallaCuandoWattsInsuficientes() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            CompatibilityTestFixtures.gpuLong(),
            CompatibilityTestFixtures.psu450(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new PsuWattageRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.PSU_INSUFFICIENT, r.getErrorCode());
    }

    @Test
    void caseFormFactorRule_fallaItxConMotherboardAtx() {
        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd()),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseItx300mm()
        );

        RuleResult r = new CaseFormFactorRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.CASE_FORM_FACTOR_INCOMPATIBLE, r.getErrorCode());
    }

    @Test
    void storageCaseSlotsRule_fallaCuandoExcedenBahias25() {
        Storage s1 = CompatibilityTestFixtures.sataSsd();
        Storage s2 = CompatibilityTestFixtures.sataSsd();
        Storage s3 = CompatibilityTestFixtures.sataSsd();
        Case c = CompatibilityTestFixtures.caseAtx350mm();
        c.setStorage25Slots(2);

        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            CompatibilityTestFixtures.motherboardAm5Ddr5(),
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(s1, s2, s3),
            null,
            CompatibilityTestFixtures.psu650(),
            c
        );

        RuleResult r = new StorageCaseSlotsRule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.STORAGE_CASE_SLOTS_25_EXCEEDED, r.getErrorCode());
    }

    @Test
    void storageMotherboardRule_fallaCuandoM2ExcedenSlotsPlaca() {
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        mb.setM2Slots(1);

        Storage n1 = CompatibilityTestFixtures.nvmeSsd();
        Storage n2 = CompatibilityTestFixtures.nvmeSsd();

        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            mb,
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(n1, n2),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new StorageMotherboardM2Rule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.STORAGE_M2_SLOTS_EXCEEDED, r.getErrorCode());
    }

    @Test
    void storageMotherboardRule_fallaCuandoSataExcedePuertos() {
        Motherboard mb = CompatibilityTestFixtures.motherboardAm5Ddr5();
        mb.setSataPorts(1);

        BuildContext ctx = CompatibilityTestFixtures.context(
            CompatibilityTestFixtures.cpuAm5High(),
            mb,
            List.of(CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32)),
            List.of(CompatibilityTestFixtures.sataSsd(), CompatibilityTestFixtures.sataSsd()),
            null,
            CompatibilityTestFixtures.psu650(),
            CompatibilityTestFixtures.caseAtx350mm()
        );

        RuleResult r = new StorageMotherboardM2Rule().evaluate(ctx);

        assertTrue(r.isError());
        assertEquals(ErrorCode.STORAGE_SATA_PORTS_EXCEEDED, r.getErrorCode());
    }
}
