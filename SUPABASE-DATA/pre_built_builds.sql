-- Script SQL para insertar builds predefinidos en Supabase
-- Estos builds son configuraciones completas de PC que los usuarios pueden seleccionar
-- Los precios están calculados sumando todos los componentes

-- Crear tabla si no existe (Hibernate puede crearla automáticamente, pero es bueno tener el script)
CREATE TABLE IF NOT EXISTS pre_built_builds (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    category VARCHAR(100),
    cpu_id BIGINT NOT NULL,
    motherboard_id BIGINT NOT NULL,
    ram_ids VARCHAR(500) NOT NULL,
    storage_ids VARCHAR(500) NOT NULL,
    gpu_id BIGINT,
    psu_id BIGINT NOT NULL,
    case_id BIGINT NOT NULL,
    total_price DECIMAL(10, 2),
    image_url VARCHAR(500)
);

-- ============================================
-- Builds Predefinidos - Gaming
-- Rango de precios: $500 - $3000
-- ============================================
-- Categorías válidas: GAMING, WORKSTATION, GRAPHIC_DESIGN, STREAMING, PERSONAL_HOME
INSERT INTO pre_built_builds (name, description, category, cpu_id, motherboard_id, ram_ids, storage_ids, gpu_id, psu_id, case_id, total_price, image_url) VALUES
-- Gaming Entry ($500-600): CPU(199.99) + MB(79.99) + RAM(29.99+30.99) + Storage(39.99) + GPU(249.99) + PSU(39.99) + Case(69.99) = 530.93
('Gaming Entry', 'PC Gaming de entrada para juegos en 1080p con gráficos medios', 'GAMING', 
 14, 11, '[43,44]', '[9]', 22, 12, 9, 530.93, NULL),
-- Gaming Budget ($800-900): CPU(299.99) + MB(99.99) + RAM(31.99+34.99) + Storage(59.99) + GPU(329.99) + PSU(49.99) + Case(89.99) = 955.93
('Gaming Budget', 'PC Gaming económica para juegos en 1080p con buena calidad', 'GAMING',
 28, 21, '[45,46]', '[13]', 120, 22, 10, 955.93, NULL),
-- Gaming Mid-Range ($1200-1400): CPU(449.99) + MB(179.99) + RAM(64.99+64.99) + Storage(99.99) + GPU(499.99) + PSU(99.99) + Case(149.99) = 1549.93
('Gaming Mid-Range', 'PC Gaming de gama media para juegos en 1440p con alta calidad', 'GAMING',
 34, 31, '[113,113]', '[24]', 131, 41, 14, 1549.93, NULL),
-- Gaming High-End ($2000-2200): CPU(599.99) + MB(299.99) + RAM(99.99+99.99) + Storage(149.99) + GPU(799.99) + PSU(129.99) + Case(199.99) = 2269.93
('Gaming High-End', 'PC Gaming de gama alta para juegos en 1440p/4K con máximo rendimiento', 'GAMING',
 121, 43, '[291,291]', '[25]', 203, 95, 15, 2269.93, NULL),
-- Gaming Enthusiast ($2800-3000): CPU(799.99) + MB(399.99) + RAM(124.99+124.99) + Storage(159.99) + GPU(1199.99) + PSU(179.99) + Case(249.99) = 3234.93
('Gaming Enthusiast', 'PC Gaming para entusiastas con componentes premium y RTX 4080', 'GAMING',
 37, 47, '[293,293]', '[26]', 203, 133, 16, 3234.93, NULL);

-- ============================================
-- Builds Predefinidos - Workstation
-- Rango de precios: $400 - $2000
-- ============================================
INSERT INTO pre_built_builds (name, description, category, cpu_id, motherboard_id, ram_ids, storage_ids, gpu_id, psu_id, case_id, total_price, image_url) VALUES
-- Workstation Basic ($400-500): CPU(199.99) + MB(89.99) + RAM(54.99+54.99) + Storage(44.99) + GPU(NULL) + PSU(42.99) + Case(69.99) = 557.93
('Workstation Basic', 'PC de trabajo básica para tareas de oficina y productividad', 'WORKSTATION',
 14, 15, '[108,108]', '[15]', NULL, 13, 9, 557.93, NULL),
