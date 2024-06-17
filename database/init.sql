-- SQL script for creating the simple Database
-- look at ERM.png for structure
CREATE SCHEMA IF NOT EXISTS PC_Builder;

USE PC_Builder;

CREATE TABLE IF NOT EXISTS brand (
  ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE (name)
);

INSERT INTO brand (
  name
) VALUES
  ('AMD'),
  ('Intel'),
  ('NVIDIA'),
  ('PowerColor'),
  ('ASUS'),
  ('Zotac'),
  ('MSI'),
  ('XFX'),
  ('Corsair'),
  ('Thermaltake'),
  ('Gigabyte'),
  ('Palit'),
  ('Gainward'),
  ('ASRock'),
  ('Lian_li'),
  ('NZXT'),
  ('Antec'),
  ('Phanteks'),
  ('Be_quiet'),
  ('Cooler_master'),
  ('Kingston'),
  ('G.Skill'),
  ('Hardware Labs'),
  ('Alphacool'),
  ('EKWB'),
  ('Noctua'),
  ('Crucial'),
  ('Samsung'),
  ('Toshiba'),
  ('Western Digital'),
  ('Seagate');

-- NOTE: User functionality
CREATE TABLE IF NOT EXISTS user (
  userID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  email VARCHAR(50) UNIQUE,
  password VARCHAR(25) NOT NULL,
  PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS mainboard (
  ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50),
  brandID INT NOT NULL,
  chipset VARCHAR(50),
  form VARCHAR(10),
  ethernet_speed INT, -- in MB/s
  IO VARCHAR(255), -- CSV for ports e.g., "USB 2.0, USB 3.0"
  other_features VARCHAR(255),
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

CREATE TABLE IF NOT EXISTS cpu (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  line VARCHAR(50),
  name VARCHAR(50),
  core_count INT,
  thread_count INT,
  clock_speed INT,
  form VARCHAR(10),
  tdp INT,
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO cpu (
  brandID, line, name, core_count, thread_count, clock_speed, form, tdp, price
) VALUES
  (1, 'Ryzen 5', '7600X', 6, 12, 3800, 'AM4', 105, 299),
  (1, 'Ryzen 7', '7700X', 8, 16, 3800, 'AM4', 105, 399),
  (1, 'Ryzen 7', '7800X3D', 8, 16, 3400, 'AM4', 105, 499),
  (1, 'Ryzen 9', '7900X', 12, 24, 3700, 'AM4', 170, 549),
  (1, 'Ryzen 9', '7950X', 16, 32, 4400, 'AM4', 170, 749),
  (2, 'Core i5', '13600K', 6, 12, 3400, 'LGA 1700', 125, 319),
  (2, 'Core i7', '13700K', 8, 16, 3600, 'LGA 1700', 125, 439),
  (2, 'Core i9', '13900K', 8, 16, 3500, 'LGA 1700', 125, 619),
  (2, 'Core i5', '12600K', 6, 12, 3300, 'LGA 1700', 125, 289),
  (2, 'Core i7', '12700K', 8, 16, 3500, 'LGA 1700', 125, 399),
  (2, 'Core i9', '12900K', 8, 16, 3400, 'LGA 1700', 125, 599),
  (1, 'Ryzen 5', '5600X', 6, 12, 3700, 'AM4', 65, 229),
  (1, 'Ryzen 7', '5800X', 8, 16, 3800, 'AM4', 105, 349),
  (1, 'Ryzen 9', '5900X', 12, 24, 3700, 'AM4', 105, 499),
  (1, 'Ryzen 9', '5950X', 16, 32, 3400, 'AM4', 105, 799),
  (2, 'Core i5', '11600K', 6, 12, 3500, 'LGA 1200', 125, 229),
  (2, 'Core i7', '11700K', 8, 16, 3600, 'LGA 1200', 125, 349),
  (2, 'Core i9', '11900K', 8, 16, 3500, 'LGA 1200', 125, 529),
  (2, 'Core i5', '10600K', 6, 12, 4100, 'LGA 1200', 125, 229),
  (2, 'Core i7', '10700K', 8, 16, 3800, 'LGA 1200', 125, 349),
  (2, 'Core i9', '10900K', 10, 20, 3700, 'LGA 1200', 125, 499),
  (1, 'Ryzen 5', '3600', 6, 12, 3600, 'AM4', 65, 199),
  (1, 'Ryzen 7', '3700X', 8, 16, 3600, 'AM4', 65, 329),
  (1, 'Ryzen 9', '3900X', 12, 24, 3800, 'AM4', 105, 499);

CREATE TABLE IF NOT EXISTS gpu (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  line VARCHAR(50),
  name VARCHAR(50),
  clock_speed INT,
  IO VARCHAR(255), -- CSV for ports e.g., "HDMI, DP"
  tdp INT,
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO gpu (
  brandID, line, name, clock_speed, IO, tdp, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'PowerColor'), 'Radeon', 'RX 7800 XT', 2210, 'HDMI, DP', 263, 499),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'GeForce', 'RTX 4080 SUPER OC Edition', 2505, 'HDMI, DP', 320, 1199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Zotac'), 'GeForce', 'RTX 4080 SUPER Trinity Black Edition', 2565, 'HDMI, DP', 320, 1199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'GeForce', 'RTX 4080 16GB Gaming Trio', 2505, 'HDMI, DP', 320, 1199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'XFX'), 'Radeon', 'RX 7900 XT', 2300, 'HDMI, DP', 300, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Zotac'), 'GeForce', 'RTX 4070 Ti AMP Extreme AIRO', 2700, 'HDMI, DP', 285, 899),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'GeForce', 'RTX 4090 White OC Edition', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Gigabyte'), 'GeForce', 'RTX 4080 SUPER Gaming OC 16G', 2535, 'HDMI, DP', 320, 1199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'GeForce', 'RTX 4090 OC Edition', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Gigabyte'), 'GeForce', 'RTX 4090 Gaming OC 24G', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'Radeon', 'RX 7900 XTX OC', 2300, 'HDMI, DP', 355, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'GeForce', 'RTX 4080 SUPER 16GB Gaming X Trio', 2505, 'HDMI, DP', 320, 1199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'Radeon', 'RX 7800 XT OC', 2210, 'HDMI, DP', 263, 499),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Gigabyte'), 'Radeon', 'RX 7900 XTX Elite 24G', 2300, 'HDMI, DP', 355, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'Radeon', 'RX 7800 XT OC Edition', 2210, 'HDMI, DP', 263, 499),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Zotac'), 'GeForce', 'RTX 4090 Trinity OC', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'PowerColor'), 'Radeon', 'RX 7900 XTX Hellhound', 2300, 'HDMI, DP', 355, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'AMD'), 'Radeon', 'RX 7900 XTX', 2300, 'HDMI, DP', 355, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'GeForce', 'RTX 4090 Suprim X 24G', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Zotac'), 'GeForce', 'RTX 4070 Ti Trinity', 2700, 'HDMI, DP', 285, 899),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Intel'), 'Arc', 'A770 Limited Edition', 2100, 'HDMI, DP', 225, 349),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Palit'), 'GeForce', 'RTX 4090 GameRock OC', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Gainward'), 'GeForce', 'RTX 4070 Ti SUPER Panther OC', 2700, 'HDMI, DP', 285, 899),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'GeForce', 'RTX 4070 Ti Gaming X Trio 12G', 2700, 'HDMI, DP', 285, 899),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASRock'), 'Radeon', 'RX 7900 XTX Taichi 24GB OC', 2300, 'HDMI, DP', 355, 999),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Gigabyte'), 'GeForce', 'RTX 4090 Master 24G', 2610, 'HDMI, DP', 450, 1599),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'GeForce', 'RTX 4090 Gaming X Trio 24G', 2610, 'HDMI, DP', 450, 1599);

