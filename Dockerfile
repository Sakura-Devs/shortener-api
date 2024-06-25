# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17  AS build
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the code and build the application
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Deploy the application
FROM openjdk:17-jdk-slim AS deploy
WORKDIR /app

# Copy the generated JAR file from the previous stage
COPY --from=build /app/target/shortener-api-1.0.0.jar app.jar

# Expose the port that your application uses
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
