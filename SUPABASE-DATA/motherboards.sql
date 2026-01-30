-- Script SQL para insertar motherboards en Supabase
-- Compatibles con Intel 9th Gen en adelante y AMD Ryzen 3000 en adelante
-- Incluye todas las marcas y gamas (baja, media, alta)

-- ============================================
-- AMD AM4 Motherboards - Ryzen 3000/5000
-- ============================================
INSERT INTO motherboards (name, brand, socket, ram_type, max_frequency, price, chipset, form_factor, ram_slots, supported_cpu_generations, power_consumption, m2_slots, sata_ports) VALUES

-- AMD AM4 - Gama Baja (A520, B450)
('ASUS Prime A520M-K', 'ASUS', 'AM4', 'DDR4', 3200, 79.99, 'A520', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 25, 1, 4),
('MSI A520M-A Pro', 'MSI', 'AM4', 'DDR4', 3200, 74.99, 'A520', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 25, 1, 4),
('Gigabyte A520M S2H', 'Gigabyte', 'AM4', 'DDR4', 3200, 69.99, 'A520', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 25, 1, 4),
('ASRock A520M-HDV', 'ASRock', 'AM4', 'DDR4', 3200, 64.99, 'A520', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 25, 1, 4),
('ASUS Prime B450M-K', 'ASUS', 'AM4', 'DDR4', 3200, 89.99, 'B450', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('MSI B450M Pro-VDH Max', 'MSI', 'AM4', 'DDR4', 3200, 84.99, 'B450', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('Gigabyte B450M DS3H', 'Gigabyte', 'AM4', 'DDR4', 3200, 79.99, 'B450', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('ASRock B450M Pro4', 'ASRock', 'AM4', 'DDR4', 3200, 74.99, 'B450', 'MATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),

-- AMD AM4 - Gama Media (B550)
('ASUS Prime B550M-K', 'ASUS', 'AM4', 'DDR4', 3600, 99.99, 'B550', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('MSI B550M Pro-VDH', 'MSI', 'AM4', 'DDR4', 3600, 94.99, 'B550', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('Gigabyte B550M DS3H', 'Gigabyte', 'AM4', 'DDR4', 3600, 89.99, 'B550', 'MATX', 2, 'Ryzen 3000,Ryzen 5000', 30, 1, 4),
('ASRock B550M Pro4', 'ASRock', 'AM4', 'DDR4', 3600, 99.99, 'B550', 'MATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 4),
('ASUS TUF Gaming B550M-Plus', 'ASUS', 'AM4', 'DDR4', 3600, 129.99, 'B550', 'MATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 4),
('MSI B550M Mortar', 'MSI', 'AM4', 'DDR4', 3600, 124.99, 'B550', 'MATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 4),
('Gigabyte B550M Aorus Pro', 'Gigabyte', 'AM4', 'DDR4', 3600, 119.99, 'B550', 'MATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 4),
('ASUS Prime B550-Plus', 'ASUS', 'AM4', 'DDR4', 3600, 139.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('MSI B550-A Pro', 'MSI', 'AM4', 'DDR4', 3600, 134.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('Gigabyte B550 Aorus Elite', 'Gigabyte', 'AM4', 'DDR4', 3600, 149.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('ASUS ROG Strix B550-F Gaming', 'ASUS', 'AM4', 'DDR4', 3600, 179.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('MSI B550 Tomahawk', 'MSI', 'AM4', 'DDR4', 3600, 159.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('Gigabyte B550 Aorus Pro', 'Gigabyte', 'AM4', 'DDR4', 3600, 169.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),
('ASRock B550 Steel Legend', 'ASRock', 'AM4', 'DDR4', 3600, 149.99, 'B550', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 30, 2, 6),

-- AMD AM4 - Gama Alta (X570)
('ASUS Prime X570-P', 'ASUS', 'AM4', 'DDR4', 3600, 199.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('MSI X570-A Pro', 'MSI', 'AM4', 'DDR4', 3600, 189.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('Gigabyte X570 UD', 'Gigabyte', 'AM4', 'DDR4', 3600, 179.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('ASUS TUF Gaming X570-Plus', 'ASUS', 'AM4', 'DDR4', 3600, 219.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('MSI X570 Tomahawk', 'MSI', 'AM4', 'DDR4', 3600, 229.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('Gigabyte X570 Aorus Elite', 'Gigabyte', 'AM4', 'DDR4', 3600, 199.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('ASUS ROG Strix X570-E Gaming', 'ASUS', 'AM4', 'DDR4', 3600, 299.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('MSI X570 Gaming Pro Carbon', 'MSI', 'AM4', 'DDR4', 3600, 279.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('Gigabyte X570 Aorus Pro', 'Gigabyte', 'AM4', 'DDR4', 3600, 249.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('ASRock X570 Taichi', 'ASRock', 'AM4', 'DDR4', 3600, 299.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('ASUS ROG Crosshair VIII Hero', 'ASUS', 'AM4', 'DDR4', 3600, 399.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 2, 8),
('MSI X570 Godlike', 'MSI', 'AM4', 'DDR4', 3600, 699.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 3, 8),
('Gigabyte X570 Aorus Xtreme', 'Gigabyte', 'AM4', 'DDR4', 3600, 599.99, 'X570', 'ATX', 4, 'Ryzen 3000,Ryzen 5000', 40, 3, 8),

-- ============================================
-- AMD AM5 Motherboards - Ryzen 7000/8000/9000
-- ============================================
-- AMD AM5 - Gama Baja (A620)
('ASUS Prime A620M-K', 'ASUS', 'AM5', 'DDR5', 5200, 99.99, 'A620', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 25, 1, 4),
('MSI A620M Pro', 'MSI', 'AM5', 'DDR5', 5200, 94.99, 'A620', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 25, 1, 4),
('Gigabyte A620M S2H', 'Gigabyte', 'AM5', 'DDR5', 5200, 89.99, 'A620', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 25, 1, 4),
('ASRock A620M Pro RS', 'ASRock', 'AM5', 'DDR5', 5200, 99.99, 'A620', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 25, 1, 4),

-- AMD AM5 - Gama Media (B650)
('ASUS Prime B650M-K', 'ASUS', 'AM5', 'DDR5', 6000, 129.99, 'B650', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('MSI B650M Pro-VDH', 'MSI', 'AM5', 'DDR5', 6000, 124.99, 'B650', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('Gigabyte B650M DS3H', 'Gigabyte', 'AM5', 'DDR5', 6000, 119.99, 'B650', 'MATX', 2, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('ASRock B650M Pro4', 'ASRock', 'AM5', 'DDR5', 6000, 129.99, 'B650', 'MATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('ASUS TUF Gaming B650M-Plus', 'ASUS', 'AM5', 'DDR5', 6000, 179.99, 'B650', 'MATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('MSI B650M Mortar', 'MSI', 'AM5', 'DDR5', 6000, 174.99, 'B650', 'MATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('Gigabyte B650M Aorus Elite', 'Gigabyte', 'AM5', 'DDR5', 6000, 169.99, 'B650', 'MATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 4),
('ASUS Prime B650-Plus', 'ASUS', 'AM5', 'DDR5', 6000, 189.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('MSI B650-A Pro', 'MSI', 'AM5', 'DDR5', 6000, 184.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('Gigabyte B650 Aorus Elite', 'Gigabyte', 'AM5', 'DDR5', 6000, 199.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('ASUS ROG Strix B650-A Gaming', 'ASUS', 'AM5', 'DDR5', 6000, 249.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('MSI B650 Tomahawk', 'MSI', 'AM5', 'DDR5', 6000, 229.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('Gigabyte B650 Aorus Pro', 'Gigabyte', 'AM5', 'DDR5', 6000, 239.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('ASRock B650 Steel Legend', 'ASRock', 'AM5', 'DDR5', 6000, 199.99, 'B650', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),

-- AMD AM5 - Gama Media-Alta (B650E)
('ASUS ROG Strix B650E-F Gaming', 'ASUS', 'AM5', 'DDR5', 6400, 279.99, 'B650E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('MSI B650E Tomahawk', 'MSI', 'AM5', 'DDR5', 6400, 269.99, 'B650E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),
('Gigabyte B650E Aorus Master', 'Gigabyte', 'AM5', 'DDR5', 6400, 299.99, 'B650E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 30, 2, 6),

-- AMD AM5 - Gama Alta (X670)
('ASUS Prime X670-P', 'ASUS', 'AM5', 'DDR5', 6400, 249.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('MSI X670-A Pro', 'MSI', 'AM5', 'DDR5', 6400, 239.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('Gigabyte X670 Gaming X', 'Gigabyte', 'AM5', 'DDR5', 6400, 229.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('ASUS TUF Gaming X670-Plus', 'ASUS', 'AM5', 'DDR5', 6400, 279.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('MSI X670 Tomahawk', 'MSI', 'AM5', 'DDR5', 6400, 289.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('Gigabyte X670 Aorus Elite', 'Gigabyte', 'AM5', 'DDR5', 6400, 269.99, 'X670', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 2, 8),
('ASUS ROG Strix X670-E Gaming', 'ASUS', 'AM5', 'DDR5', 6400, 399.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('MSI X670E Carbon', 'MSI', 'AM5', 'DDR5', 6400, 379.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('Gigabyte X670E Aorus Master', 'Gigabyte', 'AM5', 'DDR5', 6400, 399.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('ASRock X670E Taichi', 'ASRock', 'AM5', 'DDR5', 6400, 449.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('ASUS ROG Crosshair X670E Hero', 'ASUS', 'AM5', 'DDR5', 6400, 599.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('MSI X670E Godlike', 'MSI', 'AM5', 'DDR5', 6400, 799.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),
('Gigabyte X670E Aorus Xtreme', 'Gigabyte', 'AM5', 'DDR5', 6400, 699.99, 'X670E', 'ATX', 4, 'Ryzen 7000,Ryzen 8000,Ryzen 9000', 40, 3, 8),

-- ============================================
-- Intel LGA1151 Motherboards - 6th/7th/8th/9th Gen
-- ============================================
-- Intel LGA1151 - Gama Baja (H310, B360)
('ASUS Prime H310M-K', 'ASUS', 'LGA1151', 'DDR4', 2666, 69.99, 'H310', 'MATX', 2, '6th,7th,8th,9th', 20, 1, 4),
('MSI H310M Pro-VDH', 'MSI', 'LGA1151', 'DDR4', 2666, 64.99, 'H310', 'MATX', 2, '6th,7th,8th,9th', 20, 1, 4),
('Gigabyte H310M S2H', 'Gigabyte', 'LGA1151', 'DDR4', 2666, 59.99, 'H310', 'MATX', 2, '6th,7th,8th,9th', 20, 1, 4),
('ASRock H310M Pro4', 'ASRock', 'LGA1151', 'DDR4', 2666, 69.99, 'H310', 'MATX', 2, '6th,7th,8th,9th', 20, 1, 4),
('ASUS Prime B360M-A', 'ASUS', 'LGA1151', 'DDR4', 2666, 79.99, 'B360', 'MATX', 2, '8th,9th', 25, 1, 4),
('MSI B360M Pro-VDH', 'MSI', 'LGA1151', 'DDR4', 2666, 74.99, 'B360', 'MATX', 2, '8th,9th', 25, 1, 4),
('Gigabyte B360M DS3H', 'Gigabyte', 'LGA1151', 'DDR4', 2666, 69.99, 'B360', 'MATX', 2, '8th,9th', 25, 1, 4),
('ASRock B360M Pro4', 'ASRock', 'LGA1151', 'DDR4', 2666, 79.99, 'B360', 'MATX', 4, '8th,9th', 25, 1, 4),

-- Intel LGA1151 - Gama Media (B365, H370)
('ASUS Prime B365M-A', 'ASUS', 'LGA1151', 'DDR4', 2666, 89.99, 'B365', 'MATX', 2, '8th,9th', 25, 1, 4),
('MSI B365M Pro-VDH', 'MSI', 'LGA1151', 'DDR4', 2666, 84.99, 'B365', 'MATX', 2, '8th,9th', 25, 1, 4),
('Gigabyte B365M DS3H', 'Gigabyte', 'LGA1151', 'DDR4', 2666, 79.99, 'B365', 'MATX', 2, '8th,9th', 25, 1, 4),
('ASRock B365M Pro4', 'ASRock', 'LGA1151', 'DDR4', 2666, 89.99, 'B365', 'MATX', 4, '8th,9th', 25, 1, 4),
('ASUS Prime B365-Plus', 'ASUS', 'LGA1151', 'DDR4', 2666, 109.99, 'B365', 'ATX', 4, '8th,9th', 25, 2, 6),
('MSI B365-A Pro', 'MSI', 'LGA1151', 'DDR4', 2666, 104.99, 'B365', 'ATX', 4, '8th,9th', 25, 2, 6),
('Gigabyte B365 Aorus Elite', 'Gigabyte', 'LGA1151', 'DDR4', 2666, 119.99, 'B365', 'ATX', 4, '8th,9th', 25, 2, 6),
('ASUS TUF Gaming H370-Pro', 'ASUS', 'LGA1151', 'DDR4', 2666, 129.99, 'H370', 'ATX', 4, '8th,9th', 30, 2, 6),
('MSI H370-A Pro', 'MSI', 'LGA1151', 'DDR4', 2666, 124.99, 'H370', 'ATX', 4, '8th,9th', 30, 2, 6),
('Gigabyte H370 Aorus Gaming 3', 'Gigabyte', 'LGA1151', 'DDR4', 2666, 139.99, 'H370', 'ATX', 4, '8th,9th', 30, 2, 6),

-- Intel LGA1151 - Gama Alta (Z370, Z390)
('ASUS Prime Z370-P', 'ASUS', 'LGA1151', 'DDR4', 3200, 149.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z370-A Pro', 'MSI', 'LGA1151', 'DDR4', 3200, 144.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z370 UD', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 139.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS TUF Gaming Z370-Plus', 'ASUS', 'LGA1151', 'DDR4', 3200, 169.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z370 Tomahawk', 'MSI', 'LGA1151', 'DDR4', 3200, 179.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z370 Aorus Gaming 3', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 169.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS ROG Strix Z370-E Gaming', 'ASUS', 'LGA1151', 'DDR4', 3200, 249.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z370 Gaming Pro Carbon', 'MSI', 'LGA1151', 'DDR4', 3200, 229.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z370 Aorus Gaming 5', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 219.99, 'Z370', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS Prime Z390-P', 'ASUS', 'LGA1151', 'DDR4', 3200, 159.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z390-A Pro', 'MSI', 'LGA1151', 'DDR4', 3200, 154.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z390 UD', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 149.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS TUF Gaming Z390-Plus', 'ASUS', 'LGA1151', 'DDR4', 3200, 179.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z390 Tomahawk', 'MSI', 'LGA1151', 'DDR4', 3200, 189.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z390 Aorus Elite', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 179.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS ROG Strix Z390-E Gaming', 'ASUS', 'LGA1151', 'DDR4', 3200, 259.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('MSI Z390 Gaming Pro Carbon', 'MSI', 'LGA1151', 'DDR4', 3200, 239.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('Gigabyte Z390 Aorus Pro', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 229.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASRock Z390 Taichi', 'ASRock', 'LGA1151', 'DDR4', 3200, 279.99, 'Z390', 'ATX', 4, '8th,9th', 35, 2, 6),
('ASUS ROG Maximus XI Hero', 'ASUS', 'LGA1151', 'DDR4', 3200, 449.99, 'Z390', 'ATX', 4, '8th,9th', 35, 3, 8),
('MSI Z390 Godlike', 'MSI', 'LGA1151', 'DDR4', 3200, 599.99, 'Z390', 'ATX', 4, '8th,9th', 35, 3, 8),
('Gigabyte Z390 Aorus Xtreme', 'Gigabyte', 'LGA1151', 'DDR4', 3200, 499.99, 'Z390', 'ATX', 4, '8th,9th', 35, 3, 8),

-- ============================================
-- Intel LGA1200 Motherboards - 9th/10th/11th Gen
-- ============================================
-- Intel LGA1200 - Gama Baja (H470, B460)
('ASUS Prime H470M-Plus', 'ASUS', 'LGA1200', 'DDR4', 2933, 99.99, 'H470', 'MATX', 2, '9th,10th', 25, 1, 4),
('MSI H470M Pro', 'MSI', 'LGA1200', 'DDR4', 2933, 94.99, 'H470', 'MATX', 2, '9th,10th', 25, 1, 4),
('Gigabyte H470M DS3H', 'Gigabyte', 'LGA1200', 'DDR4', 2933, 89.99, 'H470', 'MATX', 2, '9th,10th', 25, 1, 4),
('ASRock H470M Pro4', 'ASRock', 'LGA1200', 'DDR4', 2933, 99.99, 'H470', 'MATX', 4, '9th,10th', 25, 1, 4),
('ASUS Prime B460M-A', 'ASUS', 'LGA1200', 'DDR4', 2933, 89.99, 'B460', 'MATX', 2, '9th,10th', 25, 1, 4),
('MSI B460M Pro-VDH', 'MSI', 'LGA1200', 'DDR4', 2933, 84.99, 'B460', 'MATX', 2, '9th,10th', 25, 1, 4),
('Gigabyte B460M DS3H', 'Gigabyte', 'LGA1200', 'DDR4', 2933, 79.99, 'B460', 'MATX', 2, '9th,10th', 25, 1, 4),

-- Intel LGA1200 - Gama Media (B560, H570)
('ASUS Prime B560M-A', 'ASUS', 'LGA1200', 'DDR4', 3200, 109.99, 'B560', 'MATX', 2, '10th,11th', 30, 1, 4),
('MSI B560M Pro-VDH', 'MSI', 'LGA1200', 'DDR4', 3200, 104.99, 'B560', 'MATX', 2, '10th,11th', 30, 1, 4),
('Gigabyte B560M DS3H', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 99.99, 'B560', 'MATX', 2, '10th,11th', 30, 1, 4),
('ASRock B560M Pro4', 'ASRock', 'LGA1200', 'DDR4', 3200, 109.99, 'B560', 'MATX', 4, '10th,11th', 30, 2, 4),
('ASUS TUF Gaming B560M-Plus', 'ASUS', 'LGA1200', 'DDR4', 3200, 139.99, 'B560', 'MATX', 4, '10th,11th', 30, 2, 4),
('MSI B560M Mortar', 'MSI', 'LGA1200', 'DDR4', 3200, 134.99, 'B560', 'MATX', 4, '10th,11th', 30, 2, 4),
('Gigabyte B560M Aorus Pro', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 129.99, 'B560', 'MATX', 4, '10th,11th', 30, 2, 4),
('ASUS Prime B560-Plus', 'ASUS', 'LGA1200', 'DDR4', 3200, 149.99, 'B560', 'ATX', 4, '10th,11th', 30, 2, 6),
('MSI B560-A Pro', 'MSI', 'LGA1200', 'DDR4', 3200, 144.99, 'B560', 'ATX', 4, '10th,11th', 30, 2, 6),
('Gigabyte B560 Aorus Elite', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 159.99, 'B560', 'ATX', 4, '10th,11th', 30, 2, 6),
('ASUS TUF Gaming H570-Pro', 'ASUS', 'LGA1200', 'DDR4', 3200, 169.99, 'H570', 'ATX', 4, '10th,11th', 30, 2, 6),
('MSI H570-A Pro', 'MSI', 'LGA1200', 'DDR4', 3200, 164.99, 'H570', 'ATX', 4, '10th,11th', 30, 2, 6),
('Gigabyte H570 Aorus Elite', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 179.99, 'H570', 'ATX', 4, '10th,11th', 30, 2, 6),

-- Intel LGA1200 - Gama Alta (Z490, Z590)
('ASUS Prime Z490-P', 'ASUS', 'LGA1200', 'DDR4', 3200, 179.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('MSI Z490-A Pro', 'MSI', 'LGA1200', 'DDR4', 3200, 174.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('Gigabyte Z490 UD', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 169.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('ASUS TUF Gaming Z490-Plus', 'ASUS', 'LGA1200', 'DDR4', 3200, 199.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('MSI Z490 Tomahawk', 'MSI', 'LGA1200', 'DDR4', 3200, 209.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('Gigabyte Z490 Aorus Elite', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 199.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('ASUS ROG Strix Z490-E Gaming', 'ASUS', 'LGA1200', 'DDR4', 3200, 279.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('MSI Z490 Gaming Pro Carbon', 'MSI', 'LGA1200', 'DDR4', 3200, 259.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('Gigabyte Z490 Aorus Pro', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 249.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('ASRock Z490 Taichi', 'ASRock', 'LGA1200', 'DDR4', 3200, 299.99, 'Z490', 'ATX', 4, '9th,10th', 35, 2, 6),
('ASUS Prime Z590-P', 'ASUS', 'LGA1200', 'DDR4', 3200, 189.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('MSI Z590-A Pro', 'MSI', 'LGA1200', 'DDR4', 3200, 184.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('Gigabyte Z590 UD', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 179.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('ASUS TUF Gaming Z590-Plus', 'ASUS', 'LGA1200', 'DDR4', 3200, 219.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('MSI Z590 Tomahawk', 'MSI', 'LGA1200', 'DDR4', 3200, 229.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('Gigabyte Z590 Aorus Elite', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 219.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('ASUS ROG Strix Z590-E Gaming', 'ASUS', 'LGA1200', 'DDR4', 3200, 299.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('MSI Z590 Gaming Pro Carbon', 'MSI', 'LGA1200', 'DDR4', 3200, 279.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('Gigabyte Z590 Aorus Pro', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 269.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('ASRock Z590 Taichi', 'ASRock', 'LGA1200', 'DDR4', 3200, 319.99, 'Z590', 'ATX', 4, '10th,11th', 35, 2, 6),
('ASUS ROG Maximus XIII Hero', 'ASUS', 'LGA1200', 'DDR4', 3200, 499.99, 'Z590', 'ATX', 4, '10th,11th', 35, 3, 8),
('MSI Z590 Godlike', 'MSI', 'LGA1200', 'DDR4', 3200, 699.99, 'Z590', 'ATX', 4, '10th,11th', 35, 3, 8),
('Gigabyte Z590 Aorus Xtreme', 'Gigabyte', 'LGA1200', 'DDR4', 3200, 599.99, 'Z590', 'ATX', 4, '10th,11th', 35, 3, 8),

-- ============================================
-- Intel LGA1700 Motherboards - 12th/13th/14th Gen
-- ============================================
-- Intel LGA1700 - Gama Baja (H610, B660)
('ASUS Prime H610M-K', 'ASUS', 'LGA1700', 'DDR4', 3200, 89.99, 'H610', 'MATX', 2, '12th,13th,14th', 25, 1, 4),
('MSI H610M Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 84.99, 'H610', 'MATX', 2, '12th,13th,14th', 25, 1, 4),
('Gigabyte H610M S2H', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 79.99, 'H610', 'MATX', 2, '12th,13th,14th', 25, 1, 4),
('ASRock H610M Pro RS', 'ASRock', 'LGA1700', 'DDR4', 3200, 89.99, 'H610', 'MATX', 2, '12th,13th,14th', 25, 1, 4),
('ASUS Prime B660M-K', 'ASUS', 'LGA1700', 'DDR4', 3200, 109.99, 'B660', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('MSI B660M Pro-VDH', 'MSI', 'LGA1700', 'DDR4', 3200, 104.99, 'B660', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('Gigabyte B660M DS3H', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 99.99, 'B660', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('ASRock B660M Pro4', 'ASRock', 'LGA1700', 'DDR4', 3200, 109.99, 'B660', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('ASUS TUF Gaming B660M-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 139.99, 'B660', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('MSI B660M Mortar', 'MSI', 'LGA1700', 'DDR4', 3200, 134.99, 'B660', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('Gigabyte B660M Aorus Pro', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 129.99, 'B660', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('ASUS Prime B660-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 149.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('MSI B660-A Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 144.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('Gigabyte B660 Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 159.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('ASUS ROG Strix B660-A Gaming', 'ASUS', 'LGA1700', 'DDR4', 3200, 199.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('MSI B660 Tomahawk', 'MSI', 'LGA1700', 'DDR4', 3200, 189.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('Gigabyte B660 Aorus Pro', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 179.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('ASRock B660 Steel Legend', 'ASRock', 'LGA1700', 'DDR4', 3200, 159.99, 'B660', 'ATX', 4, '12th,13th,14th', 30, 2, 6),

-- Intel LGA1700 - Gama Media (B760, H770)
('ASUS Prime B760M-K', 'ASUS', 'LGA1700', 'DDR4', 3200, 119.99, 'B760', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('MSI B760M Pro-VDH', 'MSI', 'LGA1700', 'DDR4', 3200, 114.99, 'B760', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('Gigabyte B760M DS3H', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 109.99, 'B760', 'MATX', 2, '12th,13th,14th', 30, 1, 4),
('ASRock B760M Pro4', 'ASRock', 'LGA1700', 'DDR4', 3200, 119.99, 'B760', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('ASUS TUF Gaming B760M-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 149.99, 'B760', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('MSI B760M Mortar', 'MSI', 'LGA1700', 'DDR4', 3200, 144.99, 'B760', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('Gigabyte B760M Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 139.99, 'B760', 'MATX', 4, '12th,13th,14th', 30, 2, 4),
('ASUS Prime B760-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 159.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('MSI B760-A Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 154.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('Gigabyte B760 Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 169.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('ASUS ROG Strix B760-A Gaming', 'ASUS', 'LGA1700', 'DDR4', 3200, 209.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('MSI B760 Tomahawk', 'MSI', 'LGA1700', 'DDR4', 3200, 199.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('Gigabyte B760 Aorus Pro', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 189.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('ASRock B760 Steel Legend', 'ASRock', 'LGA1700', 'DDR4', 3200, 169.99, 'B760', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('ASUS TUF Gaming H770-Pro', 'ASUS', 'LGA1700', 'DDR4', 3200, 179.99, 'H770', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('MSI H770-A Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 174.99, 'H770', 'ATX', 4, '12th,13th,14th', 30, 2, 6),
('Gigabyte H770 Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 189.99, 'H770', 'ATX', 4, '12th,13th,14th', 30, 2, 6),

-- Intel LGA1700 - Gama Alta (Z690, Z790)
('ASUS Prime Z690-P', 'ASUS', 'LGA1700', 'DDR4', 3200, 199.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('MSI Z690-A Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 189.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('Gigabyte Z690 UD', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 179.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('ASUS TUF Gaming Z690-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 229.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('MSI Z690 Tomahawk', 'MSI', 'LGA1700', 'DDR4', 3200, 239.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('Gigabyte Z690 Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 219.99, 'Z690', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('ASUS ROG Strix Z690-F Gaming', 'ASUS', 'LGA1700', 'DDR5', 6000, 399.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('MSI Z690 Carbon', 'MSI', 'LGA1700', 'DDR5', 6000, 379.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('Gigabyte Z690 Aorus Pro', 'Gigabyte', 'LGA1700', 'DDR5', 6000, 349.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('ASRock Z690 Taichi', 'ASRock', 'LGA1700', 'DDR5', 6000, 449.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('ASUS ROG Maximus Z690 Hero', 'ASUS', 'LGA1700', 'DDR5', 6000, 599.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 8),
('MSI Z690 Godlike', 'MSI', 'LGA1700', 'DDR5', 6000, 799.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 8),
('Gigabyte Z690 Aorus Xtreme', 'Gigabyte', 'LGA1700', 'DDR5', 6000, 699.99, 'Z690', 'ATX', 4, '12th,13th,14th', 40, 3, 8),
('ASUS Prime Z790-P', 'ASUS', 'LGA1700', 'DDR4', 3200, 209.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('MSI Z790-A Pro', 'MSI', 'LGA1700', 'DDR4', 3200, 199.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('Gigabyte Z790 UD', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 189.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('ASUS TUF Gaming Z790-Plus', 'ASUS', 'LGA1700', 'DDR4', 3200, 239.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('MSI Z790 Tomahawk', 'MSI', 'LGA1700', 'DDR4', 3200, 249.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('Gigabyte Z790 Aorus Elite', 'Gigabyte', 'LGA1700', 'DDR4', 3200, 229.99, 'Z790', 'ATX', 4, '12th,13th,14th', 35, 2, 6),
('ASUS ROG Strix Z790-F Gaming', 'ASUS', 'LGA1700', 'DDR5', 6000, 429.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('MSI Z790 Carbon', 'MSI', 'LGA1700', 'DDR5', 6000, 409.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('Gigabyte Z790 Aorus Pro', 'Gigabyte', 'LGA1700', 'DDR5', 6000, 379.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('ASRock Z790 Taichi', 'ASRock', 'LGA1700', 'DDR5', 6000, 479.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 6),
('ASUS ROG Maximus Z790 Hero', 'ASUS', 'LGA1700', 'DDR5', 6000, 649.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 8),
('MSI Z790 Godlike', 'MSI', 'LGA1700', 'DDR5', 6000, 849.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 8),
('Gigabyte Z790 Aorus Xtreme', 'Gigabyte', 'LGA1700', 'DDR5', 6000, 749.99, 'Z790', 'ATX', 4, '12th,13th,14th', 40, 3, 8);