CREATE TABLE IF NOT EXISTS ram (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(100),
  capacity INT, -- in gb
  clock_speed INT,
  timing VARCHAR(10),
  ddr_type VARCHAR(50),
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO ram (
  brandID, name, capacity, clock_speed, timing, ddr_type, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance LPX (2 x 16GB) DDR4 3200MHz C16', 32, 3200, 'C16', 'DDR4', 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance RGB Pro', 16, 3600, 'C18', 'DDR4', 90),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance', 16, 6000, 'CL36', 'DDR5', 130),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance RGB', 16, 6000, 'CL30', 'DDR5', 160),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Crucial Pro DDR4 RAM 32GB kit', 32, 3200, 'C16', 'DDR4', 170),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Ripjaws V Series 16GB (2 x 8GB)', 16, 3600, 'C16', 'DDR4', 80),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Trident Z RGB 16GB (2 x 8GB)', 16, 3200, 'C16', 'DDR4', 90),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance LPX 32GB (2 x 16GB)', 32, 3200, 'C16', 'DDR4', 160),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Dominator Platinum RGB 32GB (2 x 16GB)', 32, 3200, 'C16', 'DDR4', 210),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'FURY Beast RGB 32GB (2 x 16GB)', 32, 3600, 'C17', 'DDR4', 190),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'FURY Beast 32GB (2 x 16GB)', 32, 3200, 'C16', 'DDR4', 150),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'HyperX Predator 32GB (2 x 16GB)', 32, 3200, 'C16', 'DDR4', 160),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance LPX 64GB (2 x 32GB)', 64, 3200, 'C16', 'DDR4', 290),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Dominator Platinum RGB 64GB (2 x 32GB)', 64, 3200, 'C16', 'DDR4', 420),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Trident Z Royal 32GB (2 x 16GB)', 32, 3600, 'C18', 'DDR4', 230),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Trident Z Neo 32GB (2 x 16GB)', 32, 3600, 'C16', 'DDR4', 220),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Ripjaws V Series 64GB (2 x 32GB)', 64, 3200, 'C16', 'DDR4', 290),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance LPX 16GB (2 x 8GB)', 16, 3200, 'C16', 'DDR4', 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance RGB Pro 16GB (2 x 8GB)', 16, 3600, 'C18', 'DDR4', 90),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Ripjaws V Series 32GB (2 x 16GB)', 32, 3200, 'C16', 'DDR4', 160),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'HyperX Predator 16GB (2 x 8GB)', 16, 3200, 'C16', 'DDR4', 80),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Dominator Platinum RGB 16GB (2 x 8GB)', 16, 3200, 'C16', 'DDR4', 105),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'G.Skill'), 'Trident Z RGB 32GB (2 x 16GB)', 32, 3600, 'C16', 'DDR4', 200),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance LPX 16GB (2 x 8GB)', 16, 3200, 'C16', 'DDR4', 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Vengeance RGB 16GB (2 x 8GB)', 16, 3600, 'C18', 'DDR4', 90);

