# MakeYourBuild API

API REST desarrollada en Java con Spring Boot para validación de compatibilidad de componentes de PC y cálculo de presupuestos. Este backend funciona como un motor reutilizable que puede ser consumido por aplicaciones web propias o integrado en otras plataformas mediante una API REST.

## Descripción

MakeYourBuild API es un sistema backend robusto que valida la compatibilidad técnica entre componentes de hardware de PC (CPU, Motherboard, RAM, GPU, PSU, Storage, Case) y calcula presupuestos automáticamente. El sistema implementa un motor de reglas extensible con 17 reglas de compatibilidad que distinguen entre errores bloqueantes y advertencias, proporcionando una experiencia de usuario clara y precisa.

El proyecto está diseñado siguiendo principios de Domain-Driven Design (DDD) y Clean Architecture, con un enfoque en la separación de responsabilidades, testabilidad y extensibilidad. Las reglas de negocio están completamente desacopladas del framework Spring Boot, lo que permite reutilización y testing independiente.

## Objetivo del Proyecto

El objetivo principal de este proyecto es demostrar habilidades en:

- **Diseño de Arquitectura**: Implementación de arquitectura en capas con separación clara de responsabilidades
- **Domain-Driven Design**: Aplicación de DDD para modelar reglas de negocio
- **Principios SOLID**: Aplicación consistente de principios de diseño orientado a objetos
- **API RESTful**: Diseño e implementación de APIs REST bien estructuradas
- **Manejo de Errores**: Sistema de manejo de errores y validaciones

## Tecnologías y Stack

### Backend
- **Java 21**: Lenguaje de programación
- **Spring Boot 4.0.1**: Framework de aplicación
- **Spring Data JPA**: Persistencia de datos
- **Spring Security**: Seguridad y autenticación
- **Maven**: Gestión de dependencias y build

### Base de Datos
- **PostgreSQL 12+**: Base de datos relacional
- **Supabase**: Plataforma de base de datos en la nube
- **Hibernate/JPA**: ORM para mapeo objeto-relacional

### Arquitectura y Patrones
- **Domain-Driven Design (DDD)**: Diseño orientado al dominio
- **REST API**: Arquitectura de servicios RESTful
- **Layered Architecture**: Arquitectura en capas
- **SOLID Principles**: Principios de diseño orientado a objetos
- **Repository Pattern**: Patrón de repositorio para acceso a datos
- **Strategy Pattern**: Patrón de estrategia para reglas de compatibilidad

## Funcionalidades Principales

### Motor de Reglas Extensible
- 17 reglas de compatibilidad implementadas
- Sistema fácilmente extensible para agregar nuevas validaciones
- Separación clara entre errores bloqueantes y advertencias
- Códigos estructurados para identificación programática en el frontend

### Filtrado en Tiempo Real
- Endpoints `/compatible` para filtrar componentes mientras el usuario construye la build
- Validación incremental sin necesidad de validar toda la configuración
- Mejora de la experiencia de usuario al mostrar solo opciones válidas

### Cálculo Automático de Presupuestos
- Cálculo en tiempo real del precio total de la configuración
- Soporte para múltiples módulos de RAM y unidades de almacenamiento
- Precisión con `BigDecimal` para cálculos monetarios

### Validación de Compatibilidad
- Validación de socket CPU-Motherboard
- Validación de chipset y generación de CPU //Es posible que el socket CPU-Motherboard sea compatible, pero el chipset no soporte generaciones de CPU muy antiguas
- Validación de tipo y frecuencia de RAM
- Validación de slots disponibles (RAM, Storage, M.2)
- Validación de tamaño GPU-Case
- Validación de potencia PSU
- Validación de form factor Case-Motherboard

### Sistema de Errores y Advertencias
- Errores bloqueantes que impiden la configuración
- Advertencias informativas que no bloquean la build
- Códigos estructurados para manejo programático en el frontend
- Mensajes descriptivos y claros para el usuario


