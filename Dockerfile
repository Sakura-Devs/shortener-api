# Stage 1: Build de la aplicación
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# Copia el archivo pom.xml para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el resto del código y construye la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Despliegue de la aplicación
FROM openjdk:17-jdk-alpine AS deploy
WORKDIR /app

# Copia el archivo JAR generado en el stage anterior
COPY --from=build /app/target/shortener-api-1.0.0.jar app.jar

# Expone el puerto que tu aplicación utiliza
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]