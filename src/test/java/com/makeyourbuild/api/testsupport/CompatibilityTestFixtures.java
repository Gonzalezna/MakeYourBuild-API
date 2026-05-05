package com.makeyourbuild.api.testsupport;

import com.makeyourbuild.api.domain.enums.ComponentTier;
import com.makeyourbuild.api.domain.enums.FormFactor;
import com.makeyourbuild.api.domain.enums.PcieVersion;
import com.makeyourbuild.api.domain.enums.RamType;
import com.makeyourbuild.api.domain.enums.SocketType;
import com.makeyourbuild.api.domain.enums.StorageType;
import com.makeyourbuild.api.domain.model.BuildContext;
import com.makeyourbuild.api.domain.model.Case;
import com.makeyourbuild.api.domain.model.Cpu;
import com.makeyourbuild.api.domain.model.Gpu;
import com.makeyourbuild.api.domain.model.Motherboard;
import com.makeyourbuild.api.domain.model.Psu;
import com.makeyourbuild.api.domain.model.Ram;
import com.makeyourbuild.api.domain.model.Storage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entidades mínimas para pruebas de reglas (sin persistencia).
 */
public final class CompatibilityTestFixtures {

    private CompatibilityTestFixtures() {
    }

    public static Cpu cpuAm5High() {
        Cpu c = new Cpu();
        c.setId(1L);
        c.setSocket(SocketType.AM5);
        c.setBrand("AMD");
        c.setTdp(105);
        c.setTier(ComponentTier.HIGH);
        c.setMinRamFrequency(4800);
        c.setGeneration("Ryzen 7000");
        c.setPrice(BigDecimal.valueOf(400));
        return c;
    }

    public static Cpu cpuLga1700Mid() {
        Cpu c = new Cpu();
        c.setId(2L);
        c.setSocket(SocketType.LGA1700);
        c.setBrand("Intel");
        c.setTdp(125);
        c.setTier(ComponentTier.MID);
        c.setMinRamFrequency(3200);
        c.setGeneration("13th");
        c.setPrice(BigDecimal.valueOf(350));
        return c;
    }

    public static Motherboard motherboardAm5Ddr5() {
        Motherboard m = new Motherboard();
        m.setId(10L);
        m.setName("B650 Test");
        m.setBrand("ASUS");
        m.setSocket(SocketType.AM5);
        m.setRamType(RamType.DDR5);
        m.setMaxFrequency(6000);
        m.setFormFactor("ATX");
        m.setRamSlots(4);
        m.setSupportedCpuGenerations("Ryzen 7000,Ryzen 5000");
        m.setPowerConsumption(30);
        m.setM2Slots(2);
        m.setSataPorts(4);
        m.setPrice(BigDecimal.valueOf(200));
        return m;
    }

    public static Motherboard motherboardLga1700Ddr4() {
        Motherboard m = new Motherboard();
        m.setId(11L);
        m.setName("Z690 Test");
        m.setSocket(SocketType.LGA1700);
        m.setBrand("MSI");
        m.setRamType(RamType.DDR4);
        m.setMaxFrequency(3200);
        m.setFormFactor("ATX");
        m.setRamSlots(4);
        m.setSupportedCpuGenerations("12th,13th,14th");
        m.setPowerConsumption(25);
        m.setM2Slots(2);
        m.setSataPorts(6);
        m.setPrice(BigDecimal.valueOf(180));
        return m;
    }

    public static Ram ramDdr5(int frequency, String brand, int modules, int capacityGb) {
        Ram r = new Ram();
        r.setType(RamType.DDR5);
        r.setFrequency(frequency);
        r.setBrand(brand);
        r.setModules(modules);
        r.setCapacity(capacityGb);
        r.setPrice(BigDecimal.valueOf(120));
        return r;
    }

    public static Ram ramDdr4(int frequency, String brand, int modules, int capacityGb) {
        Ram r = new Ram();
        r.setType(RamType.DDR4);
        r.setFrequency(frequency);
        r.setBrand(brand);
        r.setModules(modules);
        r.setCapacity(capacityGb);
        r.setPrice(BigDecimal.valueOf(80));
        return r;
    }

    public static Storage sataSsd() {
        Storage s = new Storage();
        s.setId(100L);
        s.setName("SSD 1TB");
        s.setBrand("Samsung");
        s.setType(StorageType.SATA_SSD);
        s.setCapacity(1000);
        s.setFormFactor("2.5\"");
        s.setPrice(BigDecimal.valueOf(90));
        return s;
    }

    public static Storage nvmeSsd() {
        Storage s = new Storage();
        s.setId(101L);
        s.setName("NVMe 1TB");
        s.setBrand("WD");
        s.setType(StorageType.NVME_SSD);
        s.setCapacity(1000);
        s.setFormFactor("M.2");
        s.setPrice(BigDecimal.valueOf(110));
        return s;
    }

    public static Gpu gpuLong() {
        Gpu g = new Gpu();
        g.setId(50L);
        g.setName("RTX Test");
        g.setBrand("NVIDIA");
        g.setTdp(300);
        g.setPrice(BigDecimal.valueOf(700));
        g.setPcieVersion(PcieVersion.PCIE_4_0);
        g.setLength(350);
        return g;
    }

    public static Case caseAtx350mm() {
        Case c = new Case();
        c.setId(70L);
        c.setName("ATX Full");
        c.setBrand("Fractal");
        c.setSupportedFormFactor(FormFactor.ATX);
        c.setMaxGpuLength(350);
        c.setStorage25Slots(2);
        c.setStorage35Slots(2);
        c.setPrice(BigDecimal.valueOf(120));
        return c;
    }

    public static Case caseItx300mm() {
        Case c = new Case();
        c.setId(71L);
        c.setName("ITX Small");
        c.setBrand("Cooler Master");
        c.setSupportedFormFactor(FormFactor.ITX);
        c.setMaxGpuLength(300);
        c.setStorage25Slots(2);
        c.setStorage35Slots(1);
        c.setPrice(BigDecimal.valueOf(90));
        return c;
    }

    public static Psu psu650() {
        Psu p = new Psu();
        p.setId(80L);
        p.setName("650W Gold");
        p.setBrand("Corsair");
        p.setWattage(650);
        p.setPrice(BigDecimal.valueOf(100));
        return p;
    }

    public static Psu psu450() {
        Psu p = new Psu();
        p.setId(81L);
        p.setName("450W");
        p.setBrand("EVGA");
        p.setWattage(450);
        p.setPrice(BigDecimal.valueOf(60));
        return p;
    }

    public static BuildContext context(
        Cpu cpu,
        Motherboard motherboard,
        List<Ram> rams,
        List<Storage> storages,
        Gpu gpu,
        Psu psu,
        Case caseEntity
    ) {
        return new BuildContext(cpu, motherboard, rams, storages, gpu, psu, caseEntity);
    }
}