## Desafíos Técnicos y Aprendizajes
  
  - **Uso crítico de herramientas de IA**  
  Durante el desarrollo utilicé herramientas de IA como acelerador de productividad.
  Sin embargo, fue necesario revisar y refactorizar código generado automáticamente,
  donde se detectaron violaciones a principios SOLID y DRY.
  Estas refactorizaciones permitieron mejorar la mantenibilidad, reducir duplicación
  y desacoplar la lógica de negocio.

- **Diseño de reglas de compatibilidad**  
  Más que un problema puntual, el desafío fue investigar y definir cómo
  modelar reglas técnicas reales de compatibilidad entre componentes.
  Esto implicó analizar el dominio, separar reglas del framework y definir un flujo
  de validación claro y extensible.

- **Definición del flujo del sistema**  
  Aprendí la importancia de diseñar el flujo completo del programa antes de
  implementar, especialmente en un backend orientado a reglas, evitando soluciones
  acopladas o difíciles de extender.

# Ejemplos concretos de problemas/barreras encontrados.

**Problema**: Lógica de compatibilidad de form factors duplicada y basada en comparaciones de strings.

**Solución**: Centralicé la lógica en el enum `FormFactor`, encapsulando el comportamiento de compatibilidad mediante un ranking numérico y eliminando duplicación.

**Aprendizaje**: Los enums en Java pueden encapsular lógica de negocio y facilitar diseños Open/Closed.

### Manejo de Errores vs Advertencias

**Problema**: Necesitaba distinguir entre errores que bloquean la build (socket incompatible) y advertencias informativas (RAM de baja capacidad para CPU de gama alta).

**Solución**: Implementé un sistema de `RuleResult` con severidad:
- `RuleResult.error()` para errores bloqueantes
- `RuleResult.warning()` para advertencias
- Códigos estructurados (`ErrorCode`, `WarningCode`) para identificación programática
- El frontend puede manejar cada tipo de manera diferente

**Aprendizaje**: Diseñar APIs pensando en cómo el frontend consumirá los datos. Los códigos estructurados son mucho mejores que depender del parsing de strings.


### Aprendizajes Clave del Proyecto

- **SOLID**: Aplicar SOLID facilitó refactorizaciones y la extensión del sistema sin modificar lógica existente.
- **DTOs son esenciales**: Separar entidades de dominio de objetos de transferencia protege la API y facilita cambios futuros
- **Excepciones personalizadas**: Crear excepciones específicas (`EntityNotFoundException`) mejora el manejo de errores y los códigos HTTP correctos
- **Domain-Driven Design**: Separar reglas de negocio del framework permite testing más fácil y código más claro

## Arquitectura

El proyecto sigue una arquitectura en capas con separación clara de responsabilidades, siguiendo principios de Domain-Driven Design (DDD) y Clean Architecture.

```
┌─────────────────────────────────────────┐
│         Controllers (REST API)          │
│  - Sin lógica de negocio                │
│  - Solo delega a servicios              │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│           Services (Orquestadores)       │
│  - Lógica de negocio                    │
│  - Coordina repositorios y reglas       │
└─────────────────────────────────────────┘
         ↓                    ↓
┌──────────────────┐  ┌──────────────────┐
│   Repositories   │  │   Domain Rules   │
│   (Data Access)  │  │  (Business Logic)│
└──────────────────┘  └──────────────────┘
         ↓
┌─────────────────────────────────────────┐
│      Domain Models (JPA Entities)       │
│  - Entidades del dominio                │
│  - Independientes de HTTP                │
└─────────────────────────────────────────┘
```

### Principios de Diseño Aplicados

**Domain-Driven Design (DDD)**: Las reglas de negocio viven en el dominio, completamente desacopladas del framework Spring Boot. Esto permite que las reglas puedan ejecutarse independientemente del framework.

**Separation of Concerns**: Cada capa tiene una responsabilidad única y bien definida:
- Controllers: Manejo de HTTP, validación de entrada
- Services: Orquestación de lógica de negocio
- Repositories: Acceso a datos
- Domain Rules: Reglas de compatibilidad puras

**Dependency Inversion Principle**: Las capas superiores dependen de abstracciones (interfaces), no de implementaciones concretas. Por ejemplo, los servicios dependen de `CompatibilityRule` (interfaz), no de implementaciones específicas. 

