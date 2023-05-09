CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created TIMESTAMP NOT NULL,
                       modified TIMESTAMP NOT NULL
);

CREATE TABLE phones (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        number VARCHAR(20) NOT NULL,
                        user_id BIGINT NOT NULL,
                        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);
