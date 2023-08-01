--liquibase formatted sql

-- Changeset solyanik:createUsersTable
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `telegram_id` bigint DEFAULT NULL,
                         `email` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                         `first_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                         `last_name` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                         `password` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                         `role` enum('CUSTOMER','MANAGER') COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
