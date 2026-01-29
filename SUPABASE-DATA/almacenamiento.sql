-- Script SQL para insertar dispositivos de almacenamiento en Supabase
-- Incluye NVMe SSD, SATA SSD y HDD desde 128GB hasta 5TB
-- Todas las marcas principales

-- ============================================
-- NVMe SSD M.2 - 128GB a 512GB (Gama Baja)
-- ============================================
INSERT INTO storages (name, brand, type, capacity, price, read_speed, write_speed, form_factor) VALUES
('Samsung 980 250GB', 'Samsung', 'NVME_SSD', 250, 39.99, 3100, 2600, 'M.2'),
('WD Blue SN570 250GB', 'Western Digital', 'NVME_SSD', 250, 34.99, 3300, 1200, 'M.2'),
('Crucial P3 250GB', 'Crucial', 'NVME_SSD', 250, 29.99, 3500, 1900, 'M.2'),
('Kingston NV2 250GB', 'Kingston', 'NVME_SSD', 250, 27.99, 3000, 1200, 'M.2'),
('Samsung 980 500GB', 'Samsung', 'NVME_SSD', 500, 59.99, 3100, 2600, 'M.2'),
('WD Blue SN570 500GB', 'Western Digital', 'NVME_SSD', 500, 49.99, 3300, 2300, 'M.2'),
('Crucial P3 500GB', 'Crucial', 'NVME_SSD', 500, 44.99, 3500, 1900, 'M.2'),
('Kingston NV2 500GB', 'Kingston', 'NVME_SSD', 500, 39.99, 3500, 2100, 'M.2'),
('Samsung 970 EVO Plus 500GB', 'Samsung', 'NVME_SSD', 500, 79.99, 3500, 3200, 'M.2'),
('WD Black SN770 500GB', 'Western Digital', 'NVME_SSD', 500, 69.99, 5150, 4900, 'M.2'),
('Crucial P5 Plus 500GB', 'Crucial', 'NVME_SSD', 500, 64.99, 6600, 3600, 'M.2'),

-- ============================================
-- NVMe SSD M.2 - 1TB (Gama Media)
-- ============================================
('Samsung 980 1TB', 'Samsung', 'NVME_SSD', 1000, 99.99, 3100, 2600, 'M.2'),
('Samsung 980 PRO 1TB', 'Samsung', 'NVME_SSD', 1000, 149.99, 7000, 5000, 'M.2'),
('Samsung 990 PRO 1TB', 'Samsung', 'NVME_SSD', 1000, 159.99, 7450, 6900, 'M.2'),
('WD Blue SN570 1TB', 'Western Digital', 'NVME_SSD', 1000, 79.99, 3300, 3000, 'M.2'),
('WD Black SN770 1TB', 'Western Digital', 'NVME_SSD', 1000, 99.99, 5150, 4900, 'M.2'),
('WD Black SN850 1TB', 'Western Digital', 'NVME_SSD', 1000, 139.99, 7000, 5300, 'M.2'),
('WD Black SN850X 1TB', 'Western Digital', 'NVME_SSD', 1000, 149.99, 7300, 6300, 'M.2'),
('Crucial P3 1TB', 'Crucial', 'NVME_SSD', 1000, 69.99, 3500, 3000, 'M.2'),
('Crucial P5 Plus 1TB', 'Crucial', 'NVME_SSD', 1000, 119.99, 6600, 5000, 'M.2'),
('Kingston NV2 1TB', 'Kingston', 'NVME_SSD', 1000, 69.99, 3500, 2100, 'M.2'),
('Kingston KC3000 1TB', 'Kingston', 'NVME_SSD', 1000, 129.99, 7000, 6000, 'M.2'),
('Kingston Fury Renegade 1TB', 'Kingston', 'NVME_SSD', 1000, 139.99, 7300, 6000, 'M.2'),
('Sabrent Rocket 4.0 1TB', 'Sabrent', 'NVME_SSD', 1000, 119.99, 7000, 5500, 'M.2'),
('Corsair MP600 1TB', 'Corsair', 'NVME_SSD', 1000, 149.99, 4950, 4250, 'M.2'),
('Corsair MP600 Pro 1TB', 'Corsair', 'NVME_SSD', 1000, 159.99, 7000, 5500, 'M.2'),
('ADATA XPG S70 Blade 1TB', 'ADATA', 'NVME_SSD', 1000, 119.99, 7400, 6800, 'M.2'),
('ADATA XPG Gammix S70 1TB', 'ADATA', 'NVME_SSD', 1000, 129.99, 7400, 6800, 'M.2'),
('TeamGroup MP44 1TB', 'TeamGroup', 'NVME_SSD', 1000, 99.99, 7000, 6000, 'M.2'),
('MSI Spatium M480 1TB', 'MSI', 'NVME_SSD', 1000, 139.99, 7000, 5500, 'M.2'),
('Gigabyte AORUS Gen4 1TB', 'Gigabyte', 'NVME_SSD', 1000, 149.99, 5000, 4400, 'M.2'),

