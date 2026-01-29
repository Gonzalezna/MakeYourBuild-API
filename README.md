# MakeYourBuild API

API REST desarrollada en Java con Spring Boot para validación de compatibilidad de componentes de PC y cálculo de presupuestos. Este backend funciona como un motor reutilizable que puede ser consumido por aplicaciones web propias o integrado en otras plataformas mediante una API REST.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [API Endpoints](#api-endpoints)
- [DTOs (Data Transfer Objects)](#dtos-data-transfer-objects)
- [Reglas de Compatibilidad](#reglas-de-compatibilidad)
- [Manejo de Errores](#manejo-de-errores)
- [CORS y Seguridad](#cors-y-seguridad)
- [Base de Datos](#base-de-datos)
- [Ejemplos de Uso](#ejemplos-de-uso)
- [Desarrollo](#desarrollo)

---

## 🎯 Características

- ✅ **Validación de Compatibilidad**: Motor de reglas extensible para validar compatibilidad técnica entre componentes
- ✅ **Filtrado en Tiempo Real**: Endpoints de compatibilidad para filtrar componentes mientras el usuario construye la build
- ✅ **Cálculo de Presupuesto**: Cálculo automático del precio total de la configuración
- ✅ **Errores y Advertencias**: Sistema de validación que distingue entre errores bloqueantes y advertencias
- ✅ **API RESTful**: Endpoints RESTful bien estructurados y documentados
- ✅ **Arquitectura Limpia**: Separación clara entre dominio, servicios, controladores y DTOs
- ✅ **Extensible**: Sistema de reglas fácilmente extensible para agregar nuevas validaciones
- ✅ **Sin Dependencias de Frontend**: Backend completamente independiente, listo para consumir desde cualquier cliente

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas con separación clara de responsabilidades:

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

### Principios de Diseño

- **Domain-Driven Design (DDD)**: Las reglas de negocio viven en el dominio, desacopladas del framework
- **Separation of Concerns**: Cada capa tiene una responsabilidad clara
- **Dependency Inversion**: Las capas superiores dependen de abstracciones
- **Single Responsibility**: Cada clase tiene una única responsabilidad

---

## 📦 Requisitos

- **Java**: 21 o superior
- **Maven**: 3.6 o superior
- **PostgreSQL**: 12 o superior (o Supabase)
- **Spring Boot**: 4.0.1

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone <repository-url>
cd api/backend
```

### 2. Configurar Base de Datos

**IMPORTANTE**: El archivo `application.properties` con credenciales reales NO está en el repositorio por seguridad.

1. Copia el archivo de ejemplo:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

2. Edita `src/main/resources/application.properties` y reemplaza los placeholders con tus credenciales:
```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://TU_HOST:5432/postgres?sslmode=require
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=org.postgresql.Driver
```

Solo el archivo de ejemplo (`application.properties.example`) está en el repositorio.

### 3. Poblar Base de Datos (Opcional)

Los scripts SQL para poblar la base de datos con datos de ejemplo se encuentran en la carpeta `SUPABASE-DATA/`:

- `procesadores.sql` - CPUs
- `motherboards.sql` - Motherboards
- `memorias_ram.sql` - Módulos de RAM
- `almacenamiento.sql` - Unidades de almacenamiento
- `tarjetas graficas.sql` - GPUs
- `fuentes_poder.sql` - Fuentes de alimentación
- `gabinetes.sql` - Gabinetes

Ejecuta estos scripts en tu base de datos PostgreSQL usando el SQL Editor de Supabase o cualquier cliente PostgreSQL.

### 4. Compilar y Ejecutar

```bash
# Compilar
mvn clean install

# Ejecutar
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

### 5. Verificar Instalación

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

---

## 📁 Estructura del Proyecto

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
│   │   │   │   └── rules/           # Reglas de compatibilidad
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   └── exception/           # Manejo de excepciones
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Tests
├── SUPABASE-DATA/                    # Scripts SQL para datos iniciales
├── pom.xml                          # Configuración Maven
└── README.md                        # Este archivo
```

---

## 🌐 API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Health Check

#### `GET /api/health`
Verifica el estado del servidor.

**Respuesta:**
```json
{
  "status": "UP",
  "service": "MakeYourBuild API",
  "message": "Backend is running"
}
```

---

### CPU (Procesadores)

#### `GET /api/cpus`
Lista todos los CPUs disponibles.

**Respuesta:**
```json
[
  {
    "id": 1,
    "name": "AMD Ryzen 9 7950X",
    "brand": "AMD",
    "socket": "AM5",
    "price": 699.99,
    "cores": 16,
    "threads": 32,
    "baseClock": 4.5,
    "boostClock": 5.7,
    "tdp": 170,
    "tier": "high",
    "minRamFrequency": 5200,
    "generation": "RYZEN_7000"
  }
]
```

#### `GET /api/cpus/{id}`
Obtiene un CPU específico por ID.

---

### Motherboard (Placas Base)

#### `GET /api/motherboards`
Lista todas las motherboards disponibles.

#### `GET /api/motherboards/{id}`
Obtiene una motherboard específica por ID.

#### `GET /api/motherboards/compatible?cpuId={cpuId}`
Lista motherboards compatibles con un CPU específico.

**Parámetros:**
- `cpuId` (query param, requerido): ID del CPU

**Ejemplo:**
```bash
GET /api/motherboards/compatible?cpuId=1
```

---

### RAM (Memoria)

#### `GET /api/rams`
Lista todos los módulos de RAM disponibles.

#### `GET /api/rams/{id}`
Obtiene un módulo de RAM específico por ID.

#### `GET /api/rams/compatible?cpuId={cpuId}&motherboardId={motherboardId}&existingRamIds={ids}`
Lista módulos de RAM compatibles con un CPU y motherboard específicos.

**Parámetros:**
- `cpuId` (query param, opcional): ID del CPU (para futuras validaciones)
- `motherboardId` (query param, requerido): ID de la motherboard
- `existingRamIds` (query param, opcional): Lista de IDs de RAMs ya seleccionadas (separados por comas, ej: `3,4,5`)

**Validaciones:**
- **Tipo de RAM**: Debe coincidir con el tipo soportado por la motherboard (DDR4 o DDR5)
- **Frecuencia**: La frecuencia de la RAM no debe exceder la frecuencia máxima de la motherboard
- **Slots disponibles**: No debe exceder los slots disponibles en la motherboard (considerando RAMs ya seleccionadas)
- **Capacidad máxima**: No debe exceder la capacidad máxima total de RAM soportada:
  - DDR4: hasta 32GB por módulo (ej: 4 slots = 128GB máximo)
  - DDR5: hasta 64GB por módulo (ej: 4 slots = 256GB máximo)

**Ejemplo:**
```bash
GET /api/rams/compatible?cpuId=1&motherboardId=2&existingRamIds=3,4
```

**Respuesta:**
```json
[
  {
    "id": 5,
    "name": "Corsair Vengeance DDR5 32GB",
    "brand": "Corsair",
    "type": "DDR5",
    "capacity": 32,
    "frequency": 6000,
    "price": 149.99,
    "modules": 1,
    "casLatency": 36
  }
]
```

---

### Storage (Almacenamiento)

#### `GET /api/storages`
Lista todas las unidades de almacenamiento disponibles.

#### `GET /api/storages/{id}`
Obtiene una unidad de almacenamiento específica por ID.

#### `GET /api/storages/compatible?motherboardId={id}&caseId={id}&existingStorageIds={ids}`
Lista unidades de almacenamiento compatibles con una motherboard y/o case específicos.

**Parámetros:**
- `motherboardId` (query param, opcional): ID de la motherboard
- `caseId` (query param, opcional): ID del case
- `existingStorageIds` (query param, opcional): Lista de IDs de storages ya seleccionados (separados por comas, ej: `3,4,5`)

**Validaciones:**
- **NVMe M.2**: Valida que haya slots M.2 disponibles en la motherboard (considera storages ya seleccionados)
- **SATA (SSD 2.5" y HDD 3.5")**: 
  - Valida puertos SATA disponibles en la motherboard
  - Valida slots disponibles en el case (2.5" y 3.5" según corresponda)

**Ejemplo:**
```bash
GET /api/storages/compatible?motherboardId=2&caseId=3&existingStorageIds=4,5
```

**Respuesta:**
```json
[
  {
    "id": 6,
    "name": "Samsung 980 PRO 1TB",
    "brand": "Samsung",
    "type": "NVME_SSD",
    "capacity": 1000,
    "price": 149.99,
    "readSpeed": 7000,
    "writeSpeed": 5000,
    "formFactor": "M.2"
  }
]
```

---

### GPU (Tarjeta Gráfica)

#### `GET /api/gpus`
Lista todas las GPUs disponibles.

#### `GET /api/gpus/{id}`
Obtiene una GPU específica por ID.

---

### PSU (Fuente de Alimentación)

#### `GET /api/psus`
Lista todas las fuentes de alimentación disponibles.

#### `GET /api/psus/{id}`
Obtiene una fuente de alimentación específica por ID.

#### `POST /api/psus/compatible`
Lista fuentes de alimentación compatibles con una configuración parcial de build.

**Request Body:**
```json
{
  "cpuId": 1,
  "gpuId": 6,
  "motherboardId": 2,
  "ramIds": [3, 4],
  "storageIds": [5, 6]
}
```

**Nota:** Todos los campos son opcionales. El sistema calculará el consumo total estimado de los componentes proporcionados y retornará PSUs con suficiente potencia (consumo + 20% overhead).

**Cálculo de Consumo:**
- CPU TDP
- GPU TDP
- Motherboard power consumption
- RAM: 5W por módulo (suma todos los módulos de todas las RAMs seleccionadas)
- Storage: 10W por disco
- Overhead: 20% adicional

**Ejemplo:**
```bash
POST /api/psus/compatible
Content-Type: application/json

{
  "cpuId": 1,
  "gpuId": 6,
  "motherboardId": 2,
  "ramId": 3
}
```

**Respuesta:**
```json
[
  {
    "id": 7,
    "name": "Corsair RM850x",
    "brand": "Corsair",
    "wattage": 850,
    "price": 149.99,
    "efficiency": "Gold",
    "modular": true,
    "formFactor": "ATX"
  }
]
```

---

### Case (Gabinete)

#### `GET /api/cases`
Lista todos los gabinetes disponibles.

#### `GET /api/cases/{id}`
Obtiene un gabinete específico por ID.

#### `GET /api/cases/compatible?gpuId={id}&motherboardId={id}`
Lista gabinetes compatibles con una GPU y/o motherboard específicos.

**Parámetros:**
- `gpuId` (query param, opcional): ID de la GPU
- `motherboardId` (query param, opcional): ID de la motherboard

**Validaciones:**
- **GPU**: Valida que el case tenga suficiente espacio para la GPU (`maxGpuLength >= gpu.length`)
- **Motherboard**: Valida que el case soporte el form factor de la motherboard
  - ATX puede soportar: ATX, mATX, ITX
  - mATX puede soportar: mATX, ITX
  - ITX solo puede soportar: ITX
  - EATX puede soportar: ATX, mATX, ITX

**Ejemplo:**
```bash
GET /api/cases/compatible?gpuId=6&motherboardId=2
```

**Respuesta:**
```json
[
  {
    "id": 8,
    "name": "Fractal Design Meshify C",
    "brand": "Fractal Design",
    "supportedFormFactor": "ATX",
    "price": 99.99,
    "maxGpuLength": 315,
    "maxCpuCoolerHeight": 170,
    "storage25Slots": 2,
    "storage35Slots": 2,
    "includesFans": true,
    "fanSlots": 6
  }
]
```

---

### Build (Validación de Configuración)

#### `POST /api/builds/validate`
Valida una configuración completa de PC y calcula el presupuesto total.

**Request Body:**
```json
{
  "cpuId": 1,
  "motherboardId": 2,
  "ramIds": [3, 4],
  "storageIds": [5, 6],
  "gpuId": 7,
  "psuId": 8,
  "caseId": 9
}
```

**Nota:** Todos los campos son opcionales excepto los que quieras validar. Puedes enviar solo `cpuId`, `motherboardId` y `ramIds` para validar una configuración básica. `ramIds` es un array porque puedes seleccionar múltiples módulos de RAM.

**Respuesta:**
```json
{
  "valid": true,
  "totalPrice": 2549.97,
  "cpu": { /* CpuDTO */ },
  "motherboard": { /* MotherboardDTO */ },
  "ram": { /* RamDTO */ },
  "storages": [ /* StorageDTO[] */ ],
  "gpu": { /* GpuDTO */ },
  "psu": { /* PsuDTO */ },
  "caseEntity": { /* CaseDTO */ },
  "errors": [],
  "warnings": [
    {
      "code": "RAM_CAPACITY_LOW",
      "message": "Se recomienda al menos 16GB de RAM para CPUs de gama alta. Actualmente tienes 8GB",
      "component": "ram,cpu"
    }
  ]
}
```

**Códigos de Estado:**
- `200 OK`: Validación exitosa (puede tener errores o advertencias)
- `400 Bad Request`: Error en la validación del request (campos inválidos)
- `404 Not Found`: Componente no encontrado
- `500 Internal Server Error`: Error interno del servidor

---

## 📦 DTOs (Data Transfer Objects)

### CpuDTO

```json
{
  "id": 1,
  "name": "AMD Ryzen 9 7950X",
  "brand": "AMD",
  "socket": "AM5",
  "price": 699.99,
  "cores": 16,
  "threads": 32,
  "baseClock": 4.5,
  "boostClock": 5.7,
  "tdp": 170,
  "tier": "high",
  "minRamFrequency": 5200,
  "generation": "RYZEN_7000"
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre del CPU
- `brand` (String): Marca (AMD, Intel)
- `socket` (SocketType): Tipo de socket (AM4, AM5, LGA1700, LGA1200, LGA1151)
- `price` (BigDecimal): Precio en USD
- `cores` (Integer): Número de núcleos
- `threads` (Integer): Número de hilos
- `baseClock` (Double): Frecuencia base en GHz
- `boostClock` (Double): Frecuencia máxima en GHz
- `tdp` (Integer): Consumo térmico en watts
- `tier` (String): Gama (mid, high, enthusiast)
- `minRamFrequency` (Integer): Frecuencia mínima recomendada de RAM en MHz
- `generation` (String): Generación del CPU (ej: "RYZEN_7000", "INTEL_13TH")

---

### MotherboardDTO

```json
{
  "id": 1,
  "name": "ASUS ROG Strix X670E-E",
  "brand": "ASUS",
  "socket": "AM5",
  "ramType": "DDR5",
  "maxFrequency": 6400,
  "price": 499.99,
  "chipset": "X670E",
  "formFactor": "ATX",
  "ramSlots": 4,
  "supportedCpuGenerations": "RYZEN_7000,RYZEN_8000",
  "powerConsumption": 50,
  "m2Slots": 4,
  "sataPorts": 6
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre de la motherboard
- `brand` (String): Marca
- `socket` (SocketType): Tipo de socket
- `ramType` (RamType): Tipo de RAM soportado (DDR4, DDR5)
- `maxFrequency` (Integer): Frecuencia máxima de RAM en MHz
- `price` (BigDecimal): Precio
- `chipset` (String): Chipset (ej: "X670E", "B650", "Z790")
- `formFactor` (String): Factor de forma (ATX, MATX, ITX, EATX)
- `ramSlots` (Integer): Número de slots de RAM
- `supportedCpuGenerations` (String): Generaciones de CPU soportadas (separadas por comas)
- `powerConsumption` (Integer): Consumo de energía en watts
- `m2Slots` (Integer): Número de slots M.2 para NVMe
- `sataPorts` (Integer): Número de puertos SATA

---

### RamDTO

```json
{
  "id": 1,
  "name": "Corsair Vengeance DDR5 32GB",
  "brand": "Corsair",
  "type": "DDR5",
  "capacity": 32,
  "frequency": 6000,
  "price": 149.99,
  "modules": 1,
  "latency": "CL36"
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre del módulo
- `brand` (String): Marca
- `type` (RamType): Tipo de RAM (DDR4, DDR5)
- `capacity` (Integer): Capacidad en GB (por módulo)
- `frequency` (Integer): Frecuencia en MHz
- `price` (BigDecimal): Precio
- `modules` (Integer): Número de módulos (siempre 1, el usuario selecciona múltiples)
- `latency` (String): Latencia (ej: "CL36", "CL32")

---

### StorageDTO

```json
{
  "id": 1,
  "name": "Samsung 980 PRO 1TB",
  "brand": "Samsung",
  "type": "NVME_SSD",
  "capacity": 1000,
  "price": 149.99,
  "readSpeed": 7000,
  "writeSpeed": 5000,
  "formFactor": "M.2"
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre del dispositivo
- `brand` (String): Marca
- `type` (StorageType): Tipo (NVME_SSD, SATA_SSD, HDD, SATA_HDD)
- `capacity` (Integer): Capacidad en GB
- `price` (BigDecimal): Precio
- `readSpeed` (Integer): Velocidad de lectura en MB/s
- `writeSpeed` (Integer): Velocidad de escritura en MB/s
- `formFactor` (String): Factor de forma ("M.2", "2.5\"", "3.5\"")

---

### GpuDTO

```json
{
  "id": 1,
  "name": "NVIDIA RTX 4090",
  "brand": "NVIDIA",
  "tdp": 450,
  "price": 1599.99,
  "pcieVersion": "PCIE_4_0",
  "length": 304,
  "width": 3,
  "height": 137,
  "vram": 24,
  "tier": "enthusiast"
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre de la GPU
- `brand` (String): Marca
- `tdp` (Integer): Consumo en watts
- `price` (BigDecimal): Precio
- `pcieVersion` (PcieVersion): Versión PCIe (PCIE_3_0, PCIE_4_0, PCIE_5_0)
- `length` (Integer): Longitud en mm
- `width` (Integer): Slots PCIe ocupados (1, 2, 3)
- `height` (Integer): Altura en mm
- `vram` (Integer): VRAM en GB
- `tier` (String): Gama (mid, high, enthusiast)

---

### PsuDTO

```json
{
  "id": 1,
  "name": "Corsair RM850x",
  "brand": "Corsair",
  "wattage": 850,
  "price": 149.99,
  "efficiency": "Gold",
  "modular": true,
  "formFactor": "ATX"
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre de la fuente
- `brand` (String): Marca
- `wattage` (Integer): Potencia en watts
- `price` (BigDecimal): Precio
- `efficiency` (String): Certificación (Bronze, Silver, Gold, Platinum, Titanium)
- `modular` (Boolean): Si es modular o no
- `formFactor` (String): Factor de forma (ATX, SFX)

---

### CaseDTO

```json
{
  "id": 1,
  "name": "Fractal Design Meshify C",
  "brand": "Fractal Design",
  "supportedFormFactor": "ATX",
  "price": 99.99,
  "maxGpuLength": 315,
  "maxCpuCoolerHeight": 170,
  "storage25Slots": 2,
  "storage35Slots": 2,
  "includesFans": true,
  "fanSlots": 6
}
```

**Campos:**
- `id` (Long): Identificador único
- `name` (String): Nombre del gabinete
- `brand` (String): Marca
- `supportedFormFactor` (FormFactor): Factor de forma más grande soportado (ATX, MATX, ITX, EATX)
- `price` (BigDecimal): Precio
- `maxGpuLength` (Integer): Longitud máxima de GPU en mm
- `maxCpuCoolerHeight` (Integer): Altura máxima de CPU cooler en mm
- `storage25Slots` (Integer): Slots para SSD 2.5"
- `storage35Slots` (Integer): Slots para HDD 3.5"
- `includesFans` (Boolean): Si incluye ventiladores preinstalados
- `fanSlots` (Integer): Slots para ventiladores adicionales

---

### BuildRequestDTO

```json
{
  "cpuId": 1,
  "motherboardId": 2,
  "ramIds": [3, 4],
  "storageIds": [5, 6],
  "gpuId": 7,
  "psuId": 8,
  "caseId": 9
}
```

**Campos:**
- `cpuId` (Long, opcional): ID del CPU
- `motherboardId` (Long, opcional): ID de la motherboard
- `ramIds` (List<Long>, opcional): IDs de módulos de RAM (puedes seleccionar múltiples)
- `storageIds` (List<Long>, opcional): IDs de unidades de almacenamiento
- `gpuId` (Long, opcional): ID de la GPU
- `psuId` (Long, opcional): ID de la fuente
- `caseId` (Long, opcional): ID del gabinete

---

### BuildResponseDTO

```json
{
  "valid": true,
  "totalPrice": 2549.97,
  "cpu": { /* CpuDTO */ },
  "motherboard": { /* MotherboardDTO */ },
  "ram": { /* RamDTO */ },
  "storages": [ /* StorageDTO[] */ ],
  "gpu": { /* GpuDTO */ },
  "psu": { /* PsuDTO */ },
  "caseEntity": { /* CaseDTO */ },
  "errors": [],
  "warnings": []
}
```

**Campos:**
- `valid` (Boolean): Si la configuración es válida (sin errores bloqueantes)
- `totalPrice` (BigDecimal): Precio total de la configuración
- `cpu` (CpuDTO): CPU seleccionado
- `motherboard` (MotherboardDTO): Motherboard seleccionada
- `rams` (List<RamDTO>): Módulos de RAM seleccionados (puede haber múltiples)
- `storages` (List<StorageDTO>): Unidades de almacenamiento seleccionadas
- `gpu` (GpuDTO): GPU seleccionada
- `psu` (PsuDTO): Fuente seleccionada
- `caseEntity` (CaseDTO): Gabinete seleccionado
- `errors` (List<ErrorDTO>): Lista de errores bloqueantes estructurados
- `warnings` (List<WarningDTO>): Lista de advertencias estructuradas (no bloqueantes)

### ErrorDTO

```json
{
  "code": "CPU_SOCKET_MISMATCH",
  "message": "El socket de la CPU (AM5) no es compatible con el socket de la motherboard (LGA1700)",
  "component": "cpu,motherboard"
}
```

**Campos:**
- `code` (ErrorCode): Código del error para identificación programática (ver sección de códigos)
- `message` (String): Mensaje descriptivo del error
- `component` (String): Componentes afectados (separados por comas: "cpu", "motherboard", "ram", "gpu", "psu", "case", "storage")

### WarningDTO

```json
{
  "code": "RAM_CAPACITY_LOW",
  "message": "Se recomienda al menos 16GB de RAM para CPUs de gama alta. Actualmente tienes 8GB",
  "component": "ram,cpu"
}
```

**Campos:**
- `code` (WarningCode): Código de la advertencia para identificación programática (ver sección de códigos)
- `message` (String): Mensaje descriptivo de la advertencia
- `component` (String): Componentes afectados (separados por comas)

---

## 🔢 Códigos de Error y Advertencia

El sistema utiliza códigos estructurados para que el frontend pueda identificar programáticamente el tipo de error o advertencia sin depender del parsing de strings.

### Códigos de Error (ErrorCode)

| Código | Descripción | Componentes Afectados |
|--------|-------------|----------------------|
| `CPU_SOCKET_MISMATCH` | Socket de CPU incompatible con motherboard | cpu, motherboard |
| `CHIPSET_INCOMPATIBLE` | Chipset no compatible con generación de CPU | cpu, motherboard |
| `RAM_TYPE_MISMATCH` | Tipo de RAM incompatible con motherboard | ram, motherboard |
| `RAM_FREQUENCY_EXCEEDED` | Frecuencia de RAM excede el máximo de la motherboard | ram, motherboard |
| `RAM_SLOTS_EXCEEDED` | Módulos de RAM exceden slots disponibles | ram, motherboard |
| `GPU_CASE_SIZE_EXCEEDED` | GPU demasiado larga para el gabinete | gpu, case |
| `PSU_INSUFFICIENT` | Fuente no tiene suficiente potencia | psu |
| `CASE_FORM_FACTOR_INCOMPATIBLE` | Form factor de motherboard incompatible con gabinete | case, motherboard |
| `STORAGE_CASE_SLOTS_25_EXCEEDED` | Slots de 2.5" excedidos en el gabinete | storage, case |
| `STORAGE_CASE_SLOTS_35_EXCEEDED` | Slots de 3.5" excedidos en el gabinete | storage, case |
| `STORAGE_M2_SLOTS_EXCEEDED` | Slots M.2 excedidos en la motherboard | storage, motherboard |
| `STORAGE_SATA_PORTS_EXCEEDED` | Puertos SATA excedidos en la motherboard | storage, motherboard |

### Códigos de Advertencia (WarningCode)

| Código | Descripción | Componentes Afectados |
|--------|-------------|----------------------|
| `RAM_CAPACITY_LOW` | RAM insuficiente para CPU de gama alta | ram, cpu |
| `RAM_FREQUENCY_BELOW_RECOMMENDED` | Frecuencia de RAM por debajo de lo recomendado | ram, cpu |
| `CPU_RAM_BALANCE` | CPU de gama alta con RAM de baja frecuencia | cpu, ram |
| `RAM_BRAND_MISMATCH` | Múltiples marcas de RAM diferentes (puede causar problemas de compatibilidad) | ram |
| `RAM_FREQUENCY_MISMATCH` | Múltiples frecuencias de RAM diferentes (todas funcionarán a la frecuencia más baja) | ram |
| `PCIE_VERSION_MISMATCH` | Versión PCIe incompatible (preparado para futuras validaciones) | gpu, motherboard |

### Uso en el Frontend

```typescript
// Ejemplo en TypeScript
enum ErrorCode {
  CPU_SOCKET_MISMATCH = "CPU_SOCKET_MISMATCH",
  CHIPSET_INCOMPATIBLE = "CHIPSET_INCOMPATIBLE",
  RAM_TYPE_MISMATCH = "RAM_TYPE_MISMATCH",
  // ... más códigos
}

enum WarningCode {
  RAM_CAPACITY_LOW = "RAM_CAPACITY_LOW",
  RAM_FREQUENCY_BELOW_RECOMMENDED = "RAM_FREQUENCY_BELOW_RECOMMENDED",
  CPU_RAM_BALANCE = "CPU_RAM_BALANCE",
  RAM_BRAND_MISMATCH = "RAM_BRAND_MISMATCH",
  RAM_FREQUENCY_MISMATCH = "RAM_FREQUENCY_MISMATCH",
  PCIE_VERSION_MISMATCH = "PCIE_VERSION_MISMATCH"
}

// Manejo de errores
result.errors.forEach(error => {
  switch (error.code) {
    case ErrorCode.CPU_SOCKET_MISMATCH:
      // Mostrar error específico para socket
      showSocketError(error.component);
      break;
    case ErrorCode.PSU_INSUFFICIENT:
      // Sugerir fuentes más potentes
      suggestHigherWattagePSU();
      break;
    // ... más casos
  }
});

// Manejo de advertencias
result.warnings.forEach(warning => {
  switch (warning.code) {
    case WarningCode.RAM_CAPACITY_LOW:
      // Mostrar sugerencia de más RAM
      showRamSuggestion();
      break;
    case WarningCode.RAM_BRAND_MISMATCH:
      // Advertir sobre marcas diferentes de RAM
      showRamBrandWarning();
      break;
    case WarningCode.RAM_FREQUENCY_MISMATCH:
      // Advertir sobre frecuencias diferentes de RAM
      showRamFrequencyWarning();
      break;
    // ... más casos
  }
});
```

---

## ⚙️ Reglas de Compatibilidad

El sistema utiliza un motor de reglas extensible para validar la compatibilidad entre componentes. Cada regla implementa la interfaz `CompatibilityRule` y retorna un `RuleResult` con severidad `ERROR` (bloqueante) o `WARNING` (advertencia).

### Reglas Bloqueantes (ERROR)

Estas reglas impiden que la configuración sea válida:

#### 1. CPU-Motherboard Socket Compatibility
- **Regla**: `CpuMotherRule`
- **Validación**: El socket de la CPU debe coincidir con el socket de la motherboard
- **Mensaje de Error**: "El socket de la CPU ({socket}) no es compatible con el socket de la motherboard ({socket})"

#### 2. Chipset-CPU Generation Compatibility
- **Regla**: `ChipsetCompatibilityRule`
- **Validación**: La generación del CPU debe ser compatible con el chipset de la motherboard
- **Mensaje de Error**: "El chipset {chipset} no es compatible con CPUs de generación {generation}. Generaciones soportadas: {supported}"

#### 3. Motherboard-RAM Compatibility
- **Regla**: `MotherRamRule`
- **Validaciones**:
  - El tipo de RAM debe coincidir con el tipo soportado por la motherboard
  - La frecuencia de RAM no debe exceder la frecuencia máxima de la motherboard
- **Mensajes de Error**:
  - "El tipo de RAM ({type}) no es compatible con el tipo soportado por la motherboard ({type})"
  - "La frecuencia de la RAM ({freq} MHz) excede la frecuencia máxima soportada por la motherboard ({maxFreq} MHz)"

#### 4. RAM Slots Compatibility
- **Regla**: `RamSlotsRule`
- **Validación**: El número de módulos de RAM no debe exceder los slots disponibles en la motherboard
- **Mensaje de Error**: "La cantidad de módulos de RAM ({modules}) excede los slots disponibles en la motherboard ({slots})"

#### 5. GPU-Case Size Compatibility
- **Regla**: `GpuCaseSizeRule`
- **Validación**: La longitud de la GPU no debe exceder la longitud máxima soportada por el gabinete
- **Mensaje de Error**: "La GPU ({name}) es demasiado larga ({length} mm) para el gabinete ({caseName}) que soporta máximo {maxLength} mm"

#### 6. PSU Wattage Sufficiency
- **Regla**: `PsuWattageRule`
- **Validación**: La fuente debe tener suficiente potencia para todos los componentes
- **Cálculo**: CPU TDP + GPU TDP + Motherboard power + RAM (5W por módulo) + Storage (10W por disco) + 20% overhead
- **Mensaje de Error**: "La PSU ({name}) tiene {wattage}W pero se recomiendan al menos {recommended}W para esta configuración (consumo estimado: {consumption}W + 20% overhead)"

#### 7. Case-Motherboard Form Factor Compatibility
- **Regla**: `CaseFormFactorRule`
- **Validación**: El gabinete debe soportar el factor de forma de la motherboard
- **Compatibilidad**:
  - ATX puede soportar: ATX, mATX, ITX
  - mATX puede soportar: mATX, ITX
  - ITX solo puede soportar: ITX
  - EATX puede soportar: ATX, mATX, ITX
- **Mensaje de Error**: "El gabinete ({caseName}) soporta {caseFormFactor} pero la motherboard requiere {mbFormFactor}"

#### 8. Storage-Case Slots Compatibility
- **Regla**: `StorageCaseSlotsRule`
- **Validación**: Los dispositivos SATA (SSD 2.5" y HDD 3.5") deben caber en los slots disponibles del gabinete
- **Nota**: Los NVMe M.2 se validan contra la motherboard, no contra el case
- **Mensajes de Error**:
  - "Se requieren {count} slots de 2.5\" pero el gabinete ({name}) solo tiene {slots} disponibles"
  - "Se requieren {count} slots de 3.5\" pero el gabinete ({name}) solo tiene {slots} disponibles"

#### 9. Storage-Motherboard M.2 and SATA Compatibility
- **Regla**: `StorageMotherboardM2Rule`
- **Validación**: 
  - NVMe M.2: valida contra `m2Slots` de la motherboard
  - SATA (SSD 2.5" y HDD 3.5"): valida contra `sataPorts` de la motherboard
- **Mensajes de Error**:
  - "Se requieren {count} slots M.2 pero la motherboard ({name}) solo tiene {slots} disponibles"
  - "Se requieren {count} puertos SATA pero la motherboard ({name}) solo tiene {ports} disponibles"

---

### Reglas de Advertencia (WARNING)

Estas reglas no bloquean la configuración pero generan advertencias:

#### 1. RAM Recommendation for High-End CPU
- **Regla**: `RamRecommendationRule`
- **Validación**: CPUs de gama alta deberían tener al menos 16GB de RAM
- **Mensaje de Advertencia**: "Se recomienda al menos 16GB de RAM para CPUs de gama alta. Actualmente tienes {capacity}GB"

#### 2. RAM Frequency Minimum Recommendation
- **Regla**: `RamFrequencyMinimumRule`
- **Validación**: La frecuencia de RAM debe ser al menos la mínima recomendada por el CPU
- **Mensaje de Advertencia**: "Se recomienda al menos {minFreq} MHz de frecuencia de RAM para esta CPU. La RAM seleccionada tiene {freq} MHz"

#### 3. CPU-RAM Balance Check
- **Regla**: `CpuRamBalanceRule`
- **Validación**: CPUs de gama alta no deberían estar combinados con RAM de baja frecuencia
- **Umbrales**:
  - DDR4: 2666 MHz
  - DDR5: 4800 MHz
- **Mensaje de Advertencia**: "Una CPU de gama alta puede verse limitada por RAM de baja frecuencia ({freq} MHz). Se recomienda al menos {threshold} MHz para aprovechar mejor el rendimiento"

#### 4. RAM Brand Mismatch Warning
- **Regla**: `RamBrandMismatchRule`
- **Validación**: Detecta cuando se están usando RAMs de diferentes marcas
- **Condición**: Se activa cuando hay 2 o más módulos de RAM con marcas diferentes
- **Mensaje de Advertencia**: "Se están usando RAMs de diferentes marcas ({brands}). Se recomienda usar módulos de la misma marca para mejor compatibilidad y estabilidad"
- **Razón**: Mezclar marcas diferentes puede causar problemas de compatibilidad y estabilidad

#### 5. RAM Frequency Mismatch Warning
- **Regla**: `RamFrequencyMismatchRule`
- **Validación**: Detecta cuando se están usando RAMs con diferentes frecuencias
- **Condición**: Se activa cuando hay 2 o más módulos de RAM con frecuencias diferentes
- **Mensaje de Advertencia**: "Se están usando RAMs con diferentes frecuencias ({frequencies}). Todas funcionarán a la frecuencia más baja, limitando el rendimiento de los módulos más rápidos. Se recomienda usar módulos con la misma frecuencia"
- **Razón**: Cuando hay frecuencias diferentes, todas las RAMs funcionan a la frecuencia más baja, desperdiciando el potencial de los módulos más rápidos

#### 6. GPU PCIe Compatibility
- **Regla**: `GpuPcieRule`
- **Validación**: PCIe es retrocompatible, pero puede advertir sobre diferencias significativas
- **Nota**: Actualmente no genera advertencias, pero está preparado para futuras validaciones

---

## 🚨 Manejo de Errores

### Códigos de Estado HTTP

- `200 OK`: Request exitoso
- `400 Bad Request`: Error en la validación del request (campos inválidos)
- `404 Not Found`: Recurso no encontrado
- `500 Internal Server Error`: Error interno del servidor

### Formato de Errores

#### Error de Validación (400)
```json
{
  "error": "El socket de la CPU (AM5) no es compatible con el socket de la motherboard (LGA1700)"
}
```

#### Recurso No Encontrado (404)
```json
{
  "error": "CPU con ID 999 no encontrada"
}
```

#### Error Interno (500)
```json
{
  "error": "Error interno del servidor: [mensaje de error]"
}
```

### Excepciones Personalizadas

- **BusinessException**: Excepciones de negocio (400)
- **RuntimeException**: Recurso no encontrado (404) o error interno (500)
- **IllegalArgumentException**: Argumentos inválidos (400)

---

## 🔒 CORS y Seguridad

### CORS (Cross-Origin Resource Sharing)

El backend está configurado para aceptar peticiones desde los siguientes orígenes (configurables en `CorsConfig.java`):

- `http://localhost:3000` (React por defecto)
- `http://localhost:5173` (Vite por defecto)
- `http://localhost:4200` (Angular por defecto)
- `http://localhost:8080` (Otros frameworks)

**Métodos permitidos**: GET, POST, PUT, DELETE, PATCH, OPTIONS

**Headers permitidos**: Todos (`*`)

**Credenciales**: Permitidas

### Seguridad

- **CSRF**: Desactivado (no necesario para APIs REST stateless)
- **Sesiones**: Stateless (preparado para JWT en el futuro)
- **Endpoints públicos**: Todos los endpoints `/api/**` son públicos en el MVP
- **Futuro**: Se implementará autenticación JWT para endpoints de administración

---

## 🗄️ Base de Datos

### Esquema

El proyecto utiliza JPA/Hibernate con `ddl-auto=update`, lo que significa que las tablas se crean/actualizan automáticamente basándose en las entidades JPA.

### Entidades Principales

- `Cpu` - Procesadores
- `Motherboard` - Placas base
- `Ram` - Módulos de memoria
- `Storage` - Unidades de almacenamiento
- `Gpu` - Tarjetas gráficas
- `Psu` - Fuentes de alimentación
- `Case` - Gabinetes

### Poblar Base de Datos

Los scripts SQL para poblar la base de datos se encuentran en `SUPABASE-DATA/`:

1. Ejecuta `procesadores.sql` para CPUs
2. Ejecuta `motherboards.sql` para motherboards
3. Ejecuta `memorias_ram.sql` para RAM
4. Ejecuta `almacenamiento.sql` para almacenamiento
5. Ejecuta `tarjetas graficas.sql` para GPUs
6. Ejecuta `fuentes_poder.sql` para fuentes
7. Ejecuta `gabinetes.sql` para gabinetes

---

## 💡 Ejemplos de Uso

### JavaScript/TypeScript (Fetch API)

#### Obtener todos los CPUs
```javascript
const response = await fetch('http://localhost:8080/api/cpus');
const cpus = await response.json();
console.log(cpus);
```

#### Obtener motherboards compatibles
```javascript
const cpuId = 1;
const response = await fetch(`http://localhost:8080/api/motherboards/compatible?cpuId=${cpuId}`);
const motherboards = await response.json();
console.log(motherboards);
```

#### Obtener cases compatibles
```javascript
const gpuId = 6;
const motherboardId = 2;
const response = await fetch(`http://localhost:8080/api/cases/compatible?gpuId=${gpuId}&motherboardId=${motherboardId}`);
const cases = await response.json();
console.log(cases);
```

#### Obtener PSUs compatibles
```javascript
const buildRequest = {
  cpuId: 1,
  gpuId: 6,
  motherboardId: 2,
  ramIds: [3, 4],
  storageIds: [5, 6]
};

const response = await fetch('http://localhost:8080/api/psus/compatible', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(buildRequest)
});

const psus = await response.json();
console.log(psus);
```

#### Obtener storages compatibles
```javascript
const motherboardId = 2;
const caseId = 3;
const existingStorageIds = [4, 5];
const response = await fetch(
  `http://localhost:8080/api/storages/compatible?motherboardId=${motherboardId}&caseId=${caseId}&existingStorageIds=${existingStorageIds.join(',')}`
);
const storages = await response.json();
console.log(storages);
```

#### Validar una configuración
```javascript
const buildRequest = {
  cpuId: 1,
  motherboardId: 2,
  ramIds: [3, 4],
  storageIds: [5, 6],
  gpuId: 7,
  psuId: 8,
  caseId: 9
};

const response = await fetch('http://localhost:8080/api/builds/validate', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(buildRequest)
});

const result = await response.json();

if (result.valid) {
  console.log('Configuración válida!');
  console.log('Precio total:', result.totalPrice);
} else {
  console.log('Errores:', result.errors);
}

if (result.warnings.length > 0) {
  console.log('Advertencias:', result.warnings);
  // Acceder a códigos específicos
  result.warnings.forEach(warning => {
    console.log(`Código: ${warning.code}, Componente: ${warning.component}`);
  });
}
```

### cURL

#### Health Check
```bash
curl http://localhost:8080/api/health
```

#### Obtener CPUs
```bash
curl http://localhost:8080/api/cpus
```

#### Obtener Cases Compatibles
```bash
curl "http://localhost:8080/api/cases/compatible?gpuId=6&motherboardId=2"
```

#### Obtener PSUs Compatibles
```bash
curl -X POST http://localhost:8080/api/psus/compatible \
  -H "Content-Type: application/json" \
  -d '{
    "cpuId": 1,
    "gpuId": 6,
    "motherboardId": 2,
    "ramIds": [3, 4]
  }'
```

#### Obtener Storages Compatibles
```bash
curl "http://localhost:8080/api/storages/compatible?motherboardId=2&caseId=3&existingStorageIds=4,5"
```

#### Validar Build
```bash
curl -X POST http://localhost:8080/api/builds/validate \
  -H "Content-Type: application/json" \
  -d '{
    "cpuId": 1,
    "motherboardId": 2,
    "ramIds": [3, 4]
  }'
```

---

## 🛠️ Desarrollo

### Compilar

```bash
mvn clean install
```

### Ejecutar Tests

```bash
mvn test
```

### Ejecutar en Modo Desarrollo

```bash
mvn spring-boot:run
```

### Estructura de Código

- **Controllers**: Solo delegan a servicios, sin lógica de negocio
- **Services**: Orquestan repositorios y reglas de compatibilidad
- **Repositories**: Acceso a datos mediante Spring Data JPA
- **Domain Rules**: Reglas de negocio desacopladas del framework
- **DTOs**: Objetos de transferencia de datos para la API

### Agregar una Nueva Regla de Compatibilidad

1. Crea una nueva clase que implemente `CompatibilityRule`:

```java
public class NuevaRegla implements CompatibilityRule {
    @Override
    public RuleResult evaluate(BuildContext context) {
        // Lógica de validación
        if (/* condición de error */) {
            return RuleResult.error("Mensaje de error");
        }
        if (/* condición de advertencia */) {
            return RuleResult.warning("Mensaje de advertencia");
        }
        return RuleResult.valid();
    }
    
    @Override
    public String getName() {
        return "Nombre de la Regla";
    }
}
```

2. Registra la regla en `BuildService`:

```java
private final List<CompatibilityRule> rules = Arrays.asList(
    // ... otras reglas
    new NuevaRegla()
);
```

### Extender el Modelo

Para agregar un nuevo componente:

1. Crea la entidad JPA en `domain/model/`
2. Crea el DTO en `dto/`
3. Crea el repositorio en `repository/`
4. Crea el servicio en `service/`
5. Crea el controlador en `controller/`
6. Agrega reglas de compatibilidad en `domain/rules/`
7. Actualiza `BuildContext` y `BuildService`

---

## 📝 Notas Importantes

1. **Enums**: Los valores de enum se devuelven como strings en JSON (ej: "AM5", "DDR5", "NVME_SSD")
2. **Precios**: Todos los precios son `BigDecimal` y se devuelven como números decimales
3. **RAM**: Los módulos de RAM se representan individualmente. El usuario puede seleccionar múltiples módulos para configuraciones dual-channel
4. **Storage**: Se pueden seleccionar múltiples unidades de almacenamiento
5. **Validación**: La validación de builds es opcional por componente. Puedes validar solo CPU + Motherboard + RAM si lo deseas
6. **CORS**: Configura los orígenes permitidos según tu entorno (desarrollo/producción)
7. **Endpoints de Compatibilidad**: Los endpoints `/compatible` permiten filtrar componentes mientras el usuario construye la build, sin necesidad de validar toda la configuración al final. Esto mejora la experiencia de usuario al mostrar solo opciones válidas en cada paso.
8. **Configuración y Seguridad**: 
   - El archivo `application.properties` con credenciales NO debe subirse a GitHub
   - Usa `application.properties.example` como template
   - En producción, usa variables de entorno para credenciales (ver sección de Deploy)

---

## 🚀 Deploy a Producción

### Variables de Entorno Requeridas

Para deploy en plataformas como Railway, Render, Heroku, etc., configura las siguientes variables de entorno:

| Variable | Descripción | Ejemplo |
|----------|-------------|---------|
| `DATABASE_URL` | URL completa de la base de datos | `jdbc:postgresql://db.xxx.supabase.co:5432/postgres?sslmode=require` |
| `DB_USERNAME` | Usuario de la base de datos | `postgres` |
| `DB_PASSWORD` | Contraseña de la base de datos | `tu_password_seguro` |
| `PORT` | Puerto del servidor (opcional, por defecto 8080) | `8080` |
| `CORS_ORIGINS` | Orígenes permitidos para CORS | `https://tu-dominio.com` |
| `DDL_AUTO` | Modo de DDL de Hibernate (recomendado: `validate` en prod) | `validate` |
| `SHOW_SQL` | Mostrar SQL en logs (recomendado: `false` en prod) | `false` |
| `LOG_LEVEL` | Nivel de logging (recomendado: `INFO` en prod) | `INFO` |

### Ejemplo de Configuración en Railway/Render

1. Conecta tu repositorio de GitHub
2. Configura las variables de entorno en el panel de la plataforma
3. La plataforma compilará y desplegará automáticamente

**Importante**: Nunca subas credenciales reales a GitHub. El archivo `application.properties` está en `.gitignore` por seguridad.

---

## 🤝 Contribución

Este proyecto está en desarrollo activo. Para contribuir:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

## 📄 Licencia

[Especificar licencia si aplica]

---

## 📧 Contacto

[Información de contacto si aplica]

---

**Última actualización**: 2024
