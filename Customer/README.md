
# Customer Order Management API

## Overview
The **Customer Order Management API** provides endpoints for managing customers and their orders. It enables the creation of orders, validation of customers and products, and stock management.

---

## Features
- **Customer Validation**: Ensures that the customer exists before creating an order.
- **Product Validation**: Checks the availability of products in stock.
- **Order Management**: Handles order creation, including auto-generation of IDs and timestamps.
- **Optimistic Locking**: Prevents concurrent update issues during order creation.

---

## API Endpoints

### Create Customer Order
**POST** `/customers/{customerId}/createCustomerOrder`

**Description**: Creates a new order for the specified customer and validates the products.

#### Parameters:
- `customerId` *(path parameter)*: The ID of the customer placing the order.

#### Request Body Example:
```json
{
  "customerId": 2,
  "products": [
    2,
    3
  ],
  "orderDate": "2025-01-26T16:51:44.395Z"
}
```

#### Responses:
- **201 Created**: Order created successfully.
- **400 Bad Request**: Invalid input or customer/product not found.
- **409 Conflict**: Optimistic locking failure due to concurrent updates.
- **500 Internal Server Error**: An unexpected error occurred.

---

## Prerequisites
- **Java 17+**
- **Spring Boot 3.0+**
- **Spring Boot Webflux**
- **Lombok**
- **MySQL** (or any supported relational database)
- **Swagger/OpenAPI 3.0** for API documentation.

---

## Setup and Configuration

### 1. Clone the Repository
```bash
git clone https://github.com/cnbaluramesh/EtiqaHackaton.git
cd EtiqaHackaton
```

### 2. Configure Application Properties
Update the database configuration in `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/etiqatest
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 3. Build and Run the Application
```bash
mvn clean install spring-boot:run
```

---

## Testing the API
- Access the Swagger UI for API documentation and testing at:
  ```
  http://localhost:7071/swagger-ui/index.html
  ```
- Use other tools like Postman or curl for manual testing.

---

## Technology Stack
- **Backend**: Spring Boot
- **Database**: MySQL
- **API Documentation**: Swagger (OpenAPI 3.0)
- **Reactive Client**: WebClient (for product validation)

---

## Project Structure
```

src/main/java/my/etiqa/customer/demo/
│
├── controller
│   └── CustomerController.java  # Handles API requests for customer orders
│
├── entity
│   ├── Customer.java            # Represents customer data
│   ├── Order.java               # Represents order data
│   └── Product.java             # Represents product data
│
├── repository
│   ├── CustomerRepository.java  # Repository for customer operations
│   └── OrderRepository.java     # Repository for order operations
│
├── service
│   └── CustomerService.java     # Contains business logic for customer and order operations
│
└── config
    └── WebClientConfig.java     # Configures WebClient for reactive requests
```

---

## Error Handling
- **400**: Validation failures (invalid customer or product IDs, insufficient stock).
- **409**: Concurrent updates detected.
- **500**: Internal server errors with detailed logs for debugging.

---

## Future Enhancements
- **Authentication & Authorization**: Secure API endpoints.
- **Pagination**: For large datasets (e.g., customer or order lists).
- **Comprehensive Unit Tests**: Improve test coverage.

---

## Contributors
- **Your Name** - C N Balu Ramesh
- **Team Name/Organization** - Maintainers

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.
