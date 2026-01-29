package com.makeyourbuild.api.exception;

/**
 * Excepción lanzada cuando una entidad no se encuentra en el repositorio.
 * Esta excepción es más específica que RuntimeException y permite un mejor manejo de errores.
 */
public class EntityNotFoundException extends RuntimeException {
    
    private final String entityName;
    private final Long entityId;
    
    /**
     * Crea una excepción para una entidad no encontrada.
     *
     * @param entityName El nombre de la entidad (ej: "CPU", "Motherboard", "RAM")
     * @param entityId El ID de la entidad que no se encontró
     */
    public EntityNotFoundException(String entityName, Long entityId) {
        super(String.format("%s no encontrad%s con ID: %d", 
            entityName, 
            getGenderSuffix(entityName), 
            entityId));
        this.entityName = entityName;
        this.entityId = entityId;
    }
    
    /**
     * Crea una excepción para una entidad no encontrada con un mensaje personalizado.
     *
     * @param message El mensaje de error
     */
    public EntityNotFoundException(String message) {
        super(message);
        this.entityName = null;
        this.entityId = null;
    }
    
    /**
     * Determina el sufijo de género para el mensaje en español.
     * "CPU", "GPU", "PSU", "RAM", "Motherboard" son femeninas, "Case", "Storage" son masculinos.
     */
    private static String getGenderSuffix(String entityName) {
        if (entityName == null) {
            return "o";
        }
        String upper = entityName.toUpperCase();
        // Entidades femeninas
        if (upper.equals("CPU") || upper.equals("GPU") || upper.equals("PSU") || 
            upper.equals("RAM") || upper.equals("MOTHERBOARD")) {
            return "a";
        }
        // Entidades masculinas (Case, Storage)
        return "o";
    }
    
    public String getEntityName() {
        return entityName;
    }
    
    public Long getEntityId() {
        return entityId;
    }
}