CREATE TABLE IF NOT EXISTS psu (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  wattage INT,
  type VARCHAR(50), -- modular, hardwired
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO psu (
  brandID, name, wattage, type, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM850x', 850, 'Modular', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM750x', 750, 'Modular', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM650x', 650, 'Modular', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX650M', 650, 'Modular', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX550M', 550, 'Modular', 69),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX450M', 450, 'Modular', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM1000x', 1000, 'Modular', 159),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'TX650M', 650, 'Modular', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'TX750M', 750, 'Modular', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'TX850M', 850, 'Modular', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX750M', 750, 'Modular', 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM850i', 850, 'Modular', 139),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM750i', 750, 'Modular', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'RM650i', 650, 'Modular', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX650', 650, 'Hardwired', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX550', 550, 'Hardwired', 49),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CX450', 450, 'Hardwired', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'SF600', 600, 'Modular', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'SF450', 450, 'Modular', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'HX1000i', 1000, 'Modular', 189),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'HX850i', 850, 'Modular', 179),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'HX750i', 750, 'Modular', 169),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'HX650i', 650, 'Modular', 159),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'VS650', 650, 'Hardwired', 49),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'VS550', 550, 'Hardwired', 39);

CREATE TABLE IF NOT EXISTS hdd (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  storage INT, -- in GB
  rpm INT,
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO hdd (
  brandID, name, storage, rpm, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 1TB', 1000, 7200, 45),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 2TB', 2000, 7200, 65),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 3TB', 3000, 7200, 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 4TB', 4000, 7200, 105),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 6TB', 6000, 7200, 155),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Barracuda 8TB', 8000, 7200, 205),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 1TB', 1000, 7200, 45),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 2TB', 2000, 7200, 65),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 3TB', 3000, 7200, 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 4TB', 4000, 7200, 105),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 6TB', 6000, 7200, 155),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 8TB', 8000, 7200, 205),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 1TB', 1000, 7200, 45),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 2TB', 2000, 7200, 65),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 3TB', 3000, 7200, 85),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 4TB', 4000, 7200, 105),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 6TB', 6000, 7200, 155),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'P300 8TB', 8000, 7200, 205),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'IronWolf 10TB', 10000, 7200, 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'IronWolf 12TB', 12000, 7200, 349),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Red 10TB', 10000, 7200, 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Red 12TB', 12000, 7200, 349),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'N300 10TB', 10000, 7200, 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Toshiba'), 'N300 12TB', 12000, 7200, 349),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Exos 10TB', 10000, 7200, 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Seagate'), 'Exos 12TB', 12000, 7200, 349);

