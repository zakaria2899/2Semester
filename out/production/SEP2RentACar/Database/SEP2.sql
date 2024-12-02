DROP SCHEMA IF EXISTS rentacar CASCADE;

CREATE SCHEMA rentacar;

SET SCHEMA 'rentacar';

-- Customer table
CREATE TABLE customer(
                         cpr CHAR(11) PRIMARY KEY,
                         firstname VARCHAR(300),
                         surname VARCHAR(300),
                         address VARCHAR(300),
                         city VARCHAR(300),
                         zip_code SMALLINT,
                         email VARCHAR(150),
                         phone_no CHAR(8),
                         DoB DATE
);
CREATE TABLE location(
                         location varchar(50) primary key,
                         numberOfCars smallint default 0
);

-- Car table
CREATE TABLE car(
                    licensePlate CHAR(7) PRIMARY KEY,
                    brand VARCHAR(50),
                    model VARCHAR(50),
                    description VARCHAR(500),
                    fuelType varchar(20),
                    price DOUBLE PRECISION,
                    year SMALLINT,
                    seating SMALLINT,
                    greenlevel SMALLINT,
                    currentLocation VARCHAR(50),
                    FOREIGN KEY (currentLocation) references location(location) ON DELETE SET NULL
);

-- Reservation table
CREATE TABLE reservation(
                            reservationID SERIAL PRIMARY KEY,
                            car varchar(7),
                            customerID Char(11),
                            start_date DATE,
                            end_date DATE,
                            pick_up VARCHAR(50),
                            drop_off VARCHAR(50),
                            foreign key (car) references car(licensePlate) ON DELETE CASCADE ,
                            foreign key (customerID) references customer(cpr) ON DELETE CASCADE ,
                            foreign key (pick_up) references location(location) ON DELETE SET NULL ,
                            foreign key (drop_off) references location(location) ON DELETE SET NULL
);

CREATE OR REPLACE FUNCTION update_car_count()
    RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        UPDATE location SET numberofcars = numberofcars + 1 WHERE location = NEW.currentlocation;
        RETURN NEW;
    ELSIF (TG_OP = 'DELETE') THEN
        UPDATE location SET numberofcars = numberofcars - 1 WHERE location = OLD.currentlocation;
    ELSIF (TG_OP = 'UPDATE') THEN
        IF OLD.currentlocation IS NOT NULL THEN
            UPDATE location SET numberofcars = numberofcars - 1 WHERE location = OLD.currentlocation;
        END IF;
        IF NEW.currentlocation IS NOT NULL THEN
            UPDATE location SET numberofcars = numberofcars + 1 WHERE location = NEW.currentlocation;
        END IF;
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Trigger for inserting a car
CREATE TRIGGER car_insert_trigger
    AFTER INSERT ON car
    FOR EACH ROW
EXECUTE FUNCTION update_car_count();

-- Trigger for deleting a car
CREATE TRIGGER car_delete_trigger
    AFTER DELETE ON car
    FOR EACH ROW
EXECUTE FUNCTION update_car_count();

-- Trigger for updating a car
CREATE TRIGGER car_update_trigger
    AFTER UPDATE ON car
    FOR EACH ROW
EXECUTE FUNCTION update_car_count();


