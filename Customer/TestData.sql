-- etiqatest.customer definition

CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `first_name` varchar(255) NOT NULL,
                            `last_name` varchar(255) NOT NULL,
                            `email_personal` varchar(255) DEFAULT NULL,
                            `email_office` varchar(255) DEFAULT NULL,
                            `phone_number` varchar(20) DEFAULT NULL,
                            `street` varchar(255) DEFAULT NULL,
                            `city` varchar(255) DEFAULT NULL,
                            `state` varchar(255) DEFAULT NULL,
                            `postal_code` varchar(20) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO etiqatest.customer
(id, first_name, last_name, email_personal, email_office, phone_number, street, city, state, postal_code)
VALUES(1, 'John', 'Doe', 'john.doe@example.com', 'john.doe@work.com', '+1234567890', '123 Main St', 'Springfield', 'IL', '62704');
INSERT INTO etiqatest.customer
(id, first_name, last_name, email_personal, email_office, phone_number, street, city, state, postal_code)
VALUES(2, 'Jane', 'Smith', 'jane.smith@example.com', 'jane.smith@work.com', '+9876543210', '456 Oak Ave', 'San Francisco', 'CA', '94103');
INSERT INTO etiqatest.customer
(id, first_name, last_name, email_personal, email_office, phone_number, street, city, state, postal_code)
VALUES(3, 'Alice', 'Johnson', 'alice.j@example.com', 'alice.j@workplace.com', '+5647382910', '789 Pine Rd', 'Austin', 'TX', '73301');

CREATE TABLE `product_entity` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `book_title` varchar(255) NOT NULL,
                                  `book_price` float NOT NULL,
                                  `category` varchar(255) DEFAULT NULL,
                                  `book_quantity` int NOT NULL,
                                  `description` text,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_products` (
                                  `order_id` bigint NOT NULL,
                                  `product_id` bigint NOT NULL,
                                  PRIMARY KEY (`order_id`,`product_id`),
                                  CONSTRAINT `order_products_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT;

-- etiqatest.`order` definition

CREATE TABLE `order` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `customer_id` int NOT NULL,
                         `order_date` datetime NOT NULL,
                         `version` bigint DEFAULT '0',
                         PRIMARY KEY (`id`),
                         KEY `customer_id` (`customer_id`),
                         CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO etiqatest.product_entity
(id, book_title, book_price, category, book_quantity, description)
VALUES(1, 'string', 0.1, 'string', 1073741824, NULL);
INSERT INTO etiqatest.product_entity
(id, book_title, book_price, category, book_quantity, description)
VALUES(2, '1984', 15.49, 'Dystopian', 50, 'A novel written by George Orwell.');
INSERT INTO etiqatest.product_entity
(id, book_title, book_price, category, book_quantity, description)
VALUES(3, 'To Kill a Mockingbird', 10.99, 'Classic', 200, 'A novel written by Harper Lee.');
INSERT INTO etiqatest.product_entity
(id, book_title, book_price, category, book_quantity, description)
VALUES(4, 'The Catcher in the Rye', 12.99, 'Classic', 150, 'A novel written by J.D. Salinger.');
INSERT INTO etiqatest.product_entity
(id, book_title, book_price, category, book_quantity, description)
VALUES(5, 'Moby Dick', 18.0, 'Adventure', 80, 'A novel written by Herman Melville.');