CREATE TABLE IF NOT EXISTS ssd (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  storage INT, -- in GB
  type VARCHAR(50),
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO ssd (
  brandID, name, storage, type, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '970 EVO Plus 500GB', 500, 'NVMe', 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '970 EVO Plus 1TB', 1000, 'NVMe', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '970 EVO Plus 2TB', 2000, 'NVMe', 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '860 EVO 500GB', 500, 'SATA', 69),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '860 EVO 1TB', 1000, 'SATA', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Samsung'), '860 EVO 2TB', 2000, 'SATA', 249),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue SN550 500GB', 500, 'NVMe', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue SN550 1TB', 1000, 'NVMe', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue SN550 2TB', 2000, 'NVMe', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 3D NAND 500GB', 500, 'SATA', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 3D NAND 1TB', 1000, 'SATA', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Western Digital'), 'WD Blue 3D NAND 2TB', 2000, 'SATA', 219),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'P5 500GB', 500, 'NVMe', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'P5 1TB', 1000, 'NVMe', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'P5 2TB', 2000, 'NVMe', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'MX500 500GB', 500, 'SATA', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'MX500 1TB', 1000, 'SATA', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Crucial'), 'MX500 2TB', 2000, 'SATA', 219),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'A2000 500GB', 500, 'NVMe', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'A2000 1TB', 1000, 'NVMe', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'A2000 2TB', 2000, 'NVMe', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'KC2500 500GB', 500, 'NVMe', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'KC2500 1TB', 1000, 'NVMe', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Kingston'), 'KC2500 2TB', 2000, 'NVMe', 299);

CREATE TABLE IF NOT EXISTS ccase (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(100),
  form VARCHAR(20),
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

-- Step 1: Add entries for the PC cases
INSERT INTO ccase (
  brandID, name, form, price
) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 6500D Airflow Mid-Tower Dual Chamber - Black', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 6500D Airflow Mid-Tower Dual Chamber - White', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 5000D CORE Airflow Mid-Tower ATX PC Case - White', 'Mid-Tower', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 5000D CORE Airflow Mid-Tower ATX PC Case - Black', 'Mid-Tower', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 3000D AIRFLOW Mid-Tower PC Case - White', 'Mid-Tower', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 3000D RGB AIRFLOW Mid-Tower PC Case - Black', 'Mid-Tower', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 3000D RGB AIRFLOW Mid-Tower PC Case - White', 'Mid-Tower', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 3000D AIRFLOW Mid-Tower PC Case - Black', 'Mid-Tower', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR iCUE 4000D RGB AIRFLOW Mid-Tower Case, True White', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 4000D Airflow Tempered Glass Mid-Tower - Black', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR iCUE 5000D RGB AIRFLOW Mid-Tower Case, Black', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR iCUE 5000D RGB AIRFLOW Mid-Tower Case, True White', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 5000D Airflow Tempered Glass Mid-Tower - Black', 'Mid-Tower', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR iCUE 5000T RGB Tempered Glass Mid-Tower ATX PC Case - Black', 'Mid-Tower', 249),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR iCUE 5000T RGB Tempered Glass Mid-Tower ATX PC Case - White', 'Mid-Tower', 249),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 7000D Airflow Tempered Glass Full-Tower - Black', 'Full-Tower', 189),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR Obsidian Series 1000D Super-Tower Case', 'Super-Tower', 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Antec'), 'Antec DF700 Flux', 'Mid-Tower', 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Antec'), 'Antec P120 Crystal', 'Mid-Tower', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'ASUS ROG Strix Helios', 'Mid-Tower', 189),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'ASUS'), 'ASUS TUF Gaming GT501', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'be quiet! Dark Base Pro 900 Black rev. 2', 'Full-Tower', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'be quiet! Pure Base 500DX Black', 'Mid-Tower', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'be quiet! Silent Base 802 Window Black', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'Cooler Master Cosmos C700M', 'Full-Tower', 239),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'Cooler Master MasterBox TD500 Mesh White', 'Mid-Tower', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'Cooler Master MasterCase H500M', 'Mid-Tower', 179),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Fractal Design Define 7 Dark Tempered Glass Black', 'Mid-Tower', 159),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Fractal Design Meshify 2 Black', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Fractal Design Meshify 2 XL Light Tempered Glass Black', 'Mid-Tower', 169),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li Lancool II Mesh Performance', 'Mid-Tower', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li Lancool III Black', 'Mid-Tower', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li O11 Air Mini Black 7-slot', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li PC-O11 Dynamic Black', 'Mid-Tower', 139),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li O11 Dynamic EVO Black', 'Mid-Tower', 169),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li O11 Dynamic EVO White', 'Mid-Tower', 169),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li PC-O11 Dynamic Mini Black 7-slot', 'Mid-Tower', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li PC-O11 Dynamic XL Black', 'Mid-Tower', 179),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Lian_li'), 'Lian Li PC-O11 Dynamic XL Silver', 'Mid-Tower', 179),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'MSI MPG Gungnir 110R', 'Mid-Tower', 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'MSI'), 'MSI MPG Velox 100R', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'NZXT H710i Matte Black', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'NZXT H710i Matte White', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Phanteks'), 'Phanteks Eclipse P360A Black', 'Mid-Tower', 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Phanteks'), 'Phanteks Enthoo 719 Satin Black', 'Full-Tower', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Phanteks'), 'Phanteks Eclipse P500A DRGB Black', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Thermaltake Core P3 Tempered Glass Edition', 'Mid-Tower', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Thermaltake Divider 500 TG ARGB', 'Mid-Tower', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Thermaltake The Tower 900', 'Full-Tower', 299),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Thermaltake View 51 Tempered Glass Snow ARGB Edition', 'Full-Tower', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Thermaltake View 71 Tempered Glass RGB Edition', 'Full-Tower', 229),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2500D Airflow Micro ATX Dual Chamber - Black', 'Micro ATX', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2500D Airflow Micro ATX Dual Chamber - White', 'Micro ATX', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2000D AIRFLOW Mini-ITX PC Case - White', 'Mini ITX', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2000D AIRFLOW Mini-ITX PC Case - Black', 'Mini ITX', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2000D RGB AIRFLOW Mini-ITX PC Case - Black', 'Mini ITX', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'CORSAIR 2000D RGB AIRFLOW Mini-ITX PC Case - White', 'Mini ITX', 99);

