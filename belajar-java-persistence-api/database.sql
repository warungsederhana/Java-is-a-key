CREATE DATABASE belajar_java_persistence_api;

-- #############################################

USE belajar_java_persistence_api;

-- #############################################

CREATE TABLE customers (
	id VARCHAR(255) NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

ALTER TABLE customers
ADD COLUMN primary_email VARCHAR(255);

ALTER TABLE customers 
ADD COLUMN age TINYINT;

ALTER TABLE customers 
ADD COLUMN married boolean;

ALTER TABLE customers 
ADD COLUMN type varchar(50);

SELECT * FROM customers;

DESCRIBE customers;

-- #############################################

CREATE TABLE categories (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500)
) ENGINE=InnoDB;

ALTER TABLE categories 
ADD COLUMN created_at timestamp;

ALTER TABLE categories
ADD COLUMN updated_at timestamp;

DESCRIBE categories;

SELECT * FROM categories;

DELETE FROM categories 

-- #############################################