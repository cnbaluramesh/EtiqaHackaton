# Microservices Project: Customer and Product

## Overview

This repository contains two microservices:

1. **Customer Microservice**: Manages customer data and interacts with the Product Microservice to create orders.
2. **Product Microservice**: Handles product inventory and exposes APIs for fetching and updating product details.

The Customer Microservice communicates with the Product Microservice to validate product availability and create orders.

## Architecture

### Microservices:
- **Customer Microservice**
  - Exposes RESTful APIs to manage customers and create orders.
  - Interacts with the Product Microservice using WebClient for product validation and stock updates.

- **Product Microservice**
  - Exposes RESTful APIs to manage product inventory.
  - Provides APIs for fetching product details and updating stock.

### Communication:
The Customer Microservice uses **WebClient** to make HTTP calls to the Product Microservice.

---

## Project Structure

### Customer Microservice
- **Controller**: Handles HTTP requests for customers and orders.
- **Service**: Contains business logic for validating customers, interacting with the Product Microservice, and managing orders.
- **Repository**: Interfaces with the database for customer and order data.

### Product Microservice
- **Controller**: Handles HTTP requests for managing products.
- **Service**: Contains business logic for product management.
- **Repository**: Interfaces with the database for product data.

---

## Prerequisites

- **Java 17**
- **Maven**
- **MySQL**
- **Spring Boot 3.4.1**
- **Hazelcast** (for caching)

---

## How to Run

### 1. Start MySQL Server
Ensure MySQL is running and the required databases and tables are set up.

### 2. Build the Project
Run the following command to build the project:
```bash
mvn clean install
```

### 3. Start the Services
#### Customer Microservice
```bash
cd Customer
mvn spring-boot:run
```

#### Product Microservice
```bash
cd Product
mvn spring-boot:run
```

---

## APIs

### Customer Microservice

#### 1. Create Customer Order
- **Endpoint**: `POST /customers/{customerId}/createCustomerOrder`
- **Description**: Creates a new order for a customer after validating product availability with the Product Microservice.
- **Request Body**:
  ```json
  {
      "products": [1, 2],
      "orderDate": "2025-01-26T16:51:44.395Z"
  }
  ```

### Product Microservice

#### 1. Get Product Details
- **Endpoint**: `GET /product/{id}`
- **Description**: Fetches product details by ID.

#### 2. Update Product Stock
- **Endpoint**: `PUT /product/{id}`
- **Description**: Updates the stock of a product.
- **Request Body**:
  ```json
  {
      "id": 1,
      "bookQuantity": 10
  }
  ```

---

## Testing

### Code Coverage
This project uses **JaCoCo** to generate code coverage reports.

#### Generate Code Coverage Report
Run the following command:
```bash
mvn test
```
The report will be generated under `target/site/jacoco/index.html`.

---

## Technologies Used
- **Spring Boot**: Framework for building microservices.
- **WebFlux**: Asynchronous and non-blocking framework for communication.
- **MySQL**: Relational database.
- **JaCoCo**: Code coverage reporting.
- **Hazelcast**: Caching.
- **MapStruct**: Object mapping.
- **SpringDoc OpenAPI**: API documentation.

---

## Contributing
1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

---

## License
This project is licensed under the MIT License.
