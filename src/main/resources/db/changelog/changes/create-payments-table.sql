--liquibase formatted sql

-- Changeset solyanik:createPaymentsTable
CREATE TABLE `payments` (
                            `payment_amount` decimal(38,2) DEFAULT NULL,
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `rental_id` bigint DEFAULT NULL,
                            `session_id` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `status` enum('PAID','PENDING') COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `type` enum('FINE','PAYMENT') COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `url` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_6b9oj6np97hxx2k68lir605ml` (`rental_id`),
                            CONSTRAINT `FKsd5h2fdxq0uurg1m3jebdl6xd` FOREIGN KEY (`rental_id`) REFERENCES `rental` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;