**Single Responsibility Principle**: Cada clase tiene una única razón para cambiar. Por ejemplo, `CpuMotherRule` solo valida compatibilidad CPU-Motherboard, nada más.

**Open/Closed Principle**: El sistema de reglas es extensible sin modificar código existente. Para agregar una nueva regla, solo necesitas crear una nueva clase que implemente `CompatibilityRule`.

**Interface Segregation**: Interfaces específicas (`PricedComponent`, `CompatibilityRule`) en lugar de interfaces genéricas que fuerzan implementaciones innecesarias.

### Desacoplamiento del Framework

Las reglas de compatibilidad están completamente desacopladas de Spring Boot. Pueden ejecutarse independientemente del framework, lo que facilita:
- Testing unitario sin contexto de Spring
- Reutilización en otros proyectos
- Migración a otros frameworks si fuera necesario

## API Overview

### Base URL
```
http://localhost:8080/api
```

### Endpoints Principales

**Componentes Individuales**
- `GET /api/cpus` - Lista todos los CPUs
- `GET /api/cpus/{id}` - Obtiene un CPU por ID
- `GET /api/motherboards` - Lista todas las motherboards
- `GET /api/motherboards/{id}` - Obtiene una motherboard por ID
- `GET /api/rams` - Lista todos los módulos de RAM
- `GET /api/rams/{id}` - Obtiene un módulo de RAM por ID
- Similar para GPU, PSU, Storage, Case

**Endpoints de Compatibilidad**
- `GET /api/motherboards/compatible?cpuId={id}` - Motherboards compatibles con un CPU
- `GET /api/rams/compatible?motherboardId={id}&existingRamIds={ids}` - RAMs compatibles
- `GET /api/cases/compatible?gpuId={id}&motherboardId={id}` - Cases compatibles
- `GET /api/storages/compatible?motherboardId={id}&caseId={id}` - Storages compatibles
- `POST /api/psus/compatible` - PSUs compatibles (calcula consumo total)

**Validación de Build**
- `POST /api/builds/validate` - Valida una configuración completa y calcula presupuesto

**Health Check**
- `GET /api/health` - Verifica el estado del servidor

### Sistema de Respuestas

Todas las respuestas de error y advertencia incluyen:
- Código estructurado para identificación programática
- Mensaje descriptivo para el usuario
- Componentes afectados

Ejemplo de respuesta de validación:
```json
{
  "valid": true,
  "totalPrice": 2549.97,
  "cpu": { /* CpuDTO */ },
  "motherboard": { /* MotherboardDTO */ },
  "rams": [ /* List<RamDTO> */ ],
  "errors": [],
  "warnings": [
    {
      "code": "RAM_CAPACITY_LOW",
      "message": "Se recomienda al menos 16GB de RAM para CPUs de gama alta",
      "component": "ram,cpu"
    }
  ]
}
```

Para documentación completa de endpoints y DTOs, consulta el archivo `ENDPOINTS_Y_DTOS.md` en la raíz del proyecto.

## Instalación y Ejecución

### Requisitos Previos
- Java 21 o superior
- Maven 3.6 o superior
- PostgreSQL 12 o superior (o cuenta de Supabase)

### Pasos de Instalación

1. **Clonar el Repositorio**
```bash
git clone <repository-url>
cd api/backend
```

2. **Configurar Base de Datos**

El archivo `application.properties` con credenciales reales NO está en el repositorio por seguridad.

Copia el archivo de ejemplo:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edita `src/main/resources/application.properties` y reemplaza los placeholders con tus credenciales:
```properties
spring.datasource.url=jdbc:postgresql://TU_HOST:5432/postgres?sslmode=require
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=org.postgresql.Driver
```

3. **Poblar Base de Datos (Opcional)**

Los scripts SQL para poblar la base de datos con datos de ejemplo se encuentran en la carpeta `SUPABASE-DATA/`:
- `procesadores.sql` - CPUs
- `motherboards.sql` - Motherboards
- `memorias_ram.sql` - Módulos de RAM
- `almacenamiento.sql` - Unidades de almacenamiento
- `tarjetas graficas.sql` - GPUs
- `fuentes_poder.sql` - Fuentes de alimentación
- `gabinetes.sql` - Gabinetes

