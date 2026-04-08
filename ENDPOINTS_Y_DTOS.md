# Endpoints y DTOs del Backend - MakeYourBuild API

## Base URL
```
http://localhost:8080/api
```

---

## Storage (Almacenamiento)

### Endpoints
- `GET /api/storages` - Lista todos los almacenamientos
- `GET /api/storages/{id}` - Obtiene un almacenamiento por ID

### StorageDTO
```json
{
  "id": 1,
  "name": "Samsung 980 PRO 1TB",
  "brand": "Samsung",
  "type": "NVME_SSD",  // Enum: NVME_SSD, SATA_SSD, HDD
  "capacity": 1000,  // En GB
  "price": 149.99,
  "readSpeed": 7000,  // En MB/s
  "writeSpeed": 5000,  // En MB/s
  "formFactor": "M.2"  // String: "M.2", "2.5\"", "3.5\""
}
```

---

## GPU (Tarjeta Gráfica)

### Endpoints
- `GET /api/gpus` - Lista todas las GPUs
- `GET /api/gpus/{id}` - Obtiene una GPU por ID

### GpuDTO
```json
{
  "id": 1,
  "name": "NVIDIA RTX 4090",
  "brand": "NVIDIA",
  "tdp": 450,  // Consumo en watts
  "price": 1599.99,
  "pcieVersion": "PCIE_4_0",  // Enum: PCIE_3_0, PCIE_4_0, PCIE_5_0
  "length": 304,  // Longitud en mm
  "width": 3,  // Slots PCIe ocupados (1, 2, 3, etc.)
  "height": 137,  // Altura en mm
  "vram": 24,  // VRAM en GB
  "tier": "enthusiast"  // String: "mid", "high", "enthusiast"
}
```

---

## PSU (Fuente de Alimentación)

### Endpoints
- `GET /api/psus` - Lista todas las PSUs
- `GET /api/psus/{id}` - Obtiene una PSU por ID

### PsuDTO
```json
{
  "id": 1,
  "name": "Corsair RM850x",
  "brand": "Corsair",
  "wattage": 850,  // Potencia en watts
  "price": 149.99,
  "efficiency": "Gold",  // String: "Bronze", "Silver", "Gold", "Platinum", "Titanium"
  "modular": true,  // Boolean: true = modular, false = no modular
  "formFactor": "ATX"  // Enum: ATX, MATX, ITX
}
```

---

## Case (Gabinete)

### Endpoints
- `GET /api/cases` - Lista todos los gabinetes
- `GET /api/cases/{id}` - Obtiene un gabinete por ID

### CaseDTO
```json
{
  "id": 1,
  "name": "Fractal Design Meshify C",
  "brand": "Fractal Design",
  "supportedFormFactor": "ATX",  // Enum: ATX, MATX, ITX (el más grande que soporta)
  "price": 99.99,
  "maxGpuLength": 315,  // Longitud máxima de GPU en mm
  "maxCpuCoolerHeight": 170,  // Altura máxima de CPU cooler en mm
  "storage25Slots": 2,  // Cantidad de slots para SSD 2.5"
  "storage35Slots": 2,  // Cantidad de slots para HDD 3.5"
  "includesFans": true,  // Boolean: si incluye ventiladores preinstalados
  "fanSlots": 6  // Cantidad de slots para ventiladores adicionales
}
```

---

## Endpoints Existentes (CPU, Motherboard, RAM)

### CPU
- `GET /api/cpus` - Lista todos los CPUs
- `GET /api/cpus/{id}` - Obtiene un CPU por ID

### Motherboard
- `GET /api/motherboards` - Lista todas las motherboards
- `GET /api/motherboards/{id}` - Obtiene una motherboard por ID
- `GET /api/motherboards/compatible/{cpuId}` - Lista motherboards compatibles con un CPU

### RAM
- `GET /api/rams` - Lista todas las RAMs
- `GET /api/rams/{id}` - Obtiene una RAM por ID
- `GET /api/rams/compatible?cpuId={cpuId}&motherboardId={motherboardId}` - Lista RAMs compatibles

