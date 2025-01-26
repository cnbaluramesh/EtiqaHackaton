
# Spring Boot Etiqa Demo: CRUD Rest API

This project is a Spring Boot application for demonstrating CRUD operations using WebFlux, R2DBC, and OpenAPI documentation.

## Features

- Reactive programming using **Spring WebFlux** and **R2DBC**.
- OpenAPI integration with SpringDoc for API documentation.
- Cache management using **Hazelcast**.
- Database interaction with **MySQL** and **Jasync R2DBC**.
- Code generation and mapping with **MapStruct**.
- Logging with **Logback**.
- Unit testing and integration testing using **JUnit** and **Reactor Test**.
- Code coverage reporting with **JaCoCo**.

## Dependencies

### Core Dependencies

- **Spring Boot Starter WebFlux**: For building reactive web applications.
- **Spring Boot Starter Data R2DBC**: For reactive database access.
- **MySQL Connector**: For MySQL database interaction.
- **Jasync R2DBC MySQL**: Reactive MySQL driver.
- **MapStruct**: For object mapping.
- **SpringDoc OpenAPI**: For API documentation.

### Additional Dependencies

- **Lombok**: For reducing boilerplate code.
- **Hazelcast**: For distributed caching.
- **Logback**: For logging.

### Testing Dependencies

- **Spring Boot Starter Test**: For unit testing.
- **Reactor Test**: For reactive testing.

## Build and Run

### Prerequisites

- JDK 17 or higher.
- Maven 3.6 or higher.
- MySQL database.

### Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. Configure your `application.yaml` with the necessary database and cache configurations.

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the API documentation at:
   ```
   http://localhost:7070/swagger-ui.html
   ```

## Testing

Run the tests using Maven:
```bash
mvn test
```

## Code Coverage

The project uses **JaCoCo** for code coverage. To generate the coverage report:
```bash
mvn test
```

The coverage report will be available in `target/site/jacoco/index.html`.

### Limiting Coverage Validation

The JaCoCo plugin is configured to validate only the `service` package for coverage:
```xml
<includes>
    <include>my/etiqa/demo/service/*</include>
</includes>
```
 