CREATE SCHEMA IF NOT EXISTS PC_Builder;

USE PC_Builder;

CREATE TABLE IF NOT EXISTS brand (
  ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE (name)
);

INSERT INTO brand (name) VALUES
('PowerColor'),
('ASUS'),
('Zotac'),
('MSI'),
('XFX'),
('Gigabyte'),
('Intel'),
('Palit'),
('Gainward'),
('ASRock'),
('NVIDIA'),
('AMD');

CREATE TABLE IF NOT EXISTS user (
  userID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  password VARCHAR(25) NOT NULL,
  PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS mainboard (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  chipset INT,
  form VARCHAR(10),                  -- "<int>x<int>"
  ethernet_speed INT,                -- in MB/s
  IO VARCHAR(255),                   -- CSV for ports e.g., "USB 2.0, USB 3.0"
  other_features TEXT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
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
  thermals VARCHAR(255),
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS gpu (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  line VARCHAR(50),
  name VARCHAR(50),
  clock_speed INT, 
  IO VARCHAR(255),                   -- CSV for ports e.g., "HDMI, DP"
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

INSERT INTO gpu (brandID, name, clock_speed, IO) VALUES
((SELECT ID FROM brand WHERE name='PowerColor'), 'Radeon RX 7800 XT', 2210, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'GeForce RTX 4080 SUPER OC Edition', 2505, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Zotac'), 'GeForce RTX 4080 SUPER Trinity Black Edition', 2565, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='MSI'), 'GeForce RTX 4080 16GB Gaming Trio', 2505, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='XFX'), '310 Radeon RX 7900 XT', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Zotac'), 'GeForce RTX 4070 Ti AMP Extreme AIRO', 2700, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'GeForce RTX 4090 White OC Edition', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Gigabyte'), 'RTX 4080 SUPER Gaming OC 16G', 2535, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'GeForce RTX 4090 OC Edition', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Gigabyte'), 'RTX 4090 Gaming OC 24G', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'Radeon RX 7900 XTX OC', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='MSI'), 'RTX 4080 SUPER 16GB Gaming X Trio', 2505, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'Radeon RX 7800 XT OC', 2210, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Gigabyte'), 'Radeon RX 7900 XTX Elite 24G', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASUS'), 'Radeon RX 7800 XT OC Edition', 2210, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Zotac'), 'GeForce RTX 4090 Trinity OC', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='PowerColor'), 'RX 7900 XTX Hellhound', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='AMD'), 'Radeon RX 7900 XTX', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='MSI'), 'RTX 4090 Suprim X 24G', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Zotac'), 'GeForce RTX 4070 Ti Trinity', 2700, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Intel'), 'A770 Limited Edition', 2100, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Palit'), 'RTX 4090 GameRock OC', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Gainward'), 'RTX 4070 Ti SUPER Panther OC', 2700, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='MSI'), 'RTX 4070 Ti Gaming X Trio 12G', 2700, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='ASRock'), 'RX 7900 XTX Taichi 24GB OC', 2300, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='Gigabyte'), 'GeForce RTX 4090 Master 24G', 2610, 'HDMI, DP'),
((SELECT ID FROM brand WHERE name='MSI'), 'RTX 4090 Gaming X Trio 24G', 2610, 'HD'))

