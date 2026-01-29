-- Script SQL para insertar gabinetes (cases) en Supabase
-- Modelos de las marcas más usadas en Occidente
-- Desde gama económica hasta premium

-- ============================================
-- NZXT - Gama Alta/Premium
-- ============================================
INSERT INTO cases (name, brand, supported_form_factor, price, max_gpu_length, max_cpu_cooler_height, storage25_slots, storage35_slots, includes_fans, fan_slots) VALUES
('NZXT H510', 'NZXT', 'ATX', 69.99, 368, 165, 2, 2, true, 4),
('NZXT H510 Flow', 'NZXT', 'ATX', 89.99, 368, 165, 2, 2, true, 4),
('NZXT H510 Elite', 'NZXT', 'ATX', 149.99, 368, 165, 2, 2, true, 4),
('NZXT H5 Flow', 'NZXT', 'ATX', 94.99, 365, 165, 2, 2, true, 4),
('NZXT H5 Elite', 'NZXT', 'ATX', 159.99, 365, 165, 2, 2, true, 4),
('NZXT H7 Flow', 'NZXT', 'ATX', 129.99, 435, 185, 2, 2, true, 6),
('NZXT H7 Elite', 'NZXT', 'ATX', 199.99, 435, 185, 2, 2, true, 6),
('NZXT H9 Flow', 'NZXT', 'EATX', 179.99, 450, 185, 3, 2, true, 9),
('NZXT H9 Elite', 'NZXT', 'EATX', 249.99, 450, 185, 3, 2, true, 9),
('NZXT H210i', 'NZXT', 'ITX', 99.99, 325, 165, 2, 1, true, 4),
('NZXT H1', 'NZXT', 'ITX', 399.99, 305, 140, 1, 0, true, 2),

-- ============================================
-- Corsair - Gama Alta/Premium
-- ============================================
('Corsair 4000D Airflow', 'Corsair', 'ATX', 94.99, 360, 170, 2, 2, true, 6),
('Corsair 4000D', 'Corsair', 'ATX', 89.99, 360, 170, 2, 2, true, 6),
('Corsair 4000X RGB', 'Corsair', 'ATX', 124.99, 360, 170, 2, 2, true, 6),
('Corsair 5000D Airflow', 'Corsair', 'ATX', 164.99, 420, 170, 2, 2, true, 10),
('Corsair 5000D', 'Corsair', 'ATX', 154.99, 420, 170, 2, 2, true, 10),
('Corsair 5000X RGB', 'Corsair', 'ATX', 199.99, 420, 170, 2, 2, true, 10),
('Corsair 7000D Airflow', 'Corsair', 'EATX', 249.99, 450, 190, 4, 2, true, 10),
('Corsair Obsidian 500D RGB', 'Corsair', 'ATX', 249.99, 370, 170, 2, 2, true, 6),
('Corsair Obsidian 1000D', 'Corsair', 'EATX', 499.99, 500, 200, 6, 4, true, 13),
('Corsair Crystal 280X', 'Corsair', 'MATX', 139.99, 300, 150, 2, 2, true, 4),
('Corsair Crystal 570X', 'Corsair', 'ATX', 179.99, 370, 170, 2, 2, true, 6),

-- ============================================
-- Lian Li - Gama Alta/Premium
-- ============================================
('Lian Li O11 Dynamic', 'Lian Li', 'ATX', 149.99, 420, 155, 2, 2, false, 9),
('Lian Li O11 Dynamic EVO', 'Lian Li', 'ATX', 179.99, 442, 167, 2, 2, false, 9),
('Lian Li O11 Dynamic Mini', 'Lian Li', 'MATX', 119.99, 362, 170, 2, 2, false, 7),
('Lian Li O11 Air Mini', 'Lian Li', 'MATX', 109.99, 362, 170, 2, 2, true, 7),
('Lian Li Lancool II Mesh', 'Lian Li', 'ATX', 109.99, 384, 176, 2, 2, true, 6),
('Lian Li Lancool III', 'Lian Li', 'ATX', 149.99, 420, 185, 2, 2, true, 10),
('Lian Li PC-O11 Dynamic XL', 'Lian Li', 'EATX', 199.99, 446, 167, 3, 2, false, 9),
('Lian Li Lancool 205 Mesh', 'Lian Li', 'ATX', 89.99, 350, 160, 2, 2, true, 5),
('Lian Li Lancool 216', 'Lian Li', 'ATX', 109.99, 392, 180, 2, 2, true, 8),
('Lian Li Q58', 'Lian Li', 'ITX', 129.99, 320, 160, 2, 1, false, 4),

