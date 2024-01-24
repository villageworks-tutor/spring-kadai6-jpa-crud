DROP DATABASE IF EXISTS spring_sample;
DROP USER IF EXISTS student;
CREATE USER student WITH PASSWORD 'himitu';
CREATE DATABASE spring_sample OWNER student ENCODING 'utf8';