CREATE TABLE IF NOT EXISTS size (
  name VARCHAR(20) NOT NULL,
  size INT -- in cm
);

INSERT INTO size (name, size) VALUES
  ('Mini-ITX', 17),
  ('Micro-ATX', 24),
  ('ATX', 30),
  ('Extended-ATX', 35),
  ('Full-Tower', 45),
  ('Mid-Tower', 45),
  ('Super-Tower', 55);

CREATE TABLE IF NOT EXISTS fan (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  size INT, -- in mm
  type VARCHAR(50), -- e.g., rgb
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO fan (brandID, name, size, type, price) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'LL120 RGB', 120, 'RGB', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'LL140 RGB', 140, 'RGB', 49),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'SP120 RGB', 120, 'RGB', 29),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'SP140 RGB', 140, 'RGB', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'ML120 Pro RGB', 120, 'RGB', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'ML140 Pro RGB', 140, 'RGB', 44),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NF-A12x25 PWM', 120, 'Standard', 29),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NF-A14 PWM', 140, 'Standard', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NF-F12 PWM', 120, 'Standard', 24),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NF-P12 PWM', 120, 'Standard', 19),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterFan MF120R RGB', 120, 'RGB', 29),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterFan MF140R RGB', 140, 'RGB', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterFan Pro 120 Air Balance RGB', 120, 'RGB', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterFan Pro 140 Air Balance RGB', 140, 'RGB', 44),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Riing Plus 12 RGB', 120, 'RGB', 29),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Riing Plus 14 RGB', 140, 'RGB', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pure 12 ARGB Sync Radiator Fan TT Premium Edition', 120, 'ARGB', 24),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pure 14 ARGB Sync Radiator Fan TT Premium Edition', 140, 'ARGB', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Aer RGB 2 120mm', 120, 'RGB', 29),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Aer RGB 2 140mm', 140, 'RGB', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Phanteks'), 'SK120 PWM D-RGB', 120, 'D-RGB', 19),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Phanteks'), 'SK140 PWM D-RGB', 140, 'D-RGB', 24),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'iCUE QL120 RGB', 120, 'RGB', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'iCUE QL140 RGB', 140, 'RGB', 44),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'Silent Wings 3 120mm PWM', 120, 'Standard', 19);

CREATE TABLE IF NOT EXISTS cpu_cooler (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  form VARCHAR(10),
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO cpu_cooler (brandID, name, form, price) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'Hyper 212 RGB Black Edition', 'Air', 49),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'Hyper 212 EVO', 'Air', 34),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NH-D15', 'Air', 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NH-U12S', 'Air', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'H100i RGB Platinum', 'Liquid', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'H115i RGB Platinum', 'Liquid', 169),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'H150i RGB Platinum', 'Liquid', 189),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Kraken X53', 'Liquid', 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Kraken X63', 'Liquid', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Kraken Z73', 'Liquid', 249),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'Dark Rock Pro 4', 'Air', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'Dark Rock 4', 'Air', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Floe Riing RGB 360 TT Premium Edition', 'Liquid', 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Water 3.0 360 ARGB Sync', 'Liquid', 179),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML240R RGB', 'Liquid', 119),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML360R RGB', 'Liquid', 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NH-L9i', 'Air', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Noctua'), 'NH-L12S', 'Air', 49),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterAir MA410M', 'Air', 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterAir MA620P', 'Air', 69),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'A500 Dual Fan', 'Air', 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'H60', 'Liquid', 69),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'NZXT'), 'Kraken M22', 'Liquid', 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'Pure Rock 2', 'Air', 39),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Be_quiet'), 'Shadow Rock 3', 'Air', 59);

CREATE TABLE IF NOT EXISTS radiator (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  size INT, -- in mm
  price INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand (ID)
);

INSERT INTO radiator (brandID, name, size, price) VALUES
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Hydro X Series XR5 240mm', 240, 64),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Hydro X Series XR5 360mm', 360, 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Hydro X Series XR7 240mm', 240, 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Hydro X Series XR7 360mm', 360, 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pacific CL360 Max', 360, 149),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pacific CL480 Max', 480, 199),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pacific RL360', 360, 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Thermaltake'), 'Pacific RL480', 480, 159),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'EKWB'), 'EK-CoolStream Classic SE 240', 240, 59),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'EKWB'), 'EK-CoolStream Classic SE 360', 360, 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'EKWB'), 'EK-CoolStream PE 240', 240, 79),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'EKWB'), 'EK-CoolStream PE 360', 360, 109),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Alphacool'), 'NexXxoS ST30 Full Copper 240mm', 240, 64),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Alphacool'), 'NexXxoS ST30 Full Copper 360mm', 360, 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Alphacool'), 'NexXxoS XT45 Full Copper 240mm', 240, 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Alphacool'), 'NexXxoS XT45 Full Copper 360mm', 360, 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Hardware Labs'), 'Black Ice Nemesis 240GTS', 240, 69),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Hardware Labs'), 'Black Ice Nemesis 360GTS', 360, 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Hardware Labs'), 'Black Ice Nemesis 240GTX', 240, 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Hardware Labs'), 'Black Ice Nemesis 360GTX', 360, 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML240L RGB', 240, 64),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML360L RGB', 360, 99),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML240R RGB', 240, 89),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Cooler_master'), 'MasterLiquid ML360R RGB', 360, 129),
  ((SELECT brand.ID FROM brand WHERE brand.name = 'Corsair'), 'Hydro X Series XR5 480mm', 480, 129);

CREATE TABLE IF NOT EXISTS user_owned_config (
  userID INT NOT NULL,
  configID INT NOT NULL AUTO_INCREMENT,
  mainboard INT,
  ram INT,
  cpu INT,
  gpu INT,
  psu INT,
  ccase INT,
  hdd INT,
  ssd INT,
  fan INT,
  radiator INT,
  cpu_cooler INT,
  PRIMARY KEY (configID),
  FOREIGN KEY (userID) REFERENCES user(userID),
  FOREIGN KEY (mainboard) REFERENCES mainboard(ID),
  FOREIGN KEY (ram) REFERENCES ram(ID),
  FOREIGN KEY (cpu) REFERENCES cpu(ID),
  FOREIGN KEY (gpu) REFERENCES gpu(ID),
  FOREIGN KEY (psu) REFERENCES psu(ID),
  FOREIGN KEY (ccase) REFERENCES ccase(ID),
  FOREIGN KEY (hdd) REFERENCES hdd(ID),
  FOREIGN KEY (ssd) REFERENCES ssd(ID),
  FOREIGN KEY (fan) REFERENCES fan(ID),
  FOREIGN KEY (radiator) REFERENCES radiator(ID),
  FOREIGN KEY (cpu_cooler) REFERENCES cpu_cooler(ID)
);
CREATE USER IF NOT EXISTS admin IDENTIFIED BY 'verysafepassword';
CREATE USER IF NOT EXISTS guest IDENTIFIED BY 'password';
-- REVOKE DROP ON PC_Builder.* From guest;
