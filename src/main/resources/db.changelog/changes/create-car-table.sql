--liquibase formatted sql

-- Changeset solyanik:createCarTable
CREATE TABLE cars (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      model ENUM('SEDAN', 'SUV', 'HATCHBACK', 'UNIVERSAL') NOT NULL,
                      brand VARCHAR(255) NOT NULL,
                      inventory INT NOT NULL,
                      dailyFee DECIMAL(10, 2) NOT NULL,
                      type VARCHAR(50) NOT NULL,
                      user_id BIGINT,
                      rental_id BIGINT,
                      CONSTRAINT FK_User_Car FOREIGN KEY (user_id) REFERENCES users(id),
                      CONSTRAINT FK_Rental_Car FOREIGN KEY (rental_id) REFERENCES rentals(id)
);