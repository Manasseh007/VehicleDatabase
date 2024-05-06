CREATE DATABASE IF NOT EXISTS smart_vehicle_tracking;

USE smart_vehicle_tracking;

CREATE TABLE IF NOT EXISTS vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL,
    registration_number VARCHAR(20) NOT NULL,
    vehicle_location VARCHAR(50) NOT NULL,
    year INT NOT NULL
);
-- Locations table to store real-time vehicle locations
CREATE TABLE Locations (
    location_id INT PRIMARY KEY,
    vehicle_id INT,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    timestamp DATETIME,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicles(vehicle_id)
);
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE authorities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users (username, password)
VALUES ('John', '{noop}test123');

INSERT INTO users (username, password)
VALUES ('Meluleki', '{noop}mel123');

INSERT INTO users (username, password)
VALUES ('Terrence', '{noop}abc123');

INSERT INTO authorities (username, authority)
VALUES ('John', 'ROLE_USER');

INSERT INTO authorities (username, authority)
VALUES ('Meluleki', 'ADMIN_USER');

INSERT INTO authorities (username, authority)
VALUES ('Terrence', 'ROLE_USER');

INSERT INTO vehicles (make, model, registration_date, registration_number, vehicle_location, year)
VALUES ('Toyota', 'Camry', '2024-01-15', 'NUZ 2123','Gauteng', 2020);

INSERT INTO vehicles (make, model, registration_date, registration_number, vehicle_location, year)
VALUES ('Honda', 'Accord', '2023-05-20', 'NND 6789', 'Cape Town', 2018);

INSERT INTO vehicles (make, model, registration_date, registration_number, vehicle_location, year)
VALUES ('LandRover', 'Defender', '2024-03-31', 'NDW 9963', 'Johannesburg', 2022);

INSERT INTO vehicles (make, model, registration_date, registration_number, vehicle_location, year)
VALUES ('Ford', 'EcoSport', '2023-12-22', 'XYZ789', 'Durban', 2024);

