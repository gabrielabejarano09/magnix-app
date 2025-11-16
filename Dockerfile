# Etapa 1: Build con Maven
FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

# Etapa 2: Imagen de producción con Tomcat
FROM tomcat:10.1-jre21

WORKDIR /usr/local/tomcat

# Copiar WAR desde builder
COPY --from=builder /app/target/*.war webapps/magnix.war

EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/magnix/actuator/health || exit 1

CMD ["catalina.sh", "run"]
