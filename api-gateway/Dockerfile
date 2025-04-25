# Start from an OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine



# Set working directory inside the container
WORKDIR /app

# Copy the JAR into the container
COPY target/app.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
