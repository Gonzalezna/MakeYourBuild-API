package com.makeyourbuild.api.domain.enums;

/**
 * Enum para categorías de builds predefinidos.
 * Define las categorías permitidas para los Pre-Built builds.
 */
public enum PreBuiltCategory {
    GAMING("Gaming"),
    WORKSTATION("Workstation"),
    GRAPHIC_DESIGN("Graphic design"),
    STREAMING("Streaming"),
    PERSONAL_HOME("Personal/Home");
    
    private final String displayName;
    
    PreBuiltCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Convierte un String a PreBuiltCategory (case-insensitive).
     * Útil para recibir categorías desde el frontend o base de datos.
     * 
     * @param value String a convertir (puede ser displayName o nombre del enum)
     * @return PreBuiltCategory correspondiente, o null si no se encuentra
     */
    public static PreBuiltCategory fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        String trimmed = value.trim();
        
        // Buscar por displayName (case-insensitive)
        for (PreBuiltCategory category : values()) {
            if (category.displayName.equalsIgnoreCase(trimmed) || 
                category.name().equalsIgnoreCase(trimmed)) {
                return category;
            }
        }
        
        return null;
    }
}
