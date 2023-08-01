--liquibase formatted sql

-- Changeset solyanik:createCarTable
CREATE TABLE `cars` (
                        `daily_fee` decimal(38,2) DEFAULT NULL,
                        `inventory` int DEFAULT NULL,
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `brand` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                        `car_type` enum('HATCHBACK','SEDAN','SUV','UNIVERSAL') COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                        `model` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
