# 1. Base Image (JDK)
FROM eclipse-temurin:17-jdk

# 2. Set working directory inside container
WORKDIR /app

# 3. Copy jar file to container
COPY target/employee.jar app.jar

# 4. Application port
EXPOSE 8080

# 5. Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
