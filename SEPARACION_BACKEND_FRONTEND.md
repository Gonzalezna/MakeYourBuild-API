# Separación Backend y Frontend - Guía de Mejores Prácticas

##  Resumen

Este proyecto sigue una arquitectura de **separación completa** entre backend y frontend, donde:

- **Backend (API)**: Proporciona servicios REST en el puerto `8080`
- **Frontend**: Aplicación independiente que consume la API (puerto `3000`, `5173`, `4200`, etc.)

##  Arquitectura

```
┌─────────────────┐         HTTP/REST         ┌─────────────────┐
│                 │ ◄──────────────────────── │                 │
│    Frontend     │                           │    Backend      │
│  (React/Vue/    │ ────────────────────────► │  (Spring Boot)  │
│   Angular)      │         JSON/API          │                 │
│  Puerto: 3000   │                           │  Puerto: 8080   │
└─────────────────┘                           └─────────────────┘
```

## ✅ Implementaciones Realizadas

### 1. Configuración de CORS (`CorsConfig.java`)

Permite que el frontend haga peticiones al backend desde diferentes puertos:

-  Configurado para puertos comunes de desarrollo
-  Métodos HTTP permitidos: GET, POST, PUT, DELETE, PATCH, OPTIONS
-  Headers permitidos: todos
-  Credenciales habilitadas para autenticación

### 2. Configuración de Seguridad (`SecurityConfig.java`)

-  CSRF desactivado (no necesario para APIs REST stateless)
-  CORS habilitado
-  Sesiones stateless (preparado para JWT)
-  Endpoints públicos: `/api/health`, `/api/docs/**`

### 3. Estructura de Paquetes

```
com.makeyourbuild.api/
├── config/          # Configuraciones (CORS, Security, etc.)
├── controller/      # Controladores REST
├── service/         # Lógica de negocio
├── repository/      # Acceso a datos (JPA)
├── model/          # Entidades y DTOs
└── exception/      # Manejo de excepciones
```

##  Cómo Usar

### Desarrollo Local

1. **Backend**:
   ```bash
   cd api
   ./mvnw spring-boot:run
   ```
   El backend estará disponible en: `http://localhost:8080`

2. **Frontend** (en un proyecto separado):
   ```bash
   # Ejemplo con React
   npm start
   # O con Vite
   npm run dev
   ```
   El frontend estará disponible en: `http://localhost:3000` (o el puerto configurado)

### Endpoints Disponibles

- `GET /api/health` - Verificar estado del backend
- `GET /api/docs/**` - Documentación de la API (cuando se configure Swagger)

## 📝 Próximos Pasos Recomendados

### Backend

1. **Autenticación JWT**:
   - Implementar JWT para autenticación stateless
   - Crear endpoints de login/registro

2. **Documentación API**:
   - Agregar Swagger/OpenAPI para documentar endpoints
   - Configurar `/api/docs` para acceso público

3. **Validación y Manejo de Errores**:
   - Crear DTOs con validaciones
   - Implementar manejo global de excepciones

4. **Testing**:
   - Tests unitarios para servicios
   - Tests de integración para controladores

### Frontend

1. **Crear proyecto frontend separado**:
   ```bash
   # React
   npx create-react-app frontend
   
   # Vue
   npm create vite@latest frontend -- --template vue
   
   # Angular
   ng new frontend
   ```

2. **Configurar cliente HTTP**:
   - Axios o Fetch API
   - Base URL: `http://localhost:8080/api`
   - Interceptores para autenticación

3. **Manejo de CORS**:
   - El backend ya está configurado
   - Solo asegúrate de usar la URL correcta del backend

## 🔒 Seguridad en Producción

### CORS en Producción

Actualiza `CorsConfig.java` para permitir solo tu dominio de producción:

```java
configuration.setAllowedOrigins(Arrays.asList(
    "https://tu-dominio-frontend.com"
));
```

### Variables de Entorno

Usa `application.properties` o variables de entorno para configuración sensible:

```properties
# application-prod.properties
cors.allowed-origins=https://tu-dominio-frontend.com
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## 📚 Recursos Adicionales

- [Spring Boot CORS](https://spring.io/guides/gs/rest-service-cors/)
- [Spring Security](https://spring.io/projects/spring-security)
- [REST API Best Practices](https://restfulapi.net/)

## ❓ Preguntas Frecuentes

**P: ¿Puedo servir el frontend desde el mismo proyecto Spring Boot?**
R: Sí, pero no es recomendado. La separación permite escalar, desplegar y desarrollar independientemente.

**P: ¿Cómo manejo la autenticación?**
R: Implementa JWT. El backend genera tokens y el frontend los almacena (localStorage/cookies) y los envía en cada petición.

**P: ¿Qué pasa con las rutas del frontend (SPA)?**
R: El backend no necesita conocer las rutas del frontend. El frontend maneja el routing internamente.
