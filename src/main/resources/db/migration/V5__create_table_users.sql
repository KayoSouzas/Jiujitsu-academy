 CREATE TABLE users(
 id BIGINT PRIMARY KEY,
 name VARCHAR(255),
 email VARCHAR(255) unique,
 password VARCHAR(255)
);