-- ============================================
-- Fractal Design - Gama Alta/Media
-- ============================================
('Fractal Design Define 7', 'Fractal Design', 'ATX', 169.99, 467, 185, 2, 2, true, 7),
('Fractal Design Define 7 Compact', 'Fractal Design', 'ATX', 109.99, 360, 169, 2, 2, true, 5),
('Fractal Design Meshify 2', 'Fractal Design', 'ATX', 139.99, 467, 185, 2, 2, true, 7),
('Fractal Design Meshify 2 Compact', 'Fractal Design', 'ATX', 109.99, 360, 169, 2, 2, true, 5),
('Fractal Design Meshify C', 'Fractal Design', 'ATX', 89.99, 315, 172, 2, 2, true, 6),
('Fractal Design Pop Air', 'Fractal Design', 'ATX', 89.99, 405, 170, 2, 2, true, 6),
('Fractal Design Pop Mini Air', 'Fractal Design', 'MATX', 79.99, 360, 170, 2, 2, true, 5),
('Fractal Design Torrent', 'Fractal Design', 'ATX', 199.99, 423, 188, 2, 2, true, 6),
('Fractal Design Torrent Compact', 'Fractal Design', 'ATX', 149.99, 330, 180, 2, 2, true, 5),
('Fractal Design Define R6', 'Fractal Design', 'ATX', 149.99, 440, 185, 2, 2, true, 7),
('Fractal Design Core 1000', 'Fractal Design', 'MATX', 39.99, 315, 148, 1, 2, false, 2),

-- ============================================
-- Phanteks - Gama Alta/Media
-- ============================================
('Phanteks Eclipse P400A', 'Phanteks', 'ATX', 89.99, 420, 160, 2, 2, true, 6),
('Phanteks Eclipse P500A', 'Phanteks', 'ATX', 139.99, 435, 190, 2, 2, true, 7),
('Phanteks Eclipse G360A', 'Phanteks', 'ATX', 109.99, 400, 160, 2, 2, true, 6),
('Phanteks Eclipse G500A', 'Phanteks', 'ATX', 149.99, 435, 190, 2, 2, true, 7),
('Phanteks Enthoo Pro 2', 'Phanteks', 'EATX', 149.99, 490, 194, 4, 2, true, 10),
('Phanteks Enthoo Evolv X', 'Phanteks', 'ATX', 199.99, 435, 190, 2, 2, true, 7),
('Phanteks Enthoo Pro', 'Phanteks', 'ATX', 109.99, 490, 194, 2, 2, true, 8),
('Phanteks Eclipse P300A', 'Phanteks', 'ATX', 69.99, 400, 160, 2, 2, true, 4),
('Phanteks Eclipse P360A', 'Phanteks', 'ATX', 99.99, 400, 160, 2, 2, true, 6),
('Phanteks Evolv Shift XT', 'Phanteks', 'ITX', 199.99, 335, 83, 2, 0, true, 3),

-- ============================================
-- be quiet! - Gama Alta/Media
-- ============================================
('be quiet! Pure Base 500DX', 'be quiet!', 'ATX', 109.99, 369, 190, 2, 2, true, 6),
('be quiet! Pure Base 500', 'be quiet!', 'ATX', 79.99, 369, 190, 2, 2, true, 4),
('be quiet! Pure Base 600', 'be quiet!', 'ATX', 99.99, 410, 190, 2, 2, true, 6),
('be quiet! Silent Base 802', 'be quiet!', 'ATX', 169.99, 450, 185, 2, 2, true, 7),
('be quiet! Dark Base 700', 'be quiet!', 'ATX', 199.99, 450, 185, 2, 2, true, 7),
('be quiet! Dark Base Pro 900', 'be quiet!', 'EATX', 249.99, 450, 185, 4, 2, true, 9),
('be quiet! Pure Base 500FX', 'be quiet!', 'ATX', 149.99, 369, 190, 2, 2, true, 6),
('be quiet! Shadow Base 800', 'be quiet!', 'ATX', 179.99, 450, 185, 2, 2, true, 7),

