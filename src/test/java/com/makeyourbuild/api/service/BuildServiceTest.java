package com.makeyourbuild.api.service;

import com.makeyourbuild.api.domain.enums.ErrorCode;
import com.makeyourbuild.api.domain.enums.WarningCode;
import com.makeyourbuild.api.domain.model.Storage;
import com.makeyourbuild.api.dto.BuildRequestDTO;
import com.makeyourbuild.api.dto.BuildResponseDTO;
import com.makeyourbuild.api.dto.CaseDTO;
import com.makeyourbuild.api.dto.CpuDTO;
import com.makeyourbuild.api.dto.MotherboardDTO;
import com.makeyourbuild.api.dto.PsuDTO;
import com.makeyourbuild.api.dto.RamDTO;
import com.makeyourbuild.api.dto.StorageDTO;
import com.makeyourbuild.api.testsupport.CompatibilityTestFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildServiceTest {

    @Mock
    private CpuService cpuService;
    @Mock
    private MotherboardService motherboardService;
    @Mock
    private RamService ramService;
    @Mock
    private StorageService storageService;
    @Mock
    private GpuService gpuService;
    @Mock
    private PsuService psuService;
    @Mock
    private CaseService caseService;

    @InjectMocks
    private BuildService buildService;

    @Test
    void validateBuild_incluyeErrorCuandoSocketNoCoincide() {
        when(cpuService.getCpuEntityById(1L)).thenReturn(CompatibilityTestFixtures.cpuLga1700Mid());
        when(cpuService.getCpuById(1L)).thenReturn(new CpuDTO());

        when(motherboardService.getMotherboardEntityById(10L)).thenReturn(CompatibilityTestFixtures.motherboardAm5Ddr5());
        when(motherboardService.getMotherboardById(10L)).thenReturn(new MotherboardDTO());

        var ram = CompatibilityTestFixtures.ramDdr5(5600, "X", 2, 32);
        when(ramService.getRamEntityById(20L)).thenReturn(ram);
        when(ramService.getRamById(20L)).thenReturn(new RamDTO());

        Storage st = CompatibilityTestFixtures.sataSsd();
        when(storageService.getStorageEntityById(100L)).thenReturn(st);
        when(storageService.getStorageById(100L)).thenReturn(new StorageDTO());

        when(psuService.getPsuEntityById(80L)).thenReturn(CompatibilityTestFixtures.psu650());
        when(psuService.getPsuById(80L)).thenReturn(new PsuDTO());

        when(caseService.getCaseEntityById(70L)).thenReturn(CompatibilityTestFixtures.caseAtx350mm());
        when(caseService.getCaseById(70L)).thenReturn(new CaseDTO());

        BuildRequestDTO req = new BuildRequestDTO();
        req.setCpuId(1L);
        req.setMotherboardId(10L);
        req.setRamIds(List.of(20L));
        req.setStorageIds(List.of(100L));
        req.setPsuId(80L);
        req.setCaseId(70L);

        BuildResponseDTO res = buildService.validateBuild(req);

        assertFalse(res.isValid());
        assertTrue(res.getErrors().stream().anyMatch(e -> e.getCode() == ErrorCode.CPU_SOCKET_MISMATCH));
    }

    @Test
    void validateBuild_validaCuandoComponentesCoherentes() {
        when(cpuService.getCpuEntityById(1L)).thenReturn(CompatibilityTestFixtures.cpuAm5High());
        when(cpuService.getCpuById(1L)).thenReturn(new CpuDTO());

        when(motherboardService.getMotherboardEntityById(10L)).thenReturn(CompatibilityTestFixtures.motherboardAm5Ddr5());
        when(motherboardService.getMotherboardById(10L)).thenReturn(new MotherboardDTO());

        var ram = CompatibilityTestFixtures.ramDdr5(5600, "Corsair", 2, 32);
        when(ramService.getRamEntityById(20L)).thenReturn(ram);
        when(ramService.getRamById(20L)).thenReturn(new RamDTO());

        Storage st = CompatibilityTestFixtures.sataSsd();
        when(storageService.getStorageEntityById(100L)).thenReturn(st);
        when(storageService.getStorageById(100L)).thenReturn(new StorageDTO());

        when(psuService.getPsuEntityById(80L)).thenReturn(CompatibilityTestFixtures.psu650());
        when(psuService.getPsuById(80L)).thenReturn(new PsuDTO());

        when(caseService.getCaseEntityById(70L)).thenReturn(CompatibilityTestFixtures.caseAtx350mm());
        when(caseService.getCaseById(70L)).thenReturn(new CaseDTO());

        BuildRequestDTO req = new BuildRequestDTO();
        req.setCpuId(1L);
        req.setMotherboardId(10L);
        req.setRamIds(List.of(20L));
        req.setStorageIds(List.of(100L));
        req.setPsuId(80L);
        req.setCaseId(70L);

        BuildResponseDTO res = buildService.validateBuild(req);

        assertTrue(res.isValid());
        assertTrue(res.getErrors().isEmpty());
    }

    @Test
    void validateBuild_acumulaAdvertenciaMarcaRamSinInvalidar() {
        when(cpuService.getCpuEntityById(1L)).thenReturn(CompatibilityTestFixtures.cpuAm5High());
        when(cpuService.getCpuById(1L)).thenReturn(new CpuDTO());

        when(motherboardService.getMotherboardEntityById(10L)).thenReturn(CompatibilityTestFixtures.motherboardAm5Ddr5());
        when(motherboardService.getMotherboardById(10L)).thenReturn(new MotherboardDTO());

        when(ramService.getRamEntityById(20L)).thenReturn(
            CompatibilityTestFixtures.ramDdr5(5600, "Corsair", 1, 16)
        );
        when(ramService.getRamEntityById(21L)).thenReturn(
            CompatibilityTestFixtures.ramDdr5(5600, "G.Skill", 1, 16)
        );
        when(ramService.getRamById(anyLong())).thenReturn(new RamDTO());

        Storage st = CompatibilityTestFixtures.sataSsd();
        when(storageService.getStorageEntityById(100L)).thenReturn(st);
        when(storageService.getStorageById(100L)).thenReturn(new StorageDTO());

        when(psuService.getPsuEntityById(80L)).thenReturn(CompatibilityTestFixtures.psu650());
        when(psuService.getPsuById(80L)).thenReturn(new PsuDTO());

        when(caseService.getCaseEntityById(70L)).thenReturn(CompatibilityTestFixtures.caseAtx350mm());
        when(caseService.getCaseById(70L)).thenReturn(new CaseDTO());

        BuildRequestDTO req = new BuildRequestDTO();
        req.setCpuId(1L);
        req.setMotherboardId(10L);
        req.setRamIds(List.of(20L, 21L));
        req.setStorageIds(List.of(100L));
        req.setPsuId(80L);
        req.setCaseId(70L);

        BuildResponseDTO res = buildService.validateBuild(req);

        assertTrue(res.isValid());
        assertTrue(res.getWarnings().stream().anyMatch(w -> w.getCode() == WarningCode.RAM_BRAND_MISMATCH));
    }
}
