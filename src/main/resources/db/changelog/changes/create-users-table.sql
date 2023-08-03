CREATE TABLE IF NOT EXISTS users (
                                     id bigint NOT NULL AUTO_INCREMENT,
                                     telegram_id bigint DEFAULT -939766354,
                                     email varchar(255) DEFAULT NULL UNIQUE,
    first_name varchar(255) DEFAULT NULL,
    last_name varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    role varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
    );