### Build (Validación)
- `POST /api/builds/validate` - Valida una configuración completa de PC

---

## Pre-Built (Builds Predefinidos)

### Endpoints
- `GET /api/pre-built` - Lista todos los builds predefinidos
- `GET /api/pre-built?category={category}` - Lista builds predefinidos por categoría
- `GET /api/pre-built?category={category}&budget={budget}` - Busca 3 builds según categoría y presupuesto (uno por debajo, uno justo, uno por encima)
- `GET /api/pre-built/{id}` - Obtiene un build predefinido por ID
- `POST /api/pre-built/{id}/validate` - Valida un build predefinido (compatibilidad y precio)

**Nota**: Se usan query parameters en lugar de path parameters para evitar problemas con caracteres especiales como `/` en las categorías (ej: "Personal/Home").

### Categorías Válidas
Las categorías disponibles son:
- `Gaming` - PC Gaming ($500 - $3000)
- `Workstation` - PC de trabajo ($400 - $2000)
- `Graphic design` - PC para diseño gráfico ($500 - $4000)
- `Streaming` - PC para streaming ($1200 - $4500/5000)
- `Personal/Home` - PC para uso personal ($300 - $1200)

**Nota**: Las categorías son case-insensitive y pueden enviarse como:
- Display name: `"Gaming"`, `"Workstation"`, `"Graphic design"`, `"Streaming"`, `"Personal/Home"`
- Enum name: `"GAMING"`, `"WORKSTATION"`, `"GRAPHIC_DESIGN"`, `"STREAMING"`, `"PERSONAL_HOME"`

### PreBuiltDTO

El DTO incluye los componentes completos (no solo IDs) para facilitar el uso en el frontend.

```json
{
  "id": 1,
  "name": "Gaming Entry",
  "description": "PC Gaming de entrada para juegos en 1080p con gráficos medios",
  "category": "GAMING",
  "totalPrice": 530.93,
  "imageUrl": null,
  "cpu": {
    "id": 14,
    "name": "AMD Ryzen 5 5600X",
    "brand": "AMD",
    "socket": "AM4",
    "price": 199.99,
    "cores": 6,
    "threads": 12,
    "baseClock": 3.7,
    "boostClock": 4.6,
    "tdp": 65,
    "tier": "mid",
    "minRamFrequency": 3200,
    "generation": "Zen 3"
  },
  "motherboard": {
    "id": 11,
    "name": "ASUS B550M-A",
    "brand": "ASUS",
    "socket": "AM4",
    "chipset": "B550",
    "formFactor": "Micro-ATX",
    "price": 89.99,
    "ramSlots": 4,
    "maxRamCapacity": 128,
    "ramType": "DDR4",
    "m2Slots": 2,
    "sataSlots": 4
  },
  "rams": [
    {
      "id": 43,
      "name": "Corsair Vengeance LPX 16GB",
      "brand": "Corsair",
      "type": "DDR4",
      "capacity": 16,
      "frequency": 3200,
      "price": 49.99
    },
    {
      "id": 44,
      "name": "Corsair Vengeance LPX 16GB",
      "brand": "Corsair",
      "type": "DDR4",
      "capacity": 16,
      "frequency": 3200,
      "price": 49.99
    }
  ],
  "storages": [
    {
      "id": 9,
      "name": "Samsung 970 EVO 500GB",
      "brand": "Samsung",
      "type": "NVME_SSD",
      "capacity": 500,
      "interface": "PCIE_3_0",
      "price": 79.99
    }
  ],
  "gpu": {
    "id": 22,
    "name": "NVIDIA GeForce RTX 3060",
    "brand": "NVIDIA",
    "price": 329.99,
    "vram": 12,
    "length": 242,
    "tier": "mid"
  },
  "psu": {
    "id": 12,
    "name": "Corsair CX650",
    "brand": "Corsair",
    "wattage": 650,
    "efficiency": "Bronze",
    "price": 69.99
  },
  "case": {
    "id": 9,
    "name": "NZXT H510",
    "brand": "NZXT",
    "formFactor": "Mid-Tower",
    "maxGpuLength": 381,
    "price": 69.99
  }
}
```