-- ============================================
-- NVMe SSD M.2 - 2TB (Gama Alta)
-- ============================================
('Samsung 980 2TB', 'Samsung', 'NVME_SSD', 2000, 179.99, 3100, 2600, 'M.2'),
('Samsung 980 PRO 2TB', 'Samsung', 'NVME_SSD', 2000, 279.99, 7000, 5000, 'M.2'),
('Samsung 990 PRO 2TB', 'Samsung', 'NVME_SSD', 2000, 299.99, 7450, 6900, 'M.2'),
('WD Blue SN570 2TB', 'Western Digital', 'NVME_SSD', 2000, 149.99, 3300, 3000, 'M.2'),
('WD Black SN770 2TB', 'Western Digital', 'NVME_SSD', 2000, 189.99, 5150, 4900, 'M.2'),
('WD Black SN850 2TB', 'Western Digital', 'NVME_SSD', 2000, 279.99, 7000, 5300, 'M.2'),
('WD Black SN850X 2TB', 'Western Digital', 'NVME_SSD', 2000, 299.99, 7300, 6300, 'M.2'),
('Crucial P3 2TB', 'Crucial', 'NVME_SSD', 2000, 129.99, 3500, 3000, 'M.2'),
('Crucial P5 Plus 2TB', 'Crucial', 'NVME_SSD', 2000, 229.99, 6600, 5000, 'M.2'),
('Kingston NV2 2TB', 'Kingston', 'NVME_SSD', 2000, 119.99, 3500, 2800, 'M.2'),
('Kingston KC3000 2TB', 'Kingston', 'NVME_SSD', 2000, 249.99, 7000, 6000, 'M.2'),
('Kingston Fury Renegade 2TB', 'Kingston', 'NVME_SSD', 2000, 269.99, 7300, 6000, 'M.2'),
('Sabrent Rocket 4.0 2TB', 'Sabrent', 'NVME_SSD', 2000, 229.99, 7000, 5500, 'M.2'),
('Corsair MP600 2TB', 'Corsair', 'NVME_SSD', 2000, 279.99, 4950, 4250, 'M.2'),
('Corsair MP600 Pro 2TB', 'Corsair', 'NVME_SSD', 2000, 299.99, 7000, 5500, 'M.2'),
('ADATA XPG S70 Blade 2TB', 'ADATA', 'NVME_SSD', 2000, 229.99, 7400, 6800, 'M.2'),
('ADATA XPG Gammix S70 2TB', 'ADATA', 'NVME_SSD', 2000, 249.99, 7400, 6800, 'M.2'),
('TeamGroup MP44 2TB', 'TeamGroup', 'NVME_SSD', 2000, 199.99, 7000, 6000, 'M.2'),
('MSI Spatium M480 2TB', 'MSI', 'NVME_SSD', 2000, 279.99, 7000, 5500, 'M.2'),
('Gigabyte AORUS Gen4 2TB', 'Gigabyte', 'NVME_SSD', 2000, 299.99, 5000, 4400, 'M.2'),

