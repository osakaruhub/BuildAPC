CREATE SCHEMA IF NOT EXISTS PC_Builder;

USE PC_Builder;

CREATE TABLE IF NOT EXISTS mainboard (
  Chipset INT,
  form VARCHAR,
  ethernet_speed INT,            -- in MB/s
  IO VARCHAR[],                  -- USB 2.0, USB 3.0, 
  Other_Features OBJECT
);

CREATE TABLE IF NOT EXISTS cpu (
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
  brand VARCHAR,
  line VARCHAR,
  name VARCHAR,
  clock_speed INT, 
  IO VARCHAR[]                   -- HDMI, DP
);

CREATE TABLE IF NOT EXISTS ram (
  brand VARCHAR,
  name VARCHAR,
  clock_speed INT, 
  timing INT,
  type VARCHAR,
);

CREATE TABLE IF NOT EXISTS psu (
  brand VARCHAR,
  name VARCHAR,
  wattage INT,
  type VARCHAR                   -- modular, static
);

CREATE TABLE IF NOT EXISTS hdd (
  brand VARCHAR,
  name VARCHAR,
  storage INT,                   -- in MB
  rpm INT
);

CREATE TABLE IF NOT EXISTS ssd (
  brand VARCHAR,
  name VARCHAR,
  storage INT,                   -- in MB
  type VARCHAR,
);

CREATE TABLE IF NOT EXISTS case (
  brand VARCHAR,
  name VARCHAR,
  form VARCHAR,
  coolers_max INT
);

CREATE TABLE IF NOT EXISTS fan (
  brand VARCHAR,
  name VARCHAR,
  size INT,                      -- in mm
  type VARCHAR                   -- rgb
);

CREATE TABLE IF NOT EXISTS cpu_cooler (
  brand VARCHAR,
  name VARCHAR,
  form VARCHAR
);

CREATE TABLE IF NOT EXISTS radiator (
  brand VARCHAR,
  name VARCHAR,
  size INT                       -- in mm 
);
