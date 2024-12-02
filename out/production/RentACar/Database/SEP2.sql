DROP SCHEMA IF EXISTS RentACar CASCADE;

CREATE SCHEMA RentACar;

SET SCHEMA 'rentacar';

-- Creating enum types
CREATE TYPE sustainability AS ENUM ('Ja', 'Nej', 'N/A');
CREATE TYPE state AS ENUM ('Tilgængelig', 'Reserveret');


-- Customer table
CREATE TABLE Customer(
    customerID SMALLINT PRIMARY KEY,
    firstname VARCHAR(300),
    surname VARCHAR(300),
    DoB DATE,
    cpr SMALLINT,
    address VARCHAR(300),
    city VARCHAR(300),
    phone_no CHAR(8)
);


-- Department table
CREATE TABLE Department(
    departmentID SMALLINT PRIMARY KEY,
    city VARCHAR(300),
    address VARCHAR(300)
);


-- Car table
CREATE TABLE Car(
    carID SMALLINT PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    description VARCHAR(500),
    price DOUBLE PRECISION,
    year SMALLINT,
    milage SMALLINT,
    seating SMALLINT,
    greenlevel SMALLINT,
    -- Uses the set enum types
    sustainability sustainability,
    state state,
    reserved_by SMALLINT,
    rent_by SMALLINT NULL,
    FOREIGN KEY (reserved_by) REFERENCES Customer(customerID),
    FOREIGN KEY (rent_by) REFERENCES Department(departmentID),
    CONSTRAINT check_reserved_by CHECK(
        (state = 'Tilgængelig' AND reserved_by IS NULL) OR
        (state = 'Reserveret' AND reserved_by IS NOT NULL)
    )
);


-- Reservation table
CREATE TABLE Reservation(
    reservationID SMALLINT PRIMARY KEY,
    carID INT,
    CustomerID INT,
    start_date DATE,
    end_date DATE,
    estPrice SMALLINT,
    FOREIGN KEY (carID) REFERENCES Car(carID),
    FOREIGN KEY (CustomerID) REFERENCES Customer(customerID)
);