-- Workstation Standard ($800-900): CPU(329.99) + MB(139.99) + RAM(64.99+64.99) + Storage(99.99) + GPU(199.99) + PSU(64.99) + Case(94.99) = 959.93
('Workstation Standard', 'PC de trabajo estándar para diseño, programación y multitarea', 'WORKSTATION',
 17, 28, '[113,113]', '[24]', 19, 38, 12, 959.93, NULL),
-- Workstation Pro ($1400-1600): CPU(549.99) + MB(199.99) + RAM(119.99+119.99) + Storage(149.99) + GPU(329.99) + PSU(99.99) + Case(149.99) = 1609.93
('Workstation Pro', 'PC de trabajo profesional con CPU de alto rendimiento y buena GPU', 'WORKSTATION',
 36, 37, '[187,187]', '[25]', 120, 41, 14, 1609.93, NULL),
-- Workstation Elite ($1800-2000): CPU(799.99) + MB(299.99) + RAM(149.99+149.99) + Storage(279.99) + GPU(499.99) + PSU(129.99) + Case(199.99) = 2299.93
('Workstation Elite', 'PC de trabajo de élite con componentes premium para tareas intensivas', 'WORKSTATION',
 37, 43, '[180,180]', '[48]', 131, 95, 15, 2299.93, NULL);

-- ============================================
-- Builds Predefinidos - Graphic design
-- Rango de precios: $500 - $4000
-- ============================================
INSERT INTO pre_built_builds (name, description, category, cpu_id, motherboard_id, ram_ids, storage_ids, gpu_id, psu_id, case_id, total_price, image_url) VALUES
-- Design Entry ($500-600): CPU(329.99) + MB(99.99) + RAM(64.99+64.99) + Storage(99.99) + GPU(349.99) + PSU(64.99) + Case(89.99) = 1054.93
('Design Entry', 'PC para diseño gráfico básico con buena GPU para renderizado', 'GRAPHIC_DESIGN',
 17, 21, '[113,113]', '[24]', 76, 38, 10, 1054.93, NULL),
-- Design Standard ($1200-1400): CPU(449.99) + MB(179.99) + RAM(99.99+99.99) + Storage(149.99) + GPU(499.99) + PSU(99.99) + Case(149.99) = 1629.93
('Design Standard', 'PC para diseño gráfico estándar con GPU profesional', 'GRAPHIC_DESIGN',
 34, 31, '[290,290]', '[25]', 131, 41, 14, 1629.93, NULL),
-- Design Pro ($2500-2800): CPU(799.99) + MB(399.99) + RAM(149.99+149.99) + Storage(299.99) + GPU(1199.99) + PSU(179.99) + Case(249.99) = 3229.93
('Design Pro', 'PC profesional para diseño gráfico avanzado y renderizado 3D', 'GRAPHIC_DESIGN',
 37, 47, '[180,180]', '[50]', 203, 133, 16, 3229.93, NULL),
-- Design Elite ($3600-4000): CPU(799.99) + MB(599.99) + RAM(249.99+249.99) + Storage(599.99) + GPU(1599.99) + PSU(249.99) + Case(499.99) = 4249.93
('Design Elite', 'PC de élite para diseño gráfico profesional con RTX 4090 y almacenamiento masivo', 'GRAPHIC_DESIGN',
 37, 48, '[363,363]', '[72]', 212, 137, 32, 4249.93, NULL);