Ejecuta estos scripts en tu base de datos PostgreSQL usando el SQL Editor de Supabase o cualquier cliente PostgreSQL.

4. **Compilar y Ejecutar**
```bash
# Compilar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

5. **Verificar Instalación**
```bash
curl http://localhost:8080/api/health
```

Deberías recibir:
```json
{
  "status": "UP",
  "service": "MakeYourBuild API",
  "message": "Backend is running"
}
```

## Estructura del Proyecto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/makeyourbuild/api/
│   │   │   ├── config/              # Configuración (CORS, Security)
│   │   │   ├── controller/          # Controladores REST
│   │   │   ├── service/             # Servicios (lógica de negocio)
│   │   │   ├── repository/          # Repositorios JPA
│   │   │   ├── domain/
│   │   │   │   ├── model/           # Entidades JPA
│   │   │   │   ├── enums/           # Enumeraciones
│   │   │   │   ├── rules/           # Reglas de compatibilidad
│   │   │   │   └── util/            # Utilidades del dominio
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   └── exception/           # Manejo de excepciones
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.properties.example
│   └── test/                        # Tests
├── SUPABASE-DATA/                    # Scripts SQL para datos iniciales
├── pom.xml                          # Configuración Maven
└── README.md                        # Este archivo
```

### Descripción de Paquetes

**config/**: Configuraciones de Spring (CORS, Security). Las configuraciones leen de variables de entorno para flexibilidad.

**controller/**: Controladores REST. Solo delegan a servicios, sin lógica de negocio.

**service/**: Servicios que orquestan la lógica de negocio, coordinan repositorios y ejecutan reglas de compatibilidad.

**repository/**: Repositorios Spring Data JPA para acceso a datos. Interfaz simple que Spring implementa automáticamente.

**domain/model/**: Entidades JPA que representan los componentes de hardware. Son el modelo de dominio.

**domain/rules/**: Reglas de compatibilidad. Cada regla implementa `CompatibilityRule` y puede retornar errores o advertencias.

**domain/enums/**: Enumeraciones del dominio (SocketType, RamType, StorageType, FormFactor, etc.).

**domain/util/**: Utilidades del dominio (FormFactorUtils). Lógica relacionada al dominio pero que no pertenece a una entidad específica.

**dto/**: Data Transfer Objects. Objetos que se transfieren entre cliente y servidor. Separados de las entidades para proteger la API.

**exception/**: Excepciones personalizadas (EntityNotFoundException, BusinessException) y manejador global de excepciones.

## Roadmap / Mejoras Futuras

### Corto Plazo
- Implementar tests unitarios para reglas de compatibilidad
- Agregar tests de integración para endpoints principales
- Implementar paginación en endpoints de listado
- Agregar filtros y búsqueda en endpoints de componentes
- Implementacion del feature Pre-Built, donde muestra equipos pre-armados segun tu presupuesto y objetivo del equipo
- Listado de componentes disponibles en la web

### Mediano Plazo
- Implementar autenticación JWT para endpoints de administración
- Implementar el login de usuarios
- Agregar endpoints para CRUD de componentes (actualmente solo lectura)
- Implementar caché para mejorar performance en consultas frecuentes
- Agregar documentación OpenAPI/Swagger

### Largo Plazo
- Implementar sistema de puntuacion de builds, segun precio/potencia/balance de componentes
- Implementar listado de builds mas populares

## Estado del Proyecto

**Estado Actual**: MVP Completo

El proyecto está en un estado funcional completo con todas las funcionalidades principales implementadas:

-  Motor de reglas con 17 reglas de compatibilidad
-  Endpoints RESTful para todos los componentes
-  Endpoints de compatibilidad en tiempo real
-  Validación completa de builds
-  Cálculo automático de presupuestos
-  Sistema de errores y advertencias estructurado
-  Manejo de errores robusto
-  Configuración de seguridad implementada
-  Documentación completa

**Próximos Pasos**: 
- Implementar suite de tests
- Agregar documentación OpenAPI/Swagger
- Optimizaciones de performance
