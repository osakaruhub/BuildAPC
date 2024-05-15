CREATE SCHEMA IF NOT EXISTS PC_Builder;

USE PC_Builder;

CREATE TABLE IF NOT EXISTS brand {
  ID INT NOT NULL,
  name VARCHAR(20)
}

CREATE TABLE IF NOT EXISTS user {
  userID INT NOT NULL,
  name VARCHAR(30),
  password VARCHAR(25),
}

CREATE TABLE IF NOT EXISTS mainboard (
  ID INT NOT NULL,
  Chipset INT,
  form VARCHAR,                  -- "<int>x<int>"
  ethernet_speed INT,            -- in MB/s
  IO VARCHAR[],                  -- USB 2.0, USB 3.0, 
  Other_Features OBJECT
);

CREATE TABLE IF NOT EXISTS cpu (
  ID INT NOT NULL,
  brand VARCHAR,
  line VARCHAR,
  name VARCHAR,
  core_count INT,
  thread_count INT,
  clock_speed INT, 
  form VARCHAR
  tdp INT,
  thermals VARCHAR,
);

CREATE TABLE IF NOT EXISTS gpu (
  ID INT NOT NULL,
  brand VARCHAR,
  FOREIGN KEY(brand) REFERENCES brand(ID)
  line VARCHAR,
  name VARCHAR,
  clock_speed INT, 
  IO VARCHAR[]                   -- HDMI, DP
);

CREATE TABLE IF NOT EXISTS ram (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  clock_speed INT, 
  timing INT,
  type VARCHAR,
);

CREATE TABLE IF NOT EXISTS psu (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  wattage INT,
  type VARCHAR                   -- modular, static
);

CREATE TABLE IF NOT EXISTS hdd (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  storage INT,                   -- in MB
  rpm INT
);

CREATE TABLE IF NOT EXISTS ssd (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  storage INT,                   -- in MB
  type VARCHAR,
);

CREATE TABLE IF NOT EXISTS ccase (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  form VARCHAR,
  coolers_max INT
);

CREATE TABLE IF NOT EXISTS fan (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  size INT,                      -- in mm
  type VARCHAR                   -- rgb
);

CREATE TABLE IF NOT EXISTS cpu_cooler (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  form VARCHAR
);

CREATE TABLE IF NOT EXISTS radiator (
  ID INT NOT NULL,
  brand VARCHAR,
  name VARCHAR,
  size INT                       -- in mm 
);
