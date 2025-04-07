-- Active: 1743988588107@@127.0.0.1@3306@belajar_spring_restful_api
CREATE TABLE users (
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    token VARCHAR(100),
    token_expired_at BIGINT,
    PRIMARY KEY (username),
    UNIQUE (token)
);

select * from users;

DESC users;

CREATE TABLE contacts (
    id VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone VARCHAR(100),
    email VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY fk_users_contacts (username) REFERENCES users(username)
) engine=InnoDB;

SELECT * FROM contacts;

DESC contacts;


CREATE TABLE addresses (
    id VARCHAR(255) NOT NULL,
    contact_id VARCHAR(255) NOT NULL,
    street VARCHAR(100),
    city VARCHAR(100),
    province VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    postal_code VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY fk_contacts_addresses (contact_id) REFERENCES contacts(id)
)engine=InnoDB;

SELECT * from addresses;

DESC addresses;


DELETE FROM addresses;
DELETE FROM contacts;
DELETE FROM users;