-- ============================================
-- Builds Predefinidos - Streaming
-- Rango de precios: $1200 - $4500/5000
-- ============================================
INSERT INTO pre_built_builds (name, description, category, cpu_id, motherboard_id, ram_ids, storage_ids, gpu_id, psu_id, case_id, total_price, image_url) VALUES
-- Streaming Entry ($1200-1400): CPU(449.99) + MB(179.99) + RAM(99.99+99.99) + Storage(149.99) + GPU(499.99) + PSU(99.99) + Case(149.99) = 1629.93
('Streaming Entry', 'PC para streaming básico con buena CPU para encoding', 'STREAMING',
 34, 31, '[290,290]', '[25]', 131, 41, 14, 1629.93, NULL),
-- Streaming Standard ($2000-2200): CPU(599.99) + MB(299.99) + RAM(124.99+124.99) + Storage(279.99) + GPU(799.99) + PSU(129.99) + Case(199.99) = 2359.93
('Streaming Standard', 'PC para streaming estándar con GPU de gama alta', 'STREAMING',
 121, 43, '[293,293]', '[48]', 203, 95, 15, 2359.93, NULL),
-- Streaming Pro ($3200-3500): CPU(799.99) + MB(399.99) + RAM(149.99+149.99) + Storage(299.99) + GPU(1199.99) + PSU(179.99) + Case(249.99) = 3229.93
('Streaming Pro', 'PC profesional para streaming en alta calidad con RTX 4080', 'STREAMING',
 37, 47, '[180,180]', '[50]', 203, 133, 16, 3229.93, NULL),
-- Streaming Elite ($4200-4500): CPU(799.99) + MB(599.99) + RAM(249.99+249.99) + Storage(599.99) + GPU(1599.99) + PSU(249.99) + Case(499.99) = 4249.93
('Streaming Elite', 'PC de élite para streaming profesional con RTX 4090 y máximo rendimiento', 'STREAMING',
 37, 48, '[363,363]', '[72]', 212, 137, 32, 4249.93, NULL);

-- ============================================
-- Builds Predefinidos - Personal/Home
-- Rango de precios: $300 - $1200
-- ============================================
INSERT INTO pre_built_builds (name, description, category, cpu_id, motherboard_id, ram_ids, storage_ids, gpu_id, psu_id, case_id, total_price, image_url) VALUES
-- Home Basic ($300-400): CPU(99.99) + MB(64.99) + RAM(19.99+20.99) + Storage(27.99) + GPU(NULL) + PSU(29.99) + Case(39.99) = 303.93
('Home Basic', 'PC básica para tareas cotidianas, navegación web y ofimática', 'PERSONAL_HOME',
 10, 14, '[13,14]', '[12]', NULL, 9, 163, 303.93, NULL),
-- Home Standard ($500-600): CPU(199.99) + MB(89.99) + RAM(29.99+30.99) + Storage(39.99) + GPU(109.99) + PSU(39.99) + Case(69.99) = 600.93
('Home Standard', 'PC estándar para uso general, entretenimiento y productividad doméstica', 'PERSONAL_HOME',
 14, 15, '[43,44]', '[9]', 9, 12, 9, 600.93, NULL),
-- Home Premium ($900-1100): CPU(299.99) + MB(139.99) + RAM(64.99+64.99) + Storage(99.99) + GPU(249.99) + PSU(64.99) + Case(94.99) = 1079.93
('Home Premium', 'PC premium para uso familiar con buena capacidad de almacenamiento', 'PERSONAL_HOME',
 28, 28, '[113,113]', '[24]', 22, 38, 12, 1079.93, NULL),
-- Home All-in-One ($1100-1200): CPU(449.99) + MB(179.99) + RAM(99.99+99.99) + Storage(149.99) + GPU(329.99) + PSU(99.99) + Case(149.99) = 1559.93
('Home All-in-One', 'PC completa para uso general avanzado y entretenimiento multimedia', 'PERSONAL_HOME',
 34, 31, '[290,290]', '[25]', 120, 41, 14, 1559.93, NULL);

-- Nota: Los IDs de componentes (cpu_id, motherboard_id, etc.) deben corresponder
-- a IDs reales de componentes en las tablas correspondientes.
-- Ajusta estos valores según los IDs reales de tus componentes en Supabase.
-- Los precios están calculados sumando todos los componentes de cada build.