-- ============================================
-- NVMe SSD M.2 - 4TB (Gama Enthusiast)
-- ============================================
('Samsung 990 PRO 4TB', 'Samsung', 'NVME_SSD', 4000, 599.99, 7450, 6900, 'M.2'),
('WD Black SN850X 4TB', 'Western Digital', 'NVME_SSD', 4000, 549.99, 7300, 6300, 'M.2'),
('Crucial P5 Plus 4TB', 'Crucial', 'NVME_SSD', 4000, 499.99, 6600, 5000, 'M.2'),
('Kingston KC3000 4TB', 'Kingston', 'NVME_SSD', 4000, 529.99, 7000, 6000, 'M.2'),
('Sabrent Rocket 4.0 4TB', 'Sabrent', 'NVME_SSD', 4000, 499.99, 7000, 5500, 'M.2'),
('Corsair MP600 Pro 4TB', 'Corsair', 'NVME_SSD', 4000, 599.99, 7000, 5500, 'M.2'),
('ADATA XPG S70 Blade 4TB', 'ADATA', 'NVME_SSD', 4000, 499.99, 7400, 6800, 'M.2'),

-- ============================================
-- SATA SSD 2.5" - 128GB a 512GB
-- ============================================
('Samsung 870 EVO 250GB', 'Samsung', 'SATA_SSD', 250, 39.99, 560, 530, '2.5"'),
('Samsung 870 QVO 250GB', 'Samsung', 'SATA_SSD', 250, 34.99, 560, 530, '2.5"'),
('WD Blue 3D NAND 250GB', 'Western Digital', 'SATA_SSD', 250, 34.99, 560, 530, '2.5"'),
('Crucial MX500 250GB', 'Crucial', 'SATA_SSD', 250, 32.99, 560, 510, '2.5"'),
('Kingston A400 240GB', 'Kingston', 'SATA_SSD', 240, 24.99, 500, 350, '2.5"'),
('Kingston NV1 500GB', 'Kingston', 'SATA_SSD', 500, 44.99, 2100, 1700, '2.5"'),
('Samsung 870 EVO 500GB', 'Samsung', 'SATA_SSD', 500, 59.99, 560, 530, '2.5"'),
('Samsung 870 QVO 500GB', 'Samsung', 'SATA_SSD', 500, 54.99, 560, 530, '2.5"'),
('WD Blue 3D NAND 500GB', 'Western Digital', 'SATA_SSD', 500, 54.99, 560, 530, '2.5"'),
('Crucial MX500 500GB', 'Crucial', 'SATA_SSD', 500, 49.99, 560, 510, '2.5"'),
('Kingston A400 480GB', 'Kingston', 'SATA_SSD', 480, 39.99, 500, 450, '2.5"'),

-- ============================================
-- SATA SSD 2.5" - 1TB
-- ============================================
('Samsung 870 EVO 1TB', 'Samsung', 'SATA_SSD', 1000, 99.99, 560, 530, '2.5"'),
('Samsung 870 QVO 1TB', 'Samsung', 'SATA_SSD', 1000, 89.99, 560, 530, '2.5"'),
('WD Blue 3D NAND 1TB', 'Western Digital', 'SATA_SSD', 1000, 89.99, 560, 530, '2.5"'),
('WD Blue SA510 1TB', 'Western Digital', 'SATA_SSD', 1000, 79.99, 560, 520, '2.5"'),
('Crucial MX500 1TB', 'Crucial', 'SATA_SSD', 1000, 89.99, 560, 510, '2.5"'),
('Crucial BX500 1TB', 'Crucial', 'SATA_SSD', 1000, 69.99, 540, 500, '2.5"'),
('Kingston A400 960GB', 'Kingston', 'SATA_SSD', 960, 64.99, 500, 450, '2.5"'),
('Kingston NV1 1TB', 'Kingston', 'SATA_SSD', 1000, 69.99, 2100, 1700, '2.5"'),
('ADATA SU800 1TB', 'ADATA', 'SATA_SSD', 1000, 79.99, 560, 520, '2.5"'),
('ADATA SU650 960GB', 'ADATA', 'SATA_SSD', 960, 64.99, 520, 450, '2.5"'),
('TeamGroup GX2 1TB', 'TeamGroup', 'SATA_SSD', 1000, 69.99, 520, 470, '2.5"'),
('SanDisk SSD Plus 1TB', 'SanDisk', 'SATA_SSD', 1000, 79.99, 535, 450, '2.5"'),
('PNY CS900 1TB', 'PNY', 'SATA_SSD', 1000, 74.99, 515, 490, '2.5"'),

