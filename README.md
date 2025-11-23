# magnix-app

# Magnix Backend - Sistema de Gestión de Torneos y Espacios Deportivos

## Descripción del Proyecto

Magnix Backend es una API RESTful desarrollada con Spring Boot que proporciona los servicios necesarios para la gestión integral de torneos deportivos, reservas de espacios y administración de usuarios. El sistema implementa autenticación basada en JWT y manejo robusto de excepciones.

## Tecnologías Utilizadas

### Framework Principal
- **Spring Boot 3.5.7**: Framework principal para el desarrollo de la aplicación
- **Java 21**: Lenguaje de programación utilizado

### Dependencias Core
- **Spring Boot Starter Web**: Para la creación de endpoints REST
- **Spring Boot Starter Data JPA**: Capa de persistencia y ORM
- **Spring Boot Starter Security**: Implementación de seguridad y autenticación
- **Spring Boot Starter Actuator**: Monitoreo y health checks

### Base de Datos
- **MySQL 8.0**: Sistema de gestión de base de datos relacional
- **MySQL Connector/J**: Driver JDBC para conexión con MySQL

### Seguridad
- **Spring Security**: Framework de seguridad
- **JSON Web Tokens (JWT)**: Autenticación basada en tokens
  - jjwt-api 0.12.3
  - jjwt-impl 0.12.3
  - jjwt-jackson 0.12.3

### Testing
- **Spring Boot Starter Test**: Framework de pruebas
- **Spring Security Test**: Pruebas de seguridad
- **JUnit 5**: Framework de pruebas unitarias
- **Mockito**: Framework de mocking

### Herramientas de Build
- **Maven 3.9**: Gestión de dependencias y build
- **Spring Boot Maven Plugin**: Plugin para empaquetado

## Arquitectura del Sistema

El proyecto implementa una arquitectura en capas siguiendo los principios de Clean Architecture y Domain-Driven Design:

### Capas de la Aplicación

1. **Capa de Controladores** (Controller Layer)
   - Manejo de peticiones HTTP
   - Validación de entrada
   - Gestión de respuestas

2. **Capa de Servicios** (Service Layer)
   - Lógica de negocio
   - Orquestación de operaciones
   - Validaciones de dominio

3. **Capa de Repositorio** (Repository Layer)
   - Acceso a datos
   - Operaciones CRUD
   - Consultas personalizadas

4. **Capa de Seguridad** (Security Layer)
   - Autenticación JWT
   - Filtros de seguridad
   - Gestión de tokens

5. **Capa de Excepciones** (Exception Layer)
   - Manejo centralizado de errores
   - Excepciones personalizadas

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/example/magnix/
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthenticationController.java
│   │   │   ├── ReservationController.java
│   │   │   └── TournamentController.java
│   │   ├── dto/
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   ├── RegisterRequest.java
│   │   │   ├── ReservationRequest.java
│   │   │   ├── ReservationResponse.java
│   │   │   ├── TeamEnrollmentRequest.java
│   │   │   └── PlayerTournaments.java
│   │   ├── exception/
│   │   │   ├── UserNotFoundException.java
│   │   │   ├── PlayerNotFoundException.java
│   │   │   └── SlotNotAvailableException.java
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── Player.java
│   │   │   ├── Tournament.java
│   │   │   ├── Enrollment.java
│   │   │   ├── Reservation.java
│   │   │   └── Space.java
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   ├── PlayerRepository.java
│   │   │   ├── TournamentRepository.java
│   │   │   ├── EnrollmentRepository.java
│   │   │   ├── ReservationRepository.java
│   │   │   └── SpaceRepository.java
│   │   ├── security/
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   └── JwtUtil.java
│   │   ├── service/
│   │   │   ├── AuthenticationService.java
│   │   │   ├── ReservationService.java
│   │   │   └── TournamentService.java
│   │   └── MagnixApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/magnix/
        ├── service/
        │   ├── AuthenticationServiceTest.java
        │   ├── ReservationServiceTest.java
        │   └── TournamentServiceTest.java
        └── MagnixApplicationTests.java
