## 📦 Project Title
# Java Spring Boot Hexagonal Architecture Template

## 🧭 Overview
This project is a **Spring Boot template implementing Hexagonal Architecture (Ports and Adapters)**.

It demonstrates how to build a clean, maintainable, and testable application by separating:
- Domain logic
- Application use cases
- Infrastructure concerns (web, persistence)

The project includes a sample feature: **Create Product**, showcasing the full flow from API request to persistence.

## 🏗️ Architecture

This project follows **Hexagonal Architecture (Ports and Adapters)**.

### Layers

- **Domain**
    - Contains business models and rules
    - No dependency on frameworks

- **Application**
    - Contains use cases and ports
    - Coordinates domain logic

- **Adapters**
    - Web (REST API)
    - Persistence (JPA)

- **Configuration**
    - Wires dependencies using Spring

### Dependency Direction
Adapters → Application → Domain
The domain layer is completely independent of frameworks.

## 📁 Project Structure
core/
├── domain/
│ └── model/
├── application/
│ ├── command/
│ ├── port/
│ ├── usecase/
│ └── mapper/

adapters/
├── web-api-springboot/
│ ├── controller/
│ ├── request/
│ ├── response/
│ ├── mapper/
│ └── exception/
│
└── persistence/
├── adapter/
├── entity/
├── mapper/
└── repository/

src/main/java/com/projects/
└── ModuleBeanConfiguration.java

## 🚀 API Example

### Create Product

**POST** `/api/products`

#### Request
```json
{
  "name": "Dimsum",
  "price": 10.20,
  "stocks": 100
}
```
#### Response (201 Created)
```json
{
  "id": "uuid",
  "name": "Dimsum",
  "price": 10.20,
  "stocks": 100,
  "isAvailable": true
}
```
#### Response Headers
Location: /api/products/{id}

## ❗ Error Handling

The application uses a global exception handler to provide consistent error responses.

### Example (Validation Error)

```json
{
  "timestamp": "2026-01-01T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "details": [
    "name: must not be blank"
  ]
}
```

## 🧪 Testing

The project includes:

- Unit tests for use cases
- Adapter tests for persistence
- Web layer tests using `@WebMvcTest`
- Parameterized tests for validation scenarios

Technologies used:
- JUnit 5
- Mockito
- Spring MockMvc

## ⚙️ Tech Stack

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Jakarta Validation
- JUnit 5 / Mockito

## ▶️ Running the Application

```bash
./gradlew bootRun
```
or
```bash
./mvnw spring-boot:run
```


---

## 💡 Design Decisions

- Domain model enforces invariants (price, stocks)
- `isAvailable` is derived from stock, not persisted
- Use cases depend on ports (interfaces), not implementations
- Adapters handle mapping between layers
- Global exception handling ensures consistent API responses