-- ============================================
-- SATA SSD 2.5" - 2TB
-- ============================================
('Samsung 870 EVO 2TB', 'Samsung', 'SATA_SSD', 2000, 199.99, 560, 530, '2.5"'),
('Samsung 870 QVO 2TB', 'Samsung', 'SATA_SSD', 2000, 179.99, 560, 530, '2.5"'),
('WD Blue 3D NAND 2TB', 'Western Digital', 'SATA_SSD', 2000, 179.99, 560, 530, '2.5"'),
('WD Blue SA510 2TB', 'Western Digital', 'SATA_SSD', 2000, 169.99, 560, 520, '2.5"'),
('Crucial MX500 2TB', 'Crucial', 'SATA_SSD', 2000, 179.99, 560, 510, '2.5"'),
('Kingston NV1 2TB', 'Kingston', 'SATA_SSD', 2000, 139.99, 2100, 1700, '2.5"'),
('ADATA SU800 2TB', 'ADATA', 'SATA_SSD', 2000, 169.99, 560, 520, '2.5"'),
('TeamGroup GX2 2TB', 'TeamGroup', 'SATA_SSD', 2000, 149.99, 520, 470, '2.5"'),

-- ============================================
-- SATA SSD 2.5" - 4TB
-- ============================================
('Samsung 870 QVO 4TB', 'Samsung', 'SATA_SSD', 4000, 349.99, 560, 530, '2.5"'),
('Crucial MX500 4TB', 'Crucial', 'SATA_SSD', 4000, 339.99, 560, 510, '2.5"'),
('Kingston NV1 4TB', 'Kingston', 'SATA_SSD', 4000, 299.99, 2100, 1700, '2.5"'),

-- ============================================
-- HDD 3.5" - 500GB a 1TB
-- ============================================
('Seagate Barracuda 500GB', 'Seagate', 'HDD', 500, 29.99, 140, 140, '3.5"'),
('WD Blue 500GB', 'Western Digital', 'HDD', 500, 32.99, 150, 150, '3.5"'),
('Toshiba P300 500GB', 'Toshiba', 'HDD', 500, 29.99, 150, 150, '3.5"'),
('Seagate Barracuda 1TB', 'Seagate', 'HDD', 1000, 44.99, 180, 180, '3.5"'),
('WD Blue 1TB', 'Western Digital', 'HDD', 1000, 44.99, 180, 180, '3.5"'),
('Toshiba P300 1TB', 'Toshiba', 'HDD', 1000, 44.99, 180, 180, '3.5"'),

-- ============================================
-- HDD 3.5" - 2TB
-- ============================================
('Seagate Barracuda 2TB', 'Seagate', 'HDD', 2000, 59.99, 220, 220, '3.5"'),
('Seagate Barracuda Compute 2TB', 'Seagate', 'HDD', 2000, 54.99, 220, 220, '3.5"'),
('WD Blue 2TB', 'Western Digital', 'HDD', 2000, 59.99, 220, 220, '3.5"'),
('WD Blue Desktop 2TB', 'Western Digital', 'HDD', 2000, 54.99, 220, 220, '3.5"'),
('Toshiba P300 2TB', 'Toshiba', 'HDD', 2000, 59.99, 220, 220, '3.5"'),
('Toshiba X300 2TB', 'Toshiba', 'HDD', 2000, 64.99, 220, 220, '3.5"'),