```

## Modelos de Datos

### User
Entidad principal para la autenticación y gestión de usuarios.
- Campos: id, email, password, nombre, role
- Roles: PLAYER, ADMIN

### Player
Información extendida del jugador.
- Campos: id, userId, rating, matchesPlayed

### Tournament
Gestión de torneos deportivos.
- Campos: id, name, startDate, endDate, maxTeams, status

### Enrollment
Inscripción de equipos en torneos.
- Campos: id, tournamentId, teamName, playersCount

### Reservation
Reservas de espacios deportivos.
- Campos: id, spaceId, userId, date, timeSlot, status

### Space
Espacios deportivos disponibles.
- Campos: id, name, type, capacity, pricePerHour

## Configuración y Ejecución

### Prerrequisitos
- Java 21 o superior
- Maven 3.9 o superior
- MySQL 8.0 o superior
- Docker y Docker Compose (para despliegue containerizado)

### Variables de Entorno

```properties
# Base de datos
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydatabase
SPRING_DATASOURCE_USERNAME=myuser
SPRING_DATASOURCE_PASSWORD=secret

# JPA
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=false

# Servidor
SERVER_PORT=8080

# JWT
JWT_SECRET=tu-secret-key-aqui
JWT_EXPIRATION=86400000
```

### Ejecución Local

```bash
# Clonar el repositorio
git clone <repository-url>
cd magnix-app

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación está disponible en: `http://localhost:9090`

## Despliegue con Docker

### Arquitectura de Contenedores

El proyecto utiliza Docker Compose para orquestar dos servicios:

1. **magnix-mysql**: Contenedor de base de datos MySQL 8.0
2. **magnix-app**: Contenedor de la aplicación Spring Boot

### Dockerfile

El Dockerfile implementa un build multi-etapa para optimizar el tamaño de la imagen:

**Etapa 1 - Builder:**
- Base: `maven:3.9-eclipse-temurin-21`
- Función: Compilación de la aplicación
- Optimización: Cache de dependencias Maven

**Etapa 2 - Runtime:**
- Base: `eclipse-temurin:21-jre-alpine`
- Tamaño: Imagen ligera solo con JRE
- Health Check: Integrado con Spring Actuator

### Configuración de Docker Compose

```yaml
services:
  mysql:
    - Puerto expuesto: 3306
    - Volumen persistente: mysql-data
    - Health check configurado
    
  magnix-app:
    - Puerto expuesto: 9090 (mapea al 8080 interno)
    - Dependencia: mysql (con health check)
    - Restart policy: unless-stopped
    - Health check: /actuator/health
```

### Comandos de Despliegue

```bash
# Construir y levantar los servicios
docker-compose up --build

# Ejecutar en segundo plano
docker-compose up -d

# Ver logs
docker-compose logs -f magnix-app

# Detener los servicios
docker-compose down

# Detener y eliminar volúmenes
docker-compose down -v
```

### Endpoints de Monitoreo

- Health Check: `http://localhost:9090/actuator/health`
- Información: `http://localhost:9090/actuator/info`

## Pruebas

### Estructura de Pruebas

El proyecto implementa tres niveles de testing:

#### 1. Pruebas Unitarias

Ubicadas en: `src/test/java/com/example/magnix/service/`

**AuthenticationServiceTest.java**
- Pruebas de registro de usuarios
- Pruebas de login y generación de tokens
- Validación de credenciales
- Manejo de usuarios duplicados

**ReservationServiceTest.java**
- Creación de reservas
- Validación de disponibilidad de espacios
- Cancelación de reservas
- Conflictos de horarios

**TournamentServiceTest.java**
- Creación de torneos
- Inscripción de equipos
- Actualización de estados
- Validación de capacidad máxima

#### 2. Pruebas de Integración

**MagnixApplicationTests.java**
- Verificación de carga del contexto de Spring
- Integración entre capas
- Configuración de beans

Características:
- Uso de base de datos H2 en memoria para testing
- Transacciones con rollback automático
- MockMvc para simulación de peticiones HTTP

#### 3. Pruebas de Carga

Se recomienda el uso de herramientas externas para pruebas de carga:

**Apache JMeter**
```bash
# Ejecutar prueba de carga
jmeter -n -t load-test.jmx -l results.jtl
```

