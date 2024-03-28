BEGIN TRANSACTION;

DROP TABLE IF EXISTS location_logs CASCADE;
DROP TABLE IF EXISTS emergency_contacts CASCADE;
DROP TABLE IF EXISTS service_types CASCADE;
DROP TABLE IF EXISTS user_feedback CASCADE;
DROP TABLE IF EXISTS payments CASCADE;
DROP TABLE IF EXISTS driver_schedules CASCADE;
DROP TABLE IF EXISTS rides CASCADE;
DROP TABLE IF EXISTS drivers CASCADE;
DROP TABLE IF EXISTS vehicles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Recreate tables
CREATE TABLE users (
     user_id SERIAL,
     username VARCHAR(50) NOT NULL UNIQUE,
     password_hash VARCHAR(200) NOT NULL,
     role VARCHAR(50) NOT NULL,
     CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE vehicles (
      vehicle_id SERIAL,
      make VARCHAR(50) NOT NULL,
      model VARCHAR(50) NOT NULL,
      year INT NOT NULL,
      license_plate VARCHAR(50) NOT NULL UNIQUE,
      status VARCHAR(50) NOT NULL,
      capacity INT NOT NULL,
      CONSTRAINT PK_vehicle PRIMARY KEY (vehicle_id)
);

CREATE TABLE drivers (
       driver_id SERIAL,
       user_id INT NOT NULL UNIQUE,
       license_number VARCHAR(50) NOT NULL,
       vehicle_id INT,
       status VARCHAR(50) NOT NULL,
       join_date TIMESTAMP NOT NULL,
       CONSTRAINT PK_driver PRIMARY KEY (driver_id),
       CONSTRAINT FK_driver_user FOREIGN KEY (user_id) REFERENCES users(user_id),
       CONSTRAINT FK_driver_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

CREATE TABLE rides (
      ride_id SERIAL,
      user_id INT NOT NULL,
      driver_id INT,
      vehicle_id INT,
      pickup_location TEXT NOT NULL,
      dropoff_location TEXT NOT NULL,
      scheduled_pickup_time TIMESTAMP NOT NULL,
      actual_pickup_time TIMESTAMP,
      dropoff_time TIMESTAMP,
      status VARCHAR(50) NOT NULL,
      fare_estimate DECIMAL(10, 2),
      actual_fare DECIMAL(10, 2),
      CONSTRAINT PK_ride PRIMARY KEY (ride_id),
      CONSTRAINT FK_ride_user FOREIGN KEY (user_id) REFERENCES users(user_id),
      CONSTRAINT FK_ride_driver FOREIGN KEY (driver_id) REFERENCES drivers(driver_id),
      CONSTRAINT FK_ride_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);

CREATE TABLE driver_schedules (
      schedule_id SERIAL PRIMARY KEY,
      driver_id INT NOT NULL,
      start_date_time TIMESTAMP NOT NULL,
      end_date_time TIMESTAMP NOT NULL,
      status VARCHAR(50) NOT NULL,
      CONSTRAINT FK_schedule_driver FOREIGN KEY (driver_id) REFERENCES drivers(driver_id),
      CONSTRAINT CHK_schedule_time CHECK (end_date_time > start_date_time),
      CONSTRAINT CHK_schedule_status CHECK (status IN ('Available', 'On Leave'))
);

CREATE TABLE payments (
       payment_id SERIAL,
       ride_id INT NOT NULL,
       user_id INT NOT NULL,
       amount DECIMAL(10, 2) NOT NULL,
       payment_date TIMESTAMP NOT NULL,
       payment_method VARCHAR(50) NOT NULL,
       status VARCHAR(50) NOT NULL,
       CONSTRAINT PK_payment PRIMARY KEY (payment_id),
       CONSTRAINT FK_payment_ride FOREIGN KEY (ride_id) REFERENCES rides(ride_id),
       CONSTRAINT FK_payment_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_feedback (
      feedback_id SERIAL,
      ride_id INT NOT NULL,
      user_id INT NOT NULL,
      rating INT NOT NULL,
      comment TEXT,
      date_submitted TIMESTAMP NOT NULL,
      CONSTRAINT PK_user_feedback PRIMARY KEY (feedback_id),
      CONSTRAINT FK_feedback_ride FOREIGN KEY (ride_id) REFERENCES rides(ride_id),
      CONSTRAINT FK_feedback_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE service_types (
      service_type_id SERIAL,
      description VARCHAR(100) NOT NULL,
      CONSTRAINT PK_service_type PRIMARY KEY (service_type_id)
);

CREATE TABLE emergency_contacts (
        contact_id SERIAL,
        user_id INT NOT NULL,
        name VARCHAR(100) NOT NULL,
        phone_number VARCHAR(50) NOT NULL,
        relationship VARCHAR(50),
        CONSTRAINT PK_emergency_contact PRIMARY KEY (contact_id),
        CONSTRAINT FK_emergency_contact_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE location_logs (
        log_id SERIAL,
        ride_id INT NOT NULL,
        timestamp TIMESTAMP NOT NULL,
        latitude DECIMAL(9, 6) NOT NULL,
        longitude DECIMAL(9, 6) NOT NULL,
        CONSTRAINT PK_location_log PRIMARY KEY (log_id),
        CONSTRAINT FK_location_log_ride FOREIGN KEY (ride_id) REFERENCES rides(ride_id)
);

COMMIT TRANSACTION;
