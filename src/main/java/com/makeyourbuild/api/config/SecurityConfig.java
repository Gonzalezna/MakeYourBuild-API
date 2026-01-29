package com.makeyourbuild.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Configuración de seguridad para la API REST.
 * 
 * Esta configuración:
 * - Desactiva CSRF (no necesario para APIs REST stateless)
 * - Permite acceso a los endpoints de la API
 * - Configura CORS
 * - Usa sesiones stateless (JWT recomendado para producción)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desactivar CSRF para APIs REST (stateless)
            .csrf(AbstractHttpConfigurer::disable)
            
            // Configurar CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            
            // Configurar autorización de endpoints
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso público a endpoints de salud y documentación
                .requestMatchers("/api/health", "/api/docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // MVP: Permitir acceso público a todos los endpoints de la API (sin autenticación)
                .requestMatchers("/api/**").permitAll()
                // Permitir acceso a recursos estáticos (si los hay)
                .requestMatchers("/static/**", "/templates/**").permitAll()
                // Cualquier otra petición requiere autenticación (para futuras funcionalidades)
                .anyRequest().authenticated()
            )
            
            // Configurar sesiones como stateless (para JWT)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}