**Nota importante**: Los componentes opcionales (`gpu`, `psu`, `case`, `storages`) pueden ser `null` si no están incluidos en el build.

### Ejemplos de Uso

#### Obtener todos los builds predefinidos
```javascript
const response = await fetch('http://localhost:8080/api/pre-built');
const preBuiltList = await response.json();
```

#### Obtener builds por categoría
```javascript
// Sin presupuesto: retorna todos los builds de la categoría
const gamingBuilds = await fetch('http://localhost:8080/api/pre-built?category=Gaming')
  .then(res => res.json());

// Con presupuesto: retorna exactamente 3 builds (por debajo, justo, por encima)
const gamingBuildsByBudget = await fetch('http://localhost:8080/api/pre-built?category=Gaming&budget=1000')
  .then(res => res.json());
// Retorna: [build por debajo, build justo, build por encima]

// Usando enum name (también funciona)
const workstationBuilds = await fetch('http://localhost:8080/api/pre-built?category=WORKSTATION')
  .then(res => res.json());

// Con caracteres especiales (funciona correctamente con query params)
const personalBuilds = await fetch('http://localhost:8080/api/pre-built?category=Personal/Home')
  .then(res => res.json());
```

#### Obtener un build específico
```javascript
const build = await fetch('http://localhost:8080/api/pre-built/1')
  .then(res => res.json());
```

#### Validar un build predefinido
```javascript
const response = await fetch('http://localhost:8080/api/pre-built/1/validate', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  }
});
const validationResult = await response.json();
// Retorna un BuildResponseDTO con compatibilidad, advertencias y precio total
```

### Búsqueda por Presupuesto

El endpoint `/api/pre-built?category={category}&budget={budget}` retorna exactamente 3 builds:

1. **Build por debajo**: Hasta 20% menos del presupuesto (expandible si no hay suficientes)
2. **Build justo**: Dentro de ±5% del presupuesto (expandible si no hay suficientes)
3. **Build por encima**: Hasta 20% más del presupuesto (expandible si no hay suficientes)

**Comportamiento**:
- Si no hay suficientes builds en el rango inicial (-20% a +20%), el sistema expande el rango automáticamente hasta encontrar 3 builds
- Los builds se ordenan por precio ascendente
- Si hay menos de 3 builds en la categoría, retorna los disponibles

**Ejemplo**:
```javascript
// Presupuesto: $1000
// Retorna: [build ~$800, build ~$1000, build ~$1200]
const builds = await fetch('http://localhost:8080/api/pre-built?category=Gaming&budget=1000')
  .then(res => res.json());
```

### Respuesta de Validación
El endpoint `/api/pre-built/{id}/validate` retorna un `BuildResponseDTO` con:
- `isCompatible`: Boolean indicando si todos los componentes son compatibles
- `warnings`: Lista de advertencias (si las hay)
- `errors`: Lista de errores de compatibilidad (si los hay)
- `totalPrice`: Precio total del build
- `components`: Detalles de cada componente seleccionado

---

## Notas Importantes

1. **Enums**: Los valores de enum se devuelven como strings (ej: "NVME_SSD", "PCIE_4_0", "ATX")
2. **Precios**: Todos los precios son `BigDecimal` y se devuelven como números decimales
3. **CORS**: El backend está configurado para aceptar requests desde:
   - `http://localhost:3000`
   - `http://localhost:5173`
   - `http://localhost:4200`
4. **Content-Type**: Para POST requests, usar `Content-Type: application/json`

---

## Ejemplo de Uso en Frontend

### JavaScript/TypeScript
```javascript
// Obtener todos los storages
const response = await fetch('http://localhost:8080/api/storages');
const storages = await response.json();

// Obtener todas las GPUs
const gpus = await fetch('http://localhost:8080/api/gpus')
  .then(res => res.json());

// Obtener todas las PSUs
const psus = await fetch('http://localhost:8080/api/psus')
  .then(res => res.json());

// Obtener todos los cases
const cases = await fetch('http://localhost:8080/api/cases')
  .then(res => res.json());
```
