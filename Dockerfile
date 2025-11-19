# Etapa 1: Build con Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar solo pom.xml primero para aprovechar cache de Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar aplicación (crear JAR en lugar de WAR)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de producción ligera
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR compilado
COPY --from=builder /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8080

# Health check corregido
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]