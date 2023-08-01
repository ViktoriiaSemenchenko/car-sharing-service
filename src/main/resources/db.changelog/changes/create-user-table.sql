--liquibase formatted sql

-- Changeset solyanik:createUsersTable
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL,
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('MANAGER', 'CUSTOMER') NOT NULL,
                CONSTRAINT FK_Rental_User FOREIGN KEY (userId) REFERENCES users(id);
);