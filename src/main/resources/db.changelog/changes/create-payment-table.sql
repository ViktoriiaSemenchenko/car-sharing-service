--liquibase formatted sql

-- Changeset solyanik:createPaymentsTable
CREATE TABLE payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          status ENUM('PENDING', 'PAID') NOT NULL,
                          type ENUM('PAYMENT', 'FINE') NOT NULL,
                          rentalId BIGINT,
                          sessionUrl VARCHAR(255),
                          sessionId VARCHAR(255),
                          amountToPay DECIMAL(10, 2) NOT NULL,
                          CONSTRAINT FK_Payment_Rental FOREIGN KEY (rentalId) REFERENCES rentals(id)
);

