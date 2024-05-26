-- liquibase formatted sql

-- changeset razuvaev:1
CREATE TABLE IF NOT EXISTS company (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(64) NOT NULL UNIQUE
);

-- changeset razuvaev:2
CREATE TABLE IF NOT EXISTS company_locales (
                                               company_id INT,
                                               lang VARCHAR(2),
                                               description VARCHAR(255) NOT NULL,
                                               PRIMARY KEY (company_id, lang),
                                               FOREIGN KEY (company_id) REFERENCES company(id)
);

-- changeset razuvaev:3
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(64) NOT NULL UNIQUE,
                                     birth_date DATE,
                                     firstname VARCHAR(64),
                                     lastname VARCHAR(64),
                                     role ENUM('admin','qa','user','developer','manager','analyst','designer','engineer','consultant') NOT NULL,
                                     company_id INT,
                                     image VARCHAR(128),
                                     password VARCHAR(128) DEFAULT '{noop}123',
                                     FOREIGN KEY (company_id) REFERENCES company(id)
);

-- changeset razuvaev:4
CREATE TABLE IF NOT EXISTS payment (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       amount INT NOT NULL,
                                       receiver_id INT NOT NULL,
                                       FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- changeset razuvaev:5
CREATE TABLE IF NOT EXISTS chat (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255)
);

-- changeset razuvaev:6
CREATE TABLE IF NOT EXISTS users_chat (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          user_id INT NOT NULL,
                                          chat_id BIGINT NOT NULL,
                                          UNIQUE (user_id, chat_id),
                                          FOREIGN KEY (user_id) REFERENCES users(id),
                                          FOREIGN KEY (chat_id) REFERENCES chat(id)
);
