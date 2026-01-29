package com.makeyourbuild.api.domain.model;

import java.util.List;

/**
 * Contexto de una configuración de PC que se está validando.
 * Contiene los componentes seleccionados para validar compatibilidad.
 */
public class BuildContext {
    
    private Cpu cpu;
    private Motherboard motherboard;
    private List<Ram> rams; // Puede haber múltiples módulos de RAM
    private List<Storage> storages; // Puede haber múltiples unidades de almacenamiento
    private Gpu gpu;
    private Psu psu;
    private Case caseEntity;
    
    public BuildContext() {}
    
    public BuildContext(Cpu cpu, Motherboard motherboard, List<Ram> rams) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.rams = rams;
    }
    
    public Cpu getCpu() {
        return cpu;
    }
    
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }
    
    public Motherboard getMotherboard() {
        return motherboard;
    }
    
    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }
    
    public List<Ram> getRams() {
        return rams;
    }
    
    public void setRams(List<Ram> rams) {
        this.rams = rams;
    }
    
    public List<Storage> getStorages() {
        return storages;
    }
    
    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }
    
    public Gpu getGpu() {
        return gpu;
    }
    
    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }
    
    public Psu getPsu() {
        return psu;
    }
    
    public void setPsu(Psu psu) {
        this.psu = psu;
    }
    
    public Case getCase() {
        return caseEntity;
    }
    
    public void setCase(Case caseEntity) {
        this.caseEntity = caseEntity;
    }
    
    /**
     * Calcula el precio total de la configuración.
     * Suma los precios de todos los componentes seleccionados.
     *
     * @return El precio total de la configuración, o cero si no hay componentes o precios
     */
    public java.math.BigDecimal getTotalPrice() {
        java.math.BigDecimal total = java.math.BigDecimal.ZERO;
        total = addPriceIfPresent(total, cpu);
        total = addPriceIfPresent(total, motherboard);
        total = addPriceIfPresent(total, gpu);
        total = addPriceIfPresent(total, psu);
        total = addPriceIfPresent(total, caseEntity);
        total = addPricesFromList(total, rams);
        total = addPricesFromList(total, storages);
        return total;
    }

    /**
     * Suma el precio de un componente individual al total si el componente y su precio existen.
     *
     * @param total El total acumulado hasta el momento
     * @param component El componente del cual obtener el precio
     * @return El nuevo total después de sumar el precio del componente (si existe)
     */
    private java.math.BigDecimal addPriceIfPresent(
        java.math.BigDecimal total,
        PricedComponent component
    ) {
        if (component != null && component.getPrice() != null) {
            return total.add(component.getPrice());
        }
        return total;
    }

    /**
     * Suma los precios de una lista de componentes al total.
     *
     * @param total El total acumulado hasta el momento
     * @param components La lista de componentes de los cuales obtener los precios
     * @return El nuevo total después de sumar los precios de todos los componentes (si existen)
     */
    private java.math.BigDecimal addPricesFromList(
        java.math.BigDecimal total,
        java.util.List<? extends PricedComponent> components
    ) {
        if (components != null) {
            for (PricedComponent component : components) {
                total = addPriceIfPresent(total, component);
            }
        }
        return total;
    }
}
