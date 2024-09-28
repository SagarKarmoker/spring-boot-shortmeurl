# Step 1: Build stage
FROM maven:3.8.6-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY pom.xml .

# Download project dependencies
RUN mvn dependency:go-offline -B

# Copy the entire source code into the container
COPY src ./src

# Package the Spring Boot application into a JAR file
RUN mvn package -DskipTests

# Step 2: Run stage
FROM openjdk:17-jdk-slim

# Expose the port Spring Boot will run on
EXPOSE 8080

# Copy the built JAR file from the build stage
COPY --from=build /app/target/ShortTheURL-1.0.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker run --env-file .env -p 8080:8080 myapp:latest