-- ============================================
-- ASUS ROG - Gama Premium
-- ============================================
('ASUS ROG Strix Helios', 'ASUS', 'EATX', 599.99, 420, 190, 4, 2, true, 9),
('ASUS ROG Hyperion', 'ASUS', 'EATX', 699.99, 450, 200, 4, 2, true, 10),
('ASUS TUF Gaming GT501', 'ASUS', 'ATX', 149.99, 420, 180, 2, 2, true, 6),
('ASUS TUF Gaming GT301', 'ASUS', 'ATX', 99.99, 400, 160, 2, 2, true, 5),
('ASUS Prime AP201', 'ASUS', 'MATX', 79.99, 338, 170, 2, 2, false, 4),

-- ============================================
-- MSI - Gama Media/Alta
-- ============================================
('MSI MPG Gungnir 110R', 'MSI', 'ATX', 119.99, 340, 170, 2, 2, true, 6),
('MSI MPG Gungnir 111R', 'MSI', 'ATX', 139.99, 340, 170, 2, 2, true, 6),
('MSI MAG Forge 100R', 'MSI', 'ATX', 79.99, 340, 160, 2, 2, true, 4),
('MSI MAG Forge 100M', 'MSI', 'MATX', 69.99, 320, 160, 2, 2, true, 4),
('MSI MAG Vampiric 010', 'MSI', 'ATX', 99.99, 350, 160, 2, 2, true, 5),
('MSI MPG Velox 100R', 'MSI', 'ATX', 149.99, 380, 170, 2, 2, true, 7),

-- ============================================
-- Hyte - Gama Premium
-- ============================================
('Hyte Y60', 'Hyte', 'ATX', 199.99, 375, 160, 2, 2, false, 6),
('Hyte Y40', 'Hyte', 'ATX', 149.99, 365, 160, 2, 2, false, 6),
('Hyte Y70 Touch', 'Hyte', 'ATX', 299.99, 375, 160, 2, 2, false, 6),

-- ============================================
-- Cooler Master - Gama Media/Alta
-- ============================================
('Cooler Master MasterBox TD500 Mesh', 'Cooler Master', 'ATX', 99.99, 410, 165, 2, 2, true, 6),
('Cooler Master MasterBox MB520', 'Cooler Master', 'ATX', 79.99, 410, 165, 2, 2, true, 4),
('Cooler Master MasterBox Q300L', 'Cooler Master', 'MATX', 49.99, 360, 159, 1, 1, false, 3),
('Cooler Master MasterBox NR200', 'Cooler Master', 'ITX', 79.99, 330, 155, 2, 2, false, 4),
('Cooler Master MasterBox NR200P', 'Cooler Master', 'ITX', 109.99, 330, 155, 2, 2, false, 4),
('Cooler Master HAF 500', 'Cooler Master', 'ATX', 129.99, 420, 190, 2, 2, true, 6),
('Cooler Master HAF 700 EVO', 'Cooler Master', 'EATX', 299.99, 450, 200, 4, 2, true, 10),
('Cooler Master Cosmos C700M', 'Cooler Master', 'EATX', 499.99, 490, 200, 4, 2, true, 9),
('Cooler Master MasterCase H500', 'Cooler Master', 'ATX', 119.99, 410, 167, 2, 2, true, 6),
('Cooler Master MasterCase Pro 5', 'Cooler Master', 'ATX', 109.99, 412, 190, 2, 2, true, 7),
('Cooler Master Silencio S400', 'Cooler Master', 'MATX', 69.99, 350, 160, 2, 2, true, 4),

-- ============================================
-- Thermaltake - Gama Media/Alta
-- ============================================
('Thermaltake View 51', 'Thermaltake', 'EATX', 199.99, 420, 190, 2, 2, false, 9),
('Thermaltake View 37', 'Thermaltake', 'ATX', 149.99, 400, 180, 2, 2, false, 7),
('Thermaltake Core P3', 'Thermaltake', 'ATX', 139.99, 420, 180, 2, 2, false, 3),
('Thermaltake Core P5', 'Thermaltake', 'ATX', 179.99, 450, 200, 2, 2, false, 3),
('Thermaltake Level 20 VT', 'Thermaltake', 'ATX', 199.99, 420, 190, 2, 2, false, 9),
('Thermaltake Versa H18', 'Thermaltake', 'MATX', 49.99, 315, 160, 1, 2, false, 3),
('Thermaltake Versa H22', 'Thermaltake', 'ATX', 59.99, 315, 160, 1, 2, false, 3),
('Thermaltake Core V21', 'Thermaltake', 'MATX', 69.99, 285, 200, 2, 2, true, 5),
('Thermaltake Core V1', 'Thermaltake', 'ITX', 49.99, 260, 140, 1, 1, false, 2),
('Thermaltake Tower 500', 'Thermaltake', 'ATX', 179.99, 400, 200, 2, 2, false, 6),

-- ============================================
-- Aerocool - Gama Media/Económica
-- ============================================
('Aerocool Cylon', 'Aerocool', 'ATX', 59.99, 350, 160, 2, 2, true, 4),
('Aerocool Bolt', 'Aerocool', 'ATX', 49.99, 330, 160, 2, 2, true, 4),
('Aerocool Project 7 P7-C1', 'Aerocool', 'ATX', 99.99, 400, 170, 2, 2, true, 6),
('Aerocool Shard', 'Aerocool', 'ATX', 39.99, 300, 160, 1, 2, false, 3),
('Aerocool Aero One', 'Aerocool', 'ATX', 69.99, 350, 165, 2, 2, true, 5),
('Aerocool Tor', 'Aerocool', 'ATX', 79.99, 370, 170, 2, 2, true, 5),
('Aerocool Strike-X', 'Aerocool', 'ATX', 54.99, 320, 160, 2, 2, true, 4),
('Aerocool QS-240', 'Aerocool', 'MATX', 44.99, 300, 150, 1, 2, false, 3),

-- ============================================
-- Deepcool - Gama Media/Económica
-- ============================================
('Deepcool Matrexx 55 Mesh', 'Deepcool', 'ATX', 59.99, 370, 160, 2, 2, true, 5),
('Deepcool Matrexx 50', 'Deepcool', 'ATX', 49.99, 350, 160, 2, 2, true, 4),
('Deepcool Tesseract', 'Deepcool', 'ATX', 39.99, 315, 160, 1, 2, false, 3),
('Deepcool CK560', 'Deepcool', 'ATX', 89.99, 380, 170, 2, 2, true, 6),
('Deepcool CG560', 'Deepcool', 'ATX', 79.99, 380, 170, 2, 2, true, 6),
('Deepcool Macube 310', 'Deepcool', 'MATX', 69.99, 320, 160, 2, 2, true, 4),
('Deepcool Macube 550', 'Deepcool', 'ATX', 99.99, 400, 175, 2, 2, true, 7),
('Deepcool AS500 Plus', 'Deepcool', 'ATX', 109.99, 400, 175, 2, 2, true, 7),
('Deepcool CH510', 'Deepcool', 'ATX', 89.99, 380, 170, 2, 2, true, 6),

-- ============================================
-- Cougar - Gama Media
-- ============================================
('Cougar MX330-G', 'Cougar', 'ATX', 49.99, 320, 155, 1, 2, false, 3),
('Cougar MX410 Mesh', 'Cougar', 'ATX', 59.99, 350, 160, 2, 2, true, 4),
('Cougar Archon', 'Cougar', 'ATX', 79.99, 400, 170, 2, 2, true, 5),
('Cougar Panzer Max', 'Cougar', 'ATX', 149.99, 420, 180, 2, 2, true, 7),
('Cougar Gemini S', 'Cougar', 'MATX', 69.99, 320, 160, 2, 2, true, 4),
('Cougar QBX', 'Cougar', 'ITX', 59.99, 270, 105, 2, 1, false, 2),

