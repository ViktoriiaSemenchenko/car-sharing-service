--liquibase formatted sql

-- Changeset solyanik:createRentalsTable
CREATE TABLE rentals (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         rentalDate DATE,
                         returnDate DATE,
                         actualReturnDate DATE,
                         carId BIGINT,
                         userId BIGINT,
                         CONSTRAINT FK_Car_Rental FOREIGN KEY (carId) REFERENCES cars(id),
                         CONSTRAINT FK_User_Rental FOREIGN KEY (userId) REFERENCES users(id)
);