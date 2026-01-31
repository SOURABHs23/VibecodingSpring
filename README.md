# 🚀 Hello Spring Boot - Learning Project

A simple Spring Boot application to help you learn the fundamentals of Spring Boot development.

## 📋 What You'll Learn

1. **Project Structure** - How a Spring Boot project is organized
2. **Controllers** - How to handle HTTP requests
3. **Services** - Where business logic lives
4. **Models** - How to represent data
5. **REST APIs** - Building CRUD operations

## 🏗️ Project Structure

```
learningSpringBoot/
├── pom.xml                          # Maven configuration & dependencies
├── src/main/java/com/learning/
│   ├── HelloSpringBootApplication.java  # Main application entry point
│   ├── controller/
│   │   ├── HelloController.java     # Simple hello endpoints
│   │   └── TaskController.java      # Full CRUD REST API
│   ├── service/
│   │   └── TaskService.java         # Business logic
│   └── model/
│       └── Task.java                # Data model
└── src/main/resources/
    └── application.properties        # Configuration
```

## 🚦 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Start the Application

```bash
# Navigate to project directory
cd learningSpringBoot

# Run with Maven
./mvnw spring-boot:run

# Or if mvnw doesn't work:
mvn spring-boot:run
```

The application will start at: **http://localhost:8080**

## 🧪 Test the Endpoints

### Simple Hello Endpoints

| Endpoint | Description |
|----------|-------------|
| `GET /hello` | Returns a simple greeting |
| `GET /hello/{name}` | Greets you by name |
| `GET /greet?name=Alice&greeting=Hi` | Custom greeting with parameters |

### Task API (CRUD Operations)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/tasks` | Get all tasks |
| `GET` | `/api/tasks/{id}` | Get a specific task |
| `POST` | `/api/tasks` | Create a new task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |
| `PATCH` | `/api/tasks/{id}/toggle` | Toggle task completion |

### Example curl Commands

```bash
# Get all tasks
curl http://localhost:8080/api/tasks

# Get a specific task
curl http://localhost:8080/api/tasks/1

# Create a new task
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Learn Spring","description":"Master Spring Boot","completed":false}'

# Update a task
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated Task","description":"Updated description","completed":true}'

# Delete a task
curl -X DELETE http://localhost:8080/api/tasks/1

# Toggle completion
curl -X PATCH http://localhost:8080/api/tasks/1/toggle
```

## 📚 Key Concepts Explained

### @SpringBootApplication
- The main annotation that enables Spring Boot's auto-configuration
- Combines: `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`

### @RestController
- Marks a class as a REST API controller
- Returns data (JSON) directly, not HTML views

### @Service
- Marks a class as containing business logic
- Spring automatically manages its lifecycle (creates single instance)

### @GetMapping, @PostMapping, etc.
- Map HTTP requests to specific methods
- Part of Spring's web MVC framework

### Dependency Injection
- Spring automatically provides required dependencies
- Use constructor injection (recommended) or `@Autowired`

## 🔄 Next Steps to Learn

1. **Add a Database** - Use Spring Data JPA with H2 or PostgreSQL
2. **Add Validation** - Use `@Valid` and validation annotations
3. **Add Exception Handling** - Create `@ControllerAdvice` for global error handling
4. **Add Security** - Use Spring Security for authentication
5. **Add Tests** - Write unit and integration tests

## 📖 Helpful Resources

- [Spring Boot Official Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Guides](https://spring.io/guides)
- [Baeldung Spring Tutorials](https://www.baeldung.com/spring-boot)

---

Happy Learning! 🎉