**k6 (Script de ejemplo)**
```javascript
import http from 'k6/http';
import { check } from 'k6';

export let options = {
  stages: [
    { duration: '2m', target: 100 },
    { duration: '5m', target: 100 },
    { duration: '2m', target: 0 },
  ],
};

export default function () {
  let res = http.get('http://localhost:9090/actuator/health');
  check(res, { 'status was 200': (r) => r.status == 200 });
}
```

### Ejecución de Pruebas

```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con coverage
mvn test jacoco:report

# Ejecutar solo pruebas unitarias
mvn test -Dtest=*Test

# Ejecutar pruebas de integración
mvn verify

# Ver reporte de cobertura
open target/site/jacoco/index.html
```

### Métricas de Pruebas

- Cobertura de código objetivo: 80%
- Tiempo máximo de ejecución de suite: 5 minutos
- Tasa de éxito esperada: 100%

## API Endpoints

### Autenticación

**POST /api/auth/register**
- Descripción: Registro de nuevo usuario
- Request Body: `{ "email", "password", "nombre" }`
- Response: `{ "token", "user": { "id", "email", "nombre", "rol" } }`

**POST /api/auth/login**
- Descripción: Inicio de sesión
- Request Body: `{ "email", "password" }`
- Response: `{ "token", "user": { "id", "email", "nombre", "rol" } }`

### Reservas

**POST /api/reservations**
- Descripción: Crear nueva reserva
- Headers: Authorization Bearer token
- Request Body: `{ "spaceId", "date", "timeSlot" }`
- Response: Objeto Reservation

**GET /api/reservations**
- Descripción: Obtener reservas del usuario
- Headers: Authorization Bearer token
- Response: Array de Reservations

**GET /api/reservations/{id}**
- Descripción: Obtener reserva específica
- Headers: Authorization Bearer token
- Response: Objeto Reservation

**DELETE /api/reservations/{id}**
- Descripción: Cancelar reserva
- Headers: Authorization Bearer token
- Response: 204 No Content

### Torneos

**POST /api/tournaments**
- Descripción: Crear nuevo torneo
- Headers: Authorization Bearer token
- Request Body: Objeto Tournament
- Response: Objeto Tournament creado

**GET /api/tournaments**
- Descripción: Listar todos los torneos
- Response: Array de Tournaments

**GET /api/tournaments/{id}**
- Descripción: Obtener torneo específico
- Response: Objeto Tournament

**POST /api/tournaments/{id}/enroll**
- Descripción: Inscribir equipo en torneo
- Headers: Authorization Bearer token
- Request Body: `{ "teamName", "playersCount" }`
- Response: Objeto Enrollment

## Seguridad

### Implementación de JWT

- Algoritmo: HS256
- Tiempo de expiración: 24 horas
- Claims incluidos: email, role, subject

### Configuración CORS

Orígenes permitidos:
- http://localhost:3000
- http://localhost:3001
- http://127.0.0.1:3000

Métodos permitidos: GET, POST, PUT, DELETE, OPTIONS, PATCH

### Protección de Rutas

- Rutas públicas: `/api/auth/**`
- Rutas protegidas: Todas las demás requieren token JWT válido
- Roles específicos: Algunas rutas requieren rol ADMIN

## Logging y Monitoreo

### Configuración de Logs

```properties
logging.level.root=INFO
logging.level.com.example.magnix=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

### Actuator Endpoints

- `/actuator/health`: Estado de salud de la aplicación
- `/actuator/info`: Información de la aplicación
- `/actuator/metrics`: Métricas de rendimiento

## Manejo de Errores

El sistema implementa un manejo centralizado de excepciones con respuestas consistentes:

- 400 Bad Request: Validación fallida
- 401 Unauthorized: Token inválido o ausente
- 403 Forbidden: Permisos insuficientes
- 404 Not Found: Recurso no encontrado
- 409 Conflict: Conflicto de datos
- 500 Internal Server Error: Error del servidor

Formato de respuesta de error:
```json
{
  "timestamp": "2025-11-23T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Descripción del error",
  "path": "/api/endpoint"
}
```

## Contribución

Para contribuir al proyecto:

1. Fork del repositorio
2. Crear rama feature: `git checkout -b feature/nueva-funcionalidad`
3. Commit de cambios: `git commit -am 'Agregar nueva funcionalidad'`
4. Push a la rama: `git push origin feature/nueva-funcionalidad`
5. Crear Pull Request

Este proyecto es de uso académico y educativo.


