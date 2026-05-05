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
 * <p>
 * Comportamiento actual:
 * <ul>
 *   <li>CSRF desactivado (API sin formularios de navegador tradicionales)</li>
 *   <li>CORS según {@link CorsConfigurationSource} inyectado</li>
 *   <li>Rutas bajo {@code /api/**} con acceso público (MVP)</li>
 *   <li>Salud y OpenAPI/Swagger también públicos</li>
 *   <li>Sin sesión de servidor: {@link SessionCreationPolicy#STATELESS}</li>
 *   <li>Cualquier otra ruta (fuera de los patrones anteriores) exige usuario autenticado</li>
 * </ul>
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
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/health", "/api/docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/static/**", "/templates/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }
}
