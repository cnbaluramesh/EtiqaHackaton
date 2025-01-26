CREATE TABLE Product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         bookTitle VARCHAR(255) NOT NULL,
                         bookPrice FLOAT NOT NULL,
                         bookQuantity INT NOT NULL,
                         category VARCHAR(100) NOT NULL,
                         description TEXT
);