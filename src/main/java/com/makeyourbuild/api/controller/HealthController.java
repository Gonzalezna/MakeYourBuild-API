package com.makeyourbuild.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controlador de salud para verificar que la API está funcionando.
 * 
 * Este endpoint es público y puede ser usado por el frontend o herramientas
 * de monitoreo para verificar el estado del backend.
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    /**
     * Endpoint de health check para verificar que el servicio está activo.
     * 
     * @return Respuesta con status "UP", nombre del servicio y mensaje confirmando
     *         que el backend está funcionando correctamente.
     */
    @GetMapping
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "MakeYourBuild API",
            "message", "Backend is running"
        ));
    }
}
