-- Smart Parking Management System Database Schema

-- 1. Create Tariff table
CREATE TABLE IF NOT EXISTS tariff (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL UNIQUE,
                                      cost INT NOT NULL CHECK (cost > 0)
);

-- 2. Create Parking Spot table
CREATE TABLE IF NOT EXISTS parking_spot (
                                            id SERIAL PRIMARY KEY,
                                            tariff_id INT NOT NULL,
                                            is_reserved BOOLEAN NOT NULL DEFAULT FALSE,
                                            CONSTRAINT fk_tariff
                                                FOREIGN KEY (tariff_id)
                                                    REFERENCES tariff(id)
);

-- 3. Create Vehicles table
CREATE TABLE IF NOT EXISTS vehicles (
                                        id SERIAL PRIMARY KEY,
                                        licensePlate VARCHAR(20) UNIQUE NOT NULL
);

-- 4. Create Reservation table
CREATE TABLE reservation (
                             id SERIAL PRIMARY KEY,
                             vehicle_id INT NOT NULL,
                             parking_spot_id INT NOT NULL,
                             start_time TIMESTAMP NOT NULL,
                             end_time TIMESTAMP
);

-- CREATE TABLE reservation (
--                              id SERIAL PRIMARY KEY,
--                              vehicle_id INT NOT NULL,
--                              parking_spot_id INT NOT NULL,
--                              start_time TIMESTAMP NOT NULL,
--                              end_time TIMESTAMP,
--                              is_active BOOLEAN DEFAULT TRUE,
--
--                              CONSTRAINT fk_vehicle
--                                  FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
--
--                              CONSTRAINT fk_parking_spot
--                                  FOREIGN KEY (parking_spot_id) REFERENCES parking_spot(id)
-- );


-- CREATE TABLE IF NOT EXISTS reservation (
--                                            id SERIAL PRIMARY KEY,
--                                            "from" TIMESTAMP NOT NULL,
--                                            "to" TIMESTAMP NULL,
--                                            vehicle_id INT NOT NULL,
--                                            parking_spot_id INT NOT NULL,
--                                            CONSTRAINT fk_vehicle
--                                                FOREIGN KEY (vehicle_id)
--                                                    REFERENCES vehicles(id),
--                                            CONSTRAINT fk_parking_spot
--                                                FOREIGN KEY (parking_spot_id)
--                                                    REFERENCES parking_spot(id)
-- );

-- Insert sample tariffs




INSERT INTO tariff (name, cost) VALUES
                                    ('Hourly', 500),
                                    ('Daily', 3000),
                                    ('VIP', 1000)
ON CONFLICT (name) DO NOTHING;

-- ALTER TABLE parking_spot
--     ADD COLUMN is_reserved BOOLEAN NOT NULL DEFAULT FALSE;

INSERT INTO parking_spot (tariff_id, is_reserved)
SELECT 1, false FROM generate_series(1, 10)
ON CONFLICT DO NOTHING;

INSERT INTO parking_spot (tariff_id, is_reserved)
SELECT 2, false FROM generate_series(1, 5)
ON CONFLICT DO NOTHING;

INSERT INTO parking_spot (tariff_id, is_reserved)
SELECT 3, false FROM generate_series(1, 3)
ON CONFLICT DO NOTHING;

