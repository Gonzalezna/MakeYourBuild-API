# Endpoints y DTOs del Backend - MakeYourBuild API

## Base URL
```
http://localhost:8080/api
```

---

## 📦 Storage (Almacenamiento)

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

## 🎮 GPU (Tarjeta Gráfica)

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

## ⚡ PSU (Fuente de Alimentación)

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

## 🖥️ Case (Gabinete)

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

## 📋 Endpoints Existentes (CPU, Motherboard, RAM)

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

## 🔧 Notas Importantes

1. **Enums**: Los valores de enum se devuelven como strings (ej: "NVME_SSD", "PCIE_4_0", "ATX")
2. **Precios**: Todos los precios son `BigDecimal` y se devuelven como números decimales
3. **CORS**: El backend está configurado para aceptar requests desde:
   - `http://localhost:3000`
   - `http://localhost:5173`
   - `http://localhost:4200`
4. **Content-Type**: Para POST requests, usar `Content-Type: application/json`

---

## 📝 Ejemplo de Uso en Frontend

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