-- ============================================
-- HDD 3.5" - 3TB
-- ============================================
('Seagate Barracuda 3TB', 'Seagate', 'HDD', 3000, 79.99, 220, 220, '3.5"'),
('Seagate Barracuda Compute 3TB', 'Seagate', 'HDD', 3000, 74.99, 220, 220, '3.5"'),
('WD Blue 3TB', 'Western Digital', 'HDD', 3000, 79.99, 220, 220, '3.5"'),
('Toshiba P300 3TB', 'Toshiba', 'HDD', 3000, 79.99, 220, 220, '3.5"'),
('Toshiba X300 3TB', 'Toshiba', 'HDD', 3000, 84.99, 220, 220, '3.5"'),

-- ============================================
-- HDD 3.5" - 4TB
-- ============================================
('Seagate Barracuda 4TB', 'Seagate', 'HDD', 4000, 99.99, 220, 220, '3.5"'),
('Seagate Barracuda Compute 4TB', 'Seagate', 'HDD', 4000, 94.99, 220, 220, '3.5"'),
('Seagate IronWolf 4TB', 'Seagate', 'HDD', 4000, 119.99, 180, 180, '3.5"'),
('WD Blue 4TB', 'Western Digital', 'HDD', 4000, 99.99, 220, 220, '3.5"'),
('WD Blue Desktop 4TB', 'Western Digital', 'HDD', 4000, 94.99, 220, 220, '3.5"'),
('WD Red Plus 4TB', 'Western Digital', 'HDD', 4000, 124.99, 180, 180, '3.5"'),
('WD Black 4TB', 'Western Digital', 'HDD', 4000, 149.99, 220, 220, '3.5"'),
('Toshiba P300 4TB', 'Toshiba', 'HDD', 4000, 99.99, 220, 220, '3.5"'),
('Toshiba X300 4TB', 'Toshiba', 'HDD', 4000, 109.99, 220, 220, '3.5"'),
('Toshiba N300 4TB', 'Toshiba', 'HDD', 4000, 119.99, 180, 180, '3.5"'),

-- ============================================
-- HDD 3.5" - 5TB
-- ============================================
('Seagate Barracuda 5TB', 'Seagate', 'HDD', 5000, 119.99, 220, 220, '3.5"'),
('Seagate Barracuda Compute 5TB', 'Seagate', 'HDD', 5000, 114.99, 220, 220, '3.5"'),
('Seagate IronWolf 5TB', 'Seagate', 'HDD', 5000, 149.99, 180, 180, '3.5"'),
('WD Blue 5TB', 'Western Digital', 'HDD', 5000, 119.99, 220, 220, '3.5"'),
('WD Blue Desktop 5TB', 'Western Digital', 'HDD', 5000, 114.99, 220, 220, '3.5"'),
('WD Red Plus 5TB', 'Western Digital', 'HDD', 5000, 149.99, 180, 180, '3.5"'),
('WD Black 5TB', 'Western Digital', 'HDD', 5000, 179.99, 220, 220, '3.5"'),
('Toshiba X300 5TB', 'Toshiba', 'HDD', 5000, 129.99, 220, 220, '3.5"'),
('Toshiba N300 5TB', 'Toshiba', 'HDD', 5000, 149.99, 180, 180, '3.5"'),

-- ============================================
-- HDD 3.5" - 6TB a 8TB (Opciones adicionales)
-- ============================================
('Seagate Barracuda 6TB', 'Seagate', 'HDD', 6000, 139.99, 220, 220, '3.5"'),
('WD Blue 6TB', 'Western Digital', 'HDD', 6000, 139.99, 220, 220, '3.5"'),
('Seagate Barracuda 8TB', 'Seagate', 'HDD', 8000, 179.99, 220, 220, '3.5"'),
('WD Blue 8TB', 'Western Digital', 'HDD', 8000, 179.99, 220, 220, '3.5"'),
('Seagate IronWolf 8TB', 'Seagate', 'HDD', 8000, 199.99, 180, 180, '3.5"'),
('WD Red Plus 8TB', 'Western Digital', 'HDD', 8000, 199.99, 180, 180, '3.5"');
