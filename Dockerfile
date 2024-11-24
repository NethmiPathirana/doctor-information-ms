# Stage 1: Build the JAR file
FROM maven:latest AS builder

# Install OpenJDK 21 (already included in Maven image; remove redundant step)
# RUN apt-get update && apt-get install -y openjdk-21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and install dependencies (caching dependencies)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with the JAR file
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage to the container
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