CREATE TABLE IF NOT EXISTS ram (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  clock_speed INT, 
  timing INT,
  type VARCHAR(50),
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS psu (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  wattage INT,
  type VARCHAR(50),                  -- modular, static
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS hdd (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  storage INT,                       -- in MB
  rpm INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS ssd (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  storage INT,                       -- in MB
  type VARCHAR(50),
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS case (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  form VARCHAR(10),
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

-- Step 1: Add entries for the PC cases
INSERT INTO ccase (brandID, name, form) VALUES
(1, 'CORSAIR 6500D Airflow Mid-Tower Dual Chamber - Black', 'Mid-Tower'),
(1, 'CORSAIR 6500D Airflow Mid-Tower Dual Chamber - White', 'Mid-Tower'),
(1, 'CORSAIR 5000D CORE Airflow Mid-Tower ATX PC Case - White', 'Mid-Tower'),
(1, 'CORSAIR 5000D CORE Airflow Mid-Tower ATX PC Case - Black', 'Mid-Tower'),
(1, 'CORSAIR 3000D AIRFLOW Mid-Tower PC Case - White', 'Mid-Tower'),
(1, 'CORSAIR 3000D RGB AIRFLOW Mid-Tower PC Case - Black', 'Mid-Tower'),
(1, 'CORSAIR 3000D RGB AIRFLOW Mid-Tower PC Case - White', 'Mid-Tower'),
(1, 'CORSAIR 3000D AIRFLOW Mid-Tower PC Case - Black', 'Mid-Tower'),
(1, 'CORSAIR iCUE 4000D RGB AIRFLOW Mid-Tower Case, True White', 'Mid-Tower'),
(1, 'CORSAIR 4000D Airflow Tempered Glass Mid-Tower - Black', 'Mid-Tower'),
(1, 'CORSAIR iCUE 5000D RGB AIRFLOW Mid-Tower Case, Black', 'Mid-Tower'),
(1, 'CORSAIR iCUE 5000D RGB AIRFLOW Mid-Tower Case, True White', 'Mid-Tower'),
(1, 'CORSAIR 5000D Airflow Tempered Glass Mid-Tower - Black', 'Mid-Tower'),
(1, 'CORSAIR iCUE 5000T RGB Tempered Glass Mid-Tower ATX PC Case - Black', 'Mid-Tower'),
(1, 'CORSAIR iCUE 5000T RGB Tempered Glass Mid-Tower ATX PC Case - White', 'Mid-Tower'),
(1, 'CORSAIR 7000D Airflow Tempered Glass Full-Tower - Black', 'Full-Tower'),
(1, 'CORSAIR Obsidian Series 1000D Super-Tower Case', 'Super-Tower'),
(2, 'Antec DF700 Flux', 'Mid-Tower'),
(2, 'Antec P120 Crystal', 'Mid-Tower'),
(2, 'ASUS ROG Strix Helios', 'Mid-Tower'),
(2, 'ASUS TUF Gaming GT501', 'Mid-Tower'),
(2, 'be quiet! Dark Base Pro 900 Black rev. 2', 'Full-Tower'),
(2, 'be quiet! Pure Base 500DX Black', 'Mid-Tower'),
(2, 'be quiet! Silent Base 802 Window Black', 'Mid-Tower'),
(2, 'Cooler Master Cosmos C700M', 'Full-Tower'),
(2, 'Cooler Master MasterBox TD500 Mesh White', 'Mid-Tower'),
(2, 'Cooler Master MasterCase H500M', 'Mid-Tower'),
(2, 'Fractal Design Define 7 Dark Tempered Glass Black', 'Mid-Tower'),
(2, 'Fractal Design Meshify 2 Black', 'Mid-Tower'),
(2, 'Fractal Design Meshify 2 XL Light Tempered Glass Black', 'Mid-Tower'),
(2, 'Lian Li Lancool II Mesh Performance', 'Mid-Tower'),
(2, 'Lian Li Lancool III Black', 'Mid-Tower'),
(2, 'Lian Li O11 Air Mini Black 7-slot', 'Mid-Tower'),
(2, 'Lian Li PC-O11 Dynamic Black', 'Mid-Tower'),
(2, 'Lian Li O11 Dynamic EVO Black', 'Mid-Tower'),
(2, 'Lian Li O11 Dynamic EVO White', 'Mid-Tower'),
(2, 'Lian Li PC-O11 Dynamic Mini Black 7-slot', 'Mid-Tower'),
(2, 'Lian Li PC-O11 Dynamic XL Black', 'Mid-Tower'),
(2, 'Lian Li PC-O11 Dynamic XL Silver', 'Mid-Tower'),
(2, 'MSI MPG Gungnir 110R', 'Mid-Tower'),
(2, 'MSI MPG Velox 100R', 'Mid-Tower'),
(2, 'NZXT H710i Matte Black', 'Mid-Tower'),
(2, 'NZXT H710i Matte White', 'Mid-Tower'),
(2, 'Phanteks Eclipse P360A Black', 'Mid-Tower'),
(2, 'Phanteks Enthoo 719 Satin Black', 'Full-Tower'),
(2, 'Phanteks Eclipse P500A DRGB Black', 'Mid-Tower'),
(2, 'Thermaltake Core P3 Tempered Glass Edition', 'Mid-Tower'),
(2, 'Thermaltake Divider 500 TG ARGB', 'Mid-Tower'),
(2, 'Thermaltake The Tower 900', 'Full-Tower'),
(2, 'Thermaltake View 51 Tempered Glass Snow ARGB Edition', 'Full-Tower'),
(2, 'Thermaltake View 71 Tempered Glass RGB Edition', 'Full-Tower'),
(1, 'CORSAIR 2500D Airflow Micro ATX Dual Chamber - Black', 'Micro ATX'),
(1, 'CORSAIR 2500D Airflow Micro ATX Dual Chamber - White', 'Micro ATX'),
(1, 'CORSAIR 2000D AIRFLOW Mini-ITX PC Case - White', 'Mini ITX'),
(1, 'CORSAIR 2000D AIRFLOW Mini-ITX PC Case - Black', 'Mini ITX'),
(1, 'CORSAIR 2000D RGB AIRFLOW Mini-ITX PC Case - Black', 'Mini ITX'),
(1, 'CORSAIR 2000D RGB AIRFLOW Mini-ITX PC Case - White', 'Mini ITX');

-- Step 2: Create a new table for the case query results
CREATE TABLE


CREATE TABLE IF NOT EXISTS fan (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  size INT,                          -- in mm
  type VARCHAR(50),                  -- rgb
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS cpu_cooler (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  form VARCHAR(10),
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS radiator (
  ID INT NOT NULL AUTO_INCREMENT,
  brandID INT NOT NULL,
  name VARCHAR(50),
  size INT,                          -- in mm 
  PRIMARY KEY (ID),
  FOREIGN KEY (brandID) REFERENCES brand(ID)
);

CREATE TABLE IF NOT EXISTS gpu_query (
  queryID INT NOT NULL AUTO_INCREMENT,
  query_text TEXT NOT NULL,
  query_result TEXT,
  PRIMARY KEY (queryID)
);



-- Assuming AMD has ID 1 and Intel has ID 2
INSERT INTO cpu (brandID, line, name, core_count, thread_count, clock_speed, form, tdp, thermals) VALUES
(1, 'Ryzen 5', '7600X', 6, 12, 3800, 'AM4', 105, 'Not Specified'),
(1, 'Ryzen 7', '7700X', 8, 16, 3800, 'AM4', 105, 'Not Specified'),
(1, 'Ryzen 7', '7800X3D', 8, 16, 3400, 'AM4', 105, 'Not Specified'),
(1, 'Ryzen 9', '7900X', 12, 24, 3700, 'AM4', 170, 'Not Specified'),
(1, 'Ryzen 9', '7950X', 16, 32, 4400, 'AM4', 170, 'Not Specified'),
(2, 'Core i5', '13600K', 6, 12, 3400, 'LGA 1700', 125, 'Not Specified'),
(2, 'Core i7', '13700K', 8, 16, 3600, 'LGA 1700', 125, 'Not Specified'),
(2, 'Core i9', '13900K', 8, 16, 3500, 'LGA 1700', 125, 'Not Specified');


