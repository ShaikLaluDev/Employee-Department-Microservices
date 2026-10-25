
# Employee–Department Microservices

A Spring Boot microservices project implementing Employee
and Department management using REST APIs, Eureka Server,
OpenFeign, MySQL, MapStruct, and Swagger/OpenAPI.

## Technologies Used

- Java 17
- Spring Boot 4.1.2
- Spring Cloud
- Spring Data JPA
- MySQL
- Netflix Eureka Server
- Spring Cloud OpenFeign
- Spring Cloud LoadBalancer
- MapStruct
- Lombok
- Jakarta Bean Validation
- Swagger / OpenAPI
- Maven

## Microservices Architecture

Eureka Server (8761)
       |
       |--- Employee Service (8081)
       |        |
       |        |--- Employee MySQL Database
       |        |
       |        |--- OpenFeign
       |                 |
       |                 v
       |          Department Service (8082)
       |                 |
       |                 v
       |          Department MySQL Database
       |
       |--- Department Service Registration

## Modules

### Employee Service — Port 8081

Manages employee records and validates department IDs
through OpenFeign.

Endpoints:

- POST /employees
- PUT /employees/{id}
- DELETE /employees/{empId}
- GET /employees/all

### Department Service — Port 8082

Manages department records and provides an internal
lookup endpoint for Employee Service.

Endpoints:

- POST /api/departments
- POST /api/departments?id={id}
- DELETE /api/departments/{deptId}
- GET /api/departments
- GET /api/departments/internal/{id}

### Eureka Server — Port 8761

Provides service registration and discovery for
Employee Service and Department Service.

## Database Configuration

Create two MySQL databases:

```sql
CREATE DATABASE employee_service_db;
CREATE DATABASE department_service_db;
```

Configure the DB_PASSWORD environment variable
with your local MySQL password.

## Running the Application

Start the applications in the following order:

1. MySQL Server
2. Eureka Server
3. Department Service
4. Employee Service

## Swagger Documentation

Employee Service:
http://localhost:8081/swagger-ui/index.html

Department Service:
http://localhost:8082/swagger-ui/index.html

Eureka Dashboard:
http://localhost:8761

## Microservice Communication

Employee Service uses OpenFeign to call Department Service
and verify that a department exists before creating
or updating an employee.

If the department does not exist, the API returns
HTTP 404 Not Found.

## Features

- Employee CRUD operations
- Department CRUD operations
- Service discovery using Eureka
- Inter-service communication using OpenFeign
- Automatic employee and department code generation
- Request validation
- Duplicate record checking
- Global exception handling
- Swagger API documentation
- Independent MySQL databases