-- ============================================
-- Antec - Gama Media
-- ============================================
('Antec P101 Silent', 'Antec', 'ATX', 99.99, 450, 180, 2, 2, true, 6),
('Antec P110 Luce', 'Antec', 'ATX', 89.99, 400, 170, 2, 2, true, 5),
('Antec NX410', 'Antec', 'ATX', 69.99, 380, 160, 2, 2, true, 5),
('Antec NX800', 'Antec', 'ATX', 109.99, 420, 175, 2, 2, true, 6),
('Antec Dark Fleet DF500', 'Antec', 'ATX', 79.99, 400, 160, 2, 2, true, 5),
('Antec VSK3000', 'Antec', 'MATX', 39.99, 300, 150, 1, 2, false, 2),

-- ============================================
-- SilverStone - Gama Media/Alta
-- ============================================
('SilverStone RL08', 'SilverStone', 'ATX', 99.99, 330, 165, 2, 2, true, 4),
('SilverStone PM01', 'SilverStone', 'ATX', 119.99, 400, 170, 2, 2, true, 6),
('SilverStone FT05', 'SilverStone', 'ATX', 199.99, 320, 170, 2, 2, true, 4),
('SilverStone SG13', 'SilverStone', 'ITX', 49.99, 266, 61, 1, 1, false, 1),
('SilverStone RVZ03', 'SilverStone', 'ITX', 99.99, 330, 83, 2, 0, false, 2),
('SilverStone LD03', 'SilverStone', 'ITX', 149.99, 330, 83, 2, 0, false, 2),

-- ============================================
-- Zalman - Gama Media/Económica
-- ============================================
('Zalman Z1 Neo', 'Zalman', 'ATX', 49.99, 350, 160, 2, 2, true, 4),
('Zalman Z3', 'Zalman', 'ATX', 39.99, 320, 160, 1, 2, false, 3),
('Zalman Z9 Neo', 'Zalman', 'ATX', 59.99, 370, 160, 2, 2, true, 5),
('Zalman S2', 'Zalman', 'ATX', 44.99, 330, 160, 1, 2, false, 3),
('Zalman Z11 Neo', 'Zalman', 'ATX', 79.99, 400, 170, 2, 2, true, 6),

-- ============================================
-- Sharkoon - Gama Económica
-- ============================================
('Sharkoon TG5', 'Sharkoon', 'ATX', 59.99, 400, 160, 2, 2, true, 5),
('Sharkoon TG4', 'Sharkoon', 'ATX', 49.99, 380, 160, 2, 2, true, 4),
('Sharkoon VG7-W', 'Sharkoon', 'ATX', 69.99, 400, 165, 2, 2, true, 6),
('Sharkoon VS4-W', 'Sharkoon', 'ATX', 39.99, 350, 155, 1, 2, false, 3),
('Sharkoon C10', 'Sharkoon', 'MATX', 34.99, 300, 150, 1, 2, false, 2),
('Sharkoon RGB Flow', 'Sharkoon', 'ATX', 79.99, 400, 165, 2, 2, true, 6),

-- ============================================
-- GameMax - Gama Económica
-- ============================================
('GameMax F15', 'GameMax', 'ATX', 44.99, 350, 160, 1, 2, false, 3),
('GameMax Explorer', 'GameMax', 'ATX', 54.99, 380, 160, 2, 2, true, 4),
('GameMax Kamikaze Pro', 'GameMax', 'ATX', 69.99, 400, 165, 2, 2, true, 5),
('GameMax F15M', 'GameMax', 'MATX', 39.99, 320, 155, 1, 2, false, 3),
('GameMax Spark', 'GameMax', 'ATX', 49.99, 360, 160, 1, 2, false, 4),

-- ============================================
-- Rosewill - Gama Media/Económica
-- ============================================
('Rosewill Cullinan V500', 'Rosewill', 'ATX', 79.99, 400, 170, 2, 2, true, 5),
('Rosewill Prism S500', 'Rosewill', 'ATX', 89.99, 410, 170, 2, 2, true, 6),
('Rosewill Spectra C100', 'Rosewill', 'ATX', 59.99, 380, 160, 2, 2, true, 4),
('Rosewill FBM-X1', 'Rosewill', 'MATX', 29.99, 300, 150, 1, 2, false, 2),
('Rosewill Prism S', 'Rosewill', 'ATX', 69.99, 400, 165, 2, 2, true, 5),

-- ============================================
-- Montech - Gama Media/Económica
-- ============================================
('Montech Air 1000 Premium', 'Montech', 'ATX', 89.99, 400, 165, 2, 2, true, 6),
('Montech Air 900 Mesh', 'Montech', 'ATX', 79.99, 380, 165, 2, 2, true, 5),
('Montech Sky One', 'Montech', 'ATX', 99.99, 400, 170, 2, 2, true, 6),
('Montech X3 Mesh', 'Montech', 'ATX', 59.99, 350, 160, 2, 2, true, 4),
('Montech X1', 'Montech', 'ATX', 44.99, 330, 155, 1, 2, false, 3),
('Montech Air 100 Lite', 'Montech', 'MATX', 49.99, 320, 160, 1, 2, false, 4),

-- ============================================
-- Metallic Gear - Gama Media
-- ============================================
('Metallic Gear Neo Qube', 'Metallic Gear', 'ATX', 99.99, 400, 160, 2, 2, false, 6),
('Metallic Gear Neo G', 'Metallic Gear', 'ATX', 79.99, 380, 160, 2, 2, false, 5),
('Metallic Gear Neo Air', 'Metallic Gear', 'ATX', 89.99, 400, 165, 2, 2, true, 6),
('Metallic Gear Neo Mini', 'Metallic Gear', 'MATX', 69.99, 330, 160, 2, 2, false, 4),

-- ============================================
-- In Win - Gama Media/Alta
-- ============================================
('In Win 101', 'In Win', 'ATX', 79.99, 320, 160, 2, 2, false, 3),
('In Win 303', 'In Win', 'ATX', 99.99, 350, 160, 2, 2, false, 4),
('In Win A1 Plus', 'In Win', 'ITX', 199.99, 330, 160, 2, 0, true, 2),
('In Win 905', 'In Win', 'ATX', 249.99, 400, 170, 2, 2, false, 5),
('In Win 301', 'In Win', 'MATX', 89.99, 320, 160, 2, 2, false, 3),

-- ============================================
-- BitFenix - Gama Media/Económica
-- ============================================
('BitFenix Nova Mesh', 'BitFenix', 'ATX', 59.99, 350, 160, 2, 2, true, 4),
('BitFenix Enso', 'BitFenix', 'ATX', 79.99, 400, 170, 2, 2, true, 5),
('BitFenix Prodigy', 'BitFenix', 'ITX', 89.99, 320, 170, 2, 2, false, 3),
('BitFenix Shinobi', 'BitFenix', 'ATX', 69.99, 380, 165, 2, 2, true, 5),
('BitFenix Comrade', 'BitFenix', 'ATX', 44.99, 320, 160, 1, 2, false, 3),

-- ============================================
-- Apevia - Gama Económica
-- ============================================
('Apevia Predator', 'Apevia', 'ATX', 59.99, 350, 160, 2, 2, true, 4),
('Apevia Genesis', 'Apevia', 'ATX', 69.99, 380, 165, 2, 2, true, 5),
('Apevia Crusader', 'Apevia', 'ATX', 49.99, 330, 160, 1, 2, false, 4),
('Apevia X-QPACK3', 'Apevia', 'MATX', 54.99, 300, 150, 1, 2, false, 3),
('Apevia X-Dreamer4', 'Apevia', 'ATX', 44.99, 320, 155, 1, 2, false, 3),

-- ============================================
-- Kolink - Gama Económica
-- ============================================
('Kolink Observatory', 'Kolink', 'ATX', 79.99, 400, 165, 2, 2, true, 6),
('Kolink Inspire K3', 'Kolink', 'ATX', 49.99, 350, 160, 1, 2, false, 4),
('Kolink Inspire K9', 'Kolink', 'ATX', 59.99, 380, 160, 2, 2, true, 5),
('Kolink Citadel Mesh', 'Kolink', 'ATX', 69.99, 400, 165, 2, 2, true, 5),
('Kolink Stronghold', 'Kolink', 'ATX', 89.99, 420, 170, 2, 2, true, 6),
('Kolink Void', 'Kolink', 'MATX', 39.99, 320, 155, 1, 2